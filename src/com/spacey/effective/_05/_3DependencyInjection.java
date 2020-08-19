package com.spacey.effective._05;

import java.util.List;
import java.util.Objects;

public class _3DependencyInjection {

	public static void main(String[] args) {
		// This is one form of dependency injection: the dictionary is a dependency of
		// the spell checker and is injected into the spell checker when it is created.
	}

	// Dependency injection provides flexibility and testability
	// Dependency injection is equally applicable to constructors, static factories
	// (Item 1), and builders (Item 2).
	static class SpellChecker {
		private final Lexicon dictionary;

		public SpellChecker(Lexicon dictionary) {
			this.dictionary = Objects.requireNonNull(dictionary);
		}

		public boolean isValid(String word) {
			return false;
		}

		public List<String> suggestions(String typo) {
			return null;
		}

		class Lexicon {
		}
	}

}
