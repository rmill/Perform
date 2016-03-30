import com.cycling74.max.MaxObject;

import song.Song;

public class Perform extends MaxObject {
	InstrumentManager instrumentManager;
	SongManager songManager;
	Song currentSong;
	
	Perform() {
		this.instrumentManager = new InstrumentManager();
		this.songManager = new SongManager();
	}
	
	public void playSong(String name) {
		if (this.currentSong != null) {
			this.currentSong.stop();
		}
		
		Song song = this.songManager.getSong(name);
		song.play();
		this.currentSong = song;
	}
}
