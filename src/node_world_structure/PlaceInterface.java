package node_world_structure;

import java.util.ArrayList;

import geometry.Point;

public interface PlaceInterface {
	
	public void setAlti(double alt);
	
	public double getAlti();
	
	public void setCoord(Point p);
	
	public Point getCoord();
	
	public void addRoad(Road n);
	
	public ArrayList<Road> getRoads();
	
	public void setName(String name);
	
	public String getName();
	
	public void setFertility(double fert);
	
	public double getFertility();
}
