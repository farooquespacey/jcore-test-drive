package com.spacey.effective._19;

/**
 * Here, when Base constructor calls overrideMe, Child has not finished
 * initializing the final int x, and the method gets the wrong value. This will
 * almost certainly lead to bugs and errors.
 * 
 * @author Farooque
 *
 */
public class ConstructorCallsOverride {
	public static void main(String[] args) {

		abstract class Base {
			Base() {
				overrideMe();
			}

			abstract void overrideMe();
		}

		class Child extends Base {

			final int x;

			Child(int x) {
				this.x = x;
			}

			@Override
			void overrideMe() {
				System.out.println(x);
			}
		}
		new Child(42); // prints "0"
	}
}