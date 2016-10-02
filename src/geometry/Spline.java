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
	
	public Spline(ArrayList<Point> points) {
		/**
		 * creates a spline with 0.5 ratio cord to knot
		 * to create custom add individual knots with double c argument
		*/
		a = 0;
		this.points=points;
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
	
	public Point calStrictSpline(double u){
		init();
		/**
		 * Strict Spline calculation without any more then bezier three points
		 */
		Point value = new Point(0,0,0);
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
		// remove end beziers with only two points
		bez.remove(0);
		bez.remove(bez.size()-1);
		
		points.remove(0);			// remove extra point
		points.add(points.remove(0));
		Bezier bezier = new Bezier();
		bezier.addPoint(points.get(points.size()-3));
		bezier.addPoint(points.get(points.size()-2));
		bezier.addPoint(points.get(points.size()-1));
		bez.add(bezier);
	}
	
	
	public Spline split(double u){
		init();
		
		double u0 = 1/((double)bez.size());
		double ui = 0;
		while(u>=ui+u0){
			ui = ui+u0;
		}
		int k = (int)(u*bez.size());
		
		ArrayList<Bezier> bez1 = new ArrayList<Bezier>();
		ArrayList<Bezier> bez2 = new ArrayList<Bezier>();
		Bezier b1 = null;
		
		for(int i=0;i<bez.size();i++){
			if(i<k){
				bez1.add(bez.get(i));
			}else if(i==k){
				b1=bez.get(i);
			}else if(i>k){
				bez2.add(bez.get(i));
			}
		}
		double uj = ui+u0;						//ui+1
		double t = (u-ui)/(uj-ui);
		Bezier b2 = b1.split(t);
		
		
		// add split bezier to both new and old
		bez1.add(b1);
		bez2.add(0, b2);
		
		ArrayList<Point> points1 = new ArrayList<Point>();
		ArrayList<Point> tmp = null;
		for(Bezier b : bez1){
			tmp = b.getPoints();
			points1.addAll(tmp);
			points1.remove(points1.size()-1);
		}
		points1.add(tmp.get(tmp.size()-1));
		points=points1;
		
		ArrayList<Point> points2 = new ArrayList<Point>();
		for(Bezier b : bez1){
			tmp = b.getPoints();
			points2.addAll(tmp);
			points2.remove(points1.size()-1);
		}
		points2.add(tmp.get(tmp.size()-1));
		
		Spline newSp = new Spline(points2);
		return newSp;
	}
	
	public boolean modify(Point p0, Point pn){
		for(Point p : points){
			if(p.getX()==p0.getX() && p.getY()==p0.getY()){
				p=pn;
				return true;
			}
		}
		return false;
	}
}
