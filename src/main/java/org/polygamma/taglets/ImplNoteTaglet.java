/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.polygamma.taglets;

/**
 * {@code implNote} taglet.
 *
 * <p>The {@code implNote} tag lets the developer communicate useful implementation details to the
 * end-user.
 *
 * @since 1.0
 */
public class ImplNoteTaglet extends MultiNoteTaglet {

	/**
	 * Construct new {@code implNote} taglet.
	 *
	 * @since 1.0
	 */
	public ImplNoteTaglet() {
		super("implNote", "Implementation Notes");
	}
}
