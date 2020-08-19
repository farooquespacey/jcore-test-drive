package com.spacey.effective._14;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

public class _3MyOwnStreamKind {

	public static void main(String[] args) {
		// PhoneNumber pn = new PhoneNumber(15, 11, 12, "one");
		// pn.callTo(new PhoneNumber(13, 11, 12, "two"));

		PhoneNumber pnC = new PhoneNumber(15, 11, 12, "one");
		pnC.compareTo(new PhoneNumber(13, 11, 12, "two"));
	}

	@FunctionalInterface
	static interface Caller<T> {

		public static <T, U extends Callable<? super U>> Caller<T> calling(
				Function<? super T, ? extends U> keyExtractor) {
			Objects.requireNonNull(keyExtractor);
			return (Caller<T> & Serializable) (c1, c2) -> keyExtractor.apply(c1).callTo(keyExtractor.apply(c2));
		}

		public boolean call(T t, T t2);
	}

	@FunctionalInterface
	static interface Callable<T> {
		public boolean callTo(T t);
	}

	static final class PhoneNumber implements Comparable<PhoneNumber>, Callable<PhoneNumber> {
		// Comparable with comparator construction methods
		private static final Comparator<PhoneNumber> COMPARATOR = Comparator
				.comparingInt((PhoneNumber pn) -> pn.areaCode).thenComparingInt(pn -> pn.prefix)
				.thenComparingInt(pn -> pn.lineNum).thenComparing(pn -> pn.name);
		private static final Caller<PhoneNumber> CALLER = Caller.calling(pn -> pn); // invalid (bcz,it will recursively
																					// call pn's compareTo..)
		private final short areaCode, prefix, lineNum;
		private final String name;

		public PhoneNumber(int areaCode, int prefix, int lineNum, String name) {
			this.areaCode = rangeCheck(areaCode, 999, "area code");
			this.prefix = rangeCheck(prefix, 999, "prefix");
			this.lineNum = rangeCheck(lineNum, 9999, "line num");
			this.name = name;
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
			return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode && pn.name.equals(name);
		}

		@Override
		public int hashCode() {
			int result = Short.hashCode(areaCode);
			result = 31 * result + Short.hashCode(prefix);
			result = 31 * result + Short.hashCode(lineNum);
			result = 31 * result + name.hashCode();
			return result;
		}

		@Override
		public String toString() {
			return String.format("%s: %03d-%03d-%04d", name, areaCode, prefix, lineNum);
		}

		// Multiple-field Comparable with primitive fields
		public int compareTo(PhoneNumber pn) {
			System.out.println(this + " : " + pn + " -> ");
			return COMPARATOR.compare(this, pn);
		}

		@Override
		public boolean callTo(PhoneNumber t) {
			System.out.print("Making a call from " + this + " to " + t);
			boolean status = CALLER.call(this, t);
			System.out.println(" -> " + status);
			return status;
		}

	}
}
