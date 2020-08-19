package com.spacey.effective._07;

import java.util.Arrays;
import java.util.EmptyStackException;

public class _1MemoryLeak {

	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.push("first");
		stack.push("second");
		stack.push("aboutToPop");
		System.out.println("Popped Value is " + stack.pop() + " and now size has reduced to " + stack.size);
		System.out.println("Again Can Access -> " + stack.elements[2]); // Although popped, the element is still
																		// accessible. Thus a memory leak
		stack.push("replacer");
		System.out.println("But now replaced with a new value -> " + stack.elements[2]);
	}

	// Can you spot the "memory leak"?
	static class Stack {
		private Object[] elements;
		private int size = 0;
		private static final int DEFAULT_INITIAL_CAPACITY = 16;

		public Stack() {
			elements = new Object[DEFAULT_INITIAL_CAPACITY];
		}

		public void push(Object e) {
			ensureCapacity();
			elements[size++] = e;
		}

		public Object pop() {
			if (size == 0)
				throw new EmptyStackException();
			return elements[--size];
		}

		/**
		 * Ensure space for at least one more element, roughly doubling the capacity
		 * each time the array needs to grow.
		 */
		private void ensureCapacity() {
			if (elements.length == size)
				elements = Arrays.copyOf(elements, 2 * size + 1);
		}
	}

}
