package de.arguments.required;

import de.arguments.exceptions.ArgumentsException;

public class RequiredBoolean extends RequiredArg {

	public RequiredBoolean(char id) {
		super(id);
		type = "Boolean";
	}

	public RequiredBoolean(char id, String alias) {
		super(id, alias);
		type = "Boolean";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean getValue() throws ArgumentsException {
		if (!this.valueSet()) {
			throw new ArgumentsException("Value not set");
		}
		return (Boolean) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentsException {

		if (!(value instanceof Boolean)) {
			throw new ArgumentsException("Passed Object is not an Integer");
		}

		this.value = (Boolean) value;
	}

	@Override
	public void setValue(String value) throws ArgumentsException {
		try {
			this.value = new Boolean(value);
		} catch (Exception e) {
			throw new ArgumentsException("Unable to parse value: " + value);
		}
	}

}
