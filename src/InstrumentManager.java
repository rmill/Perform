import com.cycling74.max.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import instrument.Identity;
import instrument.Instrument;
import instrument.InstrumentFactory; 

public class InstrumentManager extends MaxObject
{
	HashMap<String, Instrument> instruments;
	InstrumentFactory instrumentFactory;
	MaxPatcher patcher;
	
    InstrumentManager() {
    	this.instruments = new HashMap<String, Instrument>();
    	this.instrumentFactory = new InstrumentFactory();
    	this.patcher = this.getParentPatcher();
    }
    
    protected void loop() {
    	// Get all connected MIDI interfaces
		// Check the state of connected instruments
//		Identity identity = midiInterface.sendIdentityRequest();
//		Boolean interfaceHasInstrument = midiInterface.hasInstrument();
//		
//		if (identity == null) {
//			if (interfaceHasInstrument) {
//    			// The device has been removed
//    			midiInterface.removeInstrument();
//			}
//			
//			return;
//		} 
//		
//		if (!interfaceHasInstrument) {
//			// A new device has been attached
//			Instrument instrument = this.instrumentFactory.getInstrument(identity);
//			midiInterface.setInstrument(instrument);
//		}
    	
    	//this.clock.delay(this.frequency);
    }
}
