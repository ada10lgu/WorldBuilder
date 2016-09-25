package namefiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {
	
	static Scanner reader;
	static ArrayList<String> words;
	static String work_file;

	public static void main(String[] args) {
		reader = new Scanner(System.in);
		System.out.println("files: prefix, region, townsur");
		System.out.println("enter file name: ");
		
		words = new ArrayList<String>();;
		
		readFile();
		
        System.out.println("Readfile Success");
        
        boolean end = false;
        while(!end){
        	System.out.println("entry: ");
        	String entry = reader.nextLine();
        	switch(entry){
        	case "/end" :
        		System.out.println("Saving file");
        		end = true;
        		break;
        	case "" :
        		break;
        	default :
        		if(!words.contains(entry)){
               		System.out.println("Do you want to enter: "+entry);
               		System.out.println("y / n");
               		String answer = reader.nextLine().toLowerCase();
               		switch(answer){
               		case "y" :
               			System.out.println("entry added: "+entry);
               			words.add(entry);
               			break;
               		case "n" :
               			break;
               		}
               		writeFile();
               	}else{
               		System.out.println("Entry already exist");
               	};
        		break;
        	}
        }
	}
	
	private static void readFile(){
		boolean filereading = true;
		while(filereading){
			
			String fileName = "src/namefiles/"+reader.nextLine();
			
			// This will reference one line at a time
	        String line = null;

	        try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(fileName);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);

	            while((line = bufferedReader.readLine()) != null) {
	            	String[] matris = line.split(":");
	            	System.out.println("file: "+fileName+" > ");
	            	for(int i=1;i<matris.length;i++){
	            		words.add(matris[i]);
	            		
	            	}
	            	System.out.print(words.toString());
	            	work_file = fileName;
	            	System.out.println();
	            }   

	            // Always close files.
	            bufferedReader.close();
	            filereading = false;
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                fileName + "'");
	            	System.out.println("enter file name: ");
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" 
	                + fileName + "'");                  
	            // Or we could just do this: 
	            // ex.printStackTrace();
	        }
		}
	}
	
	private static void writeFile(){
		
		String file = "";
		for(String entry : words){
			file = file+":"+entry;
		}
		
		System.out.println("file: "+file);
        BufferedWriter writer = null;
        
        try {
        	
        	writer = new BufferedWriter(new FileWriter(work_file));
        	writer.write(file);
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to find and write to: "+work_file);
        }
        catch(IOException ex) {
            System.out.println(
                "error writing to file: "+work_file);                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        finally {
        	try {
        		writer.close();
        	} catch (Exception e){
        		
        	}
        }
	
	}
	
}
