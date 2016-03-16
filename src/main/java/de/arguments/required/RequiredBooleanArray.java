package de.arguments.required;

import de.arguments.exceptions.ArgumentsException;

public class RequiredBooleanArray extends RequiredArray {

	public RequiredBooleanArray(char id) throws ArgumentsException {
		super(id);
		this.type = "BooleanArray";
	}

	public RequiredBooleanArray(char id, String alias)
			throws ArgumentsException {
		super(id, alias);
		this.type = "BooleanArray";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean[] getValue() throws ArgumentsException {

		if (valueNotSet()) {
			throw new ArgumentsException("Value not set!");
		}

		return (Boolean[]) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentsException {

		if (!(value instanceof Boolean[])) {
			throw new ArgumentsException("Object " + value
					+ " is not a Double[]!");
		}

		this.value = (Boolean[]) value;

	}

}
