package com.spacey.effective._03;

public class _6SingletonEnum {

	public static void main(String[] args) {
		Elvis elvis = Elvis.INSTANCE;
		Elvis elvis2 = Elvis.INSTANCE;
		System.out.println("Single instance inside a thread: " + (elvis.equals(elvis2)));
		new Thread(() -> {
			Elvis elvisInThread = Elvis.INSTANCE;
			System.out.println("Single instance across threads: " + elvis.equals(elvisInThread));
		}).start();
	}

	// A third way to implement a singleton is to declare a single-element enum:
	// Enum singleton - the preferred approach
	// This approach may feel a bit unnatural, but a single-element
	// enum type is often the best way to implement a singleton. Note that you can’t
	// use this approach if your singleton must extend a superclass other than Enum
	// (though you can declare an enum to implement interfaces).
	public enum Elvis {
		INSTANCE;
		public void leaveTheBuilding() {
		}
	}

}
