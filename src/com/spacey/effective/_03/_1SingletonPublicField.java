package com.spacey.effective._03;

public class _1SingletonPublicField {

	public static void main(String[] args) {

	}

	// Singleton with public final field
	public static class Elvis {
		public static final Elvis INSTANCE = new Elvis();

		private Elvis() {
			// In case client can invoke the private constructor reflectively
			// (Item 65) with the aid of the AccessibleObject.setAccessible method
			// if(askedSecondTime)
			// throw new Exception();
		}

		public void leaveTheBuilding() {
		}
	}

}
