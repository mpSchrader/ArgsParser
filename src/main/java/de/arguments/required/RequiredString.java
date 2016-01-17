package de.arguments.required;

import de.arguments.exceptions.ArgumentException;

public class RequiredString extends RequiredArg {
	
	public RequiredString(String identifier) {
		super(identifier);
	}
	
	public RequiredString(String identifier, String usage) {
		super(identifier, usage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getValue() throws ArgumentException {
		if (!this.checkIfValueIsSet()){
			throw new ArgumentException("Value not set");
		}
		return (String) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentException {
		
		if (! (value instanceof String)){
			throw new ArgumentException("Passed Object is not an Integer");
		}
		
		this.value = (String) value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		if (!this.getUsage().equals("")) {
			return identifier + " String : " + this.getUsage();
		}
		return identifier+" String";
	}
}
