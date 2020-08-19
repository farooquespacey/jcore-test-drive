package com.spacey.effective._13;

import java.util.Arrays;
import java.util.EmptyStackException;

public class _2Clone4ClassWithRefToMutableState {

	public static void main(String[] args) {
		Stack st = new Stack();
		st.push("farooque");
		Stack stCopy = st.clone();
		System.out.println(st.size + " - " + stCopy.clone().size);

	}

	static final public class Stack implements Cloneable {
		private Object[] elements;
		private int size = 0;
		private static final int DEFAULT_INITIAL_CAPACITY = 16;

		public Stack() {
			this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
		}

		public void push(Object e) {
			ensureCapacity();
			elements[size++] = e;
		}

		public Object pop() {
			if (size == 0)
				throw new EmptyStackException();
			Object result = elements[--size];
			elements[size] = null; // Eliminate obsolete reference
			return result;
		}

		// Ensure space for at least one more element.
		private void ensureCapacity() {
			if (elements.length == size)
				elements = Arrays.copyOf(elements, 2 * size + 1);
		}

		// Too Bad. The original array will be retained
		// @Override
		// public Stack clone() {
		// try {
		// return (Stack) super.clone();
		// } catch (CloneNotSupportedException e) {
		// throw new AssertionError();
		// }
		// }

		// Clone method for class with references to mutable state
		@Override
		public Stack clone() {
			try {
				Stack result = (Stack) super.clone();
				result.elements = elements.clone();
				return result;
			} catch (CloneNotSupportedException e) {
				throw new AssertionError();
			}
		}
	}

}
