package geometry;

import java.util.ArrayList;
import java.util.List;

public class Point {

	private long x, y;

	public Point(long x, long y) {
		this.x = x;
		this.y = y;
	}

	public long getX() {
		return x;

	}

	public long getY() {
		return y;
	}

	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}

	public List<Point> neighbours() {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(x, y + 1));
		points.add(new Point(x, y - 1));
		points.add(new Point(x + 1, y));
		points.add(new Point(x - 1, y));

		return points;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point)
			return ((Point) obj).hashCode() == hashCode();
		return false;
	}
}
