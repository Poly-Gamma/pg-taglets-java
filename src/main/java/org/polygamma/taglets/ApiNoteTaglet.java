/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.polygamma.taglets;

/**
 * {@code apiNote} taglet.
 *
 * <p>The {@code apiNote} tag lets the developer communicate useful API details to the end-user.
 *
 * @since 1.0
 */
public class ApiNoteTaglet extends MultiNoteTaglet {

	/**
	 * Construct new {@code apiNote} taglet.
	 *
	 * @since 1.0
	 */
	public ApiNoteTaglet() {
		super("apiNote", "API Notes");
	}
}
