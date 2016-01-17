package de.arguments.required;

import de.arguments.exceptions.ArgumentException;

public class RequiredDouble extends RequiredArg {
	
	public RequiredDouble(String identifier) {
		super(identifier);
	}
	
	public RequiredDouble(String identifier, String usage) {
		super(identifier, usage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Double getValue() throws ArgumentException {	
		if (!this.checkIfValueIsSet()){
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
	
	@Override
	public String toString() {
		if (!this.getUsage().equals("")) {
			return identifier + " Double : " + this.getUsage();
		}
		return identifier+" Double";
	}

}
