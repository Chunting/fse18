/*******************************************************************************
* Copyright (c) 2009 IBM, and others. 
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*   IBM Corporation - initial API and implementation
******************************************************************************/
package org.eclipse.ecf.provider.filetransfer.events.socket;

import java.net.Socket;
import org.eclipse.ecf.filetransfer.events.socket.ISocketConnectedEvent;
import org.eclipse.ecf.filetransfer.events.socket.ISocketEventSource;

public class SocketConnectedEvent extends AbstractSocketEvent implements ISocketConnectedEvent {

    public  SocketConnectedEvent(ISocketEventSource source, Socket factorySocket, Socket wrappedSocket) {
        super(source, factorySocket, wrappedSocket);
    }

    protected String getEventName() {
        //$NON-NLS-1$
        return "SocketConnectedEvent";
    }

    public void setSocket(Socket socket) {
        super.setSocket(socket);
    }
}
