package geometry;

import java.util.ArrayList;
import java.util.List;

public class Point {

	private long[] point;
	
	public Point(long...pos) {
		point = pos;
	}
	

	public long getX() {
		return point[0];

	}

	public long getY() {
		return point[1];
	}
	
	public long getZ(){
		return point[2];
	}
	
	public long get(int n){
		return point[n];
	}
	
	public int axisNr(){
		return point.length;
	}

	@Override
	public String toString() {
		return "[" + point[0] + "," + point[1] + "]";
	}

	public List<Point> neighbours() {
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(point[0], point[1] + 1));
		points.add(new Point(point[0], point[1] - 1));
		points.add(new Point(point[0] + 1, point[1]));
		points.add(new Point(point[0] - 1, point[1]));

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
