package node_world_structure;

import java.util.ArrayList;

import geometry.Point;

public class Node {
	private ArrayList<Road> roads;
	private Point p;
	private String name;
	
	//node attributes
	static double fertility; 
	double altitude;
	
	public Node(){
		p = null;
		roads = new ArrayList<Road>();
		fertility = 1.0;
		altitude = 0;
	}
	
	public void setAlti(double alt){
		altitude = alt;
	}
	
	public double getAlti(){
		return altitude;
	}
	
	public void setCoord(Point p){
		this.p = p;
	}
	
	public Point getCoord(){
		return p;
	}
	
	public void addRoad(Road n){
		roads.add(n);
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setFertility(double fert){
		fertility = fert;
	}
	
	public double getFertility(){
		return fertility;
	}
}
