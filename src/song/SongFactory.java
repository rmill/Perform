package song;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.*;

public class SongFactory {
	
	public Song getSong(File file) throws FileNotFoundException {
		BufferedReader inputStream = new BufferedReader(new FileReader(file));
		JSONObject songJson = new JSONObject(inputStream);
		
		
		// Get the parts of the song
		JSONArray partsJson = songJson.getJSONArray("parts");
		Part[] parts = new Part[0];
		PartFactory partFactory = new PartFactory();
		
		for (int i = 0; i < partsJson.length(); i++) {
			//Part part = partFactory.getPart(partsJson.get(i));
			//parts.add(part);
		}
		
		String name = songJson.getString("name");
		
		return new Song(name, parts);
	}
}
