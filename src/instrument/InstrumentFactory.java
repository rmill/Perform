package instrument;
import java.lang.reflect.Constructor;

public class InstrumentFactory {

	public Instrument getInstrument(String instrumentName) throws ClassNotFoundException {
		if (instrumentName == null) {
			return null;
		}
		
		String className = "instrument."  + instrumentName.replace(" ", "");
		Class<?> instrumentClass;
		
		instrumentClass = Class.forName(className);
		Instrument instance = null;
		
		try {
			Constructor<?> constructor = instrumentClass.getConstructor();
			instance = (Instrument)constructor.newInstance();
		} catch (Exception e) {
			// Log error
		}
		
		return instance;
	}
}
