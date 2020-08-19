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

class Result {

	/*
	 * Complete the 'diagonalDifference' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * 2D_INTEGER_ARRAY arr as parameter.
	 */

	public static int diagonalDifference(List<List<Integer>> arr) {
		// Write your code here
		int left = 0, right = 0;
		for (int i = 0; i < arr.size(); i++) {
			left += arr.get(i).get(i);
			right += arr.get(i).get(arr.size() - (i + 1));
			System.out.println(left + "---" + right);
		}
		return (left > right) ? (left - right) : (right - left);
	}

}

public class _007_DiagonalDiff {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		List<List<Integer>> arr = new ArrayList<>();

		IntStream.range(0, n).forEach(i -> {
			try {
				arr.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
						.collect(toList()));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		int result = Result.diagonalDifference(arr);

		System.out.println(String.valueOf(result));

		bufferedReader.close();
	}
}
