package algorithm;

import java.util.Random;

import geometry.*;
import node_world_structure.Regions;

public class CordinateAlgo extends Algorithm<Regions, Regions[]>{

	
	public CordinateAlgo(Random r) {
		super(r);
	}

	@Override
	public Regions[] calculate(Regions... data) {
		// A = pi*a*b
		
		double a = data.length*(r.nextDouble()+1)/2;
		double b = data.length*(r.nextDouble()+1)/2;
		for(Regions reg : data){
			Point tmp = new Point(r.nextDouble()*a*2-a,r.nextDouble()*b*2-b);
			
			reg.setCoord(tmp);
		}
		return data;
	}

}
