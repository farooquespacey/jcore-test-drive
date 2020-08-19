package com.spacey.effective._10;

import java.util.ArrayList;
import java.util.Objects;

public class _1SymmetryViolation {

	public static void main(String[] args) {
		CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
		String str = "polish";
		// clear violation of symmetry
		System.out.println(cis.equals(str)); // true
		System.out.println(str.equals(cis)); // false
		// +++++++++++++++++++++++++++

		ArrayList<CaseInsensitiveString> list = new ArrayList<>();
		list.add(cis);
		// problem
		System.out.println(list.contains(str));
	}

	// Broken - violates symmetry!
	static final class CaseInsensitiveString {
		private final String s;

		public CaseInsensitiveString(String s) {
			this.s = Objects.requireNonNull(s);
		}

		// Broken - violates symmetry!
		@Override
		public boolean equals(Object o) {
			if (o instanceof CaseInsensitiveString) {
				System.out.println("CIS instance");
				return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
			}
			if (o instanceof String) { // One-way interoperability!
				System.out.println("String instance");
				return s.equalsIgnoreCase((String) o);
			}
			return false;
		}
		// Remainder omitted
	}

}
