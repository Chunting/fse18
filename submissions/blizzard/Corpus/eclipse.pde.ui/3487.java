/*******************************************************************************
 *  Copyright (c) 2005, 2015 IBM Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.pde.internal.ui.parts;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.dialogs.FilteredList;
import org.eclipse.ui.dialogs.FilteredList.FilterMatcher;
import org.eclipse.ui.internal.misc.StringMatcher;

public class ConditionalListSelectionDialog extends ElementListSelectionDialog {

    private String fButtonText;

    private Object[] fElements;

    private Object[] fConditionalElements;

    public  ConditionalListSelectionDialog(Shell parent, ILabelProvider renderer, String buttonText) {
        super(parent, renderer);
        fButtonText = buttonText;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite comp = (Composite) super.createDialogArea(parent);
        int size = ((fElements != null) ? fElements.length : 0) + ((fConditionalElements != null) ? fConditionalElements.length : 0);
        final Object[] allElements = new Object[size];
        int conditionalStart = 0;
        if (fElements != null) {
            System.arraycopy(fElements, 0, allElements, 0, fElements.length);
            conditionalStart = fElements.length;
        }
        if (fConditionalElements != null)
            System.arraycopy(fConditionalElements, 0, allElements, conditionalStart, fConditionalElements.length);
        final Button button = new Button(comp, SWT.CHECK);
        Assert.isNotNull(fButtonText);
        button.setText(fButtonText);
        button.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (button.getSelection())
                    setListElements(allElements);
                else
                    setListElements(fElements);
            }
        });
        return comp;
    }

    @Override
    public void setElements(Object[] elements) {
        super.setElements(elements);
        fElements = elements;
    }

    public void setConditionalElements(Object[] elements) {
        fConditionalElements = elements;
    }

    @Override
    protected FilteredList createFilteredList(Composite parent) {
        final FilteredList list = super.createFilteredList(parent);
        list.setFilterMatcher(new FilterMatcher() {

            private StringMatcher fMatcher;

            @Override
            public void setFilter(String pattern, boolean ignoreCase, boolean ignoreWildCards) {
                fMatcher = new StringMatcher('*' + pattern + '*', ignoreCase, ignoreWildCards);
            }

            @Override
            public boolean match(Object element) {
                return fMatcher.match(list.getLabelProvider().getText(element));
            }
        });
        return list;
    }
}
