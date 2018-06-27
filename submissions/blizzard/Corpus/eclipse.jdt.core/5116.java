/*******************************************************************************
 * Copyright (c) 2000, 2016 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.internal.core.builder;

import org.eclipse.core.runtime.*;
import org.eclipse.core.resources.*;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.core.compiler.*;
import org.eclipse.jdt.internal.compiler.*;
import org.eclipse.jdt.internal.compiler.Compiler;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.jdt.internal.compiler.lookup.TypeConstants;
import org.eclipse.jdt.internal.compiler.problem.*;
import org.eclipse.jdt.internal.compiler.util.SimpleSet;
import org.eclipse.jdt.internal.compiler.util.SuffixConstants;
import org.eclipse.jdt.internal.core.JavaModelManager;
import org.eclipse.jdt.internal.core.PackageFragment;
import org.eclipse.jdt.internal.core.util.Messages;
import org.eclipse.jdt.internal.core.util.Util;
import java.io.*;
import java.util.*;

/**
 * The abstract superclass of Java builders.
 * Provides the building and compilation mechanism
 * in common with the batch and incremental builders.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class AbstractImageBuilder implements ICompilerRequestor, ICompilationUnitLocator {

    protected JavaBuilder javaBuilder;

    protected State newState;

    // local copies
    protected NameEnvironment nameEnvironment;

    protected ClasspathMultiDirectory[] sourceLocations;

    protected BuildNotifier notifier;

    protected Compiler compiler;

    protected WorkQueue workQueue;

    protected ArrayList problemSourceFiles;

    protected boolean compiledAllAtOnce;

    private boolean inCompiler;

    protected boolean keepStoringProblemMarkers;

    protected SimpleSet filesWithAnnotations = null;

    //2000 is best compromise between space used and speed
    public static int MAX_AT_ONCE = Integer.getInteger(JavaModelManager.MAX_COMPILED_UNITS_AT_ONCE, 2000).intValue();

    public static final String[] JAVA_PROBLEM_MARKER_ATTRIBUTE_NAMES = { IMarker.MESSAGE, IMarker.SEVERITY, IJavaModelMarker.ID, IMarker.CHAR_START, IMarker.CHAR_END, IMarker.LINE_NUMBER, IJavaModelMarker.ARGUMENTS, IJavaModelMarker.CATEGORY_ID };

    public static final String[] JAVA_TASK_MARKER_ATTRIBUTE_NAMES = { IMarker.MESSAGE, IMarker.PRIORITY, IJavaModelMarker.ID, IMarker.CHAR_START, IMarker.CHAR_END, IMarker.LINE_NUMBER, IMarker.USER_EDITABLE, IMarker.SOURCE_ID };

    public static final Integer S_ERROR = Integer.valueOf(IMarker.SEVERITY_ERROR);

    public static final Integer S_WARNING = Integer.valueOf(IMarker.SEVERITY_WARNING);

    public static final Integer S_INFO = Integer.valueOf(IMarker.SEVERITY_INFO);

    public static final Integer P_HIGH = Integer.valueOf(IMarker.PRIORITY_HIGH);

    public static final Integer P_NORMAL = Integer.valueOf(IMarker.PRIORITY_NORMAL);

    public static final Integer P_LOW = Integer.valueOf(IMarker.PRIORITY_LOW);

    protected  AbstractImageBuilder(JavaBuilder javaBuilder, boolean buildStarting, State newState) {
        // local copies
        this.javaBuilder = javaBuilder;
        this.nameEnvironment = javaBuilder.nameEnvironment;
        this.sourceLocations = this.nameEnvironment.sourceLocations;
        this.notifier = javaBuilder.notifier;
        // may get disabled when missing classfiles are encountered
        this.keepStoringProblemMarkers = true;
        if (buildStarting) {
            this.newState = newState == null ? new State(javaBuilder) : newState;
            this.compiler = newCompiler();
            this.workQueue = new WorkQueue();
            this.problemSourceFiles = new ArrayList(3);
            if (this.javaBuilder.participants != null) {
                for (int i = 0, l = this.javaBuilder.participants.length; i < l; i++) {
                    if (this.javaBuilder.participants[i].isAnnotationProcessor()) {
                        // initialize this set so the builder knows to gather CUs that define Annotation types
                        // each Annotation processor participant is then asked to process these files AFTER
                        // the compile loop. The normal dependency loop will then recompile all affected types
                        this.filesWithAnnotations = new SimpleSet(1);
                        break;
                    }
                }
            }
        }
    }

    public void acceptResult(CompilationResult result) {
        // In Batch mode, we write out the class files, hold onto the dependency info
        // & additional types and report problems.
        // In Incremental mode, when writing out a class file we need to compare it
        // against the previous file, remembering if structural changes occured.
        // Before reporting the new problems, we need to update the problem count &
        // remove the old problems. Plus delete additional class files that no longer exist.
        // go directly back to the sourceFile
        SourceFile compilationUnit = (SourceFile) result.getCompilationUnit();
        if (!this.workQueue.isCompiled(compilationUnit)) {
            this.workQueue.finished(compilationUnit);
            try {
                // record compilation problems before potentially adding duplicate errors
                updateProblemsFor(compilationUnit, result);
                // record tasks
                updateTasksFor(compilationUnit, result);
            } catch (CoreException e) {
                throw internalException(e);
            }
            if (result.hasInconsistentToplevelHierarchies)
                // ensure that this file is always retrieved from source for the rest of the build
                if (!this.problemSourceFiles.contains(compilationUnit))
                    this.problemSourceFiles.add(compilationUnit);
            IType mainType = null;
            String mainTypeName = null;
            String typeLocator = compilationUnit.typeLocator();
            ClassFile[] classFiles = result.getClassFiles();
            int length = classFiles.length;
            ArrayList duplicateTypeNames = null;
            ArrayList definedTypeNames = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                ClassFile classFile = classFiles[i];
                char[][] compoundName = classFile.getCompoundName();
                char[] typeName = compoundName[compoundName.length - 1];
                boolean isNestedType = classFile.isNestedType;
                // Look for a possible collision, if one exists, report an error but do not write the class file
                if (isNestedType) {
                    String qualifiedTypeName = new String(classFile.outerMostEnclosingClassFile().fileName());
                    if (this.newState.isDuplicateLocator(qualifiedTypeName, typeLocator))
                        continue;
                } else {
                    // the qualified type name "p1/p2/A"
                    String qualifiedTypeName = new String(classFile.fileName());
                    if (this.newState.isDuplicateLocator(qualifiedTypeName, typeLocator)) {
                        if (duplicateTypeNames == null)
                            duplicateTypeNames = new ArrayList();
                        duplicateTypeNames.add(compoundName);
                        if (mainType == null) {
                            try {
                                // slash separated qualified name "p1/p1/A"
                                mainTypeName = compilationUnit.initialTypeName;
                                mainType = this.javaBuilder.javaProject.findType(mainTypeName.replace('/', '.'));
                            } catch (JavaModelException e) {
                            }
                        }
                        IType type;
                        if (qualifiedTypeName.equals(mainTypeName)) {
                            type = mainType;
                        } else {
                            String simpleName = qualifiedTypeName.substring(qualifiedTypeName.lastIndexOf('/') + 1);
                            type = mainType == null ? null : mainType.getCompilationUnit().getType(simpleName);
                        }
                        createProblemFor(compilationUnit.resource, type, Messages.bind(Messages.build_duplicateClassFile, new String(typeName)), JavaCore.ERROR);
                        continue;
                    }
                    this.newState.recordLocatorForType(qualifiedTypeName, typeLocator);
                    if (result.checkSecondaryTypes && !qualifiedTypeName.equals(compilationUnit.initialTypeName))
                        acceptSecondaryType(classFile);
                }
                try {
                    definedTypeNames.add(writeClassFile(classFile, compilationUnit, !isNestedType));
                } catch (CoreException e) {
                    Util.log(e, "JavaBuilder handling CoreException");
                    if (e.getStatus().getCode() == IResourceStatus.CASE_VARIANT_EXISTS)
                        createProblemFor(compilationUnit.resource, null, Messages.bind(Messages.build_classFileCollision, e.getMessage()), JavaCore.ERROR);
                    else
                        createProblemFor(compilationUnit.resource, null, Messages.build_inconsistentClassFile, JavaCore.ERROR);
                }
            }
            if (// only initialized if an annotation processor is attached
            result.hasAnnotations && this.filesWithAnnotations != null)
                this.filesWithAnnotations.add(compilationUnit);
            this.compiler.lookupEnvironment.releaseClassFiles(classFiles);
            finishedWith(typeLocator, result, compilationUnit.getMainTypeName(), definedTypeNames, duplicateTypeNames);
            this.notifier.compiled(compilationUnit);
        }
    }

    protected void acceptSecondaryType(ClassFile classFile) {
    // noop
    }

    protected void addAllSourceFiles(final ArrayList sourceFiles) throws CoreException {
        for (int i = 0, l = this.sourceLocations.length; i < l; i++) {
            final ClasspathMultiDirectory sourceLocation = this.sourceLocations[i];
            final char[][] exclusionPatterns = sourceLocation.exclusionPatterns;
            final char[][] inclusionPatterns = sourceLocation.inclusionPatterns;
            final boolean isAlsoProject = sourceLocation.sourceFolder.equals(this.javaBuilder.currentProject);
            final int segmentCount = sourceLocation.sourceFolder.getFullPath().segmentCount();
            final IContainer outputFolder = sourceLocation.binaryFolder;
            final boolean isOutputFolder = sourceLocation.sourceFolder.equals(outputFolder);
            sourceLocation.sourceFolder.accept(new IResourceProxyVisitor() {

                public boolean visit(IResourceProxy proxy) throws CoreException {
                    switch(proxy.getType()) {
                        case IResource.FILE:
                            if (org.eclipse.jdt.internal.core.util.Util.isJavaLikeFileName(proxy.getName())) {
                                IResource resource = proxy.requestResource();
                                if (exclusionPatterns != null || inclusionPatterns != null)
                                    if (Util.isExcluded(resource.getFullPath(), inclusionPatterns, exclusionPatterns, false))
                                        return false;
                                sourceFiles.add(new SourceFile((IFile) resource, sourceLocation));
                            }
                            return false;
                        case IResource.FOLDER:
                            IPath folderPath = null;
                            if (isAlsoProject)
                                if (isExcludedFromProject(folderPath = proxy.requestFullPath()))
                                    return false;
                            if (exclusionPatterns != null) {
                                if (folderPath == null)
                                    folderPath = proxy.requestFullPath();
                                if (Util.isExcluded(folderPath, inclusionPatterns, exclusionPatterns, true)) {
                                    // but folder is excluded so do not create it in the output folder
                                    return inclusionPatterns != null;
                                }
                            }
                            if (!isOutputFolder) {
                                if (folderPath == null)
                                    folderPath = proxy.requestFullPath();
                                String packageName = folderPath.lastSegment();
                                if (packageName.length() > 0) {
                                    String sourceLevel = AbstractImageBuilder.this.javaBuilder.javaProject.getOption(JavaCore.COMPILER_SOURCE, true);
                                    String complianceLevel = AbstractImageBuilder.this.javaBuilder.javaProject.getOption(JavaCore.COMPILER_COMPLIANCE, true);
                                    if (JavaConventions.validatePackageName(packageName, sourceLevel, complianceLevel).getSeverity() != IStatus.ERROR)
                                        createFolder(folderPath.removeFirstSegments(segmentCount), outputFolder);
                                }
                            }
                    }
                    return true;
                }
            }, IResource.NONE);
            this.notifier.checkCancel();
        }
    }

    protected void cleanUp() {
        this.nameEnvironment.cleanup();
        this.javaBuilder = null;
        this.nameEnvironment = null;
        this.sourceLocations = null;
        this.notifier = null;
        this.compiler = null;
        this.workQueue = null;
        this.problemSourceFiles = null;
    }

    /* Compile the given elements, adding more elements to the work queue
* if they are affected by the changes.
*/
    protected void compile(SourceFile[] units) {
        if (this.filesWithAnnotations != null && this.filesWithAnnotations.elementSize > 0)
            // will add files that have annotations in acceptResult() & then processAnnotations() before exitting this method
            this.filesWithAnnotations.clear();
        // notify CompilationParticipants which source files are about to be compiled
        CompilationParticipantResult[] participantResults = this.javaBuilder.participants == null ? null : notifyParticipants(units);
        if (participantResults != null && participantResults.length > units.length) {
            units = new SourceFile[participantResults.length];
            for (int i = participantResults.length; --i >= 0; ) units[i] = participantResults[i].sourceFile;
        }
        int unitsLength = units.length;
        this.compiledAllAtOnce = MAX_AT_ONCE == 0 || unitsLength <= MAX_AT_ONCE;
        if (this.compiledAllAtOnce) {
            // do them all now
            if (JavaBuilder.DEBUG)
                for (int i = 0; i < unitsLength; i++) //$NON-NLS-1$
                System.out.println(//$NON-NLS-1$
                "About to compile " + units[i].typeLocator());
            compile(units, null, true);
        } else {
            // copy of units, removing units when about to compile
            SourceFile[] remainingUnits = new SourceFile[unitsLength];
            System.arraycopy(units, 0, remainingUnits, 0, unitsLength);
            int doNow = unitsLength < MAX_AT_ONCE ? unitsLength : MAX_AT_ONCE;
            SourceFile[] toCompile = new SourceFile[doNow];
            int remainingIndex = 0;
            boolean compilingFirstGroup = true;
            while (remainingIndex < unitsLength) {
                int count = 0;
                while (remainingIndex < unitsLength && count < doNow) {
                    // Although it needed compiling when this method was called, it may have
                    // already been compiled when it was referenced by another unit.
                    SourceFile unit = remainingUnits[remainingIndex];
                    if (unit != null && (compilingFirstGroup || this.workQueue.isWaiting(unit))) {
                        if (JavaBuilder.DEBUG)
                            //$NON-NLS-1$ //$NON-NLS-2$
                            System.out.println("About to compile #" + remainingIndex + " : " + unit.typeLocator());
                        toCompile[count++] = unit;
                    }
                    remainingUnits[remainingIndex++] = null;
                }
                if (count < doNow)
                    System.arraycopy(toCompile, 0, toCompile = new SourceFile[count], 0, count);
                if (!compilingFirstGroup)
                    for (int a = remainingIndex; a < unitsLength; a++) if (remainingUnits[a] != null && this.workQueue.isCompiled(remainingUnits[a]))
                        // use the class file for this source file since its been compiled
                        remainingUnits[a] = null;
                compile(toCompile, remainingUnits, compilingFirstGroup);
                compilingFirstGroup = false;
            }
        }
        if (participantResults != null) {
            for (int i = participantResults.length; --i >= 0; ) if (participantResults[i] != null)
                recordParticipantResult(participantResults[i]);
            processAnnotations(participantResults);
        }
    }

    protected void compile(SourceFile[] units, SourceFile[] additionalUnits, boolean compilingFirstGroup) {
        if (units.length == 0)
            return;
        // just to change the message
        this.notifier.aboutToCompile(units[0]);
        // extend additionalFilenames with all hierarchical problem types found during this entire build
        if (!this.problemSourceFiles.isEmpty()) {
            int toAdd = this.problemSourceFiles.size();
            int length = additionalUnits == null ? 0 : additionalUnits.length;
            if (length == 0)
                additionalUnits = new SourceFile[toAdd];
            else
                System.arraycopy(additionalUnits, 0, additionalUnits = new SourceFile[length + toAdd], 0, length);
            for (int i = 0; i < toAdd; i++) additionalUnits[length + i] = (SourceFile) this.problemSourceFiles.get(i);
        }
        String[] initialTypeNames = new String[units.length];
        for (int i = 0, l = units.length; i < l; i++) initialTypeNames[i] = units[i].initialTypeName;
        this.nameEnvironment.setNames(initialTypeNames, additionalUnits);
        this.notifier.checkCancel();
        try {
            this.inCompiler = true;
            this.compiler.compile(units);
        } catch (AbortCompilation ignored) {
        } finally {
            this.inCompiler = false;
        }
        // Check for cancel immediately after a compile, because the compiler may
        // have been cancelled but without propagating the correct exception
        this.notifier.checkCancel();
    }

    protected void copyResource(IResource source, IResource destination) throws CoreException {
        IPath destPath = destination.getFullPath();
        try {
            source.copy(destPath, IResource.FORCE | IResource.DERIVED, null);
        } catch (CoreException e) {
            source.refreshLocal(0, null);
            if (!source.exists())
                return;
            throw e;
        }
        // just in case the original was read only
        Util.setReadOnly(destination, false);
    }

    protected void createProblemFor(IResource resource, IMember javaElement, String message, String problemSeverity) {
        try {
            IMarker marker = resource.createMarker(IJavaModelMarker.JAVA_MODEL_PROBLEM_MARKER);
            int severity = problemSeverity.equals(JavaCore.WARNING) ? IMarker.SEVERITY_WARNING : IMarker.SEVERITY_ERROR;
            ISourceRange range = null;
            if (javaElement != null) {
                try {
                    range = javaElement.getNameRange();
                } catch (JavaModelException e) {
                    if (e.getJavaModelStatus().getCode() != IJavaModelStatusConstants.ELEMENT_DOES_NOT_EXIST) {
                        throw e;
                    }
                    if (!CharOperation.equals(javaElement.getElementName().toCharArray(), TypeConstants.PACKAGE_INFO_NAME)) {
                        throw e;
                    }
                }
            }
            int start = range == null ? 0 : range.getOffset();
            int end = range == null ? 1 : start + range.getLength();
            marker.setAttributes(new String[] { IMarker.MESSAGE, IMarker.SEVERITY, IMarker.CHAR_START, IMarker.CHAR_END, IMarker.SOURCE_ID }, new Object[] { message, Integer.valueOf(severity), Integer.valueOf(start), Integer.valueOf(end), JavaBuilder.SOURCE_ID });
        } catch (CoreException e) {
            throw internalException(e);
        }
    }

    protected void deleteGeneratedFiles(IFile[] deletedGeneratedFiles) {
    // no op by default
    }

    protected SourceFile findSourceFile(IFile file, boolean mustExist) {
        if (mustExist && !file.exists())
            return null;
        // assumes the file exists in at least one of the source folders & is not excluded
        ClasspathMultiDirectory md = this.sourceLocations[0];
        if (this.sourceLocations.length > 1) {
            IPath sourceFileFullPath = file.getFullPath();
            for (int j = 0, m = this.sourceLocations.length; j < m; j++) {
                if (this.sourceLocations[j].sourceFolder.getFullPath().isPrefixOf(sourceFileFullPath)) {
                    md = this.sourceLocations[j];
                    if (md.exclusionPatterns == null && md.inclusionPatterns == null)
                        break;
                    if (!Util.isExcluded(file, md.inclusionPatterns, md.exclusionPatterns))
                        break;
                }
            }
        }
        return new SourceFile(file, md);
    }

    protected void finishedWith(String sourceLocator, CompilationResult result, char[] mainTypeName, ArrayList definedTypeNames, ArrayList duplicateTypeNames) {
        if (duplicateTypeNames == null) {
            this.newState.record(sourceLocator, result.qualifiedReferences, result.simpleNameReferences, result.rootReferences, mainTypeName, definedTypeNames);
            return;
        }
        char[][] simpleRefs = result.simpleNameReferences;
        // for each duplicate type p1.p2.A, add the type name A (package was already added)
        next: for (int i = 0, l = duplicateTypeNames.size(); i < l; i++) {
            char[][] compoundName = (char[][]) duplicateTypeNames.get(i);
            char[] typeName = compoundName[compoundName.length - 1];
            int sLength = simpleRefs.length;
            for (int j = 0; j < sLength; j++) if (CharOperation.equals(simpleRefs[j], typeName))
                continue next;
            System.arraycopy(simpleRefs, 0, simpleRefs = new char[sLength + 1][], 0, sLength);
            simpleRefs[sLength] = typeName;
        }
        this.newState.record(sourceLocator, result.qualifiedReferences, simpleRefs, result.rootReferences, mainTypeName, definedTypeNames);
    }

    protected IContainer createFolder(IPath packagePath, IContainer outputFolder) throws CoreException {
        if (packagePath.isEmpty())
            return outputFolder;
        IFolder folder = outputFolder.getFolder(packagePath);
        if (!folder.exists()) {
            createFolder(packagePath.removeLastSegments(1), outputFolder);
            folder.create(IResource.FORCE | IResource.DERIVED, true, null);
        }
        return folder;
    }

    /* (non-Javadoc)
 * @see org.eclipse.jdt.internal.core.builder.ICompilationUnitLocator#fromIFile(org.eclipse.core.resources.IFile)
 */
    public ICompilationUnit fromIFile(IFile file) {
        return findSourceFile(file, true);
    }

    protected void initializeAnnotationProcessorManager(Compiler newCompiler) {
        AbstractAnnotationProcessorManager annotationManager = JavaModelManager.getJavaModelManager().createAnnotationProcessorManager();
        if (annotationManager != null) {
            annotationManager.configureFromPlatform(newCompiler, this, this.javaBuilder.javaProject);
            annotationManager.setErr(new PrintWriter(System.err));
            annotationManager.setOut(new PrintWriter(System.out));
        }
        newCompiler.annotationProcessorManager = annotationManager;
    }

    protected RuntimeException internalException(CoreException t) {
        ImageBuilderInternalException imageBuilderException = new ImageBuilderInternalException(t);
        if (this.inCompiler)
            return new AbortCompilation(true, imageBuilderException);
        return imageBuilderException;
    }

    protected boolean isExcludedFromProject(IPath childPath) throws JavaModelException {
        // is a subfolder of a package
        if (childPath.segmentCount() > 2)
            return false;
        for (int j = 0, k = this.sourceLocations.length; j < k; j++) {
            if (childPath.equals(this.sourceLocations[j].binaryFolder.getFullPath()))
                return true;
            if (childPath.equals(this.sourceLocations[j].sourceFolder.getFullPath()))
                return true;
        }
        // skip default output folder which may not be used by any source folder
        return childPath.equals(this.javaBuilder.javaProject.getOutputLocation());
    }

    protected Compiler newCompiler() {
        // disable entire javadoc support if not interested in diagnostics
        Map projectOptions = this.javaBuilder.javaProject.getOptions(true);
        String option = (String) projectOptions.get(JavaCore.COMPILER_PB_INVALID_JAVADOC);
        if (// TODO (frederic) see why option is null sometimes while running model tests!?
        option == null || option.equals(JavaCore.IGNORE)) {
            option = (String) projectOptions.get(JavaCore.COMPILER_PB_MISSING_JAVADOC_TAGS);
            if (option == null || option.equals(JavaCore.IGNORE)) {
                option = (String) projectOptions.get(JavaCore.COMPILER_PB_MISSING_JAVADOC_COMMENTS);
                if (option == null || option.equals(JavaCore.IGNORE)) {
                    option = (String) projectOptions.get(JavaCore.COMPILER_PB_UNUSED_IMPORT);
                    if (// Unused import need also to look inside javadoc comment
                    option == null || option.equals(JavaCore.IGNORE)) {
                        projectOptions.put(JavaCore.COMPILER_DOC_COMMENT_SUPPORT, JavaCore.DISABLED);
                    }
                }
            }
        }
        // called once when the builder is initialized... can override if needed
        CompilerOptions compilerOptions = new CompilerOptions(projectOptions);
        compilerOptions.performMethodsFullRecovery = true;
        compilerOptions.performStatementsRecovery = true;
        Compiler newCompiler = new Compiler(this.nameEnvironment, DefaultErrorHandlingPolicies.proceedWithAllProblems(), compilerOptions, this, ProblemFactory.getProblemFactory(Locale.getDefault()));
        CompilerOptions options = newCompiler.options;
        // temporary code to allow the compiler to revert to a single thread
        //$NON-NLS-1$
        String setting = System.getProperty("jdt.compiler.useSingleThread");
        //$NON-NLS-1$
        newCompiler.useSingleThread = setting != null && setting.equals("true");
        // enable the compiler reference info support
        options.produceReferenceInfo = true;
        if (options.complianceLevel >= ClassFileConstants.JDK1_6 && options.processAnnotations) {
            // support for Java 6 annotation processors
            initializeAnnotationProcessorManager(newCompiler);
        }
        return newCompiler;
    }

    protected CompilationParticipantResult[] notifyParticipants(SourceFile[] unitsAboutToCompile) {
        CompilationParticipantResult[] results = new CompilationParticipantResult[unitsAboutToCompile.length];
        for (int i = unitsAboutToCompile.length; --i >= 0; ) results[i] = new CompilationParticipantResult(unitsAboutToCompile[i]);
        // and what happens if some participants do not expect to be called with only a few files, after seeing 'all' the files?
        for (int i = 0, l = this.javaBuilder.participants.length; i < l; i++) this.javaBuilder.participants[i].buildStarting(results, this instanceof BatchImageBuilder);
        SimpleSet uniqueFiles = null;
        CompilationParticipantResult[] toAdd = null;
        int added = 0;
        for (int i = results.length; --i >= 0; ) {
            CompilationParticipantResult result = results[i];
            if (result == null)
                continue;
            IFile[] deletedGeneratedFiles = result.deletedFiles;
            if (deletedGeneratedFiles != null)
                deleteGeneratedFiles(deletedGeneratedFiles);
            IFile[] addedGeneratedFiles = result.addedFiles;
            if (addedGeneratedFiles != null) {
                for (int j = addedGeneratedFiles.length; --j >= 0; ) {
                    SourceFile sourceFile = findSourceFile(addedGeneratedFiles[j], true);
                    if (sourceFile == null)
                        continue;
                    if (uniqueFiles == null) {
                        uniqueFiles = new SimpleSet(unitsAboutToCompile.length + 3);
                        for (int f = unitsAboutToCompile.length; --f >= 0; ) uniqueFiles.add(unitsAboutToCompile[f]);
                    }
                    if (uniqueFiles.addIfNotIncluded(sourceFile) == sourceFile) {
                        CompilationParticipantResult newResult = new CompilationParticipantResult(sourceFile);
                        // is there enough room to add all the addedGeneratedFiles.length ?
                        if (toAdd == null) {
                            toAdd = new CompilationParticipantResult[addedGeneratedFiles.length];
                        } else {
                            int length = toAdd.length;
                            if (added == length)
                                System.arraycopy(toAdd, 0, toAdd = new CompilationParticipantResult[length + addedGeneratedFiles.length], 0, length);
                        }
                        toAdd[added++] = newResult;
                    }
                }
            }
        }
        if (added > 0) {
            int length = results.length;
            System.arraycopy(results, 0, results = new CompilationParticipantResult[length + added], 0, length);
            System.arraycopy(toAdd, 0, results, length, added);
        }
        return results;
    }

    protected abstract void processAnnotationResults(CompilationParticipantResult[] results);

    protected void processAnnotations(CompilationParticipantResult[] results) {
        boolean hasAnnotationProcessor = false;
        for (int i = 0, l = this.javaBuilder.participants.length; !hasAnnotationProcessor && i < l; i++) hasAnnotationProcessor = this.javaBuilder.participants[i].isAnnotationProcessor();
        if (!hasAnnotationProcessor)
            return;
        boolean foundAnnotations = this.filesWithAnnotations != null && this.filesWithAnnotations.elementSize > 0;
        for (int i = results.length; --i >= 0; ) results[i].reset(foundAnnotations && this.filesWithAnnotations.includes(results[i].sourceFile));
        // even if no files have annotations, must still tell every annotation processor in case the file used to have them
        for (int i = 0, l = this.javaBuilder.participants.length; i < l; i++) if (this.javaBuilder.participants[i].isAnnotationProcessor())
            this.javaBuilder.participants[i].processAnnotations(results);
        processAnnotationResults(results);
    }

    protected void recordParticipantResult(CompilationParticipantResult result) {
        // any added/changed/deleted generated files have already been taken care
        // just record the problems and dependencies - do not expect there to be many
        // must be called after we're finished with the compilation unit results but before incremental loop adds affected files
        CategorizedProblem[] problems = result.problems;
        if (problems != null && problems.length > 0) {
            // existing problems have already been removed so just add these as new problems
            this.notifier.updateProblemCounts(problems);
            try {
                storeProblemsFor(result.sourceFile, problems);
            } catch (CoreException e) {
                Util.log(e, "JavaBuilder logging CompilationParticipant's CoreException to help debugging");
            }
        }
        String[] dependencies = result.dependencies;
        if (dependencies != null) {
            ReferenceCollection refs = (ReferenceCollection) this.newState.references.get(result.sourceFile.typeLocator());
            if (refs != null)
                refs.addDependencies(dependencies);
        }
    }

    /**
 * Creates a marker from each problem and adds it to the resource.
 * The marker is as follows:
 *   - its type is T_PROBLEM
 *   - its plugin ID is the JavaBuilder's plugin ID
 *	 - its message is the problem's message
 *	 - its priority reflects the severity of the problem
 *	 - its range is the problem's range
 *	 - it has an extra attribute "ID" which holds the problem's id
 *   - it's {@link IMarker#SOURCE_ID} attribute is positioned to {@link JavaBuilder#SOURCE_ID} if
 *     the problem was generated by JDT; else the {@link IMarker#SOURCE_ID} attribute is
 *     carried from the problem to the marker in extra attributes, if present.
 */
    protected void storeProblemsFor(SourceFile sourceFile, CategorizedProblem[] problems) throws CoreException {
        if (sourceFile == null || problems == null || problems.length == 0)
            return;
        // only want the one error recorded on this source file
        if (!this.keepStoringProblemMarkers)
            return;
        HashSet managedMarkerTypes = JavaModelManager.getJavaModelManager().compilationParticipants.managedMarkerTypes();
        problems: for (int i = 0, l = problems.length; i < l; i++) {
            CategorizedProblem problem = problems[i];
            int id = problem.getID();
            // we may use a different resource for certain problems such as IProblem.MissingNonNullByDefaultAnnotationOnPackage
            // but at the start of the next problem we should reset it to the source file's resource
            IResource resource = sourceFile.resource;
            // handle missing classfile situation
            if (id == IProblem.IsClassPathCorrect) {
                String missingClassfileName = problem.getArguments()[0];
                if (JavaBuilder.DEBUG)
                    System.out.println(Messages.bind(Messages.build_incompleteClassPath, missingClassfileName));
                boolean isInvalidClasspathError = JavaCore.ERROR.equals(this.javaBuilder.javaProject.getOption(JavaCore.CORE_INCOMPLETE_CLASSPATH, true));
                // insert extra classpath problem, and make it the only problem for this project (optional)
                if (isInvalidClasspathError && JavaCore.ABORT.equals(this.javaBuilder.javaProject.getOption(JavaCore.CORE_JAVA_BUILD_INVALID_CLASSPATH, true))) {
                    // make this the only problem for this project
                    JavaBuilder.removeProblemsAndTasksFor(this.javaBuilder.currentProject);
                    this.keepStoringProblemMarkers = false;
                }
                IMarker marker = this.javaBuilder.currentProject.createMarker(IJavaModelMarker.JAVA_MODEL_PROBLEM_MARKER);
                marker.setAttributes(new String[] { IMarker.MESSAGE, IMarker.SEVERITY, IJavaModelMarker.CATEGORY_ID, IMarker.SOURCE_ID }, new Object[] { Messages.bind(Messages.build_incompleteClassPath, missingClassfileName), Integer.valueOf(isInvalidClasspathError ? IMarker.SEVERITY_ERROR : IMarker.SEVERITY_WARNING), Integer.valueOf(CategorizedProblem.CAT_BUILDPATH), JavaBuilder.SOURCE_ID });
            // even if we're not keeping more markers, still fall through rest of the problem reporting, so that offending
            // IsClassPathCorrect problem gets recorded since it may help locate the offending reference
            }
            String markerType = problem.getMarkerType();
            boolean managedProblem = false;
            if (IJavaModelMarker.JAVA_MODEL_PROBLEM_MARKER.equals(markerType) || (managedProblem = managedMarkerTypes.contains(markerType))) {
                if (id == IProblem.MissingNonNullByDefaultAnnotationOnPackage && !(CharOperation.equals(sourceFile.getMainTypeName(), TypeConstants.PACKAGE_INFO_NAME))) {
                    // for this kind of problem, marker needs to be created on the package instead of on the source file
                    // see bug 372012
                    char[] fileName = sourceFile.getFileName();
                    int pkgEnd = CharOperation.lastIndexOf('/', fileName);
                    if (pkgEnd == -1)
                        pkgEnd = CharOperation.lastIndexOf(File.separatorChar, fileName);
                    PackageFragment pkg = null;
                    if (pkgEnd != -1)
                        pkg = (PackageFragment) Util.getPackageFragment(sourceFile.getFileName(), /*no jar separator for java files*/
                        pkgEnd, -1);
                    if (pkg != null) {
                        try {
                            IMarker[] existingMarkers = pkg.resource().findMarkers(IJavaModelMarker.JAVA_MODEL_PROBLEM_MARKER, false, IResource.DEPTH_ZERO);
                            int len = existingMarkers.length;
                            for (int j = 0; j < len; j++) {
                                if (((Integer) existingMarkers[j].getAttribute(IJavaModelMarker.ID)).intValue() == IProblem.MissingNonNullByDefaultAnnotationOnPackage) {
                                    // marker already present
                                    continue problems;
                                }
                            }
                        } catch (CoreException e) {
                            if (JavaModelManager.VERBOSE) {
                                e.printStackTrace();
                            }
                        }
                        IResource tempRes = pkg.resource();
                        if (tempRes != null) {
                            resource = tempRes;
                        }
                    }
                }
                IMarker marker = resource.createMarker(markerType);
                String[] attributeNames = JAVA_PROBLEM_MARKER_ATTRIBUTE_NAMES;
                int standardLength = attributeNames.length;
                String[] allNames = attributeNames;
                int managedLength = managedProblem ? 0 : 1;
                String[] extraAttributeNames = problem.getExtraMarkerAttributeNames();
                int extraLength = extraAttributeNames == null ? 0 : extraAttributeNames.length;
                if (managedLength > 0 || extraLength > 0) {
                    allNames = new String[standardLength + managedLength + extraLength];
                    System.arraycopy(attributeNames, 0, allNames, 0, standardLength);
                    if (managedLength > 0)
                        allNames[standardLength] = IMarker.SOURCE_ID;
                    System.arraycopy(extraAttributeNames, 0, allNames, standardLength + managedLength, extraLength);
                }
                Object[] allValues = new Object[allNames.length];
                // standard attributes
                int index = 0;
                // message
                allValues[index++] = problem.getMessage();
                // severity
                allValues[index++] = problem.isError() ? S_ERROR : problem.isWarning() ? S_WARNING : S_INFO;
                allValues[index++] = // ID
                Integer.valueOf(// ID
                id);
                allValues[index++] = Integer.valueOf(// start
                problem.getSourceStart());
                allValues[index++] = Integer.valueOf(// end
                problem.getSourceEnd() + // end
                1);
                allValues[index++] = Integer.valueOf(// line
                problem.getSourceLineNumber());
                // arguments
                allValues[index++] = Util.getProblemArgumentsForMarker(problem.getArguments());
                // category ID
                allValues[index++] = Integer.valueOf(problem.getCategoryID());
                // SOURCE_ID attribute for JDT problems
                if (managedLength > 0)
                    allValues[index++] = JavaBuilder.SOURCE_ID;
                // optional extra attributes
                if (extraLength > 0)
                    System.arraycopy(problem.getExtraMarkerAttributeValues(), 0, allValues, index, extraLength);
                marker.setAttributes(allNames, allValues);
                // only want the one error recorded on this source file
                if (!this.keepStoringProblemMarkers)
                    return;
            }
        }
    }

    protected void storeTasksFor(SourceFile sourceFile, CategorizedProblem[] tasks) throws CoreException {
        if (sourceFile == null || tasks == null || tasks.length == 0)
            return;
        IResource resource = sourceFile.resource;
        for (int i = 0, l = tasks.length; i < l; i++) {
            CategorizedProblem task = tasks[i];
            if (task.getID() == IProblem.Task) {
                IMarker marker = resource.createMarker(IJavaModelMarker.TASK_MARKER);
                Integer priority = P_NORMAL;
                String compilerPriority = task.getArguments()[2];
                if (JavaCore.COMPILER_TASK_PRIORITY_HIGH.equals(compilerPriority))
                    priority = P_HIGH;
                else if (JavaCore.COMPILER_TASK_PRIORITY_LOW.equals(compilerPriority))
                    priority = P_LOW;
                String[] attributeNames = JAVA_TASK_MARKER_ATTRIBUTE_NAMES;
                int standardLength = attributeNames.length;
                String[] allNames = attributeNames;
                String[] extraAttributeNames = task.getExtraMarkerAttributeNames();
                int extraLength = extraAttributeNames == null ? 0 : extraAttributeNames.length;
                if (extraLength > 0) {
                    allNames = new String[standardLength + extraLength];
                    System.arraycopy(attributeNames, 0, allNames, 0, standardLength);
                    System.arraycopy(extraAttributeNames, 0, allNames, standardLength, extraLength);
                }
                Object[] allValues = new Object[allNames.length];
                // standard attributes
                int index = 0;
                allValues[index++] = task.getMessage();
                allValues[index++] = priority;
                allValues[index++] = Integer.valueOf(task.getID());
                allValues[index++] = Integer.valueOf(task.getSourceStart());
                allValues[index++] = Integer.valueOf(task.getSourceEnd() + 1);
                allValues[index++] = Integer.valueOf(task.getSourceLineNumber());
                allValues[index++] = Boolean.FALSE;
                allValues[index++] = JavaBuilder.SOURCE_ID;
                // optional extra attributes
                if (extraLength > 0)
                    System.arraycopy(task.getExtraMarkerAttributeValues(), 0, allValues, index, extraLength);
                marker.setAttributes(allNames, allValues);
            }
        }
    }

    protected void updateProblemsFor(SourceFile sourceFile, CompilationResult result) throws CoreException {
        CategorizedProblem[] problems = result.getProblems();
        if (problems == null || problems.length == 0)
            return;
        this.notifier.updateProblemCounts(problems);
        storeProblemsFor(sourceFile, problems);
    }

    protected void updateTasksFor(SourceFile sourceFile, CompilationResult result) throws CoreException {
        CategorizedProblem[] tasks = result.getTasks();
        if (tasks == null || tasks.length == 0)
            return;
        storeTasksFor(sourceFile, tasks);
    }

    protected char[] writeClassFile(ClassFile classFile, SourceFile compilationUnit, boolean isTopLevelType) throws CoreException {
        // the qualified type name "p1/p2/A"
        String fileName = new String(classFile.fileName());
        IPath filePath = new Path(fileName);
        IContainer outputFolder = compilationUnit.sourceLocation.binaryFolder;
        IContainer container = outputFolder;
        if (filePath.segmentCount() > 1) {
            container = createFolder(filePath.removeLastSegments(1), outputFolder);
            filePath = new Path(filePath.lastSegment());
        }
        IFile file = container.getFile(filePath.addFileExtension(SuffixConstants.EXTENSION_class));
        writeClassFileContents(classFile, file, fileName, isTopLevelType, compilationUnit);
        // answer the name of the class file as in Y or Y$M
        return filePath.lastSegment().toCharArray();
    }

    protected void writeClassFileContents(ClassFile classFile, IFile file, String qualifiedFileName, boolean isTopLevelType, SourceFile compilationUnit) throws CoreException {
        //	InputStream input = new SequenceInputStream(
        //			new ByteArrayInputStream(classFile.header, 0, classFile.headerOffset),
        //			new ByteArrayInputStream(classFile.contents, 0, classFile.contentsOffset));
        InputStream input = new ByteArrayInputStream(classFile.getBytes());
        if (file.exists()) {
            // Deal with shared output folders... last one wins... no collision cases detected
            if (JavaBuilder.DEBUG)
                //$NON-NLS-1$
                System.out.println("Writing changed class file " + file.getName());
            if (!file.isDerived())
                file.setDerived(true, null);
            file.setContents(input, true, false, null);
        } else {
            // Default implementation just writes out the bytes for the new class file...
            if (JavaBuilder.DEBUG)
                //$NON-NLS-1$
                System.out.println("Writing new class file " + file.getName());
            file.create(input, IResource.FORCE | IResource.DERIVED, null);
        }
    }
}
