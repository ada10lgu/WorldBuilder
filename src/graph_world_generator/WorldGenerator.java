package graph_world_generator;

import java.util.ArrayList;
import java.util.Random;
import algorithm.*;
import generator.*;
import geometry.*;
import node_world_structure.*;

public class WorldGenerator {
	
	Random r;
	WorldNumberOfRegions regionAlgo;
	World world;
	NameGen nameGen;
	
	public WorldGenerator(String seed){
		r = new Random(seed.hashCode());
		world = new World();
		regionAlgo = new WorldNumberOfRegions(r);
		nameGen = new NameGen(r);
	} 
	
	public void generate(){
		int region = regionAlgo.calculate();
		ArrayList<Regions> regions = new ArrayList<Regions>();
		while(region>regions.size()){
			regions.add(new Regions());
		}
		
		// connecting the regions with roads
		for(int j=0;j<regions.size();j++){
			int roads = r.nextInt(7);
			for(int i=0;i<roads;i++){
				int k = r.nextInt(regions.size()-1);
				if(j!=i){
					Road road = new Road(regions.get(j), regions.get(k));
					regions.get(j).addRoad(road);
					regions.get(k).addRoad(road);
				}
			}
		}
		
		// setting regions attributes
		
		
		// building and setting cordinates for the regions
		
		// naming regions 
		
		// naming roads
		
		// gerate towns and places in each region based on previous information
	}
}
