package node_world_structure;

import java.util.ArrayList;

public class Road {
	
	String name;
	ArrayList<Regions> road;
	
	public Road(Regions n0, Regions n1){
		name = null;
		road = new ArrayList<Regions>();
		road.add(n0);
		road.add(n1);
	}
	
	public void addNode(Regions n){
		road.add(n);
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public Regions[] getNodes(){
		Regions[] answer = new Regions[2];
		answer[0] = road.get(0);
		answer[1] = road.get(road.size()-1);
		return answer;
	}
	public double length(){
		
		// rewrite for muliple nodes in road
		double answer = road.get(0).getCoord().distance(road.get(road.size()-1).getCoord());
		return answer;
	}
}
