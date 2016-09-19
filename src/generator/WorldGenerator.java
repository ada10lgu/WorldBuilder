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
	
	
	// vector graphics
	private Spline spline;
	private ArrayList<Point> bezP;
	
	private World world;

	public WorldGenerator(String seed) {
		world = new World();
		r = new Random(seed.hashCode());
		regionAlgo = new WorldNumberOfRegions(r);
		altitudeAlgo = new WorldCalculateAltitude(r);

	}

	private ArrayList<Zone> generateRegions(int regions) {
		bezP = new ArrayList<Point>();			//bezier hashmap for allt points 
		
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
				bezOpt.add(new Point(p.getX()+0.5,p.getY()+0.5, 0));
				bezOpt.add(new Point(p.getX()+0.5,p.getY()-0.5, 0));
				bezOpt.add(new Point(p.getX()-0.5,p.getY()-0.5, 0));
				bezOpt.add(new Point(p.getX()-0.5,p.getY()+0.5, 0));
				
				System.out.println(" "+ p.getX()+" "+p.getY());
				System.out.println("Size: "+bezP.size());
				System.out.println("Add Option: "+bezOpt.toString());
				
				ArrayList<Point> bezRemove = new ArrayList<Point>();
				
				
				for(Point op : bezOpt){
					if(bezP.indexOf(op)<0){
						bezRemove.add(op);
					}
				}
				
				/**
				 * working project
				 */
				for(int i=0;i<bezOpt.size();i++){
					if(!(bezP.indexOf(bezOpt.get(i))<0)){
						switch (i) {
							case 1 : i=0;
								bezOpt.remove(i);
								i--;
								break;
							case 2 : i=1;
								bezOpt.add(bezOpt.remove(i-1));
								bezOpt.remove(0);
								i=i-2;
								break;
							case 3 : i=2;
								bezOpt.add(bezOpt.remove(i-2));
								bezOpt.add(bezOpt.remove(i-2));
								bezOpt.remove(0);
								i=i-3;
								break;
							case 4 : i=3;
								bezOpt.add(bezOpt.remove(i-3));
								bezOpt.add(bezOpt.remove(i-3));
								bezOpt.add(bezOpt.remove(i-3));
								bezOpt.remove(0);
								i=i-4;
								break;
							default:
								break;
						}
					}
					System.out.println("process: "+bezOpt.toString());
				}
				
				System.out.println("Adding: "+bezOpt.toString());
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
		spline = new Spline(bezP.get(0));
		bezP.remove(0);
		for(Point p : bezP){
			spline.addKnot(p);
		}
		System.out.println("Size: "+bezP.size());
		System.out.println(" "+bezP.toString());
		return spline;
	}

	public World generate() {
		int regions = regionAlgo.calculate();
		ArrayList<Zone> zones = generateRegions(regions);
		System.out.println("Zone Nr: "+zones.size());
		createAltitude(zones);
		world.addZones(zones);
		return world;
	}

}
