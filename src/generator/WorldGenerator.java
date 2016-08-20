package generator;

import java.util.Random;

import world.World;

public class WorldGenerator {

	private Random r;

	public WorldGenerator(String seed) {
		r = new Random(seed.hashCode());
	}

	public World generate() {
		return null;
	}

}
