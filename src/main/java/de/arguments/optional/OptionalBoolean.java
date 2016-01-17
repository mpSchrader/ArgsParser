package de.arguments.optional;

import de.arguments.exceptions.ArgumentException;

public class OptionalBoolean extends OptionalArg {

	public OptionalBoolean(String identifier, Boolean defaultt) {
		super(identifier);
		this.defaultt = defaultt;
	}

	public OptionalBoolean(String identifier, Boolean defaultt, String usage) {
		super(identifier, usage);
		this.defaultt = defaultt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean getDefault() {
		return (Boolean) defaultt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean getValue() {
		if (value != null) {
			return (Boolean) value;
		}
		return (Boolean) defaultt;
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
		if (getUsage().equals("")) {
			return identifier + " Boolean : (Default = " + defaultt + ")";
		}
		return identifier + " Boolean : " + this.getUsage() + " (Default = "
				+ defaultt + ")";
	}

}
