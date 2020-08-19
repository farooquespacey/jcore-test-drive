package com.spacey.effective._10;

public class _3SymmetryViolation {

	public static void main(String[] args) {
		Point p = new Point(1, 2);
		ColorPoint cp = new ColorPoint(1, 2, Color.RED);
		// violates symmetry
		System.out.println(p.equals(cp)); // true
		System.out.println(cp.equals(p)); // false
	}

	static class Point {
		private final int x;
		private final int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Point))
				return false;
			Point p = (Point) o;
			return p.x == x && p.y == y;
		}
		// Remainder omitted
	}

	static class ColorPoint extends Point {
		private final Color color;

		public ColorPoint(int x, int y, Color color) {
			super(x, y);
			this.color = color;
		}

		// Broken - violates symmetry!
		@Override
		public boolean equals(Object o) {
			if (!(o instanceof ColorPoint))
				return false;
			return super.equals(o) && ((ColorPoint) o).color == color;
		}
		// Remainder omitted
	}

	static enum Color {
		RED, BLUE, GREEN, YELLOW, BLACK, WHITE
	}

}
