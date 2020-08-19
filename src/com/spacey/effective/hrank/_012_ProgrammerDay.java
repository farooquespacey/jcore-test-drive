package com.spacey.effective.hrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class _012_ProgrammerDay {
	// Complete the dayOfProgrammer function below.
	static String dayOfProgrammer(int year) {
		String leapDDMM = "12.09.";
		String nonLeapDDMM = "13.09.";
		String res = "";
		if (year == 1918) {
			res = "26.09.1918";
		} else if (isLeap(year)) {
			res = leapDDMM + year;
		} else {
			res = nonLeapDDMM + year;
		}
		return res;
	}

	private static boolean isLeap(int year) {
		if (isGeorgian(year)) {
			return (year % 400 == 0) || ((year % 100 != 0) && (year % 4 == 0));
		} else {
			return (year % 4) == 0;
		}
	}

	private static boolean isGeorgian(int year) {
		return year < 1700 || year > 1918;
	}

//	private static boolean isJulian(int year) {
//		return year >= 1700 && year <= 1917;
//	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int year = Integer.parseInt(bufferedReader.readLine().trim());

		String result = dayOfProgrammer(year);
		System.out.println(result);

		bufferedReader.close();
	}
}
