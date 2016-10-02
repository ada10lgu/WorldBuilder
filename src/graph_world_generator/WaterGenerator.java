package graph_world_generator;

import java.util.ArrayList;
import java.util.Random;

import geometry.*;
import node_world_structure.Regions;

public class WaterGenerator {
	Random r;
	
	public WaterGenerator(Random r){
		this.r = r;	
		}
	
	public ArrayList<Spline> generate(Regions...world){
		ArrayList<Spline> answer = new ArrayList<Spline>();
		
		// sea coast
		ArrayList<Point> q1 = new ArrayList<Point>(); 
		ArrayList<Point> q2 = new ArrayList<Point>(); 
		ArrayList<Point> q3 = new ArrayList<Point>(); 
		ArrayList<Point> q4 = new ArrayList<Point>(); 
		
		double minX = 0;
		double maxX = 0;
		double minY = 0;
		double maxY = 0;
		
		for(Regions reg : world){
			double x = reg.getCoord().getX();
			double y = reg.getCoord().getY();
			if(x<minX){
				minX = x;
			}else if(x>maxX){
				maxX = x;
			}
			if(y<minY){
				minY = y;
			}else if(y>maxY){
				maxY = y;
			}
			
			if(x>0){
				if(y>0){
					q1.add(reg.getCoord());
				}else{
					q4.add(reg.getCoord());
				}
			}else{
				if(y>0){
					q2.add(reg.getCoord());
				}else{
					q3.add(reg.getCoord());
				}
			}
		}
		
		ArrayList<Point> coast = new ArrayList<Point>();
		coast.add(new Point(maxX, 0, 0));
		coast.add(new Point(maxX, maxY, 0));
		coast.add(new Point(0, maxY, 0));
		coast.add(new Point(minX, maxY, 0));
		coast.add(new Point(minX, 0, 0));
		coast.add(new Point(minX, minY, 0));
		coast.add(new Point(0, minY, 0));
		coast.add(new Point(maxX, minY, 0));
		coast.add(new Point(maxX, 0, 0));
		Spline tmp = new Spline(coast);
		
		answer.add(tmp);
		return answer;
	}
}
