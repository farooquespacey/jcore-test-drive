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

public class _016_NonDivisibleSubset {
	static class Result {

		/*
		 * Complete the 'nonDivisibleSubset' function below.
		 *
		 * The function is expected to return an INTEGER. The function accepts following
		 * parameters: 1. INTEGER k 2. INTEGER_ARRAY s
		 */

		/*
		 * Look for solution here : https://github.com/naskio/Hackerrank-Non-Divisible-Subset/blob/master/Solution.java
		 */
		public static int nonDivisibleSubset(int k, List<Integer> s) {
			// Write your code here
			int max = 0;
			List<Integer> maxSubset = new ArrayList<>();
			for (int i = 0; i < s.size(); i++) {
				int curr = s.get(i);
				int nextIdx = i + 1;
				maxSubset.add(curr);
				while (nextIdx < s.size()) {
					int next = s.get(nextIdx);
					boolean skipAdd = maxSubset.stream().anyMatch(e -> (e + next) % k == 0);
					if (!skipAdd)
						maxSubset.add(next);
					nextIdx++;
				}
				max = maxSubset.size() > max ? maxSubset.size() : max;
				maxSubset = new ArrayList<>();
			}
			return max;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int k = Integer.parseInt(firstMultipleInput[1]);

		List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
				.collect(toList());

		int result = Result.nonDivisibleSubset(k, s);

		System.out.print(String.valueOf(result));
		System.out.println();

		bufferedReader.close();
	}
}
