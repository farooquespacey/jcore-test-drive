package com.spacey.effective.hrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class _009_MiniMaxSum {

	// Complete the miniMaxSum function below.
	static void miniMaxSum(int[] arr) {
		List<Integer> arrs = Arrays.stream(arr).boxed().collect(Collectors.toList());
		long min=0,max=0,sum=0;
		for(int curr: arrs) {
			if(sum==0) {min=curr; max=curr;}
			min = (min > curr) ? curr: min;
			max = (max < curr) ? curr: max;
			sum += curr;
		}
		System.out.println((sum-max) + " " + (sum-min));
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int[] arr = new int[5];

		String[] arrItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < 5; i++) {
			int arrItem = Integer.parseInt(arrItems[i]);
			arr[i] = arrItem;
		}

		miniMaxSum(arr);

		scanner.close();
	}
}
