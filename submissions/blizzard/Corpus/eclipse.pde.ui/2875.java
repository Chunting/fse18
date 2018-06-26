/*******************************************************************************
 * Copyright (c) 2008, 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Lars Vogel <Lars.Vogel@vogella.com> - Bug 475241
 *******************************************************************************/
package org.eclipse.pde.internal.ds.core;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

public class Activator extends Plugin {

    // The plug-in ID
    //$NON-NLS-1$
    public static final String PLUGIN_ID = "org.eclipse.pde.ds.core";

    // The content type ID
    //$NON-NLS-1$
    public static final String CONTENT_TYPE_ID = "org.eclipse.pde.ds.core.content-type";

    // The shared instance
    private static Activator plugin;

    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
    public static Activator getDefault() {
        return plugin;
    }

    public static void log(IStatus status) {
        if (status != null) {
            ResourcesPlugin.getPlugin().getLog().log(status);
        }
    }

    public static void log(Throwable e) {
        if (e instanceof InvocationTargetException)
            e = ((InvocationTargetException) e).getTargetException();
        IStatus status = null;
        if (e instanceof CoreException) {
            status = ((CoreException) e).getStatus();
        } else if (e.getMessage() != null) {
            status = new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, e.getMessage(), e);
        }
        log(status);
    }
}
