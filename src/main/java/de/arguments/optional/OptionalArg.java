package de.arguments.optional;

import de.arguments.Arg;
import de.arguments.exceptions.ArgumentsException;

public abstract class OptionalArg extends Arg {

	protected Object defaultt;

	protected OptionalArg(char id) {
		super(id);
	}

	protected OptionalArg(char id, String alias) {
		super(id, alias);
	}

	/**
	 * Gets default value of this optional arg.
	 * 
	 * @return
	 */
	public abstract <T> T getDefault();

	/**
	 * Returns value of the object. If no value has been set the returned value
	 * equals the default value.
	 */
	@Override
	public abstract <T> T getValue() throws ArgumentsException;

	/**
	 * Sets value of Arg.<br>
	 * <br>
	 * Throws exception if the value is not for the right instance.
	 */
	@Override
	public abstract void setValue(Object value) throws ArgumentsException;

	/**
	 * 
	 */
	@Override
	public String toString() {
		String output = super.toString();
		output += " (Default = " + getDefault() + ")";

		return output;
	}

}
