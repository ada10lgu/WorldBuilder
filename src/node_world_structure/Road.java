package node_world_structure;

import java.util.ArrayList;

public class Road {
	
	ArrayList<Node> road;
	
	public Road(Node n0, Node n1){
		road = new ArrayList<Node>();
		road.add(n0);
		road.add(n1);
	}
	
	public void addNode(Node n){
		road.add(n);
	}
}
