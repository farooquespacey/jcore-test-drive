package com.spacey.effective._19;

import java.util.AbstractCollection;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
//		java.util.AbstractCollection;
//		java.util.AbstractList<>;
	}

}


class Aclass extends AbstractCollection<String>{

	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<String>() {

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String next() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}