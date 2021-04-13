package com.spacey.effective._05;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class _4FactoryMethodPattern {

	public static void main(String[] args) {
		Shape shape = ShapeFactory.getShape("circle");
		shape.draw();
	}

	static class ShapeFactory {

		public ShapeFactory() {
			System.out.println("Shape Factory");
		}

		final static Map<String, Supplier<Shape>> map = new HashMap<>();
		static {
			map.put("CIRCLE", Circle::new);
			map.put("RECTANGLE", Rectangle::new);
		}

		public static Shape getShape(String shapeType) {
			Supplier<Shape> shape = map.get(shapeType.toUpperCase());
			if (shape != null) {
				System.out.println("Calling shape.get()");
				return shape.get();
			}
			throw new IllegalArgumentException("No such shape " + shapeType.toUpperCase());
		}
	}

	static interface Shape {
		void draw();
	}

	static class Rectangle implements Shape {

		public Rectangle() {
			System.out.println("Rect");
		}

		@Override
		public void draw() {
			System.out.println("Inside Rectangle::draw() method.");
		}
	}

	static class Circle implements Shape {
		public Circle() {
			System.out.println("Circle");
		}

		@Override
		public void draw() {
			System.out.println("Inside Circle::draw() method.");
		}
	}

}
