package de.arguments.optional;

import java.util.Arrays;

import de.arguments.exceptions.ArgumentsException;

public class OptionalBooleanArray extends OptionalArray {

	public OptionalBooleanArray(char id, Boolean[] defaultt)
			throws ArgumentsException {
		super(id);
		this.defaultt = defaultt;
		type = "BooleanArray";
	}

	public OptionalBooleanArray(char id, String alias, Boolean[] defaultt)
			throws ArgumentsException {
		super(id, alias);
		this.defaultt = defaultt;
		type = "BooleanArray";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean[] getValue() throws ArgumentsException {

		if (valueNotSet()) {
			return (Boolean[]) defaultt;
		}

		return (Boolean[]) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentsException {

		if (!(value instanceof Boolean[])) {
			throw new ArgumentsException("Object " + value
					+ " is not a Boolean[]!");
		}

		System.out.println(Arrays.toString((Boolean[]) this.value));
		this.value = (Boolean[]) value;
		System.out.println("this: "+Arrays.toString((Boolean[]) this.value));
		System.out.println("Input: "+Arrays.toString((Boolean[]) value));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean[] getDefault() {
		return (Boolean[]) defaultt;
	}

}
