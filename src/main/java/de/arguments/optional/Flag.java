package de.arguments.optional;

import de.arguments.exceptions.ArgumentsException;

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
	public Boolean getValue() throws ArgumentsException {
		return isSet;
	}

	public Boolean isSet() throws ArgumentsException {
		return isSet;
	}

	@Override
	public void setValue(Object value) throws ArgumentsException {
		isSet = true;
	}

	@Override
	public void setValue(String value) throws ArgumentsException {
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
