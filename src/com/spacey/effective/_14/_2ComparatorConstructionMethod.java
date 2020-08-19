package com.spacey.effective._14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class _2ComparatorConstructionMethod {

	public static void main(String[] args) {
		List<PhoneNumber> listOfP = new ArrayList<>();
		listOfP.add(new PhoneNumber(12, 12, 12));
		listOfP.add(new PhoneNumber(12, 12, 11));
		listOfP.add(new PhoneNumber(13, 12, 11));
		listOfP.add(new PhoneNumber(13, 11, 12));
		listOfP.add(new PhoneNumber(15, 11, 12));
		listOfP.add(new PhoneNumber(14, 12, 11));
		Collections.sort(listOfP);
		listOfP.stream().filter(e -> e.lineNum == 11).forEach(System.out::println);
	}

	static final class PhoneNumber implements Comparable<PhoneNumber> {
		// Comparable with comparator construction methods
		private static final Comparator<PhoneNumber> COMPARATOR = Comparator.comparingInt((PhoneNumber pn) -> {
			System.out.println("comparing ac " + pn.areaCode);
			return pn.areaCode;
		}).thenComparingInt(pn -> {
			System.out.println("comparing pre " + pn.prefix);
			return pn.prefix;
		}).thenComparingInt(pn -> {
			System.out.println("comparing line " + pn.lineNum);
			return pn.lineNum;
		});
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

		@Override
		public String toString() {
			return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum);
		}

		// Multiple-field Comparable with primitive fields
		public int compareTo(PhoneNumber pn) {
			System.out.println(this + " : "  + pn + " -> ");
			return COMPARATOR.compare(this, pn);
		}

	}
}
