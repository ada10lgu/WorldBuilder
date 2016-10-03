package generator;

import java.util.ArrayList;
import java.util.Random;

import node_world_structure.Regions;
import node_world_structure.World;

public class DescriptGenerator {
	Random r;
	
	public DescriptGenerator(Random r){
		this.r = r;
	}
	
	public String descript(World w){
		String desc = "Between the legends and the unknown lay the world of "+ w.getName()+".";
		
		ArrayList<Regions> regions = w.getRegions();
		for(Regions r : regions){
			desc = desc+"; "+r.getName(); 
		}
		
		return desc;
	}
}
