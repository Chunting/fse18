/*******************************************************************************
 * Copyright (c) 2008, 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.pde.api.tools.internal.tasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.api.tools.internal.IApiXmlConstants;
import org.eclipse.pde.api.tools.internal.provisional.ApiPlugin;
import org.eclipse.pde.api.tools.internal.util.Util;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.ibm.icu.text.MessageFormat;

/**
 * This task can be used to convert all reports generated by the
 * apitooling.analysis task into html reports.
 */
public class AnalysisReportConversionTask extends Task {

    static final class Problem {

        String message;

        int severity;

        public  Problem(String message, int severity) {
            this.message = message;
            this.severity = severity;
        }

        public String getHtmlMessage() {
            StringBuffer buffer = new StringBuffer();
            char[] chars = this.message.toCharArray();
            for (char character : chars) {
                switch(character) {
                    case '<':
                        //$NON-NLS-1$
                        buffer.append("&lt;");
                        break;
                    case '>':
                        //$NON-NLS-1$
                        buffer.append("&gt;");
                        break;
                    case '&':
                        //$NON-NLS-1$
                        buffer.append(//$NON-NLS-1$
                        "&amp;");
                        break;
                    case '"':
                        //$NON-NLS-1$
                        buffer.append(//$NON-NLS-1$
                        "&quot;");
                        break;
                    default:
                        buffer.append(character);
                }
            }
            return String.valueOf(buffer);
        }

        @Override
        public String toString() {
            StringBuffer buffer = new StringBuffer();
            //$NON-NLS-1$
            buffer.append("Problem : ").append(this.message).append(' ').append(this.severity);
            return String.valueOf(buffer);
        }
    }

    static final class ConverterDefaultHandler extends DefaultHandler {

        String category;

        boolean debug;

        Report report;

        public  ConverterDefaultHandler(boolean debug) {
            this.debug = debug;
        }

        public Report getReport() {
            return this.report;
        }

