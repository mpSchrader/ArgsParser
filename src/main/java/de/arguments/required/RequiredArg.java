package de.arguments.required;

import de.arguments.Arg;
import de.arguments.exceptions.ArgumentException;

public abstract class RequiredArg extends Arg {
	
	protected RequiredArg(char id) {
		super(id);
	}
	
	protected RequiredArg(char id, String alias) {
		super(id, alias);
	}
	

	@Override
	public abstract <T extends Object> T getValue() throws ArgumentException;

	@Override
	public abstract void setValue(Object value) throws ArgumentException;

	public boolean checkIfValueIsSet(){
		return !(this.value == null);
	}
}
