/*******************************************************************************
 * Copyright (c) 2000, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jesper Kamstrup Linnet (eclipse@kamstrup-linnet.dk) - initial API and implementation
 *          (report 36180: Callers/Callees view)
 *   Michael Fraenkel (fraenkel@us.ibm.com) - patch
 *          (report 60714: Call Hierarchy: display search scope in view title)
 *******************************************************************************/
package org.eclipse.jdt.internal.ui.callhierarchy;

import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.PlatformUI;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.internal.ui.IJavaHelpContextIds;
import org.eclipse.jdt.internal.ui.search.JavaSearchScopeFactory;

class SearchScopeWorkingSetAction extends SearchScopeAction {

    private IWorkingSet[] fWorkingSets;

    public  SearchScopeWorkingSetAction(SearchScopeActionGroup group, IWorkingSet[] workingSets, String name) {
        super(group, name);
        setToolTipText(CallHierarchyMessages.SearchScopeActionGroup_workingset_tooltip);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.CALL_HIERARCHY_SEARCH_SCOPE_ACTION);
        this.fWorkingSets = workingSets;
    }

    @Override
    public IJavaSearchScope getSearchScope(int includeMask) {
        return JavaSearchScopeFactory.getInstance().createJavaSearchScope(fWorkingSets, includeMask);
    }

    /**
	 * @return returns the working sets
	 */
    public IWorkingSet[] getWorkingSets() {
        return fWorkingSets;
    }

    @Override
    public int getSearchScopeType() {
        return SearchScopeActionGroup.SEARCH_SCOPE_TYPE_WORKING_SET;
    }

    @Override
    public String getFullDescription(int includeMask) {
        return JavaSearchScopeFactory.getInstance().getWorkingSetScopeDescription(fWorkingSets, includeMask);
    }
}
