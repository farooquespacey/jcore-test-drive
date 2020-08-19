package com.spacey.effective._01;

import java.util.HashMap;
import java.util.Map;

public class _2CachedInstanceDemo {

	public static void main(String[] args) {
		Boolean b = BooleanKind2.valueOf(false);
		System.out.println(b);
	}

}

/**
 * Example of "preconstructed instances"
 * 
 * @author Farooque
 *
 */
class BooleanKind2 {
	public static final Map<String, Boolean> cachedMap = new HashMap<>();
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


