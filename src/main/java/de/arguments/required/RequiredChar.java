package de.arguments.required;

import de.arguments.exceptions.ArgumentsException;

public class RequiredChar extends RequiredArg {

	public RequiredChar(char id) {
		super(id);
		type = "Char";
	}

	public RequiredChar(char id, String alias) {
		super(id, alias);
		type = "Char";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Character getValue() throws ArgumentsException {
		return (Character) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentsException {

		if (!(value instanceof Character)) {
			throw new ArgumentsException("Passed parameter is not Char! ");
		}

		this.value = (Character) value;

	}

}
