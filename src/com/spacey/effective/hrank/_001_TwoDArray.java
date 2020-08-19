package com.spacey.effective.hrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class _001_TwoDArray {
	
	// Input
/*
1 1 1 0 0 0
0 1 0 0 0 0
1 1 1 0 0 0
0 0 2 4 4 0
0 0 0 2 0 0
0 0 1 2 4 0
*/

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
    	List<Integer> lOfInts = new ArrayList<>();
        int iterUntil = arr.length - 2;
        for(int i=0; i < iterUntil; i++){
        	System.out.println("Trying for " + i+1);
        	for(int j=0; j < iterUntil; j++){
        		List<Integer> intList = new ArrayList<>();
        		intList.add(arr[i][j]);
        		intList.add(arr[i][j+1]);
        		intList.add(arr[i][j+2]);
        		intList.add(arr[i+1][j+1]);
        		intList.add(arr[i+2][j]);
        		intList.add(arr[i+2][j+1]);
        		intList.add(arr[i+2][j+2]);
        		lOfInts.add(intList.stream().collect(Collectors.summingInt(e -> e)));
        	}
        }
        int max = lOfInts.stream().max(Integer::compare).get();
        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);

        System.out.println(result);

        scanner.close();
    }
}
