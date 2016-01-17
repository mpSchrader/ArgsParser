package de.arguments.required;

import de.arguments.exceptions.ArgumentException;

public class RequiredChar extends RequiredArg {

	public RequiredChar(char id) {
		super(id);
		type = "Char";
	}
	
	public RequiredChar(char id, String alias) {
		super(id,alias);
		type = "Char";
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
		String output = " Char";
		if (!getDescription().equals("")){
			output += " : "+getDescription();
		}
		return output;
	}

}
