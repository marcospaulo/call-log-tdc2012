package br.com.tdc2012.mdm;

public class PhoneCall {
	
	private int id;
	private String callType;
	private String toNumber;
	private String fromNumber;
	long callDuration = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getToNumber() {
		return toNumber;
	}

	public void setToNumber(String toNumber) {
		this.toNumber = toNumber;
	}

	public String getFromNumber() {
		return fromNumber;
	}

	public void setFromNumber(String fromNumber) {
		this.fromNumber = fromNumber;
	}

	public long getCallDuration() {
		return callDuration;
	}

	public void setCallDuration(long callDuration) {
		this.callDuration = callDuration;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Call Type: ");
		strBuilder.append(callType);
		strBuilder.append(" To Number: ");
		strBuilder.append(toNumber);
		strBuilder.append(" From Number: ");
		strBuilder.append(fromNumber);
		strBuilder.append(" Call Duration: ");
		strBuilder.append(callDuration);
		
		return strBuilder.toString();
	}

}
