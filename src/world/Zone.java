package world;

import geometry.Point;

public class Zone {
	private Point p;
	private boolean[] coast;

	public Zone(Point p) {
		this.p = p;
		coast = new boolean[4];
	}

	public long getX() {
		return (long)p.getX();
	}

	public long getY() {
		return (long)p.getY();
	}

	public double getAltitude() {
		return p.getZ();
	}

	public void addAltitude(double d) {
		p.setZ(d);
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
	
	public boolean compare(Zone z){
		boolean same = false;
		if(z.getPoint().getX()==p.getX() && z.getPoint().getY()==p.getY() && z.getPoint().getZ()==p.getZ()){
			same = true;
		}
		return same;
	}

	@Override
	public String toString() {
		return "[" + getX() + "," + getY() + " (" + getAltitude() + ")]";
	}
}
