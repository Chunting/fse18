/*******************************************************************************
 * Copyright (c) 2005, 2006 Erkki Lindpere and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Erkki Lindpere - initial API and implementation
 *******************************************************************************/
package org.eclipse.ecf.internal.provider.phpbb;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import org.eclipse.ecf.bulletinboard.IBBObject;
import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.IDCreateException;
import org.eclipse.ecf.core.identity.Namespace;
import org.eclipse.ecf.internal.bulletinboard.commons.IBBObjectFactory;
import org.eclipse.ecf.internal.provider.phpbb.identity.CategoryID;
import org.eclipse.ecf.internal.provider.phpbb.identity.PHPBBNamespace;

public class CategoryFactory implements IBBObjectFactory {

    public ID createBBObjectId(Namespace namespace, String stringValue) throws IDCreateException {
        try {
            return new CategoryID((PHPBBNamespace) namespace, new URI(stringValue));
        } catch (URISyntaxException e) {
            throw new IDCreateException(e);
        }
    }

    public ID createBBObjectId(Namespace namespace, URL baseURL, String longValue) throws IDCreateException {
        try {
            return new CategoryID((PHPBBNamespace) namespace, baseURL, Long.parseLong(longValue));
        } catch (URISyntaxException e) {
            throw new IDCreateException(e);
        }
    }

    public IBBObject createBBObject(ID id, String name, Map<String, Object> parameters) {
        return new Category((CategoryID) id, name);
    }
}
