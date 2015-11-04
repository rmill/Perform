/* InstrumentManager - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import com.cycling74.max.Atom;
import com.cycling74.max.MaxObject;
import com.cycling74.max.MaxPatcher;

public class InstrumentManager extends MaxObject
{
    public void bang() {
	MaxPatcher p = getParentPatcher();
	p.newObject("patcher", Atom.parse("10 10 100 100 0 0"));
    }
}
