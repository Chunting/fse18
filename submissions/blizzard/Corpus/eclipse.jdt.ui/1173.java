/*******************************************************************************
 * Copyright (c) 2000, 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.internal.ui.filters;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

/**
 * Filters non-java projects
 */
public class NonJavaProjectsFilter extends ViewerFilter {

    /*
	 * @see ViewerFilter
	 */
    @Override
    public boolean select(Viewer viewer, Object parent, Object element) {
        if (element instanceof IJavaProject)
            return true;
        if (element instanceof IProject) {
            IProject project = (IProject) element;
            try {
                return project.hasNature(JavaCore.NATURE_ID);
            } catch (CoreException e) {
                return true;
            }
        }
        return true;
    }
}
