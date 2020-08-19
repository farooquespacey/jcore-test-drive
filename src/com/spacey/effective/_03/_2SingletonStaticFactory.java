package com.spacey.effective._03;

public class _2SingletonStaticFactory {

	public static void main(String[] args) {
		
	}

	// Singleton with static factory
	public static class Elvis {
		private static final Elvis INSTANCE = new Elvis();

		private Elvis() {
		}

		public static Elvis getInstance() {
			return INSTANCE;
		}

		public void leaveTheBuilding() {
		}
	}
}
