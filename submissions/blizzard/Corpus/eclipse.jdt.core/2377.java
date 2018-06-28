/*******************************************************************************
 * Copyright (c) 2007 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package targets.model.pa;

import java.lang.annotation.Inherited;

@Inherited
public @interface AnnoZ {

    String annoZString();

    int annoZint() default 17;
}