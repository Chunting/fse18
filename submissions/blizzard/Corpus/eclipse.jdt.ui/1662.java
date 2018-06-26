/*******************************************************************************
 * Copyright (c) 2000, 2016 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     John Kaplan, johnkaplantech@gmail.com - 108071 [code templates] template for body of newly created class
 *     Sebastian Davids, sdavids@gmx.de - 187316 [preferences] Mark Occurences Pref Page; Link to
 *     Benjamin Muskalla <b.muskalla@gmx.net> - [preferences] Add preference for new compiler warning: MissingSynchronizedModifierInInheritedMethod - https://bugs.eclipse.org/bugs/show_bug.cgi?id=245240
 *     Guven Demir <guven.internet+eclipse@gmail.com> - [package explorer] Alternative package name shortening: abbreviation - https://bugs.eclipse.org/bugs/show_bug.cgi?id=299514
 *     Thomas Reinhardt <thomas@reinhardt.com> - [build path] user library dialog should allow to select JAR from workspace - http://bugs.eclipse.org/300542
 *     Stephan Herrmann <stephan@cs.tu-berlin.de> - [compiler][null] inheritance of null annotations as an option - https://bugs.eclipse.org/388281
 *     Gábor Kövesdán - Contribution for Bug 350000 - [content assist] Include non-prefix matches in auto-complete suggestions
 *******************************************************************************/
package org.eclipse.jdt.internal.ui.preferences;

import org.eclipse.osgi.util.NLS;

public final class PreferencesMessages extends NLS {

    //$NON-NLS-1$
    private static final String BUNDLE_NAME = "org.eclipse.jdt.internal.ui.preferences.PreferencesMessages";

    private  PreferencesMessages() {
    // Do not instantiate
    }

    public static String BuildPathsPropertyPage_error_message;

    public static String BuildPathsPropertyPage_error_title;

    public static String BuildPathsPropertyPage_job_title;

    public static String BuildPathsPropertyPage_no_java_project_message;

    public static String BuildPathsPropertyPage_closed_project_message;

    public static String BuildPathsPropertyPage_unsavedchanges_title;

    public static String BuildPathsPropertyPage_unsavedchanges_message;

    public static String BuildPathsPropertyPage_unsavedchanges_button_save;

    public static String BuildPathsPropertyPage_unsavedchanges_button_discard;

    public static String BuildPathsPropertyPage_unsavedchanges_button_ignore;

    public static String ClasspathVariablesPreferencePage_title;

    public static String ClasspathVariablesPreferencePage_description;

    public static String ClasspathVariablesPreferencePage_savechanges_title;

    public static String ClasspathVariablesPreferencePage_savechanges_message;

    public static String CleanUpPreferencePage_Description;

    public static String CleanUpPreferencePage_Title;

    public static String CodeAssistAdvancedConfigurationBlock_default_table_category_column_title;

    public static String CodeAssistAdvancedConfigurationBlock_default_table_description;

    public static String CodeAssistAdvancedConfigurationBlock_default_table_keybinding_column_title;

    public static String CodeAssistAdvancedConfigurationBlock_key_binding_hint;

    public static String CodeAssistAdvancedConfigurationBlock_page_description;

    public static String CodeAssistAdvancedConfigurationBlock_separate_table_category_column_title;

    public static String CodeAssistAdvancedConfigurationBlock_separate_table_description;

    public static String CodeAssistAdvancedConfigurationBlock_no_shortcut;

    public static String CodeAssistAdvancedConfigurationBlock_Up;

    public static String CodeAssistAdvancedConfigurationBlock_Down;

    public static String CodeAssistAdvancedConfigurationBlock_parameterNameFromAttachedJavadoc_timeout;

    public static String CodeAssistAdvancedConfigurationBlock_parameterNameFromAttachedJavadoc_timeout_emptyInput;

    public static String CodeAssistAdvancedConfigurationBlock_parameterNameFromAttachedJavadoc_timeout_invalidInput;

    public static String CodeAssistAdvancedConfigurationBlock_parameterNameFromAttachedJavadoc_timeout_invalidRange;

    public static String ImportOrganizePreferencePage_title;

    public static String ImportOrganizeConfigurationBlock_order_label;

    public static String ImportOrganizeConfigurationBlock_other_static;

    public static String ImportOrganizeConfigurationBlock_other_normal;

    public static String ImportOrganizeConfigurationBlock_order_add_button;

    public static String ImportOrganizeConfigurationBlock_order_edit_button;

    public static String ImportOrganizeConfigurationBlock_order_up_button;

    public static String ImportOrganizeConfigurationBlock_order_down_button;

    public static String ImportOrganizeConfigurationBlock_order_remove_button;

    public static String ImportOrganizeConfigurationBlock_order_add_static_button;

    public static String ImportOrganizeConfigurationBlock_order_load_button;

    public static String ImportOrganizeConfigurationBlock_order_save_button;

    public static String ImportOrganizeConfigurationBlock_ignoreLowerCase_label;

    public static String ImportOrganizeConfigurationBlock_threshold_label;

    public static String ImportOrganizeConfigurationBlock_error_invalidthreshold;

    public static String ImportOrganizeConfigurationBlock_loadDialog_title;

    public static String ImportOrganizeConfigurationBlock_loadDialog_error_title;

    public static String ImportOrganizeConfigurationBlock_loadDialog_error_message;

    public static String ImportOrganizeConfigurationBlock_saveDialog_title;

    public static String ImportOrganizeConfigurationBlock_saveDialog_error_title;

    public static String ImportOrganizeConfigurationBlock_staticthreshold_label;

    public static String ImportOrganizeConfigurationBlock_saveDialog_error_message;

    public static String ImportOrganizeInputDialog_title;

    public static String ImportOrganizeInputDialog_browse_packages_button;

    public static String ImportOrganizeInputDialog_browse_types_label;

    public static String ImportOrganizeInputDialog_title_static;

    public static String ImportOrganizeInputDialog_ChoosePackageDialog_title;

    public static String ImportOrganizeInputDialog_ChoosePackageDialog_description;

    public static String ImportOrganizeInputDialog_ChoosePackageDialog_empty;

    public static String ImportOrganizeInputDialog_ChooseTypeDialog_title;

    public static String ImportOrganizeInputDialog_ChooseTypeDialog_description;

    public static String ImportOrganizeInputDialog_ChooseTypeDialog_error_message;

    public static String ImportOrganizeInputDialog_error_invalidName;

    public static String ImportOrganizeInputDialog_error_entryExists;

    public static String ImportOrganizeInputDialog_name_group_label;

    public static String ImportOrganizeInputDialog_name_group_static_label;

    public static String JavaBasePreferencePage_description;

    public static String JavaBasePreferencePage_doubleclick_action;

    public static String JavaBasePreferencePage_doubleclick_gointo;

    public static String JavaBasePreferencePage_doubleclick_expand;

    public static String JavaBasePreferencePage_refactoring_lightweight;

    public static String JavaBasePreferencePage_refactoring_title;

    public static String JavaBasePreferencePage_refactoring_auto_save;

