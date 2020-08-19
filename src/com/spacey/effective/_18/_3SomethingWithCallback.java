package com.spacey.effective._18;

/**
 * The problem is that even though we called doSomething() on the wrapper, the
 * callback of the wrapped object got called, not the callback of the wrapper.
 * This is what Joshua Bloch refers to when he says that "callbacks elude the
 * wrapper".
 * 
 * @author Farooque
 *
 */
interface _3SomethingWithCallback {

	void doSomething();

	void call();

}

class WrappedObject implements _3SomethingWithCallback {

	private final SomeService service;

	WrappedObject(SomeService service) {
		this.service = service;
	}

	@Override
	public void doSomething() {
		service.performAsync(this);
	}

	@Override
	public void call() {
		System.out.println("WrappedObject callback!");
	}
}

class Wrapper implements _3SomethingWithCallback {

	private final WrappedObject wrappedObject;

	Wrapper(WrappedObject wrappedObject) {
		this.wrappedObject = wrappedObject;
	}

	@Override
	public void doSomething() {
		wrappedObject.doSomething();
	}

	void doSomethingElse() {
		System.out.println("We can do everything the wrapped object can, and more!");
	}

	@Override
	public void call() {
		System.out.println("Wrapper callback!");
	}
}

final class SomeService {

	void performAsync(_3SomethingWithCallback callback) {
		new Thread(() -> {
			perform();
			callback.call();
		}).start();
	}

	void perform() {
		System.out.println("Service is being performed.");
	}

	public static void main(String[] args) {
		SomeService service = new SomeService();
		WrappedObject wrappedObject = new WrappedObject(service);
		Wrapper wrapper = new Wrapper(wrappedObject);
		wrappedObject.doSomething();
		wrapper.doSomething();
	}
}
