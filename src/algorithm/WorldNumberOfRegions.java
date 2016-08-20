package algorithm;

import java.util.Random;

public class WorldNumberOfRegions extends Algorithm<Void, Integer> {

	public WorldNumberOfRegions(Random r) {
		super(r);
	}

	@Override
	public Integer calculate(Void... data) {
		return 425;

		// return r.nextInt(500) + 500;
	}

}
