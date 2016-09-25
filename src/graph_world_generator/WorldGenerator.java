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
		ArrayList<Node> regions = new ArrayList<Node>();
		while(region>regions.size()){
			regions.add(new Node(nameGen.genRegionName()));
		}
		for(int j=0;j<regions.size();j++){
			int roads = r.nextInt(7);
			for(int i=0;i<roads;i++){
				int k = r.nextInt(regions.size()-1);
				if(j!=i){
					Road road = new Road(regions.get(j), regions.get(k));
					regions.get(j).addRoad(road);
					regions.get(i).addRoad(road);
				}
			}
		}
	}
}
