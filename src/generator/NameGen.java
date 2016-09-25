package generator;

import java.io.BufferedReader;
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
		int nrsyll = gen.nextInt(3);
		int size = areaprim.size();
		String tmp;
		for(int n=0;n<=nrsyll;n++){
			tmp = areaprim.get(gen.nextInt(size));
			regionname = regionname+tmp;
		}
		return regionname;
	}
	
	private void initiation(){
		// general anglo prefix
		prefix = readFile("prefix");
		
		// japanese names
		jap_name = readFile("japese_name");
		// fantasy syllables for surr and prim names
		syll = readFile("syll");
		sursyll = readFile("sursyll");
		// ango area prime "prefix"+this+"other"
		areaprim = readFile("areaprim");
    }
	
	private ArrayList<String> readFile(String file){
		ArrayList<String> answer = new ArrayList<String>();
		
		String line = null;
        String fileName = "areaprim";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                answer.add(line);
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
        
        return answer;
	}
}
