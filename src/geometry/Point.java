package geometry;

import java.util.ArrayList;
import java.util.List;

public class Point {
	

	private double[] point;
	
	public Point(double...pos) {
		point = pos;
	}
	

	public double getX() {
		return point[0];
	}
	
	public void setX(double x){
		point[0] = x;
	}
	
	public double getY() {
		return point[1];
	}
	public void setY(double y){
		point[1] = y;
	}
	
	public double getZ(){
		return point[2];
	}
	public void setZ(double z){
		point[2] = z;
	}
	
	public double get(int n){
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
		points.add(new Point(point[0], point[1] + 1, 0));
		points.add(new Point(point[0], point[1] - 1, 0));
		points.add(new Point(point[0] + 1, point[1], 0));
		points.add(new Point(point[0] - 1, point[1], 0));

		return points;
	}
	
	public double distance(Point p){
		double dist = Math.sqrt(p.getX()*p.getX() + p.getY()*p.getY() +p.getZ()*p.getZ());
		return dist;
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
