package instrument;
import java.lang.reflect.Constructor;
import com.cycling74.max.MaxObject;

public class InstrumentFactory {

	public Instrument getInstrument (Identity identity) {
		return null;
	}
	
	public Instrument getInstrument(String instrumentName) {
		if (instrumentName == null) {
			return null;
		}
		
		String className = "instrument."  + instrumentName.replace(" ", "");
		Class<?> instrumentClass;
		Instrument instance = null;
		
		try {
			instrumentClass = Class.forName(className);
			Constructor<?> constructor = instrumentClass.getConstructor();
			instance = (Instrument)constructor.newInstance();
		} catch (Exception e) {
			MaxObject.error("Instrument '" + instrumentName + "' could not be found.");
		}
		
		return instance;
	}
}
