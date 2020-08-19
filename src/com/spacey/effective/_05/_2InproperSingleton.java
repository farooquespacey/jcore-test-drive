package com.spacey.effective._05;

import java.util.List;

public class _2InproperSingleton {

	public static void main(String[] args) {
		// You could try to have SpellChecker support multiple dictionaries by making
		// the dictionary field nonfinal and adding a method to change the dictionary in
		// an existing spell checker, but this would be awkward, error-prone, and
		// unworkable in a concurrent setting.

		// Static utility classes and singletons are inappropriate for classes whose
		// behavior is parameterized by an underlying resource.
	}

	// Inappropriate use of static utility - inflexible & untestable!
	// Assumes single dictionary
	public static class SpellChecker {
		private final Lexicon dictionary = new Lexicon();

		private SpellChecker() {
		} // Noninstantiable

		public static SpellChecker INSTANCE = new SpellChecker();

		public boolean isValid(String word) {
			// ...
			return false;
		}

		public List<String> suggestions(String typo) {
			// ...
			return null;
		}

		static class Lexicon {

		}

	}

}
