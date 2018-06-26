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
package org.eclipse.pde.api.tools.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Classes, interfaces, annotations, enums, methods and fields tagged with this
 * annotation are declaring they are not to be referenced at all by clients. For
 * example a method tagged with this annotation should not be called by clients.
 * If this annotation appears anywhere other than classes, interfaces,
 * annotations, enums, methods and fields it will be ignored.
 *
 * @since 1.0
 */
@Documented
@Target(value = { ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD })
@Retention(RetentionPolicy.SOURCE)
public @interface NoReference {
}
