package node_world_structure;

public class Regions extends Node {
	
	// unique attributes for region
	int pop;
	
	public Regions() {
		super();
	}
	
	public void setPop(double d){
		this.pop = (int) d;
	}
	
	public int getPop(){
		return pop;
	}
}
