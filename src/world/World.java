package world;

import java.util.ArrayList;

public class World {
	private ArrayList<Zone> zones;
	
	public World(){
		
	}
	
	public void addZones(ArrayList<Zone> zones){
		this.zones=zones;
	}
	
	public ArrayList<Zone> getZones(){
		return zones;
	}
}
