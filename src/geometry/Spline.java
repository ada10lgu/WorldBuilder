package geometry;



import java.util.ArrayList;

import geometry.*;

public class Spline {
	ArrayList<Point> points;
	ArrayList<Bezier> bez;

	public Spline(Point...knots) {
		points = new ArrayList<Point>();
		points.add(knots[0]);
		for(int k=1;k<knots.length;k++){
			this.addKnot(knots[k]);
		}
	}
	
	public void addKnot(Point knot){
		long[] cord;
		cord = new long[knot.axisNr()];
		for(int j=0;j<knot.axisNr();j++){
			cord[j] = knot.get(j)-points.get(points.size()).get(j);
		}
		points.add(new Point(cord));
		points.add(knot);
	}
	
	public void init(){				//init and creates bezier curves
		bez = new ArrayList<Bezier>();
		for(int k=1;k<points.size()-1;k=k+3){
			Bezier b = new Bezier();
			b.addPoint(points.get(k));
			b.addPoint(points.get(k+1));
			b.addPoint(points.get(k+2));
		}
	}
	
	public long[] calSpline(long t, Point p0, Point p2){
		long[] value = new long[p0.axisNr()];
		int k0 = points.indexOf(p0);
		int k2 = points.indexOf(p2);
		int k1;
		if(k0<k2){
			k1 = k0+1;
		}else{
			k1 = k2+1;
		}
		
		return value;
	}
}
