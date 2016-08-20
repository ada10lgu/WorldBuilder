package algorithm;

import java.util.Random;

public class WorldNumberOfRegions extends Algorithm<Integer> {

	public WorldNumberOfRegions(Random r) {
		super(r);
	}

	@Override
	public Integer calculate() {
		return r.nextInt(100) + 10;
	}

}
