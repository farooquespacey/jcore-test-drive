package com.spacey.effective._25;

import java.util.HashSet;
import java.util.Set;

public class _Main {

	public static void main(String[] args) {
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();

		set1.add("abc");
		set1.add("def");
		set1.add("ghi");

		set2.add("abc");
		set2.add("def");

		System.out.println(numElementsInCommon(set1, set2));
	}

	// Use of raw type for unknown element type - don't do this!
	// static int numElementsInCommon(Set s1, Set s2) {
	// int result = 0;
	// for (Object o1 : s1)
	// if (s2.contains(o1))
	// result++;
	// return result;
	// }

	// Uses unbounded wildcard type - typesafe and flexible
	static int numElementsInCommon(Set<?> s1, Set<?> s2) {
		int result = 0;
		for (Object o1 : s1) {
			if (s2.contains(o1))
				result++;
		}
		return result;
	}
	

}
