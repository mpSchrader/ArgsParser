package de.arguments.optional;

import de.arguments.exceptions.ArgumentException;

public class OptionalChar extends OptionalArg {

	public OptionalChar(char id, Character defaultt) {
		super(id);
		this.defaultt = defaultt;
		type = "Char";
	}

	public OptionalChar(char id, String alias, Character defaultt) {
		super(id, alias);
		this.defaultt = defaultt;
		type = "Char";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Character getDefault() {
		return (Character) defaultt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Character getValue() {
		if (value != null) {
			return (Character) value;
		}
		return (Character) defaultt;
	}

	@Override
	public void setValue(Object value) throws ArgumentException {

		if (!(value instanceof Character)) {
			throw new ArgumentException("Passed Object is not an Integer");
		}

		this.value = (Character) value;
	}

	@Override
	public void setValue(String value) throws ArgumentException {
		try {

			if (value.length() == 1) {
				this.value = value.charAt(0);
				return;
			}
			
		} catch (Exception e) {
			throw new ArgumentException("Unable to parse value: " + value);
		}
		
		throw new ArgumentException("Unable to parse value: " + value);
	}
	
	@Override
	public String toString(){
		String output = super.toString();
		output = output.substring(0,output.length() -3);
		output = output +" '"+defaultt+"')";
		return output;
	}

}
