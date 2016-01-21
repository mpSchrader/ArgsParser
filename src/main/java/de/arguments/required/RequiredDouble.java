package de.arguments.required;

import de.arguments.exceptions.ArgumentException;

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
	public Double getValue() throws ArgumentException {	
		if (!this.valueSet()){
			throw new ArgumentException("Value not set");
		}
		return (Double) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentException {
		
		if (! (value instanceof Double)){
			throw new ArgumentException("Passed Object is not an Double");
		}
		
		this.value = (Double) value;
	}

	@Override
	public void setValue(String value) throws ArgumentException {
		try {
			this.value = new Double(value);
		} catch (Exception e) {
			throw new ArgumentException("Unable to parse value: " + value);
		}
	}
	
	public void setValue(Double value) {
		this.value = value;
	}

}
