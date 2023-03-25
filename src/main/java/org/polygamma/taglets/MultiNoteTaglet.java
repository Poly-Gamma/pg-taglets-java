// SPDX-License-Identifier: Apache-2.0

package org.polygamma.taglets;

import javax.lang.model.element.Element;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import com.sun.source.doctree.DocTree;
import jdk.javadoc.doclet.Taglet;

/**
 * Taglet supporting multiple occurrences of the same tag.
 *
 * <p>This merges multiple occurrences of the same tag into an unordered HTML list. If a tag
 * occurs only once, then it is output as-is. The tag descriptor is output within a {@code dt}
 * element, while the tag note (unordered list or otherwise) is output within a single {@code dd}
 * element. Implementations may provide {@linkplain #getDetails(List, Element)  details} for a tag,
 * which is output as small text preceeding the tag notes.
 *
 * @since 1.0
 */
public class MultiNoteTaglet implements Taglet {

	private final String name;
	private final String description;
	private final Set<Location> locations;

	/**
	 * Construct new multi-note taglet.
	 *
	 * @param name Tag name.
	 * @param description Tag description.
	 * @param locations Locations tag may occur in.
	 * @since 1.0
	 */
	public MultiNoteTaglet(String name, String description, Location... locations) {
		this.name = name;
		this.description = description;
		this.locations = Set.of(locations);
	}

	/**
	 * Construct new multi-note taglet for all {@linkplain jdk.javadoc.doclet.Taglet.Location
	 * locations}.
	 *
	 * @param name Tag name.
	 * @param description Tag description.
	 * @since 1.0
	 */
	public MultiNoteTaglet(String name, String description) {
		this(name, description, Location.values());
	}

	@Override
	public Set<Location> getAllowedLocations() {
		return this.locations;
	}

	@Override
	public boolean isInlineTag() {
		return false;
	}

	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Detail string for a list of notes attached to an element.
	 *
	 * <p>The string returned, if any, is wrapped inside of a {@code small} element, preceeding the
	 * notes.
	 *
	 * @param notes Notes attached to element.
	 * @param element Element notes are attached to.
	 * @return Optional containing detail string.
	 * @since 1.0
	 */
	protected Optional<String> getDetails(List<String> notes, Element element) {
		return Optional.empty();
	}

	@Override
	public String toString(List<? extends DocTree> tags, Element element) {
		DocTreeTextScanner scanner = new DocTreeTextScanner();
		List<String> notes =
			tags.stream().map(tag -> tag.accept(scanner, null))
				.filter(Objects::nonNull).toList();

		if (notes.isEmpty())
			return "";

		StringBuilder html = new StringBuilder();

		html.append("<dt>").append(this.description).append(":</dt>");
		this.getDetails(notes, element).ifPresent(
			details -> html.append("<dd><small>").append(details).append("</small></dd>"));

		if (notes.size() == 1) {
			html.append("<dd>").append(notes.get(0)).append("</dd>");
		} else {
			html.append("<dd><ul style='padding-left: 0; list-style-position: inside'>");
			notes.forEach(note -> html.append("<li>").append(note).append("</li>"));
			html.append("</ul></dd>");
		}
		return html.toString();
	}
}
