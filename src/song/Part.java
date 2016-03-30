package song;

import java.io.File;

public class Part {
	
	String instrumentName;
	Integer channel;
	File midiFile;
	Integer track;

	Part (String instrument, Integer channel, Integer track, File midiFile) {
		this.instrumentName = instrument;
		this.channel = channel;
		this.track = track;
		this.midiFile = midiFile;
	}
}
