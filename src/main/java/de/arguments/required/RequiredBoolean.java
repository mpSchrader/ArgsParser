package de.arguments.required;

import de.arguments.exceptions.ArgumentException;

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
	public Boolean getValue() throws ArgumentException {
		if (!this.valueSet()) {
			throw new ArgumentException("Value not set");
		}
		return (Boolean) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentException {

		if (!(value instanceof Boolean)) {
			throw new ArgumentException("Passed Object is not an Integer");
		}

		this.value = (Boolean) value;
	}

	@Override
	public void setValue(String value) throws ArgumentException {
		try {
			this.value = new Boolean(value);
		} catch (Exception e) {
			throw new ArgumentException("Unable to parse value: " + value);
		}
	}

}
