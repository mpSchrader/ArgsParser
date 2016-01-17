package de.arguments.required;

import de.arguments.exceptions.ArgumentException;

public class RequiredInteger extends RequiredArg {
	
	public RequiredInteger(char id) {
		super(id);
	}
	
	public RequiredInteger(char id, String alias) {
		super(id, alias);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getValue() throws ArgumentException {
		if (!this.checkIfValueIsSet()){
			throw new ArgumentException("Value not set");
		}
		return (Integer) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentException {
		
		if (! (value instanceof Integer)){
			throw new ArgumentException("Passed Object is not an Integer");
		}
		
		this.value = (Integer) value;
	}

	@Override
	public void setValue(String value) throws ArgumentException {
		try {
			this.value = new Integer(value);
		} catch (Exception e) {
			throw new ArgumentException("Unable to parse value: " + value);
		}
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}

}
