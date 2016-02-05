package de.arguments.optional;

import de.arguments.exceptions.ArgumentsException;

public class OptionalDouble extends OptionalArg {

	public OptionalDouble(char id, Double defaultt) {
		super(id);
		this.defaultt = defaultt;
		type = "Double";
	}

	public OptionalDouble(char id, String alias, Double defaultt) {
		super(id, alias);
		this.defaultt = defaultt;
		type = "Double";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Double getValue() {
		if (value != null) {
			return (Double) value;
		}
		return (Double) defaultt;
	}

	@Override
	public void setValue(Object value) throws ArgumentsException {
		
		if (!(value instanceof Double)) {
			throw new ArgumentsException("Passed Object is not an Double: "+value);
		}

		this.value = (Double) value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Double getDefault() {
		return (Double) defaultt;
	}

}
