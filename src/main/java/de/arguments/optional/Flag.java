package de.arguments.optional;

import de.arguments.exceptions.ArgumentException;

public class Flag extends OptionalArg {

	private Boolean isSet = false;

	public Flag(char id) {
		super(id);
		type = "Flag";
	}
	
	public Flag(char id,String alias) {
		super(id,alias);
		type = "Flag";
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

	@SuppressWarnings("unchecked")
	@Override
	public Boolean getDefault() {
		return false;
	}
	
	@Override
	public String toString(){
		String output =  super.toString();
		output = output.split("\\(Default =")[0].trim();
		return output;
		
	}
	
}
