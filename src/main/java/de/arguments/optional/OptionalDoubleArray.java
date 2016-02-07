package de.arguments.optional;

import de.arguments.exceptions.ArgumentsException;

public class OptionalDoubleArray extends OptionalArray {

	public OptionalDoubleArray(char id, Double[] defaultt)
			throws ArgumentsException {
		super(id);
		this.defaultt = defaultt;
		type = "DoubleArray";
	}

	public OptionalDoubleArray(char id, String alias, Double[] defaultt)
			throws ArgumentsException {
		super(id, alias);
		this.defaultt = defaultt;
		type = "DoubleArray";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Double[] getValue() throws ArgumentsException {

		if (valueNotSet()) {
			return (Double[]) defaultt;
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

	@SuppressWarnings("unchecked")
	@Override
	public Double[] getDefault() {
		return (Double[]) defaultt;
	}

}
