package com.spacey.effective._20;

/**
 * Unfinished
 * 
 * @author Farooque
 *
 */
public class _4AbstractContainedInstanceEx {
	public static void main(String[] args) {
		// Animal2 Animal2 = new Lion2();
		Animal Animal2 = new Lion2(new AbstractAnimal("no-one") {
			public void eat() {
				System.out.println(name + " eating way");
			}

			public void kill() {
				System.out.println(name + " killing way");
			}
		});
		Animal2.wayToEat();
	}

	/**
	 * As you cannot introduce a state in an interface, the abstract class is needed
	 * to do so
	 * 
	 * @author Farooque
	 *
	 */
	static interface Animal {
		// String name;
		void eat();

		void kill();

		void wayToEat();
	}

	static abstract class AbstractAnimal implements Animal {
		String name;

		public AbstractAnimal(String by) {
			System.out.println("Abstract Cons() " + by);
			name = by;
		}

		public String getName() {
			return name;
		}

		public void eat() {
			System.out.println("Throwing error in eat()");
		}

		public void kill() {
			System.out.println("Throwing error in kill()");
		}

		// newly introduced
		public void wayToEat() {
			kill();
			eat();
		}
	}

	// class Lion2 implements Animal2 {
	// @Override
	// public void eat() {
	// System.out.println("Lion2 eat()");
	// }
	//
	// @Override
	// public void kill() {
	// System.out.println("Lion2 kill()");
	// }
	// }

	static class Lion2 implements Animal {
		// public Lion2(String by) {
		// super(by);
		// }
		private Animal abstractAnimal;

		public Lion2(Animal animal2) {
			abstractAnimal = animal2;
			// TODO Auto-generated constructor stub
		}

		@Override
		public void eat() {
			System.out.println("Lion2 eat()");
		}

		@Override
		public void kill() {
			System.out.println("Lion2 kill()");
		}

		@Override
		public void wayToEat() {
			abstractAnimal.wayToEat();
		}
	}

}
