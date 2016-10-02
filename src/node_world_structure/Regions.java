package node_world_structure;

import java.util.ArrayList;

public class Regions extends Place implements PlaceInterface {
	
	ArrayList<Settlement> settlements; 
	
	public Regions() {
		super();
		settlements = new ArrayList<Settlement>();
	}
	
	public void addRegion(Settlement...sett){
		for(Settlement s : sett){
			settlements.add(s);
		}
	}
}
