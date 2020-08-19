package com.spacey.effective.hrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class _015_ExtraLongFactorial {

    // Complete the extraLongFactorials function below.
    static void extraLongFactorials(int n) {
    	int tempN = n;
    	BigInteger res = new BigInteger("1");
    	while(tempN != 0) {
    		res = res.multiply(BigInteger.valueOf(tempN));
    		tempN--;
    	}
    	System.out.println(res);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        extraLongFactorials(n);

        scanner.close();
    }
}
