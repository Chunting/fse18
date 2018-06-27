/*******************************************************************************
 * Copyright (c) 2008, 2015 Code 9 Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Code 9 Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.pde.internal.ds.ui.editor;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.ui.dialogs.ITypeInfoFilterExtension;
import org.eclipse.jdt.ui.dialogs.ITypeInfoRequestor;
import org.eclipse.jdt.ui.dialogs.TypeSelectionExtension;
import org.eclipse.pde.internal.ds.core.IDSModel;
import org.eclipse.pde.internal.ds.core.IDSProvide;
import org.eclipse.pde.internal.ds.core.IDSService;

public class DSTypeSelectionExtension extends TypeSelectionExtension {

    private IDSModel fModel;

    class TypeInfoFilterExtension implements ITypeInfoFilterExtension {

        List<String> fProvides = new ArrayList<String>(3);

        public  TypeInfoFilterExtension(IDSProvide[] provides) {
            for (IDSProvide provide : provides) {
                fProvides.add(provide.getInterface());
            }
        }

        @Override
        public boolean select(ITypeInfoRequestor typeInfoRequestor) {
            StringBuffer buffer = new StringBuffer(typeInfoRequestor.getPackageName());
            //$NON-NLS-1$
            buffer.append(".");
            buffer.append(typeInfoRequestor.getTypeName());
            return !fProvides.contains(buffer.toString());
        }
    }

    public  DSTypeSelectionExtension() {
    }

    public  DSTypeSelectionExtension(IDSModel model) {
        fModel = model;
    }

    @Override
    public ITypeInfoFilterExtension getFilterExtension() {
        IDSService service = fModel.getDSComponent().getService();
        if (service != null)
            return new TypeInfoFilterExtension(service.getProvidedServices());
        return null;
    }
}
