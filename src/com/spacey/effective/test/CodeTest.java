package com.spacey.effective.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class CodeTest {
	int count = 0;
	double currentVal = 0.0;
	double oldValue = 0.0;
	double max = 0.0;
	double min = 0.0;
	String val = "";

	@Test
	public void test() throws IOException {
		String TEST_FUNCTION_REG = "(TEST_FUNCTION_)(.+?)(_.+)(\\.)(.+?)(,)(.+,)([0-9]+)";
		Pattern TEST_FUNCTION_PAT = Pattern.compile(TEST_FUNCTION_REG);
		String LOC = "C:\\Users\\Night King\\Downloads\\DS_libs\\";
		HashMap<String, String> oldList = new LinkedHashMap<String, String>();
		oldList.put("DCOUNTjStatements", "106");
		oldList.put("DCOUNTjFunctions", "16");
		oldList.put("FMTjStatements", "106");
		oldList.put("FMTjFunctions", "310");
		oldList.put("ICONVjStatements", "106");
		oldList.put("ICONVjFunctions", "905");
		oldList.put("OCONVjStatements", "106");
		oldList.put("OCONVjFunctions", "170");
//            oldList.put("TRIMjStatement", "106");
//            oldList.put("TRIMjFunction", "236");
		oldList.put("CacheGetjStatements", "106");
		oldList.put("CachePutjStatements", "106");
		oldList.put("TRIMFjStatements", "158");
		oldList.put("TRIMFjFunctions", "163");

		try (Scanner currentData = new Scanner(new File(LOC + "report-summary.csv"));
				FileWriter csvWriter = new FileWriter(LOC + "result-summary.csv")) {
			csvWriter.append("Function" + "," + "Result" + "\n");
			while (currentData.hasNextLine()) {
				if (count > 0) {
					Matcher TF_MAT = TEST_FUNCTION_PAT.matcher(currentData.nextLine());
					while (TF_MAT.find()) {
						String keyFound = TF_MAT.group(2) + TF_MAT.group(5);
						String valFound = TF_MAT.group(8);
						if (oldList.containsKey(keyFound)) {
							oldValue = Double.parseDouble(oldList.get(keyFound));
							currentVal = Double.parseDouble(valFound);
							if (!matchesNearby(oldValue, currentVal)) {
								System.out.println("Value not in range for " + keyFound);
								val = keyFound + "," + "fail";
							} else {
								System.out.println("Value  in range for " + keyFound);
								val = keyFound + "," + "pass";
							}
							csvWriter.append(val);
							csvWriter.append("\n");
							csvWriter.flush();
						}
					}
				}
				count++;
			}
		}
	}

	private boolean matchesNearby(double oldVal, double currVal) {
		max = oldVal + (0.05 * oldVal);
		min = oldVal - (0.05 * oldVal);
		return (currVal >= min) && (currVal <= max);
	}

}
