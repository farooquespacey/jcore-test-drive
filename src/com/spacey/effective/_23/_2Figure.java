package com.spacey.effective._23;

// Class hierarchy replacement for a tagged class
abstract class _2Figure {
	abstract double area();
}

class Circle extends _2Figure {
	final double radius;

	Circle(double radius) {
		this.radius = radius;
	}

	@Override
	double area() {
		return Math.PI * (radius * radius);
	}
}

class Rectangle extends _2Figure {
	final double length;
	final double width;

	Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	@Override
	double area() {
		return length * width;
	}
}

// also possible to do this
class Square extends Rectangle {
	Square(double side) {
		super(side, side);
	}
}