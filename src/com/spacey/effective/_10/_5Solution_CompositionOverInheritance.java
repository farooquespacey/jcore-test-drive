package com.spacey.effective._10;

import java.util.Objects;

public class _5Solution_CompositionOverInheritance {

	public static void main(String[] args) {
		Point p = new Point(1, 2);
		ColorPoint cp = new ColorPoint(1, 2, Color.RED);
		Point cpAsP = cp.asPoint();
		// symmetry solved
		System.out.println(p.equals(cpAsP)); // true
		System.out.println(cpAsP.equals(p)); // true

		// trnasitivity solved
		ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
		Point p2 = new Point(1, 2);
		ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);
		System.out.println(p1.asPoint().equals(p2)); // true
		System.out.println(p2.equals(p3.asPoint())); // true
		System.out.println(p1.equals(p3)); // false
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

	// Adds a value component without violating the equals contract
	static class ColorPoint {
		private final Point point;
		private final Color color;

		public ColorPoint(int x, int y, Color color) {
			point = new Point(x, y);
			this.color = Objects.requireNonNull(color);
		}

		/**
		 * Returns the point-view of this color point.
		 */
		public Point asPoint() {
			return point;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof ColorPoint))
				return false;
			ColorPoint cp = (ColorPoint) o;
			return cp.point.equals(point) && cp.color.equals(color);
		}
		// Remainder omitted
	}

	static enum Color {
		RED, BLUE, GREEN, YELLOW, BLACK, WHITE
	}

}
