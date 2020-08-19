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

public class _006_CompareTriplets {

	// Complete the compareTriplets function below.
	static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
		List<Integer> res = new ArrayList<Integer>(2);
		int aRank = 0, bRank = 0;
		for (int i = 0; i < a.size(); i++) {
			aRank += (a.get(i) > b.get(i)) ? 1 : 0;
			bRank += (a.get(i) < b.get(i)) ? 1 : 0;
		}
		res.add(aRank);
		res.add(bRank);
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
				.collect(toList());

		List<Integer> b = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
				.collect(toList());

		List<Integer> result = compareTriplets(a, b);

		System.out.print(result.stream().map(Object::toString).collect(joining(" ")) + "\n");

		bufferedReader.close();
	}
}
