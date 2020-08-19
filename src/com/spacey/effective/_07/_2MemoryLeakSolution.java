package com.spacey.effective._07;

import java.util.Arrays;
import java.util.EmptyStackException;

public class _2MemoryLeakSolution {

	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.push("first");
		stack.push("second");
		stack.push("aboutToPop");
		System.out.println("Popped Value is " + stack.pop() + " and now size has reduced to " + stack.size);
		System.out.println("Now Cannot Access -> " + stack.elements[2]); // Although popped, the element is still
																		// accessible. Thus a memory leak
	}

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
			Object result = elements[--size];
			elements[size] = null;
			return result;
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
