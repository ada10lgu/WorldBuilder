package algorithm;

import java.util.Random;

import node_world_structure.Regions;

public class NodeCalculateAltitude extends Algorithm<Regions, Double>{

	static Double ALT_CONSTANT;;
	
	public NodeCalculateAltitude(Random r) {
		super(r);
		ALT_CONSTANT = new Double(3000);
	}
	
	@Override
	public Double calculate(Regions... data) {
		Double alt = r.nextDouble()*ALT_CONSTANT;
		for(int i=0;i<data.length;i++){
			data[i].setAlti(r.nextDouble()*alt*3/4+alt/4);
		}
		return alt;
	}

}
