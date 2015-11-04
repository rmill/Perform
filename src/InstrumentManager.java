import com.cycling74.max.*;
import java.util.HashMap;
import instrument.Instrument;
import instrument.InstrumentFactory; 

public class InstrumentManager extends MaxObject
{
	HashMap<String, Instrument> instruments;
	InstrumentFactory instrumentFactory;
	MaxBox midiInfo;
	MaxPatcher patcher;
	MaxClock clock;
	double frequency;
	
    InstrumentManager() {
    	MaxObject.error("test2");
    	
    	this.instruments = new HashMap<String, Instrument>();
    	this.instrumentFactory = new InstrumentFactory();
    	this.frequency = 5000.;
    	this.patcher = this.getParentPatcher();
	    this.midiInfo = this.patcher.newDefault(0, 0, "midiinfo", null);
	    this.clock = new MaxClock(this, "loop");
    }
    
    public void bang() {
    	this.loadbang();
    }
    
    public void loadbang() {
    	this.patcher.connect(this.midiInfo, 0, this.getMaxBox(), 0);
    	this.loop();
    }
    
    public void anything(java.lang.String message, Atom[] args) {
    	for (int i = 0; i < args.length; i++) {
    		String instrumentName = args[i].getString();
    		
    		if (!this.instruments.containsKey(instrumentName)) {
    			try {
					Instrument instrument = this.instrumentFactory.getInstrument(instrumentName);
					this.instruments.put(instrumentName, instrument);
    			} catch (ClassNotFoundException e) {
					MaxObject.error("Instrument '" + instrumentName + "' could not be found.");
				}
    		}
    	}
    }
    
    public void loop() {
    	this.midiInfo.bang();
    	//this.clock.delay(this.frequency);
    }
}
