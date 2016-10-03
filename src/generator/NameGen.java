package generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class NameGen {
	
	static ArrayList<String> prefix;
	
	static ArrayList<String> jap_name;
	static ArrayList<String> syll;
	static ArrayList<String> sursyll;

	static ArrayList<String> areaprim;
	
	static ArrayList<String> keep;
	static ArrayList<String> harbor;
	static ArrayList<String> field;
	static ArrayList<String> city;
	static ArrayList<String> wilderness;
	static ArrayList<String> mountains;
	
	
	
	static Random gen;
	
	public NameGen(Random r){
		gen = r;
		this.initiation();
	}
	
	public String genname(){
		String name = "";
		int nrsyll = gen.nextInt(5);
		int size = syll.size();
		String tmp;
		for(int n=0;n<=nrsyll;n++){
			tmp = syll.get(gen.nextInt(size));
			name = name+tmp;
		}
		return name;	
	}

	public String gensurname(){
		String surname = "";
		int nrsyll = gen.nextInt(5);
		int size = sursyll.size();
		String tmp;
		for(int n=0;n<=nrsyll;n++){
			tmp = sursyll.get(gen.nextInt(size));
			surname = surname+tmp;
		}
		return surname;
	}
	
	public String genRegionName(){
		String regionname = "";
		int size = areaprim.size();
		String tmp;
		tmp = prefix.get(gen.nextInt(size));
		regionname = regionname + tmp;
		tmp = areaprim.get(gen.nextInt(size));
		regionname = regionname + tmp;
		return regionname;
	}
	
	private void initiation(){
		// general anglo prefix
		prefix = readFile("prefix");
		// japanese names
		jap_name = readFile("japanese_name");
		// fantasy syllables for surr and prim names
		syll = readFile("syll");
		sursyll = readFile("sursyll");
		// different types of regions/areans
		keep = readFile("keep");
		harbor = readFile("harbor");
		field = readFile("field");
		city = readFile("city");
		wilderness = readFile("wilderness");
		mountains = readFile("mountains");
		// generic region name
		areaprim = readFile("areaprim");
	}
	
	private ArrayList<String> readFile(String file){
		ArrayList<String> answer = new ArrayList<String>();
		
		ArrayList<String> working = new ArrayList<String>();
		
		String line = null;
        String fileName = "src/namefiles/"+file;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	working.add(line);
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");     
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
        for(String s : working){
        	String[] name = s.split(":");
        	for(int i=0;i<name.length;i++){
        		answer.add(name[i]);
        	}
        }
        if(!answer.isEmpty()){
        	answer.remove(0);
        }
        return answer;
	}
}
