package graph_world_generator;

import java.util.ArrayList;
import java.util.Random;
import algorithm.*;
import generator.*;
import geometry.*;
import node_world_structure.*;

public class WorldGenerator {
	
	Random r;
	WorldNumberOfRegions regionAlgo;			// number of region algo
	PopCalculate popCalc;						// pop algo
	NodeCalculateAltitude altCalc;;				// altitude algo
	FertilityCalc fertCalc;						// fertility algo
	CommodityCalc comCalc;						// commodity algo
	CordinateAlgo	cordcalc;					// coord algo;
	World world;
	NameGen nameGen;							// name algo
	WaterGenerator waterGen;					// generator for water and coast
	
	public WorldGenerator(String seed){
		r = new Random(seed.hashCode());
		world = new World();
		regionAlgo = new WorldNumberOfRegions(r);
		popCalc = new PopCalculate(r);
		nameGen = new NameGen(r);
		altCalc = new NodeCalculateAltitude(r);
		fertCalc = new FertilityCalc(r);
		comCalc = new CommodityCalc(r);
		cordcalc = new CordinateAlgo(r);
		waterGen = new WaterGenerator(r);
	} 
	
	public World generate(){
		int region = regionAlgo.calculate();
		Regions[] regMatris = new Regions[region];
		for(int i=0;i<region;i++){
			regMatris[i] = new Regions();
		}
		// Region location
		cordcalc.calculate(regMatris);
		
		// setting regions attributes
		
		// altitude calculation;
		altCalc.calculate(regMatris);
		fertCalc.calculate(regMatris);
		popCalc.calculate(regMatris);
		comCalc.calculate(regMatris);
		
		// geograph 
		world.addShore(waterGen.generate(regMatris).get(0));
	
		world.setName("Eremoorth");
		// naming regions 
		for(Regions r : regMatris){
			r.setName(nameGen.genRegionName());
		}
		
		// naming roads
		world.addRegion(regMatris);
		return world;
	}
}
