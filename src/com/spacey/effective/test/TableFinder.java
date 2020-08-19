package com.spacey.effective.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableFinder {

	public static void main(String[] args) throws FileNotFoundException {
		Pattern pat = Pattern.compile("(from)(\\s)+(?!\\()(.+?)(where)");
		Set<String> results = new HashSet<String>();
		for (File file : new File("C:\\Host\\others\\from_extract").listFiles()) {
			try (Scanner sc = new Scanner(file)) {
				boolean foundOracle = false;
				StringBuilder contentWithoutSpace = new StringBuilder();
				while (sc.hasNextLine()) {
					String thisLine = sc.nextLine();
					if (foundOracle) {
						System.out.print(".");
						contentWithoutSpace.append(thisLine);
					} else {
						foundOracle = thisLine.contains("Oracle");
						if (foundOracle) {
							System.out.println("===Found Oracle=== (" + thisLine + ")");
							System.out.print("Appending file content");
						}
					}
				}
				System.out.println();
				if (contentWithoutSpace.length() > 1) {
					System.out.println(contentWithoutSpace);
					Matcher mat = pat.matcher(contentWithoutSpace);
					while (mat.find()) {
						String tableInfo = mat.group(3);
						System.out.println("Found matches..." + tableInfo);
						results.add(tableInfo);
					}
				}
			}
		}

	}

}