    public static String JavaBasePreferencePage_search;

    public static String JavaBasePreferencePage_search_small_menu;

    public static String JavaBuildConfigurationBlock_build_recreate_modified;

    public static String JavadocConfigurationBlock_error_archive_not_found_in_workspace;

    public static String JavadocConfigurationBlock_external_radio;

    public static String JavadocConfigurationBlock_workspace_archive_selection_dialog_description;

    public static String JavadocConfigurationBlock_workspace_archive_selection_dialog_title;

    public static String JavadocConfigurationBlock_workspace_radio;

    public static String JavadocConfigurationPropertyPage_invalid_container;

    public static String JavadocConfigurationPropertyPage_not_supported;

    public static String JavadocConfigurationPropertyPage_read_only;

    public static String JavaEditorPropertyPage_SaveActionLink_Text;

    public static String NativeLibrariesPropertyPage_invalid_container;

    public static String NativeLibrariesPropertyPage_not_supported;

    public static String NativeLibrariesPropertyPage_read_only;

    public static String NewJavaProjectPreferencePage_title;

    public static String NewJavaProjectPreferencePage_description;

    public static String NewJavaProjectPreferencePage_sourcefolder_label;

    public static String NewJavaProjectPreferencePage_sourcefolder_project;

    public static String NewJavaProjectPreferencePage_sourcefolder_folder;

    public static String NewJavaProjectPreferencePage_folders_src;

    public static String NewJavaProjectPreferencePage_folders_bin;

    public static String NewJavaProjectPreferencePage_jrelibrary_label;

    public static String NewJavaProjectPreferencePage_jre_variable_description;

    public static String NewJavaProjectPreferencePage_jre_container_description;

    public static String NewJavaProjectPreferencePage_folders_error_namesempty;

    public static String NewJavaProjectPreferencePage_folders_error_invalidsrcname;

    public static String NewJavaProjectPreferencePage_folders_error_invalidbinname;

    public static String NewJavaProjectPreferencePage_folders_error_invalidcp;

    public static String NewJavaProjectPreferencePage_error_decode;

    public static String JavaEditorPreferencePage_analyseAnnotationsWhileTyping;

    public static String JavaEditorPreferencePage_multiLineComment;

    public static String JavaEditorPreferencePage_singleLineComment;

    public static String JavaEditorPreferencePage_returnKeyword;

    public static String JavaEditorPreferencePage_keywords;

    public static String JavaEditorPreferencePage_strings;

    public static String JavaEditorPreferencePage_others;

    public static String JavaEditorPreferencePage_operators;

    public static String JavaEditorPreferencePage_bracketHighlighting;

    public static String JavaEditorPreferencePage_brackets;

    public static String JavaEditorPreferencePage_javaCommentTaskTags;

    public static String JavaEditorPreferencePage_javaDocKeywords;

    public static String JavaEditorPreferencePage_javaDocHtmlTags;

    public static String JavaEditorPreferencePage_javaDocLinks;

    public static String JavaEditorPreferencePage_javaDocOthers;

    public static String JavaEditorPreferencePage_systemDefault;

    public static String JavaEditorPreferencePage_color;

    public static String JavaEditorPreferencePage_bold;

    public static String JavaEditorPreferencePage_italic;

    public static String JavaEditorPreferencePage_strikethrough;

    public static String JavaEditorPreferencePage_underline;

    public static String JavaEditorPreferencePage_enable;

    public static String JavaEditorPreferencePage_preview;

    public static String JavaEditorPreferencePage_highlightMatchingBracketAndCaretLocation;

    public static String JavaEditorPreferencePage_highlightEnclosingBrackets;

    public static String JavaEditorPreferencePage_highlightMatchingBracket;

    public static String JavaEditorPreferencePage_insertSingleProposalsAutomatically;

    public static String JavaEditorPreferencePage_showOnlyProposalsVisibleInTheInvocationContext;

    public static String JavaEditorPreferencePage_presentProposalsInAlphabeticalOrder;

    public static String JavaEditorPreferencePage_coloring_element;

    public static String JavaEditorPreferencePage_enableAutoActivation;

    public static String JavaEditorPreferencePage_automaticallyAddImportInsteadOfQualifiedName;

    public static String JavaEditorPreferencePage_suggestStaticImports;

    public static String JavaEditorPreferencePage_completionInserts;

    public static String JavaEditorPreferencePage_completionOverwrites;

    public static String JavaEditorPreferencePage_completionToggleHint;

    public static String JavaEditorPreferencePage_fillArgumentsOnMethodCompletion;

    public static String JavaEditorPreferencePage_fillParameterNamesOnMethodCompletion;

    public static String JavaEditorPreferencePage_fillBestGuessedArgumentsOnMethodCompletion;

    public static String JavaEditorPreferencePage_autoActivationDelay;

    public static String JavaEditorPreferencePage_autoActivationTriggersForJava;

    public static String JavaEditorPreferencePage_autoActivationTriggersForJavaDoc;

    public static String JavaEditorPreferencePage_completePrefixes;

    public static String JavaEditorPreferencePage_backgroundForMethodParameters;

    public static String JavaEditorPreferencePage_foregroundForMethodParameters;

    public static String JavaEditorPreferencePage_backgroundForCompletionReplacement;

    public static String JavaEditorPreferencePage_foregroundForCompletionReplacement;

    public static String JavaEditorPreferencePage_sourceHoverBackgroundColor;

    public static String JavaEditorPreferencePage_general;

    public static String JavaEditorPreferencePage_colors;

    public static String JavaEditorPreferencePage_empty_input;

    public static String JavaEditorPreferencePage_invalid_input;

    public static String JavaEditorPreferencePage_matchingBracketsHighlightColor2;

    public static String JavaEditorPreferencePage_appearanceOptions;

    public static String JavaEditorPreferencePage_typing_tabTitle;

    public static String JavaEditorPreferencePage_closeStrings;

    public static String JavaEditorPreferencePage_closeBrackets;

    public static String JavaEditorPreferencePage_closeBraces;

    public static String JavaEditorPreferencePage_closeJavaDocs;

    public static String JavaEditorPreferencePage_wrapStrings;

    public static String JavaEditorPreferencePage_escapeStrings;

    public static String JavaEditorPreferencePage_addJavaDocTags;

    public static String JavaEditorPreferencePage_smartPaste;

    public static String JavaEditorPreferencePage_link;

    public static String JavaEditorPreferencePage_importsOnPaste;

    public static String JavaEditorPreferencePage_subWordNavigation;

    public static String JavaEditorPreferencePage_typing_smartSemicolon;

    public static String JavaEditorPreferencePage_typing_smartOpeningBrace;

    public static String JavaEditorPreferencePage_typing_smartTab;

    public static String JavaEditorPreferencePage_hoverTab_title;

    public static String JavaEditorPreferencePage_smartInsertMode_message;

    public static String JavaEditorPreferencePage_smartAutoIndentAfterNewLine;

    public static String JavaEditorColoringConfigurationBlock_link;

    public static String JavaBasePreferencePage_openTypeHierarchy;

    public static String JavaBasePreferencePage_inView;

