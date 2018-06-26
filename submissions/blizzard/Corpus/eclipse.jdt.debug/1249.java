/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.debug.tests.breakpoints;

import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.jdt.debug.core.IJavaLineBreakpoint;
import org.eclipse.jdt.debug.core.IJavaMethodBreakpoint;

/**
 * Tests the Java debugger's 'toggle breakpoints target'.
 */
public class TestToggleBreakpointsTarget8 extends AbstractToggleBreakpointsTarget {

    public  TestToggleBreakpointsTarget8(String name) {
        super(name);
    // TODO Auto-generated constructor stub
    }

    /**
	 * Tests that qualified names get created for method breakpoints in default method of Java 8 interface
	 * files.
	 * 
	 * @throws Exception
	 */
    public void testInterfaceDefaultMethodBreakpoint() throws Exception {
        Listener listener = new Listener();
        IBreakpointManager manager = getBreakpointManager();
        manager.addBreakpointListener(listener);
        try {
            Path path = new Path("java8/EvalTestIntf18.java");
            // 0 based offset in document line numbers
            toggleBreakpoint(path, 20);
            IBreakpoint added = listener.getAdded();
            assertTrue("Should be a method breakpoint", added instanceof IJavaMethodBreakpoint);
            IJavaMethodBreakpoint breakpoint = (IJavaMethodBreakpoint) added;
            assertEquals("Wrong type name", "Intf18", breakpoint.getTypeName());
            assertEquals("Wrong method name", "test2", breakpoint.getMethodName());
            assertEquals("Wrong signature", "()I", breakpoint.getMethodSignature());
        } finally {
            manager.removeBreakpointListener(listener);
            removeAllBreakpoints();
        }
    }

    /**
	 * Tests that qualified names get created for method breakpoints in Static method of Java 8 interface
	 * files.
	 * 
	 * @throws Exception
	 */
    public void testInterfaceStaticMethodBreakpoint() throws Exception {
        Listener listener = new Listener();
        IBreakpointManager manager = getBreakpointManager();
        manager.addBreakpointListener(listener);
        try {
            Path path = new Path("java8/EvalTestIntf18.java");
            // 0 based offset in document line numbers
            toggleBreakpoint(path, 25);
            IBreakpoint added = listener.getAdded();
            assertTrue("Should be a method breakpoint", added instanceof IJavaMethodBreakpoint);
            IJavaMethodBreakpoint breakpoint = (IJavaMethodBreakpoint) added;
            assertEquals("Wrong type name", "Intf18", breakpoint.getTypeName());
            assertEquals("Wrong method name", "test3", breakpoint.getMethodName());
            assertEquals("Wrong signature", "()V", breakpoint.getMethodSignature());
        } finally {
            manager.removeBreakpointListener(listener);
            removeAllBreakpoints();
        }
    }

    /**
	 * Tests that qualified names does get created for method breakpoints in unimplemented method of Java 8 interface
	 * files.
	 * 
	 * @throws Exception
	 */
    public void testInterfaceUnimplementedMethodBreakpoint() throws Exception {
        Listener listener = new Listener();
        IBreakpointManager manager = getBreakpointManager();
        manager.addBreakpointListener(listener);
        try {
            Path path = new Path("java8/EvalTestIntf18.java");
            // 0 based offset in document line numbers
            toggleBreakpoint(path, 19);
            assertTrue(listener.isEmpty());
        } finally {
            manager.removeBreakpointListener(listener);
            removeAllBreakpoints();
        }
    }

    /**
	 * Tests that qualified names get created for line breakpoints in Interface implemented method
	 * files.
	 * 
	 * @throws Exception
	 */
    public void testInterfaceLineBreakpoint() throws Exception {
        Listener listener = new Listener();
        IBreakpointManager manager = getBreakpointManager();
        manager.addBreakpointListener(listener);
        try {
            Path path = new Path("java8/EvalTestIntf18.java");
            // 0 based offset in document line numbers
            toggleBreakpoint(path, 21);
            IBreakpoint added = listener.getAdded();
            assertTrue("Should be a line breakpoint", added instanceof IJavaLineBreakpoint);
            IJavaLineBreakpoint breakpoint = (IJavaLineBreakpoint) added;
            assertEquals("Wrong line number", 22, breakpoint.getLineNumber());
            assertEquals("Wrong type name", "Intf18", breakpoint.getTypeName());
        } finally {
            manager.removeBreakpointListener(listener);
            removeAllBreakpoints();
        }
    }
}
