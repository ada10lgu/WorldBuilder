package algorithm;

import java.util.ArrayList;
import java.util.Random;
import commodity.*;
import node_world_structure.Regions;

public class CommodityCalc extends Algorithm<Regions, ArrayList<Commodity>>{

	
	static double WOOD_AMOUNT_PER_CAP;
	
	
	static double WOOD_PLANK_RATIO;
	
	
	public CommodityCalc(Random r) {
		super(r);
		WOOD_AMOUNT_PER_CAP = 0.01;
		WOOD_PLANK_RATIO = 0.8;
	}

	@Override
	public ArrayList<Commodity> calculate(Regions... data) {
		
		ArrayList<Commodity> commodities = new ArrayList<Commodity>();
		
		
		for(int i=0;i<data.length;i++){
			// timber production
			double timberAmount = data[i].getPop()*WOOD_AMOUNT_PER_CAP*data[i].getFertility();
			Timber timber = new Timber((int) timberAmount);
			commodities.add(timber);
			
			
			// complex goods
			double woodplanks = timberAmount*WOOD_PLANK_RATIO;
			Woodplanks planks = new Woodplanks((int) woodplanks);
			commodities.add(planks);
		}
		
		return commodities;
	}

}
