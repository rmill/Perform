import com.cycling74.max.Atom;
import com.cycling74.max.MaxBox;
import com.cycling74.max.MaxObject;
import com.cycling74.max.MaxPatcher;

public abstract class PerformObject extends MaxObject {

	MaxPatcher patcher;
	
	public PerformObject() {
		this.patcher = this.getParentPatcher();
	}
	
	protected MaxBox getElement(int x, int y, String name, String type, Atom[] args) {
		// If the element already exists, find it
		MaxBox element = this.patcher.getNamedBox(name);
		
		if (element == null) {
			// create the element
			element = this.patcher.newDefault(x, y, type, args);
		}
		
		return element;
	}
}
