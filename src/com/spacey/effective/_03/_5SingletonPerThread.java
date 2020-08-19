package com.spacey.effective._03;

public class _5SingletonPerThread {

	public static void main(String[] args) {
		Elvis elvis = Elvis.getInstance();
		Elvis elvis2 = Elvis.getInstance();
		System.out.println("Single instance inside a thread: " + (elvis.equals(elvis2)));
		new Thread(() -> {
			Elvis elvisInThread = Elvis.getInstance();
			System.out.println("Single instance across threads: " + elvis.equals(elvisInThread));
		}).start();
	}

	// Singleton with public final field
	// Also, a separate instance for each thread
	static class Elvis {

		private static final ThreadLocal<Elvis> threadLocalYourObject = ThreadLocal.withInitial(() -> new Elvis());

		private Elvis() {
		}

		public static Elvis getInstance() {
			return threadLocalYourObject.get();
		}

		public void leaveTheBuilding() {
		}

	}

}
