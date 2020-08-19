package com.spacey.effective._10;

import java.util.Objects;

public class _2Symmetry {

	public static void main(String[] args) {
		CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
		String str = "polish";
		// retains symmetric property
		System.out.println(cis.equals(str)); // false
		System.out.println(str.equals(cis)); // false
		// +++++++++++++++++++++++++++
	}

	// Broken - violates symmetry!
	static final class CaseInsensitiveString {
		private final String s;

		public CaseInsensitiveString(String s) {
			this.s = Objects.requireNonNull(s);
		}

		@Override
		public boolean equals(Object o) {
			return o instanceof CaseInsensitiveString && ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
		}
		// Remainder omitted
	}

}
