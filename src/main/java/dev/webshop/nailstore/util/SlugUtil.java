package dev.webshop.nailstore.util;

import java.util.Locale;

public class SlugUtil {
	public static String slugify(String text) {
		if (text == null) return null;

		text = text.toLowerCase(Locale.ENGLISH);

		String[][] accents = {
				{"á", "a"},
				{"é", "e"},
				{"í", "i"},
				{"ó", "o"},
				{"ö", "o"},
				{"ő", "o"},
				{"ú", "u"},
				{"ü", "u"},
				{"ű", "u"}
		};

		for (String[] pair : accents) {
			text = text.replace(pair[0], pair[1]);
		}

		return text.replaceAll("[^a-z0-9\\s-]", "")
				.trim()
				.replaceAll("\\s+", "-");
	}
}
