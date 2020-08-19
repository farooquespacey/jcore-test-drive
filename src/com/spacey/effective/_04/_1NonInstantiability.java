package com.spacey.effective._04;

public class _1NonInstantiability {

	public static void main(String[] args) {
		// UtilityClass utilityClass = new UtilityClass();
	}

	// Noninstantiable utility class
	public static class UtilityClass {
		// Suppress default constructor for noninstantiability
		private UtilityClass() {
			throw new AssertionError();
		}
		// Remainder omitted
	}

}
