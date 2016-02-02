package de.arguments.optional;

import de.arguments.exceptions.ArgumentsException;

public class OptionalInteger extends OptionalArg {

	public OptionalInteger(char id, Integer defaultt) {
		super(id);
		this.defaultt = defaultt;
		type = "Integer";
	}

	public OptionalInteger(char id, String alias, Integer defaultt) {
		super(id, alias);
		this.defaultt = defaultt;
		type = "Integer";
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
	public void setValue(Object value) throws ArgumentsException {

		if (!(value instanceof Integer)) {
			throw new ArgumentsException("Passed Object is not an Integer");
		}

		this.value = (Integer) value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getDefault() {
		return (Integer) defaultt;
	}


}
