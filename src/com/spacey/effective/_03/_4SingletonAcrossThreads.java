package com.spacey.effective._03;

public class _4SingletonAcrossThreads {

	public static void main(String[] args) {
		// Singleton across threads
		Elvis elv = Elvis.getInstance();
		new Thread(() -> {
			Elvis elvInThread = Elvis.getInstance();
			System.out.println("Single instance across threads: " + elv.equals(elvInThread));
		}).start();
	}

	static class Elvis {
		private static final Elvis INSTANCE = new Elvis();

		private Elvis() {
		}

		public static Elvis getInstance() {
			return INSTANCE;
		}

		public void leaveTheBuilding() {
		}
	}
	// Or public field approach
	// public class Elvis {
	// public static final Elvis INSTANCE = new Elvis();
	// private Elvis() { ... }
	// public void leaveTheBuilding() { ... }
	// }

}