    public static String JavaBasePreferencePage_inPerspective;

    public static String JavaEditorPreferencePage_quickassist_lightbulb;

    public static String JavaEditorPreferencePage_showJavaElementOnly;

    public static String JavaEditorHoverConfigurationBlock_annotationRollover;

    public static String JavaEditorHoverConfigurationBlock_hoverPreferences;

    public static String JavaEditorHoverConfigurationBlock_keyModifier;

    public static String JavaEditorHoverConfigurationBlock_description;

    public static String JavaEditorHoverConfigurationBlock_modifierIsNotValid;

    public static String JavaEditorHoverConfigurationBlock_modifierIsNotValidForHover;

    public static String JavaEditorHoverConfigurationBlock_duplicateModifier;

    public static String JavaEditorHoverConfigurationBlock_nameColumnTitle;

    public static String JavaEditorHoverConfigurationBlock_modifierColumnTitle;

    public static String JavaEditorHoverConfigurationBlock_delimiter;

    public static String JavaEditorHoverConfigurationBlock_insertDelimiterAndModifierAndDelimiter;

    public static String JavaEditorHoverConfigurationBlock_insertModifierAndDelimiter;

    public static String JavaEditorHoverConfigurationBlock_insertDelimiterAndModifier;

    public static String MarkOccurrencesConfigurationBlock_title;

    public static String MarkOccurrencesConfigurationBlock_link;

    public static String MarkOccurrencesConfigurationBlock_markOccurrences;

    public static String MarkOccurrencesConfigurationBlock_markTypeOccurrences;

    public static String MarkOccurrencesConfigurationBlock_markMethodOccurrences;

    public static String MarkOccurrencesConfigurationBlock_markConstantOccurrences;

    public static String MarkOccurrencesConfigurationBlock_markFieldOccurrences;

    public static String MarkOccurrencesConfigurationBlock_markLocalVariableOccurrences;

    public static String MarkOccurrencesConfigurationBlock_markExceptionOccurrences;

    public static String MarkOccurrencesConfigurationBlock_markMethodExitPoints;

    public static String MarkOccurrencesConfigurationBlock_markImplementors;

    public static String MarkOccurrencesConfigurationBlock_markBreakContinueTargets;

    public static String MarkOccurrencesConfigurationBlock_stickyOccurrences;

    public static String JavadocConfigurationPropertyPage_IsPackageFragmentRoot_description;

    public static String JavadocConfigurationPropertyPage_IsIncorrectElement_description;

    public static String JavadocConfigurationPropertyPage_IsJavaProject_description;

    public static String JavadocConfigurationPropertyPage_location_path;

    public static String JavadocConfigurationPropertyPage_locationPath_none;

    public static String JavadocConfigurationBlock_browse_folder_button;

    public static String JavadocConfigurationBlock_error_notafolder;

    public static String JavadocConfigurationBlock_javadocFolderDialog_label;

    public static String JavadocConfigurationBlock_javadocFolderDialog_message;

    public static String JavadocConfigurationBlock_MalformedURL_error;

    public static String JavadocConfigurationBlock_validate_button;

    public static String JavadocConfigurationBlock_InvalidLocation_message;

    public static String JavadocConfigurationBlock_ValidLocation_message;

    public static String JavadocConfigurationBlock_OK_label;

    public static String JavadocConfigurationBlock_Open_label;

    public static String JavadocConfigurationBlock_UnableToValidateLocation_message;

    public static String JavadocConfigurationBlock_MessageDialog_title;

    public static String JavadocConfigurationBlock_location_type_path_label;

    public static String JavadocConfigurationBlock_location_type_jar_label;

    public static String JavadocConfigurationBlock_location_path_label;

    public static String JavadocConfigurationBlock_location_jar_label;

    public static String JavadocConfigurationBlock_jar_path_label;

    public static String JavadocConfigurationBlock_zipImportSource_title;

    public static String JavadocConfigurationBlock_location_in_jarorzip_message;

    public static String JavadocConfigurationBlock_browse_jarorzip_path_title;

    public static String JavadocConfigurationBlock_error_notafile;

    public static String JavadocConfigurationBlock_error_invalidarchivepath;

    public static String JavadocConfigurationBlock_error_archivepathnotabsolute;

    public static String JavadocConfigurationBlock_error_dialog_title;

    public static String JavadocConfigurationBlock_browse_archive_button;

    public static String JavadocConfigurationBlock_browse_archive_path_button;

    public static String ProblemSeveritiesConfigurationBlock_adapt_null_pointer_access_settings_dialog_message;

    public static String ProblemSeveritiesConfigurationBlock_adapt_null_pointer_access_settings_dialog_title;

    public static String ProblemSeveritiesConfigurationBlock_ignore_documented_unused_exceptions;

    public static String ProblemSeveritiesConfigurationBlock_ignore_documented_unused_parameters;

    public static String ProblemSeveritiesConfigurationBlock_pb_redundant_null_annotation;

    public static String ProblemSeveritiesConfigurationBlock_pb_nonnull_parameter_annotation_dropped;

    public static String ProblemSeveritiesConfigurationBlock_pb_redundant_null_check;

    public static String ProblemSeveritiesConfigurationBlock_pb_redundant_super_interface_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_redundant_type_arguments_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_resource_leak_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_resource_not_managed_via_twr_label;

    public static String ProblemSeveritiesConfigurationBlock_include_assert_in_null_analysis;

    public static String ProblemSeveritiesConfigurationBlock_treat_optional_as_fatal;

    public static String SourceAttachmentPropertyPage_error_title;

    public static String SourceAttachmentPropertyPage_error_message;

    public static String SourceAttachmentPropertyPage_invalid_container;

    public static String SourceAttachmentPropertyPage_location_path;

    public static String SourceAttachmentPropertyPage_locationPath_none;

    public static String SourceAttachmentPropertyPage_noarchive_message;

    public static String NativeLibrariesPropertyPage_invalidElementSelection_desription;

    public static String NativeLibrariesPropertyPage_errorAttaching_title;

    public static String NativeLibrariesPropertyPage_errorAttaching_message;

    public static String NativeLibrariesPropertyPage_location_path;

    public static String NativeLibrariesPropertyPage_locationPath_none;

    public static String AppearancePreferencePage_description;

    public static String AppearancePreferencePage_methodreturntype_label;

    public static String AppearancePreferencePage_showCategory_label;

    public static String AppearancePreferencePage_methodtypeparams_label;

    public static String AppearancePreferencePage_pkgNamePatternEnable_label;

    public static String AppearancePreferencePage_pkgNamePattern_label;

    public static String AppearancePreferencePage_pkgNamePatternAbbreviateEnable_label;

    public static String AppearancePreferencePage_pkgNamePatternAbbreviate_label;

    public static String AppearancePreferencePage_showMembersInPackagesView;

    public static String AppearancePreferencePage_stackViewsVerticallyInTheJavaBrowsingPerspective;

    public static String AppearancePreferencePage_note;

    public static String AppearancePreferencePage_preferenceOnlyEffectiveForNewPerspectives;

