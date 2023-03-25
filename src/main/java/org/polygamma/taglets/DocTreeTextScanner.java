/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.polygamma.taglets;

import java.util.Objects;

import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.TextTree;
import com.sun.source.util.DocTreeScanner;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A scanner which extracts text from a {@link DocTree}.
 *
 * @since 1.0
 */
public class DocTreeTextScanner extends DocTreeScanner<@Nullable String, @Nullable Void> {

	/**
	 * Construct new text scanner.
	 *
	 * @since 1.0
	 */
	public DocTreeTextScanner() {
	}

	@Override
	public @Nullable String reduce(@Nullable String a, @Nullable String b) {
		if (a != null)
			a = a.trim();
		if (b != null)
			b = b.trim();
		a = Objects.requireNonNullElse(a, "");
		b = Objects.requireNonNullElse(b, "");
		return a.isEmpty() ? b : b.isEmpty() ? a : (a + ' ' + b);
	}

	@Override
	public @Nullable String visitText(TextTree text, @Nullable Void ignored) {
		@Nullable String value = text.getBody();

		return value == null || value.isBlank() ? null : value.trim();
	}
}
