package com.spacey.effective.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Main {

	static void decode(String encodedString) {
		String res = upperCaseFirst("Stored Proc");
		System.out.println(res);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < encodedString.length(); i++) {
			System.out.println("In");
			if (i % 2 == 0) {
				int duplicateTill = Integer.parseInt("" + encodedString.charAt(i + 1) + "");
				System.out.println("Duplicate " + encodedString.charAt(i) + " till " + duplicateTill);
				while (duplicateTill != 0) {
					sb.append(encodedString.charAt(i));
					--duplicateTill;
				}
			}
		}
	}

	private static String upperCaseFirst(String nm) {
		StringBuilder res = new StringBuilder();
		for (String piece : nm.split(" ")) {
			res.append(piece.substring(0, 1).toUpperCase() + piece.substring(1) + " ");
		}
		return res.toString().trim();
	}

	public static void main(String[] args) throws InterruptedException {
		TimeUnit.SECONDS.sleep(5);

		except(3);

//		decode("a1h9d3");
//		listToSet();
//		recursiveReverser("string of farooque");
//		Person p = new Person("farooque", 27);
//		Person p2 = p;
//		System.out.println(p == p2);
	}

	private static void except(int tries) {
		try {
			int res = 0 / 0;
			System.out.println(res);
		} catch (Exception e) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Trying.. ");
			if (tries > 0)
				except(tries - 1);
		}
		System.out.println("bla");
	}

	private static void recursiveReverser(String strToReverse) {
		String[] trimmedString = strToReverse.trim().split(" ", 1);
		System.out.println(Arrays.toString(trimmedString) + "  " + trimmedString.length);
		if (trimmedString.length > 0) {
//			String strAfterSpace = trimmedString[0];
			System.out.println();
			recursiveReverser(trimmedString[1]);
			System.out.println(trimmedString[0]);
		}
	}

	private static void listToSet() {
		LinkedList<Integer> l1 = new LinkedList<Integer>();
		List<Integer> l2 = new ArrayList<Integer>();
		l1.add(2);
		l1.add(3);
		l1.add(3);
		l2.add(5);
		l2.add(7);
		l2.add(27);
		l1.addAll(l2);
		Set<Integer> set1 = new HashSet<Integer>();
		set1.addAll(l1);
		System.out.println(set1);
	}

	static class Person {
		String name;
		int id;

		public Person(String name, int id) {
			this.name = name;
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

	}

}
