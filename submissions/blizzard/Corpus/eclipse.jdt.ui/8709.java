/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.ui.text.folding;

import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * Contributors to the
 * <code>org.eclipse.jdt.ui.foldingStructureProvider</code> extension
 * point must specify an implementation of this interface which will create and
 * maintain {@link org.eclipse.jface.text.source.projection.ProjectionAnnotation} objects
 * that define folded regions in the {@link org.eclipse.jface.text.source.projection.ProjectionViewer}.
 * <p>
 * Clients may implement this interface.
 * </p>
 * <p>
 * In order to provide backward compatibility for clients of <code>IJavaFoldingStructureProvider</code>, extension
 * interfaces are used to provide a means of evolution. The following extension interfaces exist:
 * <ul>
 * <li>{@link org.eclipse.jdt.ui.text.folding.IJavaFoldingStructureProviderExtension} since version 3.2 introducing
 *		the following functions:
 *			<ul>
 *            <li>collapsing of comments and members</li>
 *            <li>expanding and collapsing of certain java elements</li>
 *			</ul>
 * </li>
 * </ul>
 * </p>
 *
 * @since 3.0
 */
public interface IJavaFoldingStructureProvider {

    /**
	 * Installs this structure provider on the given editor and viewer.
	 * Implementations should listen to the projection events generated by
	 * <code>viewer</code> and enable / disable generation of projection
	 * structure accordingly.
	 *
	 * @param editor the editor that this provider works on
	 * @param viewer the projection viewer that displays the annotations created
	 *        by this structure provider
	 */
    public abstract void install(ITextEditor editor, ProjectionViewer viewer);

    /**
	 * Uninstalls this structure provider. Any references to editors or viewers
	 * should be cleared.
	 */
    public abstract void uninstall();

    /**
	 * (Re-)initializes the structure provided by the receiver.
	 */
    public abstract void initialize();
}
