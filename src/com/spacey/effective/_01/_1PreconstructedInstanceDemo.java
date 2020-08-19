package com.spacey.effective._01;

public class _1PreconstructedInstanceDemo {

	public static void main(String[] args) {
		Boolean b = BooleanKind.valueOf(false);
		System.out.println(b);
	}

}

/**
 * Example of "preconstructed instances"
 * 
 * @author Farooque
 *
 */
class BooleanKind {
	/**
	 * The {@code Boolean} object corresponding to the primitive value {@code true}.
	 */
	public static final Boolean TRUE = new Boolean(true);

	/**
	 * The {@code Boolean} object corresponding to the primitive value
	 * {@code false}.
	 */
	public static final Boolean FALSE = new Boolean(false);

	public static Boolean valueOf(boolean b) {
		return (b ? TRUE : FALSE);
	}
}


