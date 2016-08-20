package generator;

import geometry.Point;

import java.util.ArrayList;
import java.util.Random;

import world.World;
import algorithm.WorldNumberOfRegions;

public class WorldGenerator {

	private Random r;
	private WorldNumberOfRegions regionAlgo;

	public WorldGenerator(String seed) {
		r = new Random(seed.hashCode());
		regionAlgo = new WorldNumberOfRegions(r);

	}

	private ArrayList<Point> generateRegions(int regions) {
		ArrayList<Point> points = new ArrayList<>();
		ArrayList<Point> options = new ArrayList<>();

		Point first = new Point(0, 0);
		options.addAll(first.neighbours());
		points.add(first);

		while (points.size() < regions) {
			int index = r.nextInt(options.size());
			System.out.println(index);
			Point p = options.remove(index);
			options.addAll(p.neighbours());
			if (!points.contains(p))
				points.add(p);
		}

		return points;
	}

	public World generate() {
		int regions = regionAlgo.calculate();
		System.out.println(generateRegions(regions));

		return null;
	}

}
