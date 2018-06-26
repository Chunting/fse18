/*******************************************************************************
 *  Copyright (c) 2000, 2012 IBM Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.pde.internal.core.text.plugin;

import java.io.PrintWriter;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.core.IModel;
import org.eclipse.pde.core.plugin.IPluginAttribute;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.internal.core.ischema.ISchema;
import org.eclipse.pde.internal.core.ischema.ISchemaElement;
import org.eclipse.pde.internal.core.text.DocumentTextNode;
import org.eclipse.pde.internal.core.text.IDocumentAttributeNode;
import org.eclipse.pde.internal.core.text.IDocumentElementNode;
import org.eclipse.pde.internal.core.text.IDocumentTextNode;

public class PluginElementNode extends PluginParentNode implements IPluginElement {

    private static final long serialVersionUID = 1L;

    private transient ISchemaElement elementInfo;

    @Override
    public IPluginElement createCopy() {
        return null;
    }

    @Override
    public IPluginAttribute getAttribute(String name) {
        return (IPluginAttribute) getNodeAttributesMap().get(name);
    }

    @Override
    public IPluginAttribute[] getAttributes() {
        return getNodeAttributesMap().values().toArray(new IPluginAttribute[getNodeAttributesMap().size()]);
    }

    @Override
    public int getAttributeCount() {
        return getNodeAttributesMap().size();
    }

    @Override
    public String getText() {
        IDocumentTextNode node = getTextNode();
        //$NON-NLS-1$
        return node == null ? "" : node.getText();
    }

    @Override
    public void setAttribute(String name, String value) throws CoreException {
        setXMLAttribute(name, value);
    }

    @Override
    public void setText(String text) throws CoreException {
        IDocumentTextNode node = getTextNode();
        String oldText = node == null ? null : node.getText();
        if (node == null) {
            node = new DocumentTextNode();
            node.setEnclosingElement(this);
            addTextNode(node);
        }
        node.setText(text.trim());
        firePropertyChanged(node, P_TEXT, oldText, text);
    }

    @Override
    public String write(boolean indent) {
        String sep = getLineDelimiter();
        StringBuffer buffer = new StringBuffer();
        if (indent)
            buffer.append(getIndent());
        IDocumentElementNode[] children = getChildNodes();
        String text = getText();
        buffer.append(writeShallow(false));
        if (getAttributeCount() > 0 || children.length > 0 || text.length() > 0)
            buffer.append(sep);
        if (children.length > 0 || text.length() > 0) {
            if (text.length() > 0) {
                buffer.append(getIndent());
                //$NON-NLS-1$
                buffer.append(//$NON-NLS-1$
                "   ");
                buffer.append(text);
                buffer.append(sep);
            }
            for (int i = 0; i < children.length; i++) {
                children[i].setLineIndent(getLineIndent() + 3);
                buffer.append(children[i].write(true));
                buffer.append(sep);
            }
        }
        if (getAttributeCount() > 0 || children.length > 0 || text.length() > 0)
            buffer.append(getIndent());
        //$NON-NLS-1$ //$NON-NLS-2$
        buffer.append("</" + getXMLTagName() + ">");
        return buffer.toString();
    }

    @Override
    public String writeShallow(boolean terminate) {
        String sep = getLineDelimiter();
        //$NON-NLS-1$
        StringBuffer buffer = new StringBuffer("<" + getXMLTagName());
        IDocumentAttributeNode[] attrs = getNodeAttributes();
        for (int i = 0; i < attrs.length; i++) {
            if (attrs[i].getAttributeValue().length() > 0)
                //$NON-NLS-1$
                buffer.append(//$NON-NLS-1$
                sep + getIndent() + "      " + attrs[i].write());
        }
        if (terminate)
            //$NON-NLS-1$
            buffer.append("/");
        //$NON-NLS-1$
        buffer.append(">");
        return buffer.toString();
    }

    @Override
    public String getName() {
        return getXMLTagName();
    }

    @Override
    public void setName(String name) throws CoreException {
        setXMLTagName(name);
    }

    @Override
    public Object getElementInfo() {
        if (elementInfo == null) {
            IDocumentElementNode node = getParentNode();
            for (; ; ) {
                if (node == null || node instanceof IPluginExtension)
                    break;
                node = node.getParentNode();
            }
            if (node != null) {
                IPluginExtension extension = (IPluginExtension) node;
                ISchema schema = (ISchema) extension.getSchema();
                if (schema != null) {
                    elementInfo = schema.findElement(getName());
                }
            }
        }
        return elementInfo;
    }

    @Override
    public void reconnect(IDocumentElementNode parent, IModel model) {
        super.reconnect(parent, model);
        // Transient Field:  Element Info
        // Not necessary to reconnect schema.
        // getElementInfo will retrieve the schema on demand if it is null
        elementInfo = null;
    }

    @Override
    public void write(String indent, PrintWriter writer) {
        // Used for text transfers for copy, cut, paste operations
        writer.write(write(true));
    }
}
