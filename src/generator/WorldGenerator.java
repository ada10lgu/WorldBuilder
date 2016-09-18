package generator;

import geometry.Point;
import geometry.Spline;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import world.World;
import world.Zone;
import algorithm.WorldCalculateAltitude;
import algorithm.WorldNumberOfRegions;

public class WorldGenerator {

	private Random r ;
	private WorldNumberOfRegions regionAlgo;
	private WorldCalculateAltitude altitudeAlgo;
	
	private Spline spline;
	
	private World world;

	public WorldGenerator(String seed) {
		world = new World();
		r = new Random(seed.hashCode());
		regionAlgo = new WorldNumberOfRegions(r);
		altitudeAlgo = new WorldCalculateAltitude(r);

	}

	private ArrayList<Zone> generateRegions(int regions) {
		ArrayList<Point> bezP = new ArrayList<Point>();			//bezier hashmap for allt points 
		
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
				
				
				/**
				 * Bezier mapping adding clockwise from 12.00 forward
				 */
				ArrayList<Point> bezOpt = new ArrayList<Point>();
				bezOpt.add(new Point(p.getX(),p.getY()+0.5, 0));
				bezOpt.add(new Point(p.getX()+0.5,p.getY()+0.5, 0));
				bezOpt.add(new Point(p.getX()+0.5,p.getY(), 0));
				bezOpt.add(new Point(p.getX()+0.5,p.getY()-0.5, 0));
				bezOpt.add(new Point(p.getX(),p.getY()-0.5, 0));
				bezOpt.add(new Point(p.getX()-0.5,p.getY()-0.5, 0));
				bezOpt.add(new Point(p.getX()-0.5,p.getY(), 0));
				bezOpt.add(new Point(p.getX()-0.5,p.getY()+0.5, 0));
				
		//		ArrayList<Point> temp = new ArrayList<Point>();
				
				int start=-1;
				int aIndex=0;					// starting index for insert in bezP
				int end=-1;						
				int bIndex=0;					//ending index for insert in bezP
				for(int i=0;i<bezOpt.size();i++){
					int tmp = bezP.indexOf(bezOpt.get(i));
					if(tmp>=0 && start>=0){
						start=i;
						aIndex=tmp;
					}else if(tmp>=0){
						if(end<0){
							//	normal case start finds before end
							end=i;
							bIndex=tmp;
						}else{	
							// inverting start and end
							end=start;
							start=i;
						}
					}
				}
				
				/**
				 * removing 
				 */
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
	
	public Spline buildSpline(){
		spline = new Spline();
		for(int i=0;i<world.getZones().size();i++){
			boolean[] coast = world.getZones().get(i).getCoastBoolean();
			if(coast[0]){
				
			}
		}
		
		return spline;
	}

	public World generate() {
		int regions = regionAlgo.calculate();
		ArrayList<Zone> zones = generateRegions(regions);
		createAltitude(zones);
		world.addZones(zones);
		return world;
	}

}
