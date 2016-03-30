
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cycling74.max.Atom;
import com.cycling74.max.MaxBox;
import com.cycling74.max.MaxClock;
import com.cycling74.max.MaxPatcher;

import instrument.Instrument;
import instrument.InstrumentFactory;
import midi.SysexMessage;
import midi.SysexMessageFactory;

public class MidiInterface extends PerformObject {

	String name;
	Instrument instrument;
	MaxBox midiIn;
	MaxBox midiOut;
	MaxBox sysexIn;
	MaxPatcher patcher;
	List<Integer> sysexMessage;
	SysexMessageFactory sysexMessageFactory;
	
	MidiInterface(String name) {
		this.name = name;
		this.patcher = this.getParentPatcher();
		
		this.midiIn = this.patcher.newDefault(0, 20, "midiout", null);
		this.midiOut = this.patcher.newDefault(0, 30, "midiin", null);
		this.sysexIn = this.patcher.newDefault(0, 40, "sysexin", null);
		this.sysexMessageFactory = new SysexMessageFactory();
	}
	
	public Boolean hasInstrument() {
		return this.instrument == null;
	}
	
	public void removeInstrument() {
		this.instrument = null;
	}
	
	public MidiInterface setInstrument(Instrument device) {
		this.instrument = device;
		return this;
	}
	
	public void send(Integer code) {
		this.midiOut.send(code);
	}
	
	public void send(SysexMessage message) {
		for(Integer code: message.getCodes()) {
			this.send(code);
		}
	}
	
	public void sysexIn (Atom arg) {
		Integer code = arg.getInt();
		
		if (code == SysexMessage.CODE_START) {
			this.sysexMessage = new ArrayList<Integer>();
		}
		
		this.sysexMessage.add(code);
		
		if (code == SysexMessage.CODE_END) {
			SysexMessage sysexMessage = this.sysexMessageFactory.getSysexMessage(this.sysexMessage);
			
			if (sysexMessage == null) {
				return;
			}
			
			this.sysexMessage = null;
//			this.processSysexMessage(sysexMessage);
		}
	}

//	public void processSysexMessage(SysexMessage sysexMessage) {
//		switch(sysexMessage.getClass().getName()) {
//			case "IdentityResponse":
//				this.processIdentityResponse(sysexMessage);
//				break;
//			default:
//				this.sendSysexMessage(sysexMessage);
//		}
//	}
}
