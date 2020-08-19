package com.spacey.effective._03;

import java.lang.reflect.Constructor;

public class _3CaveatInPublicFieldOrFactoryWay {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Constructor<?> sampleConstructor = Elvis.class.getDeclaredConstructor();
		System.out.println("sampleConstructor.isAccessible: " + sampleConstructor.isAccessible());
		sampleConstructor.setAccessible(true);
		System.out.println("sampleConstructor.isAccessible: " + sampleConstructor.isAccessible());

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
