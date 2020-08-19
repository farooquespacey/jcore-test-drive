package com.spacey.effective.hrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class _003_MinSwap {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
    	int noOfSwaps = arr.length - 1;
    	for(int i=1; i<=arr.length; i++) {
    		if(i == arr[i-1]) noOfSwaps -= 1;
    	}
    	return noOfSwaps;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        System.out.print(String.valueOf(res));
        System.out.println();

        scanner.close();
    }
}

