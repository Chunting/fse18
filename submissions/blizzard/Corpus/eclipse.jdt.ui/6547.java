/*******************************************************************************
 * Copyright (c) 2005, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.internal.corext.refactoring.scripting;

import java.util.Map;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ltk.core.refactoring.Refactoring;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.jdt.core.refactoring.descriptors.JavaRefactoringDescriptor;
import org.eclipse.jdt.internal.core.refactoring.descriptors.RefactoringSignatureDescriptorFactory;
import org.eclipse.jdt.internal.corext.refactoring.JavaRefactoringArguments;
import org.eclipse.jdt.internal.corext.refactoring.generics.InferTypeArgumentsRefactoring;

/**
 * Refactoring contribution for the infer typearguments refactoring.
 *
 * @since 3.2
 */
public final class InferTypeArgumentsRefactoringContribution extends JavaUIRefactoringContribution {

    @Override
    public final Refactoring createRefactoring(JavaRefactoringDescriptor descriptor, RefactoringStatus status) throws CoreException {
        JavaRefactoringArguments arguments = new JavaRefactoringArguments(descriptor.getProject(), retrieveArgumentMap(descriptor));
        return new InferTypeArgumentsRefactoring(arguments, status);
    }

    @Override
    public RefactoringDescriptor createDescriptor() {
        return RefactoringSignatureDescriptorFactory.createInferTypeArgumentsDescriptor();
    }

    @Override
    public RefactoringDescriptor createDescriptor(String id, String project, String description, String comment, Map<String, String> arguments, int flags) {
        return RefactoringSignatureDescriptorFactory.createInferTypeArgumentsDescriptor(project, description, comment, arguments, flags);
    }
}
