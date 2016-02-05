package de.arguments.optional;

import de.arguments.exceptions.ArgumentsException;

public class OptionalIntegerArray extends OptionalArray {

	public OptionalIntegerArray(char id, Integer[] defaultt)
			throws ArgumentsException {
		super(id);
		this.defaultt = defaultt;
		type = "IntegerArray";
	}

	public OptionalIntegerArray(char id, String alias, Integer[] defaultt)
			throws ArgumentsException {
		super(id, alias);
		this.defaultt = defaultt;
		type = "IntegerArray";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer[] getValue() throws ArgumentsException {

		if (valueNotSet()) {
			return (Integer[]) defaultt;
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

	@SuppressWarnings("unchecked")
	@Override
	public Integer[] getDefault() {
		return (Integer[]) defaultt;
	}

}
