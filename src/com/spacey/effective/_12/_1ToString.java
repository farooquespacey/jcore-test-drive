package com.spacey.effective._12;

public class _1ToString {

	public static void main(String[] args) {
		PhoneNumber pn = new PhoneNumber(707, 867, 5309);
		System.out.println(pn);

	}

	// Class with a typical toString method
	static final class PhoneNumber {
		private final short areaCode, prefix, lineNum;

		public PhoneNumber(int areaCode, int prefix, int lineNum) {
			this.areaCode = rangeCheck(areaCode, 999, "area code");
			this.prefix = rangeCheck(prefix, 999, "prefix");
			this.lineNum = rangeCheck(lineNum, 9999, "line num");
		}

		private static short rangeCheck(int val, int max, String arg) {
			if (val < 0 || val > max)
				throw new IllegalArgumentException(arg + ": " + val);
			return (short) val;
		}

		@Override
		public boolean equals(Object o) {
			if (o == this)
				return true;
			if (!(o instanceof PhoneNumber))
				return false;
			PhoneNumber pn = (PhoneNumber) o;
			return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode;
		}

		@Override
		public int hashCode() {
			int result = Short.hashCode(areaCode);
			result = 31 * result + Short.hashCode(prefix);
			result = 31 * result + Short.hashCode(lineNum);
			return result;
		}

		/**
		 * Returns the string representation of this phone number. The string consists
		 * of twelve characters whose format is "XXX-YYY-ZZZZ", where XXX is the area
		 * code, YYY is the prefix, and ZZZZ is the line number. Each of the capital
		 * letters represents a single decimal digit.
		 *
		 * If any of the three parts of this phone number is too small to fill up its
		 * field, the field is padded with leading zeros. For example, if the value of
		 * the line number is 123, the last four characters of the string representation
		 * will be "0123".
		 */
		@Override
		public String toString() {
			return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum);
		}

		// If you decide not to specify a format, the documentation comment should read
		// something like this:
		/**
		 * Returns a brief description of this potion. The exact details of the
		 * representation are unspecified and subject to change, but the following may
		 * be regarded as typical:
		 *
		 * "[Potion #9: type=love, smell=turpentine, look=india ink]"
		 */
		// @Override public String toString() { ... }
	}
}
