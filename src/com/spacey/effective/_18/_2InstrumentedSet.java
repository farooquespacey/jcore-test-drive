package com.spacey.effective._18;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// Wrapper class - uses composition in place of inheritance
public class _2InstrumentedSet<E> extends _2ForwardingSet<E> {
	private int addCount = 0;

	public _2InstrumentedSet(Set<E> s) {
		super(s);
	}

	@Override
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}

	public int getAddCount() {
		return addCount;
	}

	public static void main(String[] args) {
		_2InstrumentedSet<String> s = new _2InstrumentedSet<>(new HashSet<>());
		System.out.println(s.addAll(Arrays.asList("Snap", "Crackle", "Pop")));
		System.out.println(s.addCount);
	}
}