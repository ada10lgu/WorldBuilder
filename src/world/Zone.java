package world;

import geometry.Point;

public class Zone {
	private Point p;
	private double altitude;

	public Zone(Point p, double altitude) {
		this.p = p;
		this.altitude = altitude;
	}

	public long getX() {
		return p.getX();
	}

	public long getY() {
		return p.getY();
	}

	public double getAltitude() {
		return altitude;
	}

	public void addAltitude(double d) {
		altitude += d;
	}

	public Point getPoint() {
		return p;
	}

	@Override
	public String toString() {
		return "[" + getX() + "," + getY() + " (" + getAltitude() + ")]";
	}
}
