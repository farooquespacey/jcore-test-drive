package com.spacey.effective._20;

public class _3AbstractUseCase {
	public static void main(String[] args) {
		// Animal animal = new Lion();
		Animal animal = new Lion("no-name");
		animal.wayToEat();
	}
}

/**
 * As you cannot introduce a state in an interface, and also the wayToEat() is
 * same accross implementaions(interface default still allows you that tho) the
 * abstract class is needed
 * 
 * @author Farooque
 *
 */
interface Animal {
	// String name;
	void eat();

	void kill();

	void wayToEat();
}

abstract class AbstractAnimal implements Animal {
	String name;

	public AbstractAnimal(String by) {
		System.out.println("Abstract Cons() " + by);
		name = by;
	}

	public String getName() {
		return name;
	}

	// newly introduced (can be common accros other concrete classes)
	public void wayToEat() {
		kill();
		eat();
	}

	// Or
	// @Override
	// public void eat() {
	// System.out.println("Throwing eat error");
	// }
	//
	// @Override
	// public void kill() {
	// System.out.println("Throwing kill error");
	// }

}

// class Lion implements Animal {
// @Override
// public void eat() {
// System.out.println("Lion eat()");
// }
//
// @Override
// public void kill() {
// System.out.println("Lion kill()");
// }
// }

class Lion extends AbstractAnimal {
	public Lion(String by) {
		super(by);
	}

	@Override
	public void eat() {
		System.out.println("Lion eat()");
	}

	@Override
	public void kill() {
		System.out.println("Lion kill()");
	}

}
