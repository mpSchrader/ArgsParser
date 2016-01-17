package de.arguments.optional;

import de.arguments.exceptions.ArgumentException;

public class OptionalString extends OptionalArg {

	public OptionalString(char id, String defaultt) {
		super(id);
		this.defaultt = defaultt;
	}

	public OptionalString(char id,String alias, String defaultt) {
		super(id, alias);
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

}
