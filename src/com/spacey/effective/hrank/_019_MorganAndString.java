package com.spacey.effective.hrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class _019_MorganAndString {

	// Complete the morganAndString function below.
	static String morganAndString(String a, String b) {
		StringBuilder sb = new StringBuilder();
		int aPointer = 0;
		int bPointer = 0;
		boolean neighbourComparisonEnds = true;
		while (a.length() > aPointer && b.length() > bPointer) {
			char aChar = a.charAt(aPointer);
			char bChar = b.charAt(bPointer);
			int comparison = Character.compare(aChar, bChar);
			if (comparison > 0) {
				System.out.println("b wins: " + bChar);
				sb.append(bChar);
				bPointer++;
			} else if (comparison < 0) {
				System.out.println("a wins: " + aChar);
				sb.append(aChar);
				aPointer++;
			} else {
				System.out.println("both equals");
				int tempAPointer = aPointer + 1;
				int tempBPointer = bPointer + 1;
				while (neighbourComparisonEnds && a.length() > tempAPointer && b.length() > tempBPointer) {
					char tempAChar = a.charAt(tempAPointer);
					char tempBChar = b.charAt(tempBPointer);
					int neighbourComparison = Character.compare(tempAChar, tempBChar);
					if (neighbourComparison > 0) {
						System.out.println("b wins with neighbour support: " + bChar);
						sb.append(bChar);
						bPointer++;
						neighbourComparisonEnds = true;
						break;
					} else if (neighbourComparison > 0) {
						System.out.println("a wins with neighbour support: " + aChar);
						sb.append(aChar);
						aPointer++;
						neighbourComparisonEnds = true;
						break;
					} else {
						tempAPointer++;
						tempBPointer++;
						neighbourComparisonEnds = false;
					}
				}
				if (!neighbourComparisonEnds) {
					System.out.println("big tie");
					sb.append(aChar);
					aPointer++;
				}
			}
		}
		if (aPointer == a.length() && bPointer == b.length()) {
			System.out.println("len: a==b");
			return sb.toString();
		} else if (aPointer == a.length()) {
			System.out.println("len: a<b");
			return sb.toString() + b.substring(bPointer);
		} else {
			System.out.println("len: a>b");
			return sb.toString() + a.substring(aPointer);
		}
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		int t = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int tItr = 0; tItr < t; tItr++) {
			String a = scanner.nextLine();

			String b = scanner.nextLine();

			String result = morganAndString(a, b);
			System.out.println(result);
		}

		scanner.close();
	}
}