        @Override
        public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
            if (IApiXmlConstants.ELEMENT_API_TOOL_REPORT.equals(name)) {
                String componentID = attributes.getValue(IApiXmlConstants.ATTR_COMPONENT_ID);
                if (this.debug) {
                    System.out.println("component id : " + //$NON-NLS-1$
                    String.valueOf(//$NON-NLS-1$
                    componentID));
                }
                this.report = new Report(componentID);
            } else if (IApiXmlConstants.ATTR_CATEGORY.equals(name)) {
                this.category = attributes.getValue(IApiXmlConstants.ATTR_VALUE);
                if (this.debug) {
                    System.out.println(//$NON-NLS-1$
                    "category : " + //$NON-NLS-1$
                    this.category);
                }
            } else if (IApiXmlConstants.ELEMENT_API_PROBLEM.equals(name)) {
                String message = attributes.getValue(IApiXmlConstants.ATTR_MESSAGE);
                if (this.debug) {
                    System.out.println(//$NON-NLS-1$
                    "problem message : " + //$NON-NLS-1$
                    message);
                }
                int severity = Integer.parseInt(attributes.getValue(IApiXmlConstants.ATTR_SEVERITY));
                if (this.debug) {
                    System.out.println(//$NON-NLS-1$
                    "problem severity : " + //$NON-NLS-1$
                    severity);
                }
                this.report.addProblem(this.category, new Problem(message, severity));
            } else if (IApiXmlConstants.ELEMENT_RESOLVER_ERROR.equals(name)) {
                String message = attributes.getValue(IApiXmlConstants.ATTR_MESSAGE);
                if (this.debug) {
                    System.out.println(//$NON-NLS-1$
                    "Resolver error : " + //$NON-NLS-1$
                    message);
                }
                this.report.addProblem(this.category, new Problem(message, ApiPlugin.SEVERITY_WARNING));
            } else if (IApiXmlConstants.ELEMENT_BUNDLE.equals(name)) {
                String bundleName = attributes.getValue(IApiXmlConstants.ATTR_NAME);
                if (this.debug) {
                    System.out.println(//$NON-NLS-1$
                    "bundle name : " + //$NON-NLS-1$
                    bundleName);
                }
                this.report.addNonApiBundles(bundleName);
            } else if (this.debug) {
                if (!IApiXmlConstants.ELEMENT_PROBLEM_MESSAGE_ARGUMENTS.equals(name) && !IApiXmlConstants.ELEMENT_PROBLEM_MESSAGE_ARGUMENT.equals(name) && !IApiXmlConstants.ELEMENT_API_PROBLEMS.equals(name) && !IApiXmlConstants.ELEMENT_PROBLEM_EXTRA_ARGUMENT.equals(name) && !IApiXmlConstants.ELEMENT_PROBLEM_EXTRA_ARGUMENTS.equals(name)) {
                    System.out.println("unknown element : " + //$NON-NLS-1$
                    String.valueOf(//$NON-NLS-1$
                    name));
                }
            }
        }
    }

    private static final class Report {

        String componentID;

        String link;

        List<String> nonApiBundles;

        Map<String, List<Problem>> problemsPerCategories;

         Report(String componentID) {
            this.componentID = componentID;
        }

        public void addNonApiBundles(String bundleName) {
            if (this.nonApiBundles == null) {
                this.nonApiBundles = new ArrayList();
            }
            this.nonApiBundles.add(bundleName);
        }

        public void addProblem(String category, Problem problem) {
            if (this.problemsPerCategories == null) {
                this.problemsPerCategories = new HashMap();
            }
            List<Problem> problemsList = this.problemsPerCategories.get(category);
            if (problemsList == null) {
                problemsList = new ArrayList();
                this.problemsPerCategories.put(category, problemsList);
            }
            problemsList.add(problem);
        }

        public String[] getNonApiBundles() {
            if (this.nonApiBundles == null || this.nonApiBundles.size() == 0) {
                return NO_NON_API_BUNDLES;
            }
            String[] nonApiBundlesNames = new String[this.nonApiBundles.size()];
            this.nonApiBundles.toArray(nonApiBundlesNames);
            return nonApiBundlesNames;
        }

        public Problem[] getProblems(String category) {
            if (this.problemsPerCategories == null) {
                return NO_PROBLEMS;
            }
            List<Problem> problemsList = this.problemsPerCategories.get(category);
            int size = problemsList == null ? 0 : problemsList.size();
            if (size == 0) {
                return NO_PROBLEMS;
            }
            Problem[] problems = new Problem[size];
            problemsList.toArray(problems);
            return problems;
        }

        public int getProblemSize(String category) {
            if (this.problemsPerCategories == null) {
                return 0;
            }
            List<Problem> problemsList = this.problemsPerCategories.get(category);
            return problemsList == null ? 0 : problemsList.size();
        }

        public boolean isNonApiBundlesReport() {
            return this.componentID == null;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }

    private static final class Summary {

        int apiUsageNumber;

        int bundleVersionNumber;

        int compatibilityNumber;

        int componentResolutionNumber;

        String componentID;

        String link;

        public  Summary(Report report) {
            super();
            this.apiUsageNumber = report.getProblemSize(APIToolsAnalysisTask.USAGE);
            this.bundleVersionNumber = report.getProblemSize(APIToolsAnalysisTask.BUNDLE_VERSION);
            this.compatibilityNumber = report.getProblemSize(APIToolsAnalysisTask.COMPATIBILITY);
            this.componentResolutionNumber = report.getProblemSize(APIToolsAnalysisTask.COMPONENT_RESOLUTION);
            this.componentID = report.componentID;
            this.link = report.link;
        }

        @Override
        public String toString() {
            return //$NON-NLS-1$
            MessageFormat.format(//$NON-NLS-1$
            "{0} : compatibility {1}, api usage {2}, bundle version {3}, resolution {4}, link {5}", this.componentID, Integer.toString(this.compatibilityNumber), Integer.toString(this.apiUsageNumber), Integer.toString(this.bundleVersionNumber), Integer.toString(this.componentResolutionNumber), this.link);
        }
    }

    static final Problem[] NO_PROBLEMS = new Problem[0];

    static final String[] NO_NON_API_BUNDLES = new String[0];

    boolean debug;

    private String htmlReportsLocation;

    private File htmlRoot;

    private File reportsRoot;

    private String xmlReportsLocation;

    private void dumpFooter(PrintWriter writer) {
        writer.println(Messages.W3C_page_footer);
    }

    private void dumpHeader(PrintWriter writer, Report report) {
        writer.println(MessageFormat.format(Messages.fullReportTask_apiproblemheader, report.componentID));
        // dump the summary
        writer.println(MessageFormat.format(Messages.fullReportTask_apiproblemsummary, Integer.toString(report.getProblemSize(APIToolsAnalysisTask.COMPATIBILITY)), Integer.toString(report.getProblemSize(APIToolsAnalysisTask.USAGE)), Integer.toString(report.getProblemSize(APIToolsAnalysisTask.BUNDLE_VERSION))));
        if (report.getProblemSize(APIToolsAnalysisTask.COMPONENT_RESOLUTION) == 1) {
            writer.println(MessageFormat.format(Messages.fullReportTask_resolutiondetailsSingle, report.componentID, Integer.toString(report.getProblemSize(APIToolsAnalysisTask.COMPONENT_RESOLUTION))));
        } else if (report.getProblemSize(APIToolsAnalysisTask.COMPONENT_RESOLUTION) > 1) {
            writer.println(MessageFormat.format(Messages.fullReportTask_resolutiondetails, report.componentID, Integer.toString(report.getProblemSize(APIToolsAnalysisTask.COMPONENT_RESOLUTION))));
        }
    }

    private void dumpIndexEntry(int i, PrintWriter writer, Summary summary) {
        if (debug) {
            System.out.println(summary);
        }
        if ((i % 2) == 0) {
            writer.println(MessageFormat.format(Messages.fullReportTask_indexsummary_even, summary.componentID, Integer.toString(summary.compatibilityNumber), Integer.toString(summary.apiUsageNumber), Integer.toString(summary.bundleVersionNumber), summary.link));
        } else {
            writer.println(MessageFormat.format(Messages.fullReportTask_indexsummary_odd, summary.componentID, Integer.toString(summary.compatibilityNumber), Integer.toString(summary.apiUsageNumber), Integer.toString(summary.bundleVersionNumber), summary.link));
        }
        if (summary.componentResolutionNumber > 0) {
            if ((i % 2) == 0) {
                writer.println(MessageFormat.format(Messages.fullReportTask_resolutionsummary_even, summary.componentID, Integer.toString(summary.componentResolutionNumber)));
            } else {
                writer.println(MessageFormat.format(Messages.fullReportTask_resolutionsummary_odd, summary.componentID, Integer.toString(summary.componentResolutionNumber)));
            }
        }
    }

    /**
	 * Write out the index file
	 *
	 * @param reportsRoot
	 * @param summaries
	 * @param allNonApiBundleSummary
	 */
    private void dumpIndexFile(File reportsRoot, Summary[] summaries, Summary allNonApiBundleSummary) {
        //$NON-NLS-1$
        File htmlFile = new File(this.htmlReportsLocation, "index.html");
        PrintWriter writer = null;
        try {
            FileWriter fileWriter = new FileWriter(htmlFile);
            writer = new PrintWriter(new BufferedWriter(fileWriter));
            if (allNonApiBundleSummary != null) {
                writer.println(MessageFormat.format(Messages.fullReportTask_indexheader, NLS.bind(Messages.fullReportTask_nonApiBundleSummary, allNonApiBundleSummary.link)));
            } else {
                writer.println(MessageFormat.format(Messages.fullReportTask_indexheader, Messages.fullReportTask_apiBundleSummary));
            }
            Arrays.sort(summaries, new Comparator<Object>() {

                @Override
                public int compare(Object o1, Object o2) {
                    Summary summary1 = (Summary) o1;
                    Summary summary2 = (Summary) o2;
                    return summary1.componentID.compareTo(summary2.componentID);
                }
            });
            for (int i = 0, max = summaries.length; i < max; i++) {
                dumpIndexEntry(i, writer, summaries[i]);
            }
            writer.println(Messages.fullReportTask_indexfooter);
            writer.flush();
        } catch (IOException e) {
            throw new BuildException(NLS.bind(Messages.ioexception_writing_html_file, htmlFile.getAbsolutePath()));
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private void dumpNonApiBundles(PrintWriter writer, Report report) {
        writer.println(Messages.fullReportTask_bundlesheader);
        String[] nonApiBundleNames = report.getNonApiBundles();
        for (int i = 0; i < nonApiBundleNames.length; i++) {
            String bundleName = nonApiBundleNames[i];
            if ((i % 2) == 0) {
                writer.println(MessageFormat.format(Messages.fullReportTask_bundlesentry_even, bundleName));
            } else {
                writer.println(MessageFormat.format(Messages.fullReportTask_bundlesentry_odd, bundleName));
            }
        }
        writer.println(Messages.fullReportTask_bundlesfooter);
    }

    private void dumpProblems(PrintWriter writer, String categoryName, Problem[] problems, boolean printEmptyCategory) {
        if (problems != null && problems.length != 0) {
            writer.println(MessageFormat.format(Messages.fullReportTask_categoryheader, categoryName));
            for (int i = 0, max = problems.length; i < max; i++) {
                Problem problem = problems[i];
                if ((i % 2) == 0) {
                    switch(problem.severity) {
                        case ApiPlugin.SEVERITY_ERROR:
                            writer.println(MessageFormat.format(Messages.fullReportTask_problementry_even_error, problem.getHtmlMessage()));
                            break;
                        case ApiPlugin.SEVERITY_WARNING:
                            writer.println(MessageFormat.format(Messages.fullReportTask_problementry_even_warning, problem.getHtmlMessage()));
                            break;
                        default:
                            break;
                    }
                } else {
                    switch(problem.severity) {
                        case ApiPlugin.SEVERITY_ERROR:
                            writer.println(MessageFormat.format(Messages.fullReportTask_problementry_odd_error, problem.getHtmlMessage()));
                            break;
                        case ApiPlugin.SEVERITY_WARNING:
                            writer.println(MessageFormat.format(Messages.fullReportTask_problementry_odd_warning, problem.getHtmlMessage()));
                            break;
                        default:
                            break;
                    }
                }
            }
            writer.println(Messages.fullReportTask_categoryfooter);
        } else if (printEmptyCategory) {
            writer.println(MessageFormat.format(Messages.fullReportTask_category_no_elements, categoryName));
        }
    }

    private void dumpReport(File xmlFile, Report report) {
        // create file writer
        // generate the html file name from the xml file name
        String htmlName = extractNameFromXMLName(xmlFile);
        report.setLink(extractLinkFrom(htmlName));
        File htmlFile = new File(htmlName);
        File parent = htmlFile.getParentFile();
        if (!parent.exists()) {
            if (!parent.mkdirs()) {
                throw new BuildException(NLS.bind(Messages.could_not_create_file, htmlName));
            }
        }
        PrintWriter writer = null;
        try {
            FileWriter fileWriter = new FileWriter(htmlFile);
            writer = new PrintWriter(new BufferedWriter(fileWriter));
            if (report.isNonApiBundlesReport()) {
                dumpNonApiBundles(writer, report);
            } else {
                dumpHeader(writer, report);
                dumpProblems(writer, Messages.AnalysisReportConversionTask_component_resolution_header, report.getProblems(APIToolsAnalysisTask.COMPONENT_RESOLUTION), false);
                dumpProblems(writer, Messages.fullReportTask_compatibility_header, report.getProblems(APIToolsAnalysisTask.COMPATIBILITY), true);
                dumpProblems(writer, Messages.fullReportTask_api_usage_header, report.getProblems(APIToolsAnalysisTask.USAGE), true);
                dumpProblems(writer, Messages.fullReportTask_bundle_version_header, report.getProblems(APIToolsAnalysisTask.BUNDLE_VERSION), true);
                dumpFooter(writer);
            }
            writer.flush();
        } catch (IOException e) {
            throw new BuildException(NLS.bind(Messages.ioexception_writing_html_file, htmlName));
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
	 * Run the ant task
	 */
    @Override
    public void execute() throws BuildException {
        if (this.debug) {
            //$NON-NLS-1$
            System.out.println("xmlReportsLocation : " + this.xmlReportsLocation);
            //$NON-NLS-1$
            System.out.println("htmlReportsLocation : " + this.htmlReportsLocation);
        }
        if (this.xmlReportsLocation == null) {
            throw new BuildException(Messages.missing_xml_files_location);
        }
        this.reportsRoot = new File(this.xmlReportsLocation);
        if (!this.reportsRoot.exists() || !this.reportsRoot.isDirectory()) {
            throw new BuildException(NLS.bind(Messages.invalid_directory_name, this.xmlReportsLocation));
        }
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        if (parser == null) {
            throw new BuildException(Messages.could_not_create_sax_parser);
        }
        if (this.htmlReportsLocation == null) {
            this.htmlReportsLocation = this.xmlReportsLocation;
        }
        this.htmlRoot = new File(this.htmlReportsLocation);
        if (!this.htmlRoot.exists()) {
            if (!this.htmlRoot.mkdirs()) {
                throw new BuildException(NLS.bind(Messages.could_not_create_file, this.htmlReportsLocation));
            }
        }
        if (this.debug) {
            //$NON-NLS-1$
            System.out.println("output name :" + this.htmlReportsLocation);
        }
        try {
            // retrieve all xml reports
            File[] allFiles = Util.getAllFiles(reportsRoot, new FileFilter() {

                @Override
                public boolean accept(File pathname) {
                    return pathname.isDirectory() || //$NON-NLS-1$
                    pathname.getName().endsWith(//$NON-NLS-1$
                    ".xml");
                }
            });
            if (allFiles != null) {
                int length = allFiles.length;
                List<Summary> summariesList = new ArrayList(length);
                Summary nonApiBundleSummary = null;
                for (int i = 0; i < length; i++) {
                    File file = allFiles[i];
                    ConverterDefaultHandler defaultHandler = new ConverterDefaultHandler(this.debug);
                    parser.parse(file, defaultHandler);
                    Report report = defaultHandler.getReport();
                    if (report == null) {
                        // ignore that file. It could be the counts.xml file.
                        if (this.debug) {
                            System.out.println(//$NON-NLS-1$
                            "Skipped file : " + //$NON-NLS-1$
                            file.getAbsolutePath());
                        }
                        continue;
                    }
                    dumpReport(file, report);
                    if (report.isNonApiBundlesReport()) {
                        nonApiBundleSummary = new Summary(report);
                    } else {
                        summariesList.add(new Summary(report));
                    }
                }
                // tools bundles (ignore count.xml)
                if (!summariesList.isEmpty() || nonApiBundleSummary != null) {
                    Summary[] summaries = new Summary[summariesList.size()];
                    summariesList.toArray(summaries);
                    dumpIndexFile(reportsRoot, summaries, nonApiBundleSummary);
                }
            }
        } catch (SAXException e) {
        } catch (IOException e) {
        }
    }

    private String extractLinkFrom(String fileName) {
        StringBuffer buffer = new StringBuffer();
        buffer.append('.').append(fileName.substring(this.htmlRoot.getAbsolutePath().length()).replace('\\', '/'));
        return String.valueOf(buffer);
    }

    private String extractNameFromXMLName(File xmlFile) {
        String fileName = xmlFile.getAbsolutePath();
        int index = fileName.lastIndexOf('.');
        StringBuffer buffer = new StringBuffer();
        buffer.append(fileName.substring(this.reportsRoot.getAbsolutePath().length(), index)).append(".html");
        File htmlFile = new File(this.htmlReportsLocation, String.valueOf(buffer));
        return htmlFile.getAbsolutePath();
    }

    public void setDebug(String debugValue) {
        this.debug = Boolean.toString(true).equals(debugValue);
    }

    public void setHtmlFiles(String htmlFilesLocation) {
        this.htmlReportsLocation = htmlFilesLocation;
    }

    public void setXmlFiles(String xmlFilesLocation) {
        this.xmlReportsLocation = xmlFilesLocation;
    }
}
