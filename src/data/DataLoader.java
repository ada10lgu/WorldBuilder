package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.JSONObject;

public class DataLoader {

	JSONObject obj;

	public DataLoader(File f) throws FileNotFoundException {

		if (f.exists()) {
			Scanner s = new Scanner(f);

			StringBuilder sb = new StringBuilder();

			while (s.hasNext()) {
				sb.append(s.nextLine());
			}
			s.close();

			String data = sb.toString();

			obj = new JSONObject(data);
		} else {
			obj = new JSONObject();
		}
	}

	@Override
	public String toString() {
		return obj.toString();
	}

}
