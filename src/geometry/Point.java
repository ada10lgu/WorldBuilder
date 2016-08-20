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
		
		return points;
	}
}
