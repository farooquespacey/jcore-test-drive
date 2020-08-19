package com.spacey.effective._10;

public class _4SymmetrySolutionViolatesTransitivity {

	public static void main(String[] args) {
		Point p = new Point(1, 2);
		ColorPoint cp = new ColorPoint(1, 2, Color.RED);
		// symmetry solved
		System.out.println(p.equals(cp)); // true
		System.out.println(cp.equals(p)); // true

		// but violated trnasitivity
		ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
		Point p2 = new Point(1, 2);
		ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);
		System.out.println(p1.equals(p2)); // true
		System.out.println(p2.equals(p3)); // true
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

	static class ColorPoint extends Point {
		private final Color color;

		public ColorPoint(int x, int y, Color color) {
			super(x, y);
			this.color = color;
		}

		// You might try to fix the symmetry problem by having ColorPoint.equals ignore
		// color when doing “mixed comparisons”:
		// Broken - violates transitivity!
		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Point))
				return false;
			// If o is a normal Point, do a color-blind comparison
			if (!(o instanceof ColorPoint))
				return o.equals(this);
			// o is a ColorPoint; do a full comparison
			return super.equals(o) && ((ColorPoint) o).color == color;
		}
		// Remainder omitted
	}

	static enum Color {
		RED, BLUE, GREEN, YELLOW, BLACK, WHITE
	}

}
