package de.arguments.required;

import de.arguments.exceptions.ArgumentsException;

public class RequiredDouble extends RequiredArg {
	
	public RequiredDouble(char id) {
		super(id);
		type = "Double";
	}
	
	public RequiredDouble(char id, String alias) {
		super(id, alias);
		type = "Double";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Double getValue() throws ArgumentsException {	
		if (!this.valueSet()){
			throw new ArgumentsException("Value not set");
		}
		return (Double) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentsException {
		
		if (! (value instanceof Double)){
			throw new ArgumentsException("Passed Object is not an Double");
		}
		
		this.value = (Double) value;
	}

	@Override
	public void setValue(String value) throws ArgumentsException {
		try {
			this.value = new Double(value);
		} catch (Exception e) {
			throw new ArgumentsException("Unable to parse value: " + value);
		}
	}
	
	public void setValue(Double value) {
		this.value = value;
	}

}
