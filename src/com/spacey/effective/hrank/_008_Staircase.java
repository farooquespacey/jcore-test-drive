package com.spacey.effective.hrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class _008_Staircase {

	// Complete the staircase function below.
	static void staircase(int n) {
		String filler = "#";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int j = 0, spaces = n - (i + 1);
			while (j < n) {
				sb.append(((j < spaces) ? " " : filler));
				j++;
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		staircase(n);

		scanner.close();
	}
}
