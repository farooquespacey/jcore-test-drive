package com.spacey.effective._26;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List lis = new ArrayList<>();
		lis.add("asd");
		// System.out.println((Integer) lis.get(0));

		List<Object> lisOfObj = new ArrayList<>();
		lisOfObj.add("das");

		List<String> strings = new ArrayList<>();
		unsafeAdd(strings, Integer.valueOf(42));
		String s = strings.get(0); // Has compiler-generated cast
	}

	private static void unsafeAdd(List<?> list, Object o) {
		// list.add(o); //wont' work
	}

}
