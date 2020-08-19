package com.spacey.effective.hrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class _017_SherlockAndValidString {

	// Complete the isValid function below.
	static String isValid(String s) {
		Map<Character, Integer> charMap = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			charMap.computeIfPresent(c, (k, v) -> v + 1);
			charMap.putIfAbsent(c, 1);
		}
		int max = 0;
		boolean firstDiff = true;
		boolean passes = true;
		for (int val : charMap.values()) {
			if (max == 0) {
				max = val;
			} else if (val == max) {
				continue;
			} else {
				passes = (val-1==max || max-1==val) && firstDiff;
				firstDiff = false;
				if(!passes) break;
			}
		}
		if (passes)
			return "YES";
		System.out.println(charMap);
		return "NO";
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		String s = scanner.nextLine();

		String result = isValid(s);

		System.out.println(result);
		scanner.close();
	}
}
