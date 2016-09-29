package algorithm;

import java.util.Random;

import node_world_structure.Regions;

public class PopCalculate extends Algorithm<Regions, Integer>{

	static private int popconstant;
	
	public PopCalculate(Random r) {
		super(r);
		popconstant = 40000;
	}

	@Override
	public Integer calculate(Regions... data) {
		int totpop = popconstant*data.length;
		int pop = 0;
		for(int i=0;i<data.length;i++){
			pop = r.nextInt(totpop/data.length-(popconstant/4))+popconstant/4;
			data[i].setPop(pop*data[i].getFertility());
			totpop = totpop - pop;
		}
		return totpop;
	}
}
