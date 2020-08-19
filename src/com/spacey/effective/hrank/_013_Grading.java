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

public class _013_Grading {
	static class Result {

		/*
		 * Complete the 'gradingStudents' function below.
		 *
		 * The function is expected to return an INTEGER_ARRAY. The function accepts
		 * INTEGER_ARRAY grades as parameter.
		 */

		public static List<Integer> gradingStudents(List<Integer> grades) {
			// Write your code here
			List<Integer> finalGrades = new ArrayList<>(grades.size());
			for (int g : grades) {
				finalGrades.add(g + roundableTo(g));
			}
			return finalGrades;
		}

		private static int roundableTo(int g) {
			if ((g > 37) && ((g % 5) > 2)) {
				return 5 - (g % 5);
			}
			return 0;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int gradesCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> grades = IntStream.range(0, gradesCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).map(String::trim).map(Integer::parseInt).collect(toList());

		List<Integer> result = Result.gradingStudents(grades);

		System.out.print(result.stream().map(Object::toString).collect(joining("\n")) + "\n");

		bufferedReader.close();
	}
}
