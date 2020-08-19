package com.spacey.effective.test;

import java.io.IOException;

public class InterfaceContractMain {

	public static void main(String[] args) throws IOException {
		String s1 = "farooque";
		m1((t) -> {
			System.out.println("My own comparable, I am always greater..." + t);
			return 1;
		});

	}

	public static <A> void m1(Comparable<A> c) {
		System.out.println(c.compareTo((A) "sad"));
	}
}
