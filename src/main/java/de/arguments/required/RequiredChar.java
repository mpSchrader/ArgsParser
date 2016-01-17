package de.arguments.required;

import de.arguments.exceptions.ArgumentException;

public class RequiredChar extends RequiredArg {

	public RequiredChar(String identifier) {
		super(identifier);
	}
	
	public RequiredChar(String identifier, String description) {
		super(identifier,description);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Character getValue() throws ArgumentException {
		return (Character) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentException {
		
		if(! (value instanceof Character)){
			throw new ArgumentException("Passed parameter is not Char! ");
		}
		
		this.value = (Character) value;

	}

	@Override
	public void setValue(String value) throws ArgumentException {
		
		if (value.length() != 1){
			throw new ArgumentException("Passed String is not a single Char! ");
		}

		this.value = value.charAt(0);
	}
	
	public String toString(){
		String output = identifier+" Char";
		if (!getUsage().equals("")){
			output += " : "+getUsage();
		}
		return output;
	}

}
