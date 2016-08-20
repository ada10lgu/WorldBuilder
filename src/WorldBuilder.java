import generator.WorldGenerator;
import world.World;

public class WorldBuilder {
	public static void main(String[] args) {

		String seed = "poop";

		WorldGenerator wg = new WorldGenerator(seed);

		World w = wg.generate();

		System.out.println(w);
	}
}
