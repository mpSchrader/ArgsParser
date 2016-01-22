package de.arguments.optional;

import de.arguments.exceptions.ArgumentsException;

public class OptionalString extends OptionalArg {

	public OptionalString(char id, String defaultt) {
		super(id);
		this.defaultt = defaultt;
		type = "String";
	}

	public OptionalString(char id,String alias, String defaultt) {
		super(id, alias);
		this.defaultt = defaultt;
		type = "String";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getValue() {
		if (value != null) {
			return (String) value;
		}
		return (String) defaultt;
	}

	@Override
	public void setValue(Object value) throws ArgumentsException {

		if (!(value instanceof String)) {
			throw new ArgumentsException("Passed Object is not an Integer");
		}

		this.value = (String) value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getDefault() {
		return (String) defaultt;
	}

	@Override
	public String toString(){
		String output = super.toString();
		
		int endIndex = output.length() -(((String) defaultt).length()+1);
		output = output.substring(0,endIndex);
		output = output +"\""+defaultt+"\")";
		return output;
	}
	
}
