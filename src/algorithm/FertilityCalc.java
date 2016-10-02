package algorithm;

import java.util.Random;

import node_world_structure.Regions;

public class FertilityCalc extends Algorithm<Regions, Double>{

	double ALT_FERTILITY;
	
	public FertilityCalc(Random r) {
		super(r);
		ALT_FERTILITY = 3000;
	}

	@Override
	public Double calculate(Regions... data) {
		
		double fert = 0;
		for(int i=0;i<data.length;i++){
			fert = r.nextDouble()*((3000-data[i].getAlti())/3000)/4 +((3000-data[i].getAlti())/3000)*3/4;
			data[i].setFertility(fert);
		}
		return fert;
	}
	
}
