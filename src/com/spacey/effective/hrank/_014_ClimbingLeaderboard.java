package com.spacey.effective.hrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class _014_ClimbingLeaderboard {

	// Complete the climbingLeaderboard function below.
	static int[] climbingLeaderboard(int[] scores, int[] alice) {
		// (*) to be able to add in sorted fashion
//		Set<Integer> sortedNums = new TreeSet<Integer>();
//		for (int i = 0; i < scores.length; i++) {
//			sortedNums.add(scores[i]);
//		}
		
		//----
		Set<Integer> sortedNums = new LinkedHashSet<Integer>(scores.length);
		for (int i = scores.length-1; i >= 0; i--) {
			sortedNums.add(scores[i]);
		}
		List<Integer> sortedNumLst = new ArrayList<Integer>(sortedNums);
		// OR (time-consuming)
//		List<Integer> sortedNumLst = new ArrayList<Integer>(scores.length);
//		for (int i = scores.length - 1; i >= 0; i--) {
//			if(sortedNumLst.contains(scores[i])) continue;
//			sortedNumLst.add(scores[i]);
//		}
		
		
		System.out.println(sortedNumLst);
		System.out.println("Ranks before: " + sortedNumLst.size());
		int[] ranks = new int[alice.length];
		int index = 0;
		int n = sortedNumLst.size();
		for (int i = 0; i < alice.length; i++) {
			while(n>index && alice[i] >= sortedNumLst.get(index))
				index += 1;
			ranks[i] = n+1-index;
		}
//		for (int i = 0; i < alice.length; i++) {
//			int INCREMENT = sortedNums.contains(alice[i]) ? 0 : 1;
//			ranks[i] = sortedNums.tailSet(alice[i]).size() + INCREMENT;
//		}
		
		
		// (*) int array to list(int)
		// List<Integer> iList =
		// Arrays.stream(alice).boxed().collect(Collectors.toList());
		// (*) list(int) to IntStream
		// IntStream is = iList.parallelStream().mapToInt(Integer::intValue);
		// (*) Using indices instead of values in foreach
//		IntStream.range(0, iList.size()).parallel().forEach(idx -> {
//			int INCREMENT = sortedNums.contains(iList.get(idx)) ? 0 : 1;
//			ranks[idx] = sortedNums.tailSet(iList.get(idx)).size() + INCREMENT;
//		});
		
		return ranks;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		int scoresCount = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int[] scores = new int[scoresCount];

		String[] scoresItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < scoresCount; i++) {
			int scoresItem = Integer.parseInt(scoresItems[i]);
			scores[i] = scoresItem;
		}

		int aliceCount = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int[] alice = new int[aliceCount];

		String[] aliceItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < aliceCount; i++) {
			int aliceItem = Integer.parseInt(aliceItems[i]);
			alice[i] = aliceItem;
		}

		int[] result = climbingLeaderboard(scores, alice);

		for (int i = 0; i < result.length; i++) {
			System.out.print(String.valueOf(result[i]));

			if (i != result.length - 1) {
				System.out.print("\n");
			}
		}

		System.out.println();
		;

		scanner.close();
	}
}