    public static String AppearancePreferencePage_packageNameCompressionPattern_error_isEmpty;

    public static String AppearancePreferencePage_packageNameAbbreviationPattern_error_isInvalid;

    public static String AppearancePreferencePage_foldEmptyPackages;

    public static String CodeFormatterPreferencePage_title;

    public static String SourceAttachmentPropertyPage_not_supported;

    public static String SourceAttachmentPropertyPage_read_only;

    public static String TodoTaskPreferencePage_title;

    public static String TodoTaskPreferencePage_description;

    public static String TodoTaskConfigurationBlock_markers_tasks_high_priority;

    public static String TodoTaskConfigurationBlock_markers_tasks_normal_priority;

    public static String TodoTaskConfigurationBlock_markers_tasks_low_priority;

    public static String TodoTaskConfigurationBlock_markers_tasks_add_button;

    public static String TodoTaskConfigurationBlock_markers_tasks_remove_button;

    public static String TodoTaskConfigurationBlock_markers_tasks_edit_button;

    public static String TodoTaskConfigurationBlock_markers_tasks_name_column;

    public static String TodoTaskConfigurationBlock_markers_tasks_priority_column;

    public static String TodoTaskConfigurationBlock_markers_tasks_setdefault_button;

    public static String TodoTaskConfigurationBlock_casesensitive_label;

    public static String TodoTaskConfigurationBlock_needsbuild_title;

    public static String TodoTaskConfigurationBlock_tasks_default;

    public static String TodoTaskConfigurationBlock_needsfullbuild_message;

    public static String TodoTaskConfigurationBlock_needsprojectbuild_message;

    public static String TodoTaskInputDialog_new_title;

    public static String TodoTaskInputDialog_edit_title;

    public static String TodoTaskInputDialog_name_label;

    public static String TodoTaskInputDialog_priority_label;

    public static String TodoTaskInputDialog_priority_high;

    public static String TodoTaskInputDialog_priority_normal;

    public static String TodoTaskInputDialog_priority_low;

    public static String TodoTaskInputDialog_error_enterName;

    public static String TodoTaskInputDialog_error_comma;

    public static String TodoTaskInputDialog_error_entryExists;

    public static String TodoTaskInputDialog_error_noSpace;

    public static String PropertyAndPreferencePage_useworkspacesettings_change;

    public static String PropertyAndPreferencePage_showprojectspecificsettings_label;

    public static String PropertyAndPreferencePage_useprojectsettings_label;

    public static String JavaBuildPreferencePage_title;

    public static String JavaBuildConfigurationBlock_section_general;

    public static String JavaBuildConfigurationBlock_section_output_folder;

    public static String JavaBuildConfigurationBlock_section_build_path_problems;

    public static String JavaBuildConfigurationBlock_error;

    public static String JavaBuildConfigurationBlock_warning;

    public static String JavaBuildConfigurationBlock_info;

    public static String JavaBuildConfigurationBlock_ignore;

    public static String JavaBuildConfigurationBlock_needsbuild_title;

    public static String JavaBuildConfigurationBlock_needsfullbuild_message;

    public static String JavaBuildConfigurationBlock_needsprojectbuild_message;

    public static String JavaBuildConfigurationBlock_resource_filter_description;

    public static String JavaBuildConfigurationBlock_resource_filter_label;

    public static String JavaBuildConfigurationBlock_build_invalid_classpath_label;

    public static String JavaBuildConfigurationBlock_build_clean_outputfolder_label;

    public static String JavaBuildConfigurationBlock_enable_exclusion_patterns_label;

    public static String JavaBuildConfigurationBlock_enable_multiple_outputlocations_label;

    public static String JavaBuildConfigurationBlock_pb_incomplete_build_path_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_discourraged_reference_label;

    public static String JavaBuildConfigurationBlock_pb_build_path_cycles_label;

    public static String JavaBuildConfigurationBlock_pb_duplicate_resources_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_forbidden_reference_label;

    public static String JavaBuildConfigurationBlock_pb_max_per_unit_label;

    public static String JavaBuildConfigurationBlock_pb_check_prereq_binary_level_label;

    public static String JavaBuildConfigurationBlock_pb_output_overlapping_with_source_label;

    public static String JavaBuildConfigurationBlock_pb_strictly_compatible_jre_not_available_label;

    public static String JavaBuildConfigurationBlock_empty_input;

    public static String JavaBuildConfigurationBlock_invalid_input;

    public static String JavaBuildConfigurationBlock_filter_invalidsegment_error;

    public static String ProblemSeveritiesPreferencePage_title;

    public static String ProblemSeveritiesConfigurationBlock_enable_annotation_null_analysis;

    public static String ProblemSeveritiesConfigurationBlock_error;

    public static String ProblemSeveritiesConfigurationBlock_warning;

    public static String ProblemSeveritiesConfigurationBlock_info;

    public static String ProblemSeveritiesConfigurationBlock_ignore;

    public static String ProblemSeveritiesConfigurationBlock_section_potential_programming_problems;

    public static String ProblemSeveritiesConfigurationBlock_section_unnecessary_code;

    public static String ProblemSeveritiesConfigurationBlock_section_code_style;

    public static String ProblemSeveritiesConfigurationBlock_section_deprecations;

    public static String ProblemSeveritiesConfigurationBlock_section_name_shadowing;

    public static String ProblemSeveritiesConfigurationBlock_section_null_analysis;

    public static String ProblemSeveritiesConfigurationBlock_section_annotations;

    public static String ProblemSeveritiesConfigurationBlock_needsbuild_title;

    public static String ProblemSeveritiesConfigurationBlock_needsfullbuild_message;

    public static String ProblemSeveritiesConfigurationBlock_needsprojectbuild_message;

    public static String NullAnnotationsConfigurationDialog_nonnull_annotations_label;

    public static String NullAnnotationsConfigurationDialog_nonnull_annotations_description;

    public static String ProblemSeveritiesConfigurationBlock_missing_nonnull_by_default_annotation;

    public static String NullAnnotationsConfigurationDialog_nonnullbydefault_annotation_error;

    public static String NullAnnotationsConfigurationDialog_nonnullbydefault_annotations_label;

    public static String NullAnnotationsConfigurationDialog_nonnullbydefault_annotations_description;

    public static String NullAnnotationsConfigurationDialog_nonnull_annotation_error;

    public static String NullAnnotationsConfigurationDialog_null_annotations_description;

    public static String NullAnnotationsConfigurationDialog_nullable_annotation_error;

    public static String NullAnnotationsConfigurationDialog_nullable_annotations_label;

    public static String NullAnnotationsConfigurationDialog_nullable_annotations_description;

    public static String NullAnnotationsConfigurationDialog_add_button;

    public static String NullAnnotationsConfigurationDialog_notFound_info;

    public static String NullAnnotationsConfigurationDialog_primary_label;

    public static String NullAnnotationsConfigurationDialog_secondary_label;

    public static String ProblemSeveritiesConfigurationBlock_common_description;

    public static String ProblemSeveritiesConfigurationBlock_pb_unavoidable_generic_type_problems;

