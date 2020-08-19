package com.spacey.effective._20;

/**
 * simulate multiple inheritance
 * +++++++++++++++++++++++++++++
 * 
 * Suppose you have 2 kinds of things in your domain : Trucks and Kitchens
 * 
 * Trucks have a driveTo() method and Kitchens a cook() method.
 * 
 * Now suppose Pauli decides to sell pizzas from the back of a delivery truck.
 * He wants a thing where he can driveTo() and cook() with.
 * 
 * In C++ he would use multiple inheritance to do this.
 * 
 * In Java that was considered to be too dangerous so you can inherit from a
 * main class, but you can "inherit" behaviors from interfaces, which are for
 * all intents and purposes abstract classes with no fields or method
 * implementations.
 * 
 * So in Java we tend to implement multiple inheritance using delegations :
 * 
 * Pauli subclasses a truck and adds a kitchen to the truck in a member variable
 * called kitchen. He implements the Kitchen interface by calling
 * kitchen.cook().
 * 
 * 
 * <See Below code>
 * 
 * 
 * He is a happy man because he can now do things like ;
 * 
 * pizzaTruck.driveTo(beach); pizzaTruck.cook(pizzaWithExtraAnchovies); Ok, this
 * silly story was to make the point that it is no simulation of multiple
 * inheritance, it is real multiple inheritance with the proviso that you can
 * only inherit the contract, only inherit from empty abstract base classes
 * which are called interfaces.
 * 
 * (update: with the coming of default methods interfaces now can also provide
 * some behavior to be inherited)
 * 
 * @author Farooque
 *
 */

class Truck {
	public void driveTo(String place) {
		System.out.println("Driving to " + place);
	}
}

interface Kitchen {
	public void cook(String food);
}

class _2PizzaTruck extends Truck implements Kitchen {
	Kitchen kitchen;
	
	public _2PizzaTruck(Kitchen kitchen) {
		this.kitchen = kitchen;
	}

	public void cook(String foodItem) {
		kitchen.cook(foodItem);
//		System.out.println("Cooking " + foodItem);
	}
	
	public static void main(String[] args) {
		_2PizzaTruck pizzaTruck = new _2PizzaTruck(new Kitchen() {
			
			@Override
			public void cook(String food) {
				System.out.println("Cooking " + food);
			}
		});
//		_2PizzaTruck pizzaTruck = new _2PizzaTruck();
		pizzaTruck.driveTo("Marina");
		pizzaTruck.cook("pizzaWithExtraAnchovies");
	}
}