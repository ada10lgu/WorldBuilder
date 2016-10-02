package node_world_structure;

import java.util.ArrayList;

import geometry.Point;
import geometry.Spline;

abstract class Place {
	
	// attributes for region
	int pop;
	private ArrayList<Road> roads;
	private Point p;
	private String name;
	
	private Spline coast;
	
	//node attributes
	static double fertility; 
	
	public Place() {
		coast = null;
		name = "";
		roads = new ArrayList<Road>();
		p = new Point(0,0,0);
		fertility = 1.0;
	}
	
	public void setCoast(Spline sp){
		coast = sp;
	}
	public Spline getCoast(){
		return coast;
	}
	
	public boolean hasCoast(){
		if(coast!=null){
			return true;
		}
		return false;
	}
	
	public void setPop(double d){
		this.pop = (int) d;
	}
	
	public int getPop(){
		return pop;
	}

	public void setAlti(double alt) {
		p.setZ(alt);
	}

	public double getAlti() {
		return p.getZ();
	}

	public void setCoord(Point p) {
		this.p.setX(p.getX());
		this.p.setY(p.getY());
	}

	public Point getCoord() {
		return p;
	}

	public void addRoad(Road n) {
		roads.add(n);
	}

	public ArrayList<Road> getRoads() {
		return roads;
	}

	public void setName(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setFertility(double fert) {
		fertility = fert;
	}

	public double getFertility() {
		return fertility;
	}
}
