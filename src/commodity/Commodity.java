package commodity;

import java.util.ArrayList;

public abstract class Commodity {
	// commodities attributes
	int value;
	ArrayList<Commodity> commo_req;
	int amount;
	double quality;
	String tag;
	
	public Commodity(){
		commo_req = new ArrayList<Commodity>();
		tag = "";
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public void setQuality(double qual){
		quality = qual;
	}
	
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	public int getAmount(){
		return amount;
	}
	
	public void setReqCommodity(ArrayList<Commodity> reqCom){
		commo_req = reqCom;
	}
	
	public ArrayList<Commodity> getReqCommodity(){
		return commo_req;
	}
}
