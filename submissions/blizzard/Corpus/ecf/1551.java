/****************************************************************************
* Copyright (c) 2004 Composent, Inc. and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Composent, Inc. - initial API and implementation
*****************************************************************************/
package org.eclipse.ecf.provider.comm.tcp;

import java.io.Serializable;

public class PingMessage implements Serializable {

    private static final long serialVersionUID = 3258407318374004536L;

    /**
	 * @since 4.3
	 */
    public  PingMessage() {
    //
    }

    public String toString() {
        //$NON-NLS-1$
        StringBuffer buf = new StringBuffer("PingMessage");
        return buf.toString();
    }
}
