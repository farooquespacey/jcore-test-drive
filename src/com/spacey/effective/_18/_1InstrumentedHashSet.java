package com.spacey.effective._18;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

// Broken - Inappropriate use of inheritance!
public class _1InstrumentedHashSet<E> extends HashSet<E> {
	// The number of attempted element insertions
	private int addCount = 0;

	public _1InstrumentedHashSet() {
	}

	public _1InstrumentedHashSet(int initCap, float loadFactor) {
		super(initCap, loadFactor);
	}

	@Override
	public boolean add(E e) {
		System.out.println("This too called");
		addCount++;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		int sizeBeforeAdd = super.size();
		int sizeOfCollectionToStore = c.size();
		c.forEach(this::add);
		return (sizeBeforeAdd + sizeOfCollectionToStore) == super.size();
	}

	public int getAddCount() {
		return addCount;
	}
	
	public static void main(String[] args) {
		_1InstrumentedHashSet<String> s = new _1InstrumentedHashSet<>();
		System.out.println(s.addAll(Arrays.asList("Snap", "Crackle", "Pop")));
		System.out.println(s.addCount);
	}
}