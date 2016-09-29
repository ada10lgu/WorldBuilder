package algorithm;

import java.util.Random;

public abstract class Algorithm<F,E> {

	protected Random r;

	public Algorithm(Random r) {
		this.r = r;
	}

	public abstract E calculate(F... data);
	
}
