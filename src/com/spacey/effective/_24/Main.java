package com.spacey.effective._24;

import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<>();
		HashMap<String, String> hashMp = new HashMap<>();
		hashMp.put("fa", "adf");
		hashMp.put("fda", "sdasd");

		// static nested class - Node implement Map.Entry
		System.out.println(hashMp.entrySet());

		System.out.println(hashMp.size());
		// nonstatic nested class (inner class) - KeyIterator implements Iterator
		Iterator<String> itr = hashMp.keySet().iterator();
		System.out.println(itr.next());
		System.out.println(itr.hasNext());
		hashMp.keySet().clear();
		System.out.println(hashMp.size());
	}

}

// Typical use of a nonstatic member class
class MySet<E> extends AbstractSet<E> {

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new MyIterator();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	private class MyIterator implements Iterator<E> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}