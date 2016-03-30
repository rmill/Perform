package midi;

public class IdentityRequest extends SysexMessage {

	public IdentityRequest () {
		super();
		
		this.codes.add(SysexMessage.CODE_START);
		this.codes.add(SysexMessage.CODE_NON_REAL_TIME);
		this.codes.add(SysexMessage.CODE_DISREGARD_CHANNEL);
		this.codes.add(SysexMessage.CODE_GENERAL_INFORMATION);
		this.codes.add(SysexMessage.CODE_IDENTITY_REQUEST);
		this.codes.add(SysexMessage.CODE_END);
	}
}
