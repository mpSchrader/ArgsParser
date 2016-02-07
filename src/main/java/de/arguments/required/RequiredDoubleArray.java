package de.arguments.required;

import de.arguments.exceptions.ArgumentsException;

public class RequiredDoubleArray extends RequiredArray {

	public RequiredDoubleArray(char id) throws ArgumentsException {
		super(id);
		this.type = "DoubleArray";
	}

	public RequiredDoubleArray(char id, String alias)
			throws ArgumentsException {
		super(id, alias);
		this.type = "DoubleArray";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Double[] getValue() throws ArgumentsException {

		if (valueNotSet()) {
			throw new ArgumentsException("Value not set!");
		}

		return (Double[]) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentsException {

		if (!(value instanceof Double[])) {
			throw new ArgumentsException("Object " + value
					+ " is not a Double[]!");
		}

		this.value = (Double[]) value;

	}

}
