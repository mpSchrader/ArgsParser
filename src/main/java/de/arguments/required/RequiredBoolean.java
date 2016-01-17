package de.arguments.required;

import de.arguments.exceptions.ArgumentException;

public class RequiredBoolean extends RequiredArg {

	public RequiredBoolean(String identifier) {
		super(identifier);
	}

	public RequiredBoolean(String identifier, String usage) {
		super(identifier, usage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean getValue() throws ArgumentException {
		if (!this.checkIfValueIsSet()) {
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

	@Override
	public String toString() {
		if (!this.getUsage().equals("")) {
			return identifier + " Boolean : " + this.getUsage();
		}
		return identifier+" Boolean";
	}

}
