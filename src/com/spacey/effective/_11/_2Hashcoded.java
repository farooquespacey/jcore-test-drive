package com.spacey.effective._11;

import java.util.HashMap;
import java.util.Map;

public class _2Hashcoded {

	public static void main(String[] args) {
		Map<PhoneNumber, String> m = new HashMap<>();
		m.put(new PhoneNumber(707, 867, 5309), "Jenny");
		System.out.println(m.get(new PhoneNumber(707, 867, 5309)));
	}

	// Class with a typical hashcode method
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
			System.out.println("Equals Check...");
			if (o == this)
				return true;
			if (!(o instanceof PhoneNumber))
				return false;
			PhoneNumber pn = (PhoneNumber) o;
			return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode;
		}

		// Typical hashCode method
		@Override
		public int hashCode() {
			System.out.println("Hashcode Check...");
			int result = Short.hashCode(areaCode);
			result = 31 * result + Short.hashCode(prefix);
			result = 31 * result + Short.hashCode(lineNum);
			return result;
		}
	}
}
