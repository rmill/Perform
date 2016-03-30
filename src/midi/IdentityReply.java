package midi;

import java.util.List;

public class IdentityReply extends SysexMessage {

	Integer manufacturerId;
	Integer familyCode;
	Integer modelNumber;
	String versionNumber;
	
	IdentityReply (List<Integer> codes) {
		super(codes);
		
		// The manufacturer ID
		this.manufacturerId = codes.get(5);
		
		// The family ID (2 bytes)
		Integer familyLsb = codes.get(6);
		Integer familyMsb = (codes.get(7) << 8);
		this.familyCode = (familyLsb + familyMsb);
		
		// The model number (2 bytes)
		Integer modelNumberLsb = codes.get(8);
		Integer modelNumberMsb = (codes.get(9) << 8);
		this.modelNumber = (modelNumberLsb + modelNumberMsb);
		
		// The version number
		this.versionNumber = String.format("%s.%s.%s", codes.get(10), codes.get(11), codes.get(12));
	}
}
