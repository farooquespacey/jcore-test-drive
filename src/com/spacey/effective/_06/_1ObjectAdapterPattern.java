package com.spacey.effective._06;

import java.util.ArrayList;
import java.util.List;

/**
 * When an object is immutable, it is obvious it can be reused safely, but there
 * are other situations where it is far less obvious, even counterintuitive.
 * Consider the case of adapters [Gamma95], also known as views. An adapter is
 * an object that delegates to a backing object, providing an alternative
 * interface. Because an adapter has no state beyond that of its backing object,
 * there’s no need to create more than one instance of a given adapter to a
 * given object.
 * 
 * @author Farooque
 *
 */
public class _1ObjectAdapterPattern {

	public static void main(String[] args) {
		System.out.println("Creating drawing of shapes...");
		Drawing drawing = new Drawing();
		drawing.addShape(new Rectangle());
		drawing.addShape(new Circle());
		drawing.addShape(new GeometricShapeObjectAdapter(new Triangle()));
		drawing.addShape(new GeometricShapeObjectAdapter(new Rhombus()));
		System.out.println("Drawing...");
		drawing.draw();
		System.out.println("Resizing...");
		drawing.resize();
	}

	static interface Shape {
		void draw();

		void resize();

		String description();

		boolean isHide();
	}

	static class Rectangle implements Shape {
		@Override
		public void draw() {
			System.out.println("Drawing Rectangle");
		}

		@Override
		public void resize() {
			System.out.println("Resizing Rectangle");
		}

		@Override
		public String description() {
			return "Rectangle object";
		}

		@Override
		public boolean isHide() {
			return false;
		}
	}

	static class Circle implements Shape {
		@Override
		public void draw() {
			System.out.println("Drawing Circle");
		}

		@Override
		public void resize() {
			System.out.println("Resizing Circle");
		}

		@Override
		public String description() {
			return "Circle object";
		}

		@Override
		public boolean isHide() {
			return false;
		}
	}

	static class Drawing {
		List<Shape> shapes = new ArrayList<Shape>();

		public Drawing() {
			super();
		}

		public void addShape(Shape shape) {
			shapes.add(shape);
		}

		public List<Shape> getShapes() {
			return new ArrayList<Shape>(shapes);
		}

		public void draw() {
			if (shapes.isEmpty()) {
				System.out.println("Nothing to draw!");
			} else {
				shapes.stream().forEach(shape -> shape.draw());
			}
		}

		public void resize() {
			if (shapes.isEmpty()) {
				System.out.println("Nothing to resize!");
			} else {
				shapes.stream().forEach(shape -> shape.resize());
			}
		}
	}

	// Extra ones developed by someone else maybe
	static interface GeometricShape {
		double area();

		double perimeter();

		void drawShape();
	}

	// Part of Extra-Geometric-Shape API
	static class Triangle implements GeometricShape {
		// sides
		private final double a;
		private final double b;
		private final double c;

		public Triangle() {
			this(1.0d, 1.0d, 1.0d);
		}

		public Triangle(double a, double b, double c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public double area() {
			// Heron's formula:
			// Area = SquareRoot(s * (s - a) * (s - b) * (s - c))
			// where s = (a + b + c) / 2, or 1/2 of the perimeter of the triangle
			double s = (a + b + c) / 2;
			return Math.sqrt(s * (s - a) * (s - b) * (s - c));
		}

		@Override
		public double perimeter() {
			// P = a + b + c
			return a + b + c;
		}

		@Override
		public void drawShape() {
			System.out.println("Drawing Triangle with area: " + area() + " and perimeter: " + perimeter());
		}
	}

	// Part of Extra-Geometric-Shape API
	static class Rhombus implements GeometricShape {
		// sides
		private final double a;
		private final double b;

		public Rhombus() {
			this(1.0d, 1.0d);
		}

		public Rhombus(double a, double b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public double area() {
			double s = a * b;
			return s;
		}

		@Override
		public double perimeter() {
			return 2 * (a + b);
		}

		@Override
		public void drawShape() {
			System.out.println("Drawing Rhombus with area: " + area() + " and perimeter: " + perimeter());
		}
	}

	// Object Adapter Pattern
	static class GeometricShapeObjectAdapter implements Shape {
		private GeometricShape adaptee;

		public GeometricShapeObjectAdapter(GeometricShape adaptee) {
			super();
			this.adaptee = adaptee;
		}

		@Override
		public void draw() {
			adaptee.drawShape();
		}

		@Override
		public void resize() {
			System.out.println(description() + " can't be resized. Please create new one with required values.");
		}

		@Override
		public String description() {
			if (adaptee instanceof Triangle) {
				return "Triangle object";
			} else if (adaptee instanceof Rhombus) {
				return "Rhombus object";
			} else {
				return "Unknown object";
			}
		}

		@Override
		public boolean isHide() {
			return false;
		}
	}

}
