package com.spacey.effective._18;

//basic class which we will wrap
class Model {
	Controller controller;

	Model(Controller controller) {
		this.controller = controller;
		controller.register(this); // Pass SELF reference
	}

	public void makeChange() {
		System.out.println("Making changes");
	}
}

class Controller {
	private Model model;

	public void register(Model model) {
		this.model = model;
	}

	// Here the wrapper just fails to count changes,
	// because it does not know about the wrapped object
	// references leaked
	public void doChanges() {
		model.makeChange();
	}
}

// wrapper class
public class _3ModelChangesCounter {
	private Model model;
	public int changesMade;

	_3ModelChangesCounter(Model model) {
		this.model = model;
	}

	// The wrapper is intended to count changes,
	// but those changes which are invoked from
	// Controller are just skipped
	public void makeChange() {
		model.makeChange();
		changesMade++;
	}

	public static void main(String[] args) {
		Controller controller = new Controller();
		Model model = new Model(controller);
		_3ModelChangesCounter modelChangesCounter = new _3ModelChangesCounter(model);
		model.makeChange();
//		controller.register(model);
//		controller.doChanges();
		System.out.println(modelChangesCounter.changesMade);
	}
}