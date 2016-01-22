package de.arguments.required;

import de.arguments.exceptions.ArgumentsException;

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
	public String getValue() throws ArgumentsException {
		if (!this.valueSet()){
			throw new ArgumentsException("Value not set");
		}
		return (String) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentsException {
		
		if (! (value instanceof String)){
			throw new ArgumentsException("Passed Object is not an Integer");
		}
		
		this.value = (String) value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
