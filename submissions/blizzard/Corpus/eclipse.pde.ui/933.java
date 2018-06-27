/*******************************************************************************
 * Copyright (c) 2008, 2016 Code 9 Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Code 9 Corporation - initial API and implementation
 *     Bartosz Michalik <bartosz.michalik@gmail.com> - bug 240737
 *     Benjamin Cabe <benjamin.cabe@anyware-tech.com> - bug 265931
 *******************************************************************************/
package org.eclipse.pde.internal.core.product;

import java.io.PrintWriter;
import org.eclipse.pde.internal.core.iproduct.IPluginConfiguration;
import org.eclipse.pde.internal.core.iproduct.IProductModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class PluginConfiguration extends ProductObject implements IPluginConfiguration {

    private static final long serialVersionUID = -3549668957352554876L;

    private boolean fAutoStart;

    private int fStartLevel;

    private String fId;

    /**
	 * Only for parsing usage
	 * @param model
	 */
     PluginConfiguration(IProductModel model) {
        super(model);
    }

    @Override
    public void parse(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            //$NON-NLS-1$
            fId = element.getAttribute("id");
            fAutoStart = Boolean.valueOf(element.getAttribute(P_AUTO_START)).booleanValue();
            fStartLevel = Integer.parseInt(element.getAttribute(P_START_LEVEL));
        }
    }

    @Override
    public void write(String indent, PrintWriter writer) {
        //$NON-NLS-1$ //$NON-NLS-2$
        writer.print(indent + "<plugin id=\"" + fId + "\"");
        //$NON-NLS-1$//$NON-NLS-2$
        writer.print(" autoStart=\"" + fAutoStart + "\"");
        //$NON-NLS-1$ //$NON-NLS-2$
        writer.print(" startLevel=\"" + fStartLevel + "\"");
        //$NON-NLS-1$
        writer.println(" />");
    }

    @Override
    public String getId() {
        return fId;
    }

    @Override
    public int getStartLevel() {
        return fStartLevel;
    }

    @Override
    public boolean isAutoStart() {
        return fAutoStart;
    }

    @Override
    public void setAutoStart(boolean autostart) {
        boolean oldValue = fAutoStart;
        fAutoStart = autostart;
        if (isEditable() && oldValue != fAutoStart)
            firePropertyChanged(P_AUTO_START, Boolean.valueOf(oldValue), Boolean.valueOf(fAutoStart));
    }

    @Override
    public void setStartLevel(int startLevel) {
        int oldValue = fStartLevel;
        fStartLevel = startLevel;
        if (isEditable() && oldValue != fStartLevel)
            firePropertyChanged(P_START_LEVEL, Integer.valueOf(oldValue), Integer.valueOf(fStartLevel));
    }

    @Override
    public void setId(String id) {
        fId = id;
    }
}
