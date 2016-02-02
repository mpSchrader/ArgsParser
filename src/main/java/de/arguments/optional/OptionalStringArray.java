package de.arguments.optional;

import de.arguments.exceptions.ArgumentsException;

public class OptionalStringArray extends OptionalArray {

	public OptionalStringArray(char id, String[] defaultt)
			throws ArgumentsException {
		super(id);
		this.defaultt = defaultt;
		type = "StringArray";
	}

	public OptionalStringArray(char id, String alias, String[] defaultt)
			throws ArgumentsException {
		super(id, alias);
		this.defaultt = defaultt;
		type = "StringArray";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String[] getValue() throws ArgumentsException {

		if (valueNotSet()) {
			return (String[]) defaultt;
		}

		return (String[]) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentsException {

		if (!(value instanceof String[])) {
			throw new ArgumentsException("Object " + value
					+ " is not a String[]!");
		}

		this.value = (String[]) value;

	}

	@SuppressWarnings("unchecked")
	@Override
	public String[] getDefault() {
		return (String[]) defaultt;
	}

	@Override
	protected String defaultToString() {
		String output = "[";
		for (String def : (String[]) defaultt) {
			
			if (def.startsWith("\"") && def.endsWith("\"")) {
				output += def + ", ";
			} else {
				output += "\"" + def + "\", ";
			}
			
		}
		output = output.substring(0, output.length() - 2);
		output += "]";
		return output;
	}
}
