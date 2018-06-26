/****************************************************************************
 * Copyright (c) 2007 Composent, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Composent, Inc. - initial API and implementation
 *****************************************************************************/
package org.eclipse.ecf.internal.presence.collab.ui.view;

import java.util.HashMap;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.IViewCategory;
import org.eclipse.ui.views.IViewDescriptor;
import org.eclipse.ui.views.IViewRegistry;

public class ShowViewDialogTreeContentProvider implements ITreeContentProvider {

    private HashMap parents = new HashMap();

    public Object[] getChildren(Object element) {
        if (element instanceof IViewRegistry)
            return ((IViewRegistry) element).getCategories();
        else if (element instanceof IViewCategory) {
            final IViewDescriptor[] children = ((IViewCategory) element).getViews();
            for (int i = 0; i < children.length; ++i) parents.put(children[i], element);
            return children;
        } else
            return new Object[0];
    }

    public Object getParent(Object element) {
        if (element instanceof IViewCategory)
            return PlatformUI.getWorkbench().getViewRegistry();
        else if (element instanceof IViewDescriptor)
            return parents.get(element);
        else
            return null;
    }

    public boolean hasChildren(Object element) {
        if (element instanceof IViewRegistry || element instanceof IViewCategory)
            return true;
        return false;
    }

    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    public void dispose() {
        parents = null;
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        parents.clear();
    }
}
