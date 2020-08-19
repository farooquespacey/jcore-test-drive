package com.spacey.effective.test;

import java.util.Scanner;

public class ScannerSkipExample2 {
	public static void main(String args[]) {
		String str = "JavaTpoint:102:131:150:123";
		// Initialize Scanner object
		Scanner scan = new Scanner(str);
		scan.useDelimiter(":");
		// Initialize the String pattern
		String pattern = "[a-zA-Z]*";
		// Printing the tokenized Strings
		while (scan.hasNext()) {
			// Skipping first occurrence of the Pattern
			scan.skip(pattern);
			System.out.println(scan.next() + scan.delimiter());
		}
		scan.close();
	}
}