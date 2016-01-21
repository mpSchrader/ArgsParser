package de.arguments.required;

import de.arguments.exceptions.ArgumentException;

public class RequiredString extends RequiredArg {
	
	public RequiredString(char id) {
		super(id);
		type = "String";
	}
	
	public RequiredString(char id, String alias) {
		super(id, alias);
		type = "String";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getValue() throws ArgumentException {
		if (!this.valueSet()){
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

}
