package midi;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;

public class SysexMessageFactory {
	
	final protected HashMap<Integer, String> classMap = new HashMap<Integer, String>(){{
		put(0, "IdentityRequest");
		put(1, "IdentityResponse");
	}};
	
	public SysexMessage getSysexMessage(List<Integer> codes) {
		// Handle empty message or message that will have no message type code (third value)
		if (codes.isEmpty() || codes.size() < 3) {
			return null;
		}
		
		Integer messageTypeCode = codes.get(3);
		
		// If we do not have the message type return a generic one
		if (!this.classMap.containsKey(messageTypeCode)) {
			return new SysexMessage(codes);
		}
		
		// Return the specific message class
		String className = this.classMap.get(messageTypeCode);
		Class<?> sysexMessageClass;
		SysexMessage instance = null;
		
		try {
			sysexMessageClass = Class.forName(className);
			Constructor<?> constructor = sysexMessageClass.getConstructor();
			instance = (SysexMessage)constructor.newInstance();
		} catch (Exception e) {
			return null;
		}
		
		return instance;
	}
}
