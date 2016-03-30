import java.util.HashMap;

import com.cycling74.max.Atom;
import com.cycling74.max.DataTypes;
import com.cycling74.max.MaxBox;
import com.cycling74.max.MaxClock;
import com.cycling74.max.MaxObject;
import com.cycling74.max.MaxPatcher;

import midi.SysexMessage;
import midi.IdentityRequest;

public class MessageManager extends PerformObject {

	double frequency;
	private static MessageManager instance;
	HashMap<String, MidiInterface> midiInterfaces;
	MaxClock refreshMidiInterfacesClock;
	MaxClock refreshAttachedDevicesClock;
	MaxBox midiInfo;
	
	public MessageManager() {
		int[] inlets = {DataTypes.MESSAGE};
		this.declareInlets(inlets);
		
		this.midiInterfaces = new HashMap<String, MidiInterface>();
    	this.frequency = 5000.;
//	    this.refreshMidiInterfacesClock = new MaxClock(this, "refreshMidiInterfaces");
//	    this.refreshAttachedDevicesClock = new MaxClock(this, "refreshAttachedDevices");
	}
	
	public static MessageManager getInstance() {
		if (instance == null) {
			instance = new MessageManager();
		}
		
		return instance;
	}
	
	public void loadbang() {
		this.midiInfo = this.getElement(0, 40, "MessageManager-midiinfo", "midiinfo", null);
//    	this.refreshMidiInterfacesClock.delay(this.frequency);
//    	this.refreshAttachedDevicesClock.delay(this.frequency);
		
		
		this.refreshMidiInterfaces();
    }
	
	public void anything(String message, Atom[] args) {
		int inlet = this.getInlet();

		switch(inlet) {
			case 0: this.midiInfoInlet(message, args); break;
		}
	}
	
	public void midiInfoInlet(String message, Atom[] args) {
		if (!message.equals("append")) {
			return;
		}
		
		String connectedInterfaceName = args[0].getString();
		if (!this.midiInterfaces.containsKey(connectedInterfaceName)) {
			// Create the MIDI interface
			MidiInterface midiInterface = new MidiInterface(connectedInterfaceName);
			this.midiInterfaces.put(connectedInterfaceName, midiInterface);
			MessageManager.post("Added MidiInterface: " + connectedInterfaceName);
		}
	}
	
	public void frequency(Atom[] args) {
    	switch(args.length) {
    		case 0:
    			outlet(1, this.frequency);
    			break;
    		case 1: 
    			if (!args[0].isFloat()) {
    				MaxObject.error("[frequency] Must be a float");
    			} else {
    				this.frequency = args[0].getFloat();
    			}
    			
    			break;
    		default: 
    			MaxObject.error("[frequency] Too many arguements");
    	}
    }
	
	public void refreshMidiInterfaces() {
		this.midiInfo.bang();
//		this.refreshMidiInterfacesClock.delay(this.frequency);
	}
	
	public void refreshAttachedDevices() {
		for(String key: this.midiInterfaces.keySet()) {
			MidiInterface midiInterface = this.midiInterfaces.get(key);
			SysexMessage identityRequest = new IdentityRequest();
			midiInterface.send(identityRequest);
		}
		
		this.refreshAttachedDevicesClock.delay(this.frequency);
	}
}
