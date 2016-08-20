package Test;

import java.io.File;
import java.io.FileNotFoundException;

import data.DataLoader;

public class DataTester {
	public static void main(String[] args) throws FileNotFoundException {

		DataLoader dl = new DataLoader(new File("data/temp.json"));
System.out.println(dl);
	}
}
