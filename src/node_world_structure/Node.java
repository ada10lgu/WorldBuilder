package node_world_structure;

import java.util.ArrayList;

import geometry.Point;

public class Node {
	private ArrayList<Road> roads;
	private Point p;
	private String name;
	
	public Node(){
		p = null;
		roads = new ArrayList<Road>();
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
}
