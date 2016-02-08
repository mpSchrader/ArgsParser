package de.arguments.optional;

import java.util.Arrays;

import de.arguments.exceptions.ArgumentsException;

public abstract class OptionalArray extends OptionalArg {

	protected OptionalArray(char id) throws ArgumentsException {
		super(id);
	}

	protected OptionalArray(char id, String alias) throws ArgumentsException {
		super(id, alias);
	}

	public String toString() {
		String output = super.toString().split(" \\(Default =")[0];
		output += " (Default = "
				+ defaultToString() + ")";
		return output;
	}
	
	protected String defaultToString(){
		return Arrays.toString((Object[]) defaultt);
	}

}
