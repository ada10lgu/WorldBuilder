package generator;

import geometry.Point;
import geometry.Spline;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import world.World;
import world.Zone;
import algorithm.WorldCalculateAltitude;
import algorithm.WorldNumberOfRegions;

public class WorldGenerator {

	private Random r ;
	private WorldNumberOfRegions regionAlgo;
	private WorldCalculateAltitude altitudeAlgo;
	
	
	private World world;
	private Spline sp;

	public WorldGenerator(String seed) {
		world = new World();
		r = new Random(seed.hashCode());
		regionAlgo = new WorldNumberOfRegions(r);
		altitudeAlgo = new WorldCalculateAltitude(r);

	}

	private ArrayList<Zone> generateRegions(int regions) {

		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<Point> options = new ArrayList<Point>();

		Point first = new Point(0, 0);
		options.addAll(first.neighbours());
		points.add(first);

		while (points.size() < regions) {
			int index = r.nextInt(options.size());
			Point p = options.remove(index);
			if (!points.contains(p)) {
				points.add(p);
				options.addAll(p.neighbours());
			}
		}
		ArrayList<Zone> zones = new ArrayList<Zone>();

		for (Point p : points)
			zones.add(new Zone(p, 0));
		return zones;
	}

	private void createAltitude(ArrayList<Zone> zones) {
		long xMin = Long.MAX_VALUE;
		long xMax = Long.MIN_VALUE;
		long yMin = Long.MAX_VALUE;
		long yMax = Long.MIN_VALUE;

		for (Zone z : zones) {
			xMin = Math.min(xMin, z.getX());
			xMax = Math.max(xMax, z.getX());
			yMin = Math.min(yMin, z.getY());
			yMax = Math.max(yMax, z.getY());
		}

		int xSize = (int) (xMax - xMin + 1);
		int ySize = (int) (yMax - yMin + 1);

		HashMap<Point, Zone> sortedList = new HashMap<Point, Zone>();

		for (Zone z : zones) {
			sortedList.put(z.getPoint(), z);
		}

		// Left to right
		for (long y = yMin; y <= yMax; y++) {
			altitudeAlgo.reset();
			for (long x = xMin; x <= xMax; x++) {
				Point p = new Point(x, y);
				Zone z = sortedList.get(p);
				altitudeAlgo.calculate(z);
			}
		}

		// Right to left
		for (long y = yMin; y <= yMax; y++) {
			altitudeAlgo.reset();
			for (long x = xMax; x >= xMin; x--) {
				Point p = new Point(x, y);
				Zone z = sortedList.get(p);
				altitudeAlgo.calculate(z);
			}
		}

		for (long x = xMin; x <= xMax; x++) {
			altitudeAlgo.reset();
			for (long y = yMin; y <= yMax; y++) {
				Point p = new Point(x, y);
				Zone z = sortedList.get(p);
				altitudeAlgo.calculate(z);
			}
		}
		for (long x = xMin; x <= xMax; x++) {
			altitudeAlgo.reset();
			for (long y = yMax; y >= yMin; y--) {
				Point p = new Point(x, y);
				Zone z = sortedList.get(p);
				altitudeAlgo.calculate(z);
			}
		}

	}
	
	public Spline buildSpline(ArrayList<Zone> zones){
		// create list of points
		ArrayList<Point> points = new ArrayList<Point>();
		
		for(int i=0;i<zones.size();i++){
			double x = zones.get(i).getPoint().getX();
			double y = zones.get(i).getPoint().getY();
			double z = zones.get(i).getAltitude();
			
			Point p0 = new Point(x+0.5, y+0.5, z);
			Point p1 = new Point(x+0.5, y-0.5, z);
			Point p2 = new Point(x-0.5, y-0.5, z);
			Point p3 = new Point(x-0.5, y+0.5, z);
			points.add(p0);
			points.add(p1);
			points.add(p2);
			points.add(p3);
			
			
			for(Point po : points){
				ArrayList<Integer> index = new ArrayList<Integer>();
				for(int n=0;n<points.size();n++){
					if(po.equals(points.get(n))){
						index.add(n);
					}
					if(index.size()==4){
						points.remove(index.get(0));
						points.remove(index.get(1));
						points.remove(index.get(2));
						points.remove(index.get(3));
						break;
					}
				}
			}
		}
		
		Spline spline = new Spline(points.remove(0));
		for(Point p : points)
			spline.addKnot(p);
		
		
		return spline;
	}

	public World generate() {
		int regions = regionAlgo.calculate();
		ArrayList<Zone> zones = generateRegions(regions);
		createAltitude(zones);
		world.addZones(zones);
		sp = buildSpline(zones);
		sp.init();							//initate make ready for calctulation
		sp.join();							// join both ands bezier curves to create a circle
		return world;
	}
	
	public Spline getSpline(){
		return sp;
	}

}
