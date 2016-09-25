package node_world_structure;

import java.util.ArrayList;

public class World {
	
	ArrayList<Node> regions;
	
	public World(){
		regions = new ArrayList<Node>();
	}
	
	public void addRegion(Node n){
		regions.add(n);
	}
	
	public void addRegions(ArrayList<Node> regions){
		this.regions.addAll(regions);
	}
	
	public int size(){
		return regions.size();
	}
}
