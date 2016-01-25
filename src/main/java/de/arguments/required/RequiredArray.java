package de.arguments.required;

import de.arguments.exceptions.ArgumentsException;

public abstract class RequiredArray extends RequiredArg {
	
	public RequiredArray(char id) throws ArgumentsException {
		super(id);
	}

	public RequiredArray(char id, String alias)
			throws ArgumentsException {
		super(id, alias);
	}
	
}
