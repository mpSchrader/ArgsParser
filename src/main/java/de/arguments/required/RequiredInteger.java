package de.arguments.required;

import de.arguments.exceptions.ArgumentsException;

public class RequiredInteger extends RequiredArg {

	public RequiredInteger(char id) {
		super(id);
		type = "Integer";
	}

	public RequiredInteger(char id, String alias) {
		super(id, alias);
		type = "Integer";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getValue() throws ArgumentsException {
		if (!this.valueSet()) {
			throw new ArgumentsException("Value not set");
		}
		return (Integer) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentsException {

		if (!(value instanceof Integer)) {
			throw new ArgumentsException("Passed Object is not an Integer");
		}

		this.value = (Integer) value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
