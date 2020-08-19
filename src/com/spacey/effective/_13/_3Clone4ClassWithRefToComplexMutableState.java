package com.spacey.effective._13;

public class _3Clone4ClassWithRefToComplexMutableState {

	public static void main(String[] args) {

	}

	// Recursive clone method for class with complex mutable state
	public static class HashTable implements Cloneable {
		private Entry[] buckets; //

		private static class Entry {
			final Object key;
			Object value;
			Entry next;

			Entry(Object key, Object value, Entry next) {
				this.key = key;
				this.value = value;
				this.next = next;
			}

			// Recursively copy the linked list headed by this Entry
			// Entry deepCopy() {
			// return new Entry(key, value, next == null ? null : next.deepCopy());
			// }

			// Iteratively copy the linked list headed by this Entry
			Entry deepCopy() {
				Entry result = new Entry(key, value, next);
				for (Entry p = result; p.next != null; p = p.next)
					p.next = new Entry(p.next.key, p.next.value, p.next.next);
				return result;
			}
		}
		
		
		// Broken clone method - results in shared mutable state!
		// @Override
		// public HashTable clone() {
		// try {
		// HashTable result = (HashTable) super.clone();
		// result.buckets = buckets.clone();
		// return result;
		// } catch (CloneNotSupportedException e) {
		// throw new AssertionError();
		// }
		// }

		@Override
		public HashTable clone() {
			try {
				HashTable result = (HashTable) super.clone();
				result.buckets = new Entry[buckets.length];
				for (int i = 0; i < buckets.length; i++)
					if (buckets[i] != null)
						result.buckets[i] = buckets[i].deepCopy();
				return result;
			} catch (CloneNotSupportedException e) {
				throw new AssertionError();
			}
		}

	}

}
