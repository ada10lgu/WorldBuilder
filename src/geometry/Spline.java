package geometry;



import java.util.ArrayList;

import geometry.*;

public class Spline {
	ArrayList<Point> points;
	ArrayList<Bezier> bez;
	int a;

	public Spline(Point...knots) {
		/**
		 * creates a spline with 0.5 ratio cord to knot
		 * to create custom add individual knots with double c argument
		*/
		a = 0;
		points = new ArrayList<Point>();
		if(knots.length>0){
			points.add(knots[0]);
		}
		
		for(int k=1;k<knots.length;k++){
			this.addKnot(knots[k]);
		}
	}
	
	public void addKnot(Point knot){
		/**
		 * 	Standard knot creates with a cord 0.5u between last knot and new knot 
		 */
		this.addKnot(knot, 0.5);
	}
	
	public void addKnot(Point knot, double c){
		/*
		 * 	old knot ----(1-c)---- cord ---(c)----- new knot
		 */
		double[] cord;
		if(a==0 || a>knot.axisNr()){
			a= knot.axisNr();
		}
		cord = new double[a];
		for(int k=0;k<a;k++){
			cord[k] =  ((1-c)*points.get(points.size()-1).get(k)+c*knot.get(k));  
		}
		points.add(new Point(cord));
		points.add(knot);
	}
	
	public void init(){				//initiate and creates bezier curves
		bez = new ArrayList<Bezier>();
		
		for(int k=0;k<points.size();k=k+2){
			Bezier b = new Bezier();
			if(k!=0){
				b.addPoint(points.get(k-1));
			}
			b.addPoint(points.get(k));
			if(k+1<points.size()){
				b.addPoint(points.get(k+1));
			}
			bez.add(b);
		}
	}
	
	public double[] calStrictSpline(double u){
		/**
		 * Strict Spline calculation without any more then bezier three points
		 */
		double[] value = new double[a];
		double u0 = 1/((double)bez.size());
		double ui = 0;
		while(u>=ui+u0){
			ui = ui+u0;
		}
		
		double uj = ui+u0;						//ui+1
		int k = (int)(u*bez.size());
		double t = (u-ui)/(uj-ui);
		value = bez.get(k).calBez(t);
		return value;
	}
	
	public void join(){
		
		// not sure if it works
		bez.remove(0);
		bez.remove(bez.size()-1);
		points.remove(0);
		points.add(points.get(0));
		Bezier bezier = new Bezier();
		bezier.addPoint(points.remove(0));
		bezier.addPoint(points.get(points.size()-1));
		bezier.addPoint(points.get(points.size()-2));
		bez.add(bezier);
	}
}
