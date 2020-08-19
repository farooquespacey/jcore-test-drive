package com.spacey.effective._28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Array vs List
 * 
 * @author Farooque
 *
 */
public class _Main {

	public static void main(String[] args) {
		// Fails at runtime!
		Object[] objectArray = new Long[1];
		objectArray[0] = "I don't fit in"; // Throws ArrayStoreException

		// Won't compile!
		// List<Object> ol = new ArrayList<Long>(); // Incompatible types
		// ol.add("I don't fit in");

		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		// Why generic array creation is illegal - won't compile!
		// List<String>[] stringLists = new List<String>[1]; // (1)
		// List<Integer> intList = List.of(42); // (2)
		// Object[] objects = stringLists; // (3)
		// objects[0] = intList; // (4)
		// String s = stringLists[0].get(0); // (5)

		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// Use List<E> in preference to E{}, check the below member classes one after
		// other

	}

	// Chooser - a class badly in need of generics!
	// static public class Chooser {
	// private final Object[] choiceArray;
	//
	// public Chooser(Collection choices) {
	// choiceArray = choices.toArray();
	// }
	//
	// public Object choose() {
	// Random rnd = ThreadLocalRandom.current();
	// return choiceArray[rnd.nextInt(choiceArray.length)];
	// }
	// }

	// A first cut at making Chooser generic - won't compile
	// public class Chooser<T> {
	// private final T[] choiceArray;
	//
	// public Chooser(Collection<T> choices) {
	// choiceArray = choices.toArray();
	// }
	// // choose method unchanged
	// }

	// No big deal, you say, I’ll cast the Object array to a T array -
	// You still get a warning
	// public class Chooser<T> {
	// private final T[] choiceArray;
	//
	// public Chooser(Collection<T> choices) {
	// choiceArray = (T[]) choices.toArray();
	// }
	//
	// public Object choose() {
	// Random rnd = ThreadLocalRandom.current();
	// return choiceArray[rnd.nextInt(choiceArray.length)];
	// }
	// }

	// List-based Chooser - typesafe
	public class Chooser<T> {
		private final List<T> choiceList;

		public Chooser(Collection<T> choices) {
			choiceList = new ArrayList<>(choices);
		}

		public T choose() {
			Random rnd = ThreadLocalRandom.current();
			return choiceList.get(rnd.nextInt(choiceList.size()));
		}
	}

}
