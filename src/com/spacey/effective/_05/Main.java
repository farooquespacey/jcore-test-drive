package com.spacey.effective._05;

import java.util.ArrayList;
import java.util.Objects;

public class Main {

	public static void main(String[] args) {
		ReflexViolation ref = new ReflexViolation();
		ReflexViolation ref2 = new ReflexViolation();
		ReflexViolation ref3 = new ReflexViolation();
		ref.name = "abc";
		ref2.name = "abc";
		System.out.println(ref.equals(ref2));
		ArrayList<ReflexViolation> refSet = new ArrayList<>();
		System.out.println("ADding 1");
		refSet.add(ref);
		System.out.println("ADding 2");
		refSet.add(ref2);
		refSet.forEach(System.out::println);
		System.out.println("now");
		System.out.println(refSet.contains(ref));
		
		System.out.println("++++++++++");
			
		
	}

}

class ReflexViolation{
	public String name;
	
	@Override
	public boolean equals(Object obj) {
		System.out.println("Equals called");
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Objects.toString(name);
	}
}