    public static String ProblemSeveritiesConfigurationBlock_pb_unsafe_type_op_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_raw_type_reference;

    public static String ProblemSeveritiesConfigurationBlock_pb_final_param_bound_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_inexact_vararg_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_accidential_assignement_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_local_variable_hiding_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_field_hiding_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_special_param_hiding_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_unqualified_field_access_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_finally_block_not_completing_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_undocumented_empty_block_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_unused_throwing_exception_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_unused_throwing_exception_when_overriding_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_unused_throwing_exception_ignore_unchecked_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_missing_serial_version_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_missing_static_on_method_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_missing_synchronized_on_inherited_method;

    public static String ProblemSeveritiesConfigurationBlock_pb_missing_hashcode_method;

    public static String ProblemSeveritiesConfigurationBlock_pb_overriding_pkg_dflt_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_method_naming_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_no_effect_assignment_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_incompatible_interface_method_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_indirect_access_to_static_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_hidden_catchblock_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_static_access_receiver_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_unused_imports_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_unused_local_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_unused_parameter_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_unused_exception_parameter_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_unused_type_parameter;

    public static String ProblemSeveritiesConfigurationBlock_pb_signal_param_in_overriding_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_suppress_optional_errors_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_unused_private_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_non_externalized_strings_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_dead_code;

    public static String ProblemSeveritiesConfigurationBlock_pb_deprecation_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_deprecation_in_deprecation_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_deprecation_when_overriding_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_empty_statement_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_unnecessary_type_check_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_incomplete_enum_switch_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_switch_missing_default_case_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_unnecessary_else_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_synth_access_emul_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_autoboxing_problem_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_char_array_in_concat_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_comparing_identical;

    public static String ProblemSeveritiesConfigurationBlock_pb_missing_override_annotation_for_interface_method_implementations_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_missing_override_annotation_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_missing_deprecated_annotation_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_missing_enum_case_despite_default;

    public static String ProblemSeveritiesConfigurationBlock_pb_annotation_super_interface_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_type_parameter_hiding_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_unused_label_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_unused_object_allocation_label;

    public static String JavadocProblemsPreferencePage_title;

    public static String JavadocProblemsConfigurationBlock_allStandardTags;

    public static String JavadocProblemsConfigurationBlock_public;

    public static String JavadocProblemsConfigurationBlock_protected;

    public static String JavadocProblemsConfigurationBlock_default;

    public static String JavadocProblemsConfigurationBlock_private;

    public static String JavadocProblemsConfigurationBlock_error;

    public static String JavadocProblemsConfigurationBlock_warning;

    public static String JavadocProblemsConfigurationBlock_info;

    public static String JavadocProblemsConfigurationBlock_ignore;

    public static String JavadocProblemsConfigurationBlock_note_title;

    public static String JavadocProblemsConfigurationBlock_note_message;

    public static String JavadocProblemsConfigurationBlock_needsbuild_title;

    public static String JavadocProblemsConfigurationBlock_needsfullbuild_message;

    public static String JavadocProblemsConfigurationBlock_needsprojectbuild_message;

    public static String JavadocProblemsConfigurationBlock_javadoc_description;

    public static String JavadocProblemsConfigurationBlock_pb_javadoc_support_label;

    public static String JavadocProblemsConfigurationBlock_pb_invalid_javadoc_label;

    public static String JavadocProblemsConfigurationBlock_pb_invalid_javadoc_tags_label;

    public static String JavadocProblemsConfigurationBlock_pb_invalid_javadoc_tags_visibility_label;

    public static String JavadocProblemsConfigurationBlock_pb_invalid_javadoc_tags_not_visible_ref_label;

    public static String JavadocProblemsConfigurationBlock_pb_invalid_javadoc_tags_deprecated_label;

    public static String JavadocProblemsConfigurationBlock_pb_missing_javadoc_label;

    public static String JavadocProblemsConfigurationBlock_pb_missing_javadoc_tags_visibility_label;

    public static String JavadocProblemsConfigurationBlock_pb_missing_javadoc_tags_overriding_label;

    public static String JavadocProblemsConfigurationBlock_pb_missing_comments_label;

    public static String JavadocProblemsConfigurationBlock_pb_missing_comments_method_type_parameter_label;

    public static String JavadocProblemsConfigurationBlock_pb_missing_comments_visibility_label;

    public static String JavadocProblemsConfigurationBlock_pb_missing_comments_overriding_label;

    public static String JavadocProblemsConfigurationBlock_pb_missing_tag_description;

    public static String JavadocProblemsConfigurationBlock_returnTag;

    public static String CompliancePreferencePage_title;

    public static String ComplianceConfigurationBlock_error;

    public static String ComplianceConfigurationBlock_warning;

    public static String ComplianceConfigurationBlock_info;

    public static String ComplianceConfigurationBlock_ignore;

    public static String ComplianceConfigurationBlock_version11;

    public static String ComplianceConfigurationBlock_version12;

    public static String ComplianceConfigurationBlock_version13;

    public static String ComplianceConfigurationBlock_version14;

    public static String ComplianceConfigurationBlock_version15;

    public static String ComplianceConfigurationBlock_needsbuild_title;

    public static String ComplianceConfigurationBlock_needsfullbuild_message;

    public static String ComplianceConfigurationBlock_needsprojectbuild_message;

    public static String ComplianceConfigurationBlock_variable_attr_label;

    public static String ComplianceConfigurationBlock_line_number_attr_label;

    public static String ComplianceConfigurationBlock_source_file_attr_label;

    public static String ComplianceConfigurationBlock_codegen_unused_local_label;

    public static String ComplianceConfigurationBlock_cldc11_requires_source13_compliance_se14;

    public static String ComplianceConfigurationBlock_codegen_inline_jsr_bytecode_label;

    public static String ComplianceConfigurationBlock_codegen_method_parameters_attr;

    public static String ComplianceConfigurationBlock_compiler_compliance_label;

    public static String ComplianceConfigurationBlock_default_settings_label;

    public static String ComplianceConfigurationBlock_source_compatibility_label;

    public static String ComplianceConfigurationBlock_codegen_targetplatform_label;

    public static String ComplianceConfigurationBlock_pb_assert_as_identifier_label;

    public static String ComplianceConfigurationBlock_pb_enum_as_identifier_label;

    public static String ComplianceConfigurationBlock_compliance_follows_EE_label;

    public static String ComplianceConfigurationBlock_compliance_follows_EE_with_EE_label;

    public static String ComplianceConfigurationBlock_compliance_group_label;

    public static String ComplianceConfigurationBlock_classfiles_group_label;

    public static String CodeStylePreferencePage_title;

    public static String CodeTemplatesPreferencePage_title;

    public static String NameConventionConfigurationBlock_field_label;

    public static String NameConventionConfigurationBlock_static_label;

    public static String NameConventionConfigurationBlock_arg_label;

    public static String NameConventionConfigurationBlock_local_label;

    public static String NameConventionConfigurationBlock_keywordthis_label;

    public static String NameConventionConfigurationBlock_isforbooleangetters_label;

