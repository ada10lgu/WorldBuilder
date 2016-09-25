package node_world_structure;

import java.util.ArrayList;

import geometry.Point;

public class Node {
	private ArrayList<Road> roads;
	private Point p;
	private String name;
	
	public Node(String name){
		p = null;
		roads = new ArrayList<Road>();
		this.name = name;
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
	
	public String getName(){
		return name;
	}
}
