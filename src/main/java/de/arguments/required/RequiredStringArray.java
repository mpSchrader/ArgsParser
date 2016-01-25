package de.arguments.required;

import de.arguments.exceptions.ArgumentsException;

public class RequiredStringArray extends RequiredArray {

	public RequiredStringArray(char id) throws ArgumentsException {
		super(id);
		this.type = "StringArray";
	}

	public RequiredStringArray(char id, String alias) throws ArgumentsException {
		super(id, alias);
		this.type = "StringArray";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String[] getValue() throws ArgumentsException {

		if (valueNotSet()) {
			throw new ArgumentsException("Value not set!");
		}

		return (String[]) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentsException {

		if (!(value instanceof String[])) {
			throw new ArgumentsException("Object " + value
					+ " is not a String[]!");
		}

		this.value  = (String[]) value;

	}

	@Override
	public void setValue(String value) throws ArgumentsException {

		String[] rawValues = value.split(",");
		setValue(rawValues);

	}

}
