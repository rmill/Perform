package midi;

import java.util.List;

public class SysexMessage {
	
	public static final Integer CODE_START = 240;
	public static final Integer CODE_NON_REAL_TIME = 126;
	public static final Integer CODE_DISREGARD_CHANNEL = 127;
	public static final Integer CODE_GENERAL_INFORMATION = 6;
	public static final Integer CODE_IDENTITY_REQUEST = 1;
	public static final Integer CODE_END = 247;

	String name;
	
	List<Integer> codes;

	SysexMessage () {}
	
	SysexMessage (List<Integer> codes) {
		this.codes = codes;
		this.parse(codes);
	}
	
	protected Boolean parse (List<Integer> codes) {
		// Codes cannot be empty
		if (codes.isEmpty()) {
			return false;
		}
		
		// The first code must be SYSEX_START
		Integer sysexStart = codes.get(0);
		if (sysexStart != SysexMessage.CODE_START) {
			return false;
		}
		
		// The last code must be SYSEX_END
		Integer sysexEnd = codes.get(codes.size() - 1);
		if (sysexEnd != SysexMessage.CODE_END) {
			return false;
		}
		
		return true;
	}

	public List<Integer> getCodes() {
		return this.codes;
	}
}
