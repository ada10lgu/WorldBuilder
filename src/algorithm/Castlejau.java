package algorithm;

import geometry.Point;

import java.util.ArrayList;
import java.util.Random;

public class Castlejau {

	private ArrayList<Bezier> beziers;
	
	public Castlejau() {
		beziers = new ArrayList<Bezier>();
	}
	
	public void plot(ArrayList<Point> points){	
		Bezier bez = new Bezier();
		bez.addPoints(points);
		beziers.add(bez);
	}

	
}

