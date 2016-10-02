package node_world_structure;

import java.util.ArrayList;

import geometry.Spline;

public class World extends Place {
	
	ArrayList<Regions> regions;
	ArrayList<Spline> coast;
	
	public World(){
		regions = new ArrayList<Regions>();
		coast = new ArrayList<Spline>();
	}
	
	public void addRegion(Regions...reg){
		for(int i=0;i<reg.length;i++){
			regions.add(reg[i]);
		}
	}
	
	public void addRegions(ArrayList<Regions> regions){
		this.regions.addAll(regions);
	}
	
	public ArrayList<Regions> getRegions(){
		return regions;
	}
	
	public int size(){
		return regions.size();
	}
	
	public void addShore(Spline...sp){
		for(Spline s : sp){
			coast.add(s);
		}
	}
	
	public ArrayList<Spline> getShores(){
		return coast;
	}
}
