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

	protected void checkArrayStructure(String[] rawValues)
			throws ArgumentsException {
		if (!rawValues[0].startsWith("[")) {
			throw new ArgumentsException("Raw Array does not start with '['");
		}

		if (!rawValues[rawValues.length - 1].endsWith("]")) {
			throw new ArgumentsException("Raw Array does not end with ']'");
		}

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
