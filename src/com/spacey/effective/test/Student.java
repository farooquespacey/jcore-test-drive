package com.spacey.effective.test;

import java.io.FileReader;
import java.io.IOException;

public class Student implements AutoCloseable, Comparable<Student> {
	private String name;
	private int rank;
	private FileReader markSheet;

	public Student(String nm, int r, FileReader ms) {
		name = nm;
		rank = r;
		markSheet = ms;
	}

	public void read() throws IOException {
		System.out.println(markSheet.read());
	}

	public void close() throws IOException {
		System.out.println("I am closing your dumb file");
		markSheet.close();
	}

	@Override
	public int compareTo(Student o) {
		return Integer.compare(rank, o.rank);
	}
	
//	public void compare(Comparable<T>) {
//		
//	}

}
