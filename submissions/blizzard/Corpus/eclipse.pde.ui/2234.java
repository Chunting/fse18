/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package a.b.c;

import org.eclipse.pde.api.tools.annotations.NoExtend;

/**
 * Tests invalid @NoExtend annotations on nested inner annotations
 */
@NoExtend
public @interface test3 {

    /**
	 */
    @NoExtend
    @interface inner {
    }

    @interface inner1 {

        /**
		 */
        @NoExtend
        @interface inner2 {
        }
    }

    @interface inner2 {
    }
}

@interface outer {

    @NoExtend
    @interface inner {
    }
}
