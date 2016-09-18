package world;

import geometry.Point;

public class Zone {
	private Point p;
	private double altitude;
	private boolean[] coast;

	public Zone(Point p, double altitude) {
		this.p = p;
		coast = new boolean[4];
		this.altitude = altitude;
	}

	public long getX() {
		return (long)p.getX();
	}

	public long getY() {
		return (long)p.getY();
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
	
	public void setCoast(boolean w, boolean n, boolean e, boolean s){
		coast[0]=w;
		coast[1]=n;
		coast[2]=e;
		coast[3]=s;
	}
	
	public boolean[] getCoastBoolean(){
		return coast;
	}

	@Override
	public String toString() {
		return "[" + getX() + "," + getY() + " (" + getAltitude() + ")]";
	}
}