    public static String NameConventionConfigurationBlock_dialog_prefix;

    public static String NameConventionConfigurationBlock_dialog_suffix;

    public static String NameConventionConfigurationBlock_exceptionname_label;

    public static String NameConventionConfigurationBlock_error_emptyprefix;

    public static String NameConventionConfigurationBlock_error_emptysuffix;

    public static String NameConventionConfigurationBlock_error_invalidprefix;

    public static String NameConventionConfigurationBlock_error_invalidsuffix;

    public static String NameConventionConfigurationBlock_list_label;

    public static String NameConventionConfigurationBlock_list_edit_button;

    public static String NameConventionConfigurationBlock_list_name_column;

    public static String NameConventionConfigurationBlock_list_prefix_column;

    public static String NameConventionConfigurationBlock_list_suffix_column;

    public static String NameConventionConfigurationBlock_field_dialog_title;

    public static String NameConventionConfigurationBlock_field_dialog_message;

    public static String NameConventionConfigurationBlock_static_dialog_title;

    public static String NameConventionConfigurationBlock_static_dialog_message;

    public static String NameConventionConfigurationBlock_static_final_dialog_message;

    public static String NameConventionConfigurationBlock_static_final_dialog_title;

    public static String NameConventionConfigurationBlock_static_final_label;

    public static String NameConventionConfigurationBlock_arg_dialog_title;

    public static String NameConventionConfigurationBlock_arg_dialog_message;

    public static String NameConventionConfigurationBlock_local_dialog_title;

    public static String NameConventionConfigurationBlock_local_dialog_message;

    public static String NameConventionConfigurationBlock_override_link_label;

    public static String MembersOrderPreferencePage_category_button_up;

    public static String MembersOrderPreferencePage_category_button_down;

    public static String MembersOrderPreferencePage_visibility_button_up;

    public static String MembersOrderPreferencePage_visibility_button_down;

    public static String MembersOrderPreferencePage_label_description;

    public static String MembersOrderPreferencePage_fields_label;

    public static String MembersOrderPreferencePage_constructors_label;

    public static String MembersOrderPreferencePage_methods_label;

    public static String MembersOrderPreferencePage_staticfields_label;

    public static String MembersOrderPreferencePage_staticmethods_label;

    public static String MembersOrderPreferencePage_initialisers_label;

    public static String MembersOrderPreferencePage_staticinitialisers_label;

    public static String MembersOrderPreferencePage_types_label;

    public static String MembersOrderPreferencePage_public_label;

    public static String MembersOrderPreferencePage_private_label;

    public static String MembersOrderPreferencePage_protected_label;

    public static String MembersOrderPreferencePage_default_label;

    public static String MembersOrderPreferencePage_usevisibilitysort_label;

    public static String CodeTemplateBlock_templates_comment_node;

    public static String CodeTemplateBlock_templates_code_node;

    public static String CodeTemplateBlock_catchblock_label;

    public static String CodeTemplateBlock_methodstub_label;

    public static String CodeTemplateBlock_constructorstub_label;

    public static String CodeTemplateBlock_newtype_label;

    public static String CodeTemplateBlock_classbody_label;

    public static String CodeTemplateBlock_interfacebody_label;

    public static String CodeTemplateBlock_enumbody_label;

    public static String CodeTemplateBlock_annotationbody_label;

    public static String CodeTemplateBlock_typecomment_label;

    public static String CodeTemplateBlock_fieldcomment_label;

    public static String CodeTemplateBlock_filecomment_label;

    public static String CodeTemplateBlock_methodcomment_label;

    public static String CodeTemplateBlock_overridecomment_label;

    public static String CodeTemplateBlock_delegatecomment_label;

    public static String CodeTemplateBlock_constructorcomment_label;

    public static String CodeTemplateBlock_gettercomment_label;

    public static String CodeTemplateBlock_settercomment_label;

    public static String CodeTemplateBlock_getterstub_label;

    public static String CodeTemplateBlock_setterstub_label;

    public static String CodeTemplateBlock_templates_edit_button;

    public static String CodeTemplateBlock_templates_import_button;

    public static String CodeTemplateBlock_templates_export_button;

    public static String CodeTemplateBlock_templates_exportall_button;

    public static String CodeTemplateBlock_createcomment_label;

    public static String CodeTemplateBlock_templates_label;

    public static String CodeTemplateBlock_preview;

    public static String CodeTemplateBlock_import_title;

    public static String CodeTemplateBlock_import_extension;

    public static String CodeTemplateBlock_export_title_singular;

    public static String CodeTemplateBlock_export_title_plural;

    public static String CodeTemplateBlock_export_filename;

    public static String CodeTemplateBlock_export_extension;

    public static String CodeTemplateBlock_export_exists_title;

    public static String CodeTemplateBlock_export_exists_message;

    public static String CodeTemplateBlock_error_read_title;

    public static String CodeTemplateBlock_error_read_message;

    public static String CodeTemplateBlock_error_parse_message;

    public static String CodeTemplateBlock_error_write_title;

    public static String CodeTemplateBlock_error_write_message;

    public static String CodeTemplateBlock_export_error_title;

    public static String CodeTemplateBlock_export_error_hidden;

    public static String CodeTemplateBlock_export_error_canNotWrite;

    public static String TypeFilterPreferencePage_description;

    public static String TypeFilterPreferencePage_list_label;

    public static String TypeFilterPreferencePage_add_button;

    public static String TypeFilterPreferencePage_addpackage_button;

    public static String TypeFilterPreferencePage_edit_button;

    public static String TypeFilterPreferencePage_remove_button;

    public static String TypeFilterPreferencePage_selectall_button;

    public static String TypeFilterPreferencePage_deselectall_button;

    public static String TypeFilterPreferencePage_choosepackage_label;

    public static String TypeFilterPreferencePage_choosepackage_description;

    public static String TypeFilterInputDialog_title;

    public static String TypeFilterInputDialog_message;

    public static String TypeFilterInputDialog_browse_button;

    public static String TypeFilterInputDialog_error_enterName;

    public static String TypeFilterInputDialog_error_invalidName;

    public static String TypeFilterInputDialog_error_entryExists;

    public static String TypeFilterInputDialog_choosepackage_label;

    public static String TypeFilterInputDialog_choosepackage_description;

    public static String SpellingPreferencePage_empty_threshold;

    public static String SpellingPreferencePage_invalid_threshold;

    public static String SpellingPreferencePage_ignore_digits_label;

    public static String SpellingPreferencePage_ignore_mixed_label;

    public static String SpellingPreferencePage_ignore_sentence_label;

    public static String SpellingPreferencePage_ignore_upper_label;

    public static String SpellingPreferencePage_ignore_url_label;

    public static String SpellingPreferencePage_ignore_non_letters_label;

    public static String SpellingPreferencePage_ignore_single_letters_label;

    public static String SpellingPreferencePage_ignore_java_strings_label;

    public static String SpellingPreferencePage_ignore_ampersand_in_properties_label;

    public static String SpellingPreferencePage_proposals_threshold;

    public static String SpellingPreferencePage_problems_threshold;

