/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.polygamma.taglets;

import javax.lang.model.element.Element;
import java.util.List;
import java.util.Optional;

/**
 * {@code assert} taglet.
 *
 * <p>This taglet handles {@code assert} tags, which may occur once or more within the documentation
 * of a method, constructor, or type. The assert tag lets the user know under which assumptions
 * the tagged element is expected to operate, and where the use of the tagged element may result in
 * undefined behaviour. The tag renders an additional note before the assertions stating behaviour
 * is undefined when any of the assertions do not hold true.
 *
 * @since 1.0
 */
public class AssertTaglet extends MultiNoteTaglet {

	/**
	 * Construct a new {@code assert} taglet.
	 *
	 * @since 1.0
	 */
	public AssertTaglet() {
		super("assert", "Assertions", Location.METHOD, Location.CONSTRUCTOR, Location.TYPE);
	}

	@Override
	protected Optional<String> getDetails(List<String> notes, Element element) {
		return Optional.of(
			(notes.size() > 1 ? "If any of the assertions below do " :
			 "If the assertion below does ") +
			"not hold true, behaviour <i>may</i> be <em>undefined</em>.");
	}
}
