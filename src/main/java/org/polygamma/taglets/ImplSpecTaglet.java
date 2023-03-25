/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.polygamma.taglets;

/**
 * {@code implSpec} taglet.
 *
 * <p>The {@code implSpec} tag lets the developer communicate useful implementation requirements to
 * the end-user.
 *
 * @since 1.0
 */
public class ImplSpecTaglet extends MultiNoteTaglet {

	/**
	 * Construct new {@code implNote} taglet.
	 *
	 * @since 1.0
	 */
	public ImplSpecTaglet() {
		super("implSpec", "Implementation Specification");
	}
}