    public static String SpellingPreferencePage_dictionary_label;

    public static String SpellingPreferencePage_encoding_label;

    public static String SpellingPreferencePage_workspace_dictionary_label;

    public static String SpellingPreferencePage_browse_label;

    public static String SpellingPreferencePage_dictionary_error;

    public static String SpellingPreferencePage_dictionary_none;

    public static String SpellingPreferencePage_locale_error;

    public static String SpellingPreferencePage_filedialog_title;

    public static String SpellingPreferencePage_enable_contentassist_label;

    public static String SpellingPreferencePage_group_user;

    public static String SpellingPreferencePage_group_dictionary;

    public static String SpellingPreferencePage_group_dictionaries;

    public static String SpellingPreferencePage_group_advanced;

    public static String SpellingPreferencePage_user_dictionary_description;

    public static String SpellingPreferencePage_variables;

    public static String UserLibraryPreferencePage_title;

    public static String UserLibraryPreferencePage_description;

    public static String UserLibraryPreferencePage_libraries_label;

    public static String UserLibraryPreferencePage_libraries_new_button;

    public static String UserLibraryPreferencePage_libraries_edit_button;

    public static String UserLibraryPreferencePage_libraries_addjar_button;

    public static String UserLibraryPreferencePage_libraries_addexternaljar_button;

    public static String UserLibraryPreferencePage_libraries_remove_button;

    public static String UserLibraryPreferencePage_libraries_load_button;

    public static String UserLibraryPreferencePage_libraries_save_button;

    public static String UserLibraryPreferencePage_operation;

    public static String UserLibraryPreferencePage_operation_error;

    public static String UserLibraryPreferencePage_config_error_title;

    public static String UserLibraryPreferencePage_config_error_message;

    public static String UserLibraryPreferencePage_browsejar_new_title;

    public static String UserLibraryPreferencePage_browsejar_edit_title;

    public static String UserLibraryPreferencePage_LibraryNameDialog_new_title;

    public static String UserLibraryPreferencePage_LibraryNameDialog_edit_title;

    public static String UserLibraryPreferencePage_LibraryNameDialog_name_label;

    public static String UserLibraryPreferencePage_LibraryNameDialog_issystem_label;

    public static String UserLibraryPreferencePage_LibraryNameDialog_name_error_entername;

    public static String UserLibraryPreferencePage_LibraryNameDialog_name_error_exists;

    public static String UserLibraryPreferencePage_LoadSaveDialog_save_title;

    public static String UserLibraryPreferencePage_LoadSaveDialog_save_ok_title;

    public static String UserLibraryPreferencePage_LoadSaveDialog_load_title;

    public static String UserLibraryPreferencePage_LoadSaveDialog_location_label;

    public static String UserLibraryPreferencePage_LoadSaveDialog_location_button;

    public static String UserLibraryPreferencePage_LoadSaveDialog_list_selectall_button;

    public static String UserLibraryPreferencePage_LoadSaveDialog_load_replace_message;

    public static String UserLibraryPreferencePage_LoadSaveDialog_load_replace_multiple_message;

    public static String UserLibraryPreferencePage_LoadSaveDialog_list_deselectall_button;

    public static String UserLibraryPreferencePage_LoadSaveDialog_list_save_label;

    public static String UserLibraryPreferencePage_LoadSaveDialog_list_load_label;

    public static String UserLibraryPreferencePage_LoadSaveDialog_filedialog_save_title;

    public static String UserLibraryPreferencePage_LoadSaveDialog_filedialog_load_title;

    public static String UserLibraryPreferencePage_LoadSaveDialog_error_empty;

    public static String UserLibraryPreferencePage_LoadSaveDialog_error_invalidfile;

    public static String UserLibraryPreferencePage_LoadSaveDialog_overwrite_title;

    public static String UserLibraryPreferencePage_LoadSaveDialog_save_ok_message;

    public static String UserLibraryPreferencePage_LoadSaveDialog_overwrite_message;

    public static String UserLibraryPreferencePage_LoadSaveDialog_save_errordialog_title;

    public static String UserLibraryPreferencePage_LoadSaveDialog_save_errordialog_message;

    public static String UserLibraryPreferencePage_LoadSaveDialog_location_error_save_enterlocation;

    public static String UserLibraryPreferencePage_LoadSaveDialog_location_error_save_invalid;

    public static String UserLibraryPreferencePage_LoadSaveDialog_list_error_save_nothingselected;

    public static String UserLibraryPreferencePage_LoadSaveDialog_location_error_load_enterlocation;

    public static String UserLibraryPreferencePage_LoadSaveDialog_location_error_load_invalid;

    public static String UserLibraryPreferencePage_LoadSaveDialog_list_error_load_nothingselected;

    public static String UserLibraryPreferencePage_LoadSaveDialog_load_badformat;

    public static String UserLibraryPreferencePage_LoadSaveDialog_load_replace_title;

    public static String EditTemplateDialog_error_noname;

    public static String EditTemplateDialog_error_invalidName;

    public static String EditTemplateDialog_error_invalidPattern;

    public static String EditTemplateDialog_title_new;

    public static String EditTemplateDialog_title_edit;

    public static String EditTemplateDialog_name;

    public static String EditTemplateDialog_description;

    public static String EditTemplateDialog_context;

    public static String EditTemplateDialog_pattern;

    public static String EditTemplateDialog_insert_variable;

    public static String EditTemplateDialog_undo;

    public static String EditTemplateDialog_redo;

    public static String EditTemplateDialog_cut;

    public static String EditTemplateDialog_copy;

    public static String EditTemplateDialog_paste;

    public static String EditTemplateDialog_select_all;

    public static String EditTemplateDialog_content_assist;

    public static String JavaEditorPreferencePage_folding_title;

    public static String FoldingConfigurationBlock_enable;

    public static String FoldingConfigurationBlock_combo_caption;

    public static String FoldingConfigurationBlock_info_no_preferences;

    public static String FoldingConfigurationBlock_error_not_exist;

    public static String FoldingConfigurationBlock_warning_providerNotFound_resetToDefault;

    public static String PropertiesFileEditorPreferencePage_key;

    public static String PropertiesFileEditorPreferencePage_value;

    public static String PropertiesFileEditorPreferencePage_assignment;

    public static String PropertiesFileEditorPreferencePage_argument;

    public static String PropertiesFileEditorPreferencePage_comment;

    public static String PropertiesFileEditorPreferencePage_foreground;

    public static String PropertiesFileEditorPreferencePage_color;

    public static String PropertiesFileEditorPreferencePage_bold;

    public static String PropertiesFileEditorPreferencePage_italic;

    public static String PropertiesFileEditorPreferencePage_strikethrough;

    public static String PropertiesFileEditorPreferencePage_underline;

    public static String PropertiesFileEditorPreferencePage_preview;

    public static String PropertiesFileEditorPreferencePage_link;

    public static String SmartTypingConfigurationBlock_autoclose_title;

    public static String SmartTypingConfigurationBlock_automove_title;

    public static String SmartTypingConfigurationBlock_tabs_message_tab_text;

