package de.arguments.array;

import de.arguments.exceptions.ArgumentException;
import de.arguments.optional.OptionalArg;

abstract class OptionalArray extends OptionalArg {
	
	protected OptionalArray(char id) throws ArgumentException {
		super(id);
	}

	protected OptionalArray(char id, String alias)
			throws ArgumentException {
		super(id, alias);
	}
	
	protected void checkArrayStructure(String[] rawValues) throws ArgumentException {
		if (!rawValues[0].startsWith("[")){
			throw new ArgumentException("Raw Array does not start with '['");
		}
		
		if (!rawValues[rawValues.length-1].endsWith("]")){
			throw new ArgumentException("Raw Array does not end with ']'");
		}
		
	}

}
