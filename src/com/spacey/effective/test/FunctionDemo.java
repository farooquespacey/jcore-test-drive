package com.spacey.effective.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionDemo {

	// user data, config data is used to perform thirdparty call

	public static Function<String, Function<String, String>> invokeHelper() {
		return (in) -> {
			if (in.equals("send_message")) {
				return (String t2) -> t2 + "_send_message_response";
			} else {
				return (String t2) -> t2 + "_receive_message_response";
			}
		};
	}

	public static void main(String[] args) {

//		String userData = "abc";
//		System.out.println(invokeHelper().apply("send_message").apply("bean"));
		List<String> l1 = new LinkedList<>();
		l1.add("123");
		l1.add("456");
		l1.add("789");
		
		System.out.println(l1.stream().filter(e -> e.startsWith("1")).count());
		
		l1.stream().forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("farooque", "Orion");
		map1.put("kesavan", "google");
		map1.put("prat", "VIP");
		map1.put("ragu", null);
		map1.computeIfPresent("ragu", (k,v) -> "$");
		map1.forEach((key,val) -> System.out.println());
		System.out.println(map1);
		
		List<String> l2 = new LinkedList<>();
		l2.add("abc");
		l2.add("def");
		l2.add("ghi");
		System.out.println(l2);
		
//		List<Integer> l2 = l1.stream().map(e -> {
//			return Integer.parseInt(e);
//		}).collect(Collectors.toList());
//		System.out.println(l2);
//
//		int sum = l2.stream().reduce((e1, e2) -> e1 + e2).get();
//		int se = l2.stream().reduce(1, (e1, e2) -> {return e1 + e2;}).intValue();
//		System.out.println(se);
	}

}

//class Stream {
//	
//	Collection e;
//	
//	public Stream(Collection e) {
//		
//	}
//	
//	forEach
//}

