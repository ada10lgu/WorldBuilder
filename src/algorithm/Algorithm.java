package algorithm;

import java.util.Random;

public abstract class Algorithm<E> {

	protected Random r;

	public Algorithm(Random r) {
		this.r = r;
	}

	public abstract E calculate();
}
