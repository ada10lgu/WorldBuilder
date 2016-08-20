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

		Point p = new Point(0, 0);
		options.addAll(p.neighbours());
		points.add(p);
		return points;
	}

	public World generate() {
		int regions = regionAlgo.calculate();
		System.out.println(generateRegions(regions));

		return null;
	}

}
