/*******************************************************************************
 * Copyright (c) 2009 EclipseSource and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.ecf.osgi.services.discovery;

import org.osgi.service.discovery.ServicePublication;

public interface ECFServicePublication extends ServicePublication {

    //$NON-NLS-1$
    public static final String SERVICE_TYPE = "osgiservices";

    //$NON-NLS-1$
    public static final String DEFAULT_SERVICE_NAME_PREFIX = "service ";

    //$NON-NLS-1$
    public static final String SERVICE_NAME_PROP = "ecf.sp.svcname";

    //$NON-NLS-1$
    public static final String NAMING_AUTHORITY_PROP = "ecf.sp.namingauth";

    //$NON-NLS-1$
    public static final String SCOPE_PROP = "ecf.sp.scope";

    //$NON-NLS-1$
    public static final String SERVICE_PROTOCOL_PROP = "ecf.sp.protocol";

    //$NON-NLS-1$
    public static final String PROP_KEY_ENDPOINT_CONTAINERID = "ecf.sp.cid";

    //$NON-NLS-1$
    public static final String PROP_KEY_ENDPOINT_CONTAINERID_NAMESPACE = "ecf.sp.cns";
}
