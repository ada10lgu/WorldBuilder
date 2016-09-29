package commodity;

import java.util.ArrayList;

public class Woodplanks extends Commodity{
	
	public Woodplanks(int amount){
		commo_req = new ArrayList<Commodity>();
		this.amount = amount;
		commo_req.add(new Timber(amount));
		tag = "";
		
	}
	
	public void setTimber(Woodplanks wood){
		commo_req.remove(0);
		commo_req.add(0, wood);
	}
	
	public Woodplanks getTimber(){
		return (Woodplanks) commo_req.get(0);
	}
}
