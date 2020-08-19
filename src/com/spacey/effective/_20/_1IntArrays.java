package com.spacey.effective._20;
// Concrete implementation built atop skeletal implementation - Page 95

import java.util.AbstractList;
import java.util.Collections;
import java.util.List;

public class _1IntArrays {

	static List<Integer> intArrayAsList(final int[] a) {
		if (a == null)
			throw new NullPointerException();

		return new AbstractList<Integer>() {
			public Integer get(int i) {
				return a[i]; // Autoboxing (Item 5)
			}

			@Override
			public Integer set(int i, Integer val) {
				System.out.println("Called from Shuffle " + val);
				int oldVal = a[i];
				a[i] = val; // Auto-unboxing
				return oldVal; // Autoboxing
			}

			public int size() {
				return a.length;
			}
		};
	}

	public static void main(String[] args) {
		int[] a = new int[10];
		for (int i = 0; i < a.length; i++)
			a[i] = i;
		List<Integer> list = intArrayAsList(a);
		// list.set(1, 12);
		// list.add(52);
		System.out.println(list.get(3));

		Collections.shuffle(list); // make use of set() method we implemented for IntArray
		System.out.println(list);
	}
}