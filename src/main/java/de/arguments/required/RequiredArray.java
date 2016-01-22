package de.arguments.required;

import de.arguments.exceptions.ArgumentsException;

abstract class RequiredArray extends RequiredArg {
	
	protected RequiredArray(char id) throws ArgumentsException {
		super(id);
	}

	protected RequiredArray(char id, String alias)
			throws ArgumentsException {
		super(id, alias);
	}
	
	protected void checkArrayStructure(String[] rawValues) throws ArgumentsException {
		if (!rawValues[0].startsWith("[")){
			throw new ArgumentsException("Raw Array does not start with '['");
		}
		
		if (!rawValues[rawValues.length-1].endsWith("]")){
			throw new ArgumentsException("Raw Array does not end with ']'");
		}
		
	}
	
}
