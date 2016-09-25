package world;

import geometry.Point;
import java.util.ArrayList;

public class World {
	private ArrayList<Zone> zones;
	private Zone[][] grid;
	private ArrayList<Zone> coast;
	
	public World(){
		zones = new ArrayList<Zone>();
	}
	
	public void addZones(ArrayList<Zone> input){
		zones.addAll(input);
	}
	
	public void init(){
		
	}
	
	public ArrayList<Zone> getZones(){
		return zones;
	}

}
