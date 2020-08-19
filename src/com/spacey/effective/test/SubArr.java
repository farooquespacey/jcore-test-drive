package com.spacey.effective.test;

import java.util.Arrays;

public class SubArr {

	public static void main(String[] args) {
		String[] org = new String[] { "1", "2", "3" };
		String[] subArr = Arrays.copyOfRange(org, 1, org.length);
		System.out.println(subArr[0]);
	}

}
