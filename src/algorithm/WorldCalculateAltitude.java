package algorithm;

import java.util.Arrays;
import java.util.Random;

import node_world_structure.Regions;
import world.Zone;

public class WorldCalculateAltitude extends Algorithm<Zone, Double> {

	private int x = 0;

	public WorldCalculateAltitude(Random r) {
		super(r);
	}

	public void reset() {
		x = 0;
	}

	@Override
	public Double calculate(Zone... data) {
		Zone z = data[0];
		if (z == null) {
			reset();
			return 0.0;
		}
		x++;
		Double answer = Math.log(x + 1)*Math.pow(x, 2)/100;
		z.addAltitude(answer);
		return answer;
	}

}
