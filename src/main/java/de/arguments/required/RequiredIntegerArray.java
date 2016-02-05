package de.arguments.required;

import de.arguments.exceptions.ArgumentsException;

public class RequiredIntegerArray extends RequiredArray {

	public RequiredIntegerArray(char id) throws ArgumentsException {
		super(id);
		this.type = "IntegerArray";
	}

	public RequiredIntegerArray(char id, String alias)
			throws ArgumentsException {
		super(id, alias);
		this.type = "IntegerArray";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer[] getValue() throws ArgumentsException {

		if (valueNotSet()) {
			throw new ArgumentsException("Value not set!");
		}

		return (Integer[]) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentsException {

		if (!(value instanceof Integer[])) {
			throw new ArgumentsException("Object " + value
					+ " is not a Integer[]!");
		}

		this.value = (Integer[]) value;

	}

}
