package de.arguments.required;

import de.arguments.Arg;
import de.arguments.exceptions.ArgumentsException;

public abstract class RequiredArg extends Arg {

	protected RequiredArg(char id) {
		super(id);
	}

	protected RequiredArg(char id, String alias) {
		super(id, alias);
	}

	/**
	 * Gets value of the Arg.<br>
	 * <br>
	 * Throws an exception if value not has been set.
	 */
	@Override
	public abstract <T extends Object> T getValue() throws ArgumentsException;

	/**
	 * Sets value of Arg.<br>
	 * <br>
	 * Throws exception if the value is not for the right instance.
	 */
	@Override
	public abstract void setValue(Object value) throws ArgumentsException;

}
