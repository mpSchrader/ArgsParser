package de.arguments.required;

import de.arguments.exceptions.ArgumentException;

abstract class RequiredArray extends RequiredArg {

	protected Object[] value;
	
	protected RequiredArray(String identifier) throws ArgumentException {
		super(identifier);
	}

	protected RequiredArray(String identifier, String usage)
			throws ArgumentException {
		super(identifier, usage);
	}

}
