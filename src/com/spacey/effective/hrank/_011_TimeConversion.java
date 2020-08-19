package com.spacey.effective.hrank;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class _011_TimeConversion {

    /*
     * Complete the timeConversion function below.
     */
    static String timeConversion(String s) {
        /*
         * Write your code here.
         */
    	String[] inps = s.split(":");
    	int hour = Integer.parseInt(inps[0]);
    	if(inps[2].contains("PM")) {
    		hour = (hour==12) ? hour : (12+hour);
    	} else if(hour==12) {hour = 00;}
    	return String.format("%02d", hour) + ":" 
    	+ inps[1] + ":" + inps[2].replaceAll("(\\SM)", "");
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String s = scan.nextLine();

        String result = timeConversion(s);

        System.out.println(result);
    }
}

