package de.arguments.optional;

import de.arguments.exceptions.ArgumentException;

public class OptionalDouble extends OptionalArg {

	public OptionalDouble(String identifier, Double defaultt) {
		super(identifier);
		this.defaultt = defaultt;
	}

	public OptionalDouble(String identifier, Double defaultt, String usage) {
		super(identifier, usage);
		this.defaultt = defaultt;
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
	public void setValue(Object value) throws ArgumentException {

		if (!(value instanceof Double)) {
			throw new ArgumentException("Passed Object is not an Double");
		}

		this.value = (Double) value;
	}

	@Override
	public void setValue(String value) throws ArgumentException {
		try {
			this.value = new Double(value);
		} catch (Exception e) {
			throw new ArgumentException("Unable to parse value: " + value);
		}
	}
	
	public void setValue(Double value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Double getDefault() {
		return (Double) defaultt;
	}
	
	@Override
	public String toString() {
		if (getUsage().equals("")){
			return identifier + " Double : (Default = "
					+ defaultt + ")";
		}
		return identifier + " Double : " + this.getUsage() + " (Default = "
				+ defaultt + ")";
	}

}
