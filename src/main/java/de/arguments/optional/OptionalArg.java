package de.arguments.optional;

import de.arguments.Arg;
import de.arguments.exceptions.ArgumentException;

public abstract class OptionalArg extends Arg {

	protected Object defaultt;
	
	protected OptionalArg(String identifier) {
		super(identifier);
	}
	
	protected OptionalArg(String identifier, String describtion) {
		super(identifier,describtion);
	}

	public abstract <T> T getDefault();
	
	@Override
	public abstract <T> T getValue() throws ArgumentException;

	@Override
	public abstract void setValue(Object value) throws ArgumentException;


}
