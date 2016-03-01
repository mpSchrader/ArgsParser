package de.arguments.optional;

import de.arguments.exceptions.ArgumentsException;

public class OptionalCharArray extends OptionalArray {

	public OptionalCharArray(char id, Character[] defaultt)
			throws ArgumentsException {
		super(id);
		this.defaultt = defaultt;
		type = "CharArray";
	}

	public OptionalCharArray(char id, String alias, Character[] defaultt)
			throws ArgumentsException {
		super(id, alias);
		this.defaultt = defaultt;
		type = "CharArray";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Character[] getValue() throws ArgumentsException {

		if (valueNotSet()) {
			return (Character[]) defaultt;
		}

		return (Character[]) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentsException {

		if (!(value instanceof Character[])) {
			throw new ArgumentsException("Object " + value
					+ " is not a Character[]!");
		}

		this.value = (Character[]) value;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Character[] getDefault() {
		return (Character[]) defaultt;
	}

}
