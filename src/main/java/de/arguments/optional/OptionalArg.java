package de.arguments.optional;

import de.arguments.Arg;
import de.arguments.exceptions.ArgumentException;

public abstract class OptionalArg extends Arg {

	protected Object defaultt;

	public OptionalArg(char id) {
		super(id);
	}

	protected OptionalArg(char id, String alias) {
		super(id, alias);
	}

	public abstract <T> T getDefault();

	@Override
	public abstract <T> T getValue() throws ArgumentException;

	@Override
	public abstract void setValue(Object value) throws ArgumentException;

	@Override
	public String toString() {
		String output = super.toString();
		output += " (Default = " + getDefault() + ")";

		return output;
	}

}
