package de.arguments.required;

import de.arguments.exceptions.ArgumentsException;

public class RequiredCharArray extends RequiredArray {

	public RequiredCharArray(char id) throws ArgumentsException {
		super(id);
		this.type = "CharArray";
	}

	public RequiredCharArray(char id, String alias)
			throws ArgumentsException {
		super(id, alias);
		this.type = "CharArray";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Character[] getValue() throws ArgumentsException {

		if (valueNotSet()) {
			throw new ArgumentsException("Value not set!");
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

}
