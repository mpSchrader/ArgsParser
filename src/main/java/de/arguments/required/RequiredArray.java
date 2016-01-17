package de.arguments.required;

import de.arguments.exceptions.ArgumentException;

abstract class RequiredArray extends RequiredArg {

	protected Object[] value;
	
	protected RequiredArray(char id) throws ArgumentException {
		super(id);
	}

	protected RequiredArray(char id, String alias)
			throws ArgumentException {
		super(id, alias);
	}

}
