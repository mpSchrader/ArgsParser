package de.arguments.optional;

import de.arguments.exceptions.ArgumentException;

public class Flag extends OptionalArg {

	private Boolean isSet = false;

	public Flag(String identifier) {
		super(identifier);
	}

	public Flag(String identifier, String description) {
		super(identifier, description);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean getValue() throws ArgumentException {
		return isSet;
	}

	public Boolean isSet() throws ArgumentException {
		return isSet;
	}

	@Override
	public void setValue(Object value) throws ArgumentException {
		isSet = true;
	}

	@Override
	public void setValue(String value) throws ArgumentException {
		isSet = true;
	}

	@Override
	public String toString() {
		if (!getUsage().equals("")){
			return identifier + " Flag : " + this.getUsage();
		}
		return identifier + " Flag";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean getDefault() {
		return false;
	}
	
}
