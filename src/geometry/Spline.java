package geometry;



import java.util.ArrayList;

import geometry.*;

public class Spline {
	ArrayList<Point> points;
	ArrayList<Bezier> bez;
	int a;

	public Spline(Point...knots) {
		a = 0;
		points = new ArrayList<Point>();
		points.add(knots[0]);
		for(int k=1;k<knots.length;k++){
			this.addKnot(knots[k]);
		}
	}
	
	public void addKnot(Point knot){
		double[] cord;
		if(a==0 || a>knot.axisNr()){
			a= knot.axisNr();
		}
		cord = new double[a];
		for(int k=0;k<a;k++){
			cord[k] =  (points.get(points.size()-1).get(k)+knot.get(k))/2;  
		}
		points.add(new Point(cord));
		points.add(knot);
	}
	
	public void init(){				//init and creates bezier curves
		bez = new ArrayList<Bezier>();
		
		for(int k=0;k<points.size();k=k+2){
			System.out.println(k+" "+points.size());
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
	
	public double[] calSpline(double u){
		init();
		double[] value = new double[a];
		double u0 = 1/((double)bez.size());
		double ui = 0;
		while(u>ui+u0){
			ui = ui+u0;
		}
		double uj = ui+u0;						//ui+1
		int k = (int)(u*bez.size());
		double t = (u-ui)/(uj-ui);
		value = bez.get(k).calBez(t);
		return value;
	}
}
