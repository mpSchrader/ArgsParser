package de.arguments.optional;

import de.arguments.exceptions.ArgumentException;

public class OptionalInteger extends OptionalArg {

	public OptionalInteger(String identifier, Integer defaultt) {
		super(identifier);
		this.defaultt = defaultt;
	}

	public OptionalInteger(String identifier, Integer defaultt, String usage) {
		super(identifier, usage);
		this.defaultt = defaultt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getValue() {
		if (value != null) {
			return (Integer) value;
		}
		return (Integer) defaultt;
	}

	@Override
	public void setValue(Object value) throws ArgumentException {

		if (!(value instanceof Integer)) {
			throw new ArgumentException("Passed Object is not an Integer");
		}

		this.value = (Integer) value;
	}

	@Override
	public void setValue(String value) throws ArgumentException {
		try {
			this.value = new Integer(value);
		} catch (Exception e) {
			throw new ArgumentException("Unable to parse value: " + value);
		}
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getDefault() {
		return (Integer) defaultt;
	}

	@Override
	public String toString() {
		if (getUsage().equals("")) {
			return identifier + " Integer : (Default = " + defaultt + ")";
		}
		return identifier + " Integer : " + this.getUsage() + " (Default = "
				+ defaultt + ")";
	}

}
