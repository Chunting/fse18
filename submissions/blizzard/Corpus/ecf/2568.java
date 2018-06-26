/****************************************************************************
 * Copyright (c) 2007, 2010 Composent, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Composent, Inc. - initial API and implementation
 *    Henrich Kraemer - bug 295030, Update Manager doesn't work with SOCKS proxy  
 *****************************************************************************/
package org.eclipse.ecf.provider.filetransfer.util;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import org.eclipse.ecf.core.util.Proxy;

/**
 * Helper class for setting the JRE proxy value (http and socks).
 */
public class JREProxyHelper {

    //$NON-NLS-1$
    private static final String SOCKS_PROXY_PORT_SYSTEM_PROPERTY = "socksProxyPort";

    //$NON-NLS-1$
    private static final String SOCKS_PROXY_HOST_SYSTEM_PROPERTY = "socksProxyHost";

    //$NON-NLS-1$
    private static final String HTTP_PROXY_PORT_SYSTEM_PROPERTY = "http.proxyPort";

    //$NON-NLS-1$
    private static final String HTTP_PROXY_HOST_SYSTEM_PROPERTY = "http.proxyHost";

    private String proxyHostProperty;

    private String proxyPortProperty;

    private String oldHost;

    private String oldPort;

    public void setupProxy(final Proxy proxy2) {
        Properties systemProperties = System.getProperties();
        proxyHostProperty = (proxy2.getType().equals(Proxy.Type.HTTP)) ? HTTP_PROXY_HOST_SYSTEM_PROPERTY : SOCKS_PROXY_HOST_SYSTEM_PROPERTY;
        proxyPortProperty = (proxy2.getType().equals(Proxy.Type.HTTP)) ? HTTP_PROXY_PORT_SYSTEM_PROPERTY : SOCKS_PROXY_PORT_SYSTEM_PROPERTY;
        oldHost = systemProperties.getProperty(proxyHostProperty);
        if (oldHost != null) {
            oldPort = systemProperties.getProperty(proxyPortProperty);
        }
        systemProperties.setProperty(proxyHostProperty, proxy2.getAddress().getHostName());
        int proxyPort = proxy2.getAddress().getPort();
        if (proxyPort != -1)
            //$NON-NLS-1$
            systemProperties.setProperty(proxyPortProperty, proxyPort + "");
        final String username = proxy2.getUsername();
        boolean setAuthenticator = false;
        if (//$NON-NLS-1$
        username != null && !username.equals("")) {
            //$NON-NLS-1$
            final String password = (proxy2.getPassword() == null) ? "" : proxy2.getPassword();
            if (proxy2.hasCredentials()) {
                Authenticator.setDefault(new Authenticator() {

                    /* (non-Javadoc)
					 * @see java.net.Authenticator#getPasswordAuthentication()
					 */
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password.toCharArray());
                    }
                });
                setAuthenticator = true;
            }
        }
        if (!setAuthenticator) {
            Authenticator.setDefault(new Authenticator() {

                /* (non-Javadoc)
				 * @see java.net.Authenticator#getPasswordAuthentication()
				 */
                protected PasswordAuthentication getPasswordAuthentication() {
                    return null;
                }
            });
        }
    }

    public void dispose() {
        // reset old values
        if (oldHost != null) {
            System.getProperties().setProperty(proxyHostProperty, oldHost);
            oldHost = null;
            if (oldPort != null) {
                System.getProperties().setProperty(proxyPortProperty, oldPort);
                oldPort = null;
            }
        }
    }
}
