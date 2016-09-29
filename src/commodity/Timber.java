package commodity;

import java.util.ArrayList;

public class Timber extends Commodity {
	
	static double STAND_VALUE;
	
	public Timber(int amount){
		commo_req = new ArrayList<Commodity>();
		this.amount = amount;
		STAND_VALUE = 0.5;					// standard value rarity of value
	}
	

}
