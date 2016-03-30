import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cycling74.max.*;

import instrument.Instrument;
import song.Song;
import song.SongFactory; 

public class SongManager extends MaxObject 
{
	SongFactory songFactory;
	HashMap<String, Song> songs;
	MaxPatcher patcher;
	String[] filePaths;
	
    SongManager() {
    	this.songFactory = new SongFactory();
    	this.filePaths = new String[0];
    	this.songs = new HashMap<String, Song>();
    }
    
    public Song getSong(String name) {
    	return this.songs.get(name);
    }
    
    protected void readSongsFromDisk(String[] filePaths) {
    	// Do not parse an empty list
    	if (filePaths.length == 0) {
    		return;
    	}
    	
    	// Parse all of the .song files
    	for (int i = 0; i < filePaths.length; i++) {
    		File filePath = new File(filePaths[i]);
    		
    		if (!filePath.exists()) {
    			MaxObject.error("Folder or file could not be found: " + filePath.toString());
    			continue;
    		}
    		
    		// Recurssively get songs in subdirectories
    		if (filePath.isDirectory()) {
    			this.readSongsFromDisk(filePath.list());
    			continue;
    		}
    		
    		try {
    			Song song = this.songFactory.getSong(filePath);
//        		this.songs.add(song);
    		} catch (FileNotFoundException e) {
				MaxObject.error("Folder or file could not be found: " + filePath.toString());
				continue;
			}
    	}
    }
}
