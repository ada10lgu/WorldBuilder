package generator;

import java.util.ArrayList;
import java.util.Random;

public class NameGen {
	
	static ArrayList<String> syll;
	static ArrayList<String> sursyll;
	static Random gen;
	
	public NameGen(){
		this.initiation();
	}
	
	public String genname(long seed){
		String name = "";
		gen = new Random(seed);
		int nrsyll = gen.nextInt(5);
		int size = syll.size();
		String tmp;
		for(int n=0;n<=nrsyll;n++){
			tmp = syll.get(gen.nextInt(size));
			name = name+tmp;
		}
		return name;	
	}

	public String gensurname(long seed){
		String surname = "";
		gen = new Random(seed);
		int nrsyll = gen.nextInt(5);
		int size = sursyll.size();
		String tmp;
		for(int n=0;n<=nrsyll;n++){
			tmp = sursyll.get(gen.nextInt(size));
			surname = surname+tmp;
		}
		return surname;
	}
	
	private void initiation(){
		syll = new ArrayList<String>();
		// read from text file and put in
		
		sursyll = new ArrayList<String>();
		// read from text file and put into
	}
}
