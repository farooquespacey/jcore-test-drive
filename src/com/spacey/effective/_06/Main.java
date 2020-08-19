package com.spacey.effective._06;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		String s = new String("abc");
		String s2 = new String("abc");
		System.out.println(s == s2); // that's why above way is not recomended
		// You can often avoid creating unnecessary objects by using static factory
		// methods (Item 1) in preference to constructors on immutable classes that
		// provide both
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		// Some object creations are much more expensive than others. If you’re going
		// to need such an “expensive object” repeatedly, it may be advisable to cache
		// it for reuse. Unfortunately, it’s not always obvious when you’re creating
		// such an object. See the below isRomanNumeral(String) method.

		// To improve the performance, explicitly compile the regular expression into a
		// Pattern instance (which is immutable) as part of class initialization, cache
		// it, and reuse the same instance for every invocation of the isRomanNumeral
		// method As shown in the below RomanNumerals class:
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		keySetActAsAdapter();
	}

	// Performance can be greatly improved!
	// As String.matches is the easiest way to check if a string matches
	// a regular expression, it’s not suitable for repeated use in
	// performance-critical situations. The problem is that it internally creates a
	// Pattern instance for the regular expression and uses it only once, after
	// which it becomes eligible for garbage collection. Creating a Pattern instance
	// is expensive because it requires compiling the regular expression into a
	// finite state machine.
	static boolean isRomanNumeral(String s) {
		return s.matches("^(?=.)M*(C[MD]|D?C{0,3})" + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
	}

	// Reusing expensive object for improved performance
	static class RomanNumerals {
		private static final Pattern ROMAN = Pattern
				.compile("^(?=.)M*(C[MD]|D?C{0,3})" + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

		static boolean isRomanNumeral(String s) {
			return ROMAN.matcher(s).matches();
		}
	}

	static void keySetActAsAdapter() {
		Map<String, String> hMap = new HashMap<>();
		hMap.put("a", "abc");
		hMap.put("b", "bcd");
		hMap.put("c", "cde");
		hMap.put("d", "def");
		Set<String> setBackedByMap = hMap.keySet();
		setBackedByMap.remove("a"); // removal affects the original Map
		hMap.keySet().forEach(System.out::println);
	}

	// Another way to create unnecessary objects is autoboxing, which allows the
	// programmer to mix primitive and boxed primitive types, boxing and unboxing
	// automatically as needed. Autoboxing blurs but does not erase the distinction
	// between primitive and boxed primitive types.
	// Hideously slow! Can you spot the object creation?
	private static long sum() {
		Long sum = 0L;
		for (long i = 0; i <= Integer.MAX_VALUE; i++)
			sum += i;
		return sum;
	}
	// prefer primitives to boxed primitives, and watch out for unintentional
	// autoboxing.

}
