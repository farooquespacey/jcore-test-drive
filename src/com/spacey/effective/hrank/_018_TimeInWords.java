package com.spacey.effective.hrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class _018_TimeInWords {
	private static final String[] numNames = { "", "one", "two", "three", "four", "five", "six", "seven",
			"eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
			"seventeen", "eighteen", "nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four",
			"twenty five", "twenty six", "twenty seven", "twenty eight", "twenty nine" };
	private static final String PAST ="past";
	private static final String TO ="to";
	private static final String QUARTER ="quarter";
	private static final String HALF ="half";	
	private static final String OCLOCK =" o' clock";

	// Complete the timeInWords function below.
	static String timeInWords(int h, int m) {
		String res = "";
		String hourName = numNames[h];
		if(m == 0) {
			res = hourName + OCLOCK;
		} else if(1 <= m && m <= 30) {
			if(m == 15) res = QUARTER + " " + PAST + " " + hourName;
			else if(m == 30) res = HALF + " " + PAST + " " + hourName;
			else if(m != 1) res = numNames[m] + " minutes " + PAST + " " + hourName;
			else res = numNames[m] + " minute " + PAST + " " + hourName;
		} else {
			hourName = numNames[(h+1)%12];
			int reversed = 60 - m;
			if(reversed == 15) res = QUARTER + " " + TO + " " + hourName;
			else if(reversed != 1) res = numNames[reversed] + " minutes " + TO + " " + hourName;
			else res = numNames[reversed] + " minute " + TO + " " + hourName;
		}
		return res;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		int h = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		scanner.skip(".*?\\|");
		scanner.skip("([|])?");

		int m = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		String result = timeInWords(h, m);

		System.out.println(result);
		scanner.close();
	}
}