    public static String SmartTypingConfigurationBlock_tabs_message_others_text;

    public static String SmartTypingConfigurationBlock_tabs_message_tooltip;

    public static String SmartTypingConfigurationBlock_tabs_message_spaces;

    public static String SmartTypingConfigurationBlock_tabs_message_tabs;

    public static String SmartTypingConfigurationBlock_tabs_message_tabsAndSpaces;

    public static String SmartTypingConfigurationBlock_pasting_title;

    public static String SmartTypingConfigurationBlock_strings_title;

    public static String SmartTypingConfigurationBlock_indentation_title;

    public static String CodeAssistConfigurationBlock_typeFilters_link;

    public static String CodeAssistConfigurationBlock_sortingSection_title;

    public static String CodeAssistConfigurationBlock_autoactivationSection_title;

    public static String CodeAssistConfigurationBlock_insertionSection_title;

    public static String JavaEditorPreferencePage_coloring_category_java;

    public static String JavaEditorPreferencePage_coloring_category_javadoc;

    public static String JavaEditorPreferencePage_coloring_category_comments;

    public static String ProjectSelectionDialog_title;

    public static String ProjectSelectionDialog_desciption;

    public static String ProjectSelectionDialog_filter;

    public static String JavaCompilerPropertyPage_ignore_optional_problems_label;

    public static String JavaCompilerPropertyPage_invalid_element_selection;

    static {
        NLS.initializeMessages(BUNDLE_NAME, PreferencesMessages.class);
    }

    public static String NameConventionConfigurationBlock_use_override_annotation_label;

    public static String NullAnnotationsConfigurationDialog_error_message;

    public static String NullAnnotationsConfigurationDialog_error_title;

    public static String NullAnnotationsConfigurationDialog_restore_defaults;

    public static String NullAnnotationsConfigurationDialog_title;

    public static String NullAnnotationsConfigurationDialog_use_default_annotations_for_null;

    public static String ProblemSeveritiesConfigurationBlock_pb_unhandled_surpresswarning_tokens;

    public static String ProblemSeveritiesConfigurationBlock_pb_enable_surpresswarning_annotation;

    public static String SmartTypingConfigurationBlock_annotationReporting_link;

    public static String TypeFilterPreferencePage_restricted_link;

    public static String TypeFilterPreferencePage_hideDiscouraged_label;

    public static String TypeFilterPreferencePage_hideForbidden_label;

    public static String UserLibraryPreferencePage_UserLibraryPreferencePage_libraries_up_button;

    public static String UserLibraryPreferencePage_UserLibraryPreferencePage_libraries_down_button;

    public static String EditTemplateDialog_autoinsert;

    public static String ComplianceConfigurationBlock_jrecompliance_info;

    public static String ComplianceConfigurationBlock_jrecompliance_info_project;

    public static String ProblemSeveritiesConfigurationBlock_section_generics;

    public static String JavaBasePreferencePage_dialogs;

    public static String JavaBasePreferencePage_do_not_hide_description;

    public static String JavaBasePreferencePage_do_not_hide_button;

    public static String JavaBasePreferencePage_do_not_hide_dialog_title;

    public static String JavaBasePreferencePage_do_not_hide_dialog_message;

    public static String CodeAssistConfigurationBlock_matchCamelCase_label;

    public static String CodeAssistConfigurationBlock_matchSubstring_label;

    public static String ComplianceConfigurationBlock_version16;

    public static String ComplianceConfigurationBlock_version17;

    public static String ComplianceConfigurationBlock_version18;

    public static String ComplianceConfigurationBlock_versionCLDC11;

    public static String ComplianceConfigurationBlock_src_greater_compliance;

    public static String ComplianceConfigurationBlock_classfile_greater_compliance;

    public static String ComplianceConfigurationBlock_classfile_greater_source;

    public static String ProblemSeveritiesConfigurationBlock_enable_syntactic_null_analysis_for_fields;

    public static String ProblemSeveritiesConfigurationBlock_inherit_null_annotations;

    public static String ProblemSeveritiesConfigurationBlock_pb_parameter_assignment;

    public static String ProblemSeveritiesConfigurationBlock_pb_null_reference;

    public static String ProblemSeveritiesConfigurationBlock_pb_null_spec_violation;

    public static String ProblemSeveritiesConfigurationBlock_pb_null_unchecked_conversion;

    public static String ProblemSeveritiesConfigurationBlock_pb_pessimistic_analysis_for_free_type_variables;

    public static String ProblemSeveritiesConfigurationBlock_pb_nonnull_typevar_maybe_legacy;

    public static String ProblemSeveritiesConfigurationBlock_pb_potential_null_reference;

    public static String ProblemSeveritiesConfigurationBlock_pb_null_annotation_inference_conflict;

    public static String ProblemSeveritiesConfigurationBlock_pb_potential_resource_leak_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_potentially_missing_static_on_method_label;

    public static String ProblemSeveritiesConfigurationBlock_pb_fall_through_case;

    public static String ProblemSeveritiesConfigurationBlock_unused_suppresswarnings_token;

    public static String CodeAssistConfigurationBlock_hideDeprecated_label;

    public static String CodeAssistStaticMembersConfigurationBlock_description;

    public static String CodeAssistStaticMembersConfigurationBlock_newType_button;

    public static String CodeAssistStaticMembersConfigurationBlock_newMember_button;

    public static String CodeAssistStaticMembersConfigurationBlock_edit_button;

    public static String CodeAssistStaticMembersConfigurationBlock_remove_button;

    public static String FavoriteStaticMemberInputDialog_member_new_title;

    public static String FavoriteStaticMemberInputDialog_member_edit_title;

    public static String FavoriteStaticMemberInputDialog_member_labelText;

    public static String FavoriteStaticMemberInputDialog_type_new_title;

    public static String FavoriteStaticMemberInputDialog_type_edit_title;

    public static String FavoriteStaticMemberInputDialog_type_labelText;

    public static String FavoriteStaticMemberInputDialog_browse_button;

    public static String FavoriteStaticMemberInputDialog_ChooseTypeDialog_title;

    public static String FavoriteStaticMemberInputDialog_ChooseTypeDialog_description;

    public static String FavoriteStaticMemberInputDialog_ChooseTypeDialog_error_message;

    public static String FavoriteStaticMemberInputDialog_error_invalidMemberName;

    public static String FavoriteStaticMemberInputDialog_error_invalidTypeName;

    public static String FavoriteStaticMemberInputDialog_error_entryExists;

    public static String OptionsConfigurationBlock_Disabled;

    public static String OptionsConfigurationBlock_Enabled;

    public static String OptionsConfigurationBlock_NoOptionMatchesTheFilter;

    public static String OptionsConfigurationBlock_Off;

    public static String OptionsConfigurationBlock_On;

    public static String OptionsConfigurationBlock_RefreshFilter;

    public static String OptionsConfigurationBlock_TypeFilterText;

    public static String OptionsConfigurationBlock_IgnoreOptionalProblemsLink;

    public static String FilterTextControl_Clear;

    public static String FilterTextControl_ClearFilterField;
}
