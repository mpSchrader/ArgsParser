package de.arguments.optional;

import de.arguments.exceptions.ArgumentException;

public class OptionalString extends OptionalArg {

	public OptionalString(String identifier, String defaultt) {
		super(identifier);
		this.defaultt = defaultt;
	}

	public OptionalString(String identifier, String defaultt, String usage) {
		super(identifier, usage);
		this.defaultt = defaultt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getValue() {
		if (value != null) {
			return (String) value;
		}
		return (String) defaultt;
	}

	@Override
	public void setValue(Object value) throws ArgumentException {

		if (!(value instanceof String)) {
			throw new ArgumentException("Passed Object is not an Integer");
		}

		this.value = (String) value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getDefault() {
		return (String) defaultt;
	}

	@Override
	public String toString() {
		if (getUsage().equals("")){
			return identifier + " String : (Default = \""
					+ defaultt + "\")";
		}
		return identifier + " String : " + this.getUsage() + " (Default = \""
				+ defaultt + "\")";
	}

}
