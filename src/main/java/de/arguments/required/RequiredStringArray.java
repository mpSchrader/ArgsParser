package de.arguments.required;

import java.util.ArrayList;
import java.util.List;

import de.arguments.exceptions.ArgumentsException;

public class RequiredStringArray extends RequiredArray {

	public RequiredStringArray(char id) throws ArgumentsException {
		super(id);
		this.type = "StringArray";
	}

	public RequiredStringArray(char id, String alias) throws ArgumentsException {
		super(id, alias);
		this.type = "StringArray";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String[] getValue() throws ArgumentsException {

		if (valueNotSet()) {
			throw new ArgumentsException("Value not set!");
		}

		return (String[]) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentsException {

		if (!(value instanceof String[])) {
			throw new ArgumentsException("Object " + value
					+ " is not a String[]!");
		}

		String[] rawValues = (String[]) value;
		checkArrayStructure(rawValues);
		prepareArray(rawValues);
		this.value = combineStringArray(rawValues);			

	}

	private String[] combineStringArray(String[] rawValues)
			throws ArgumentsException {

		List<String> values = new ArrayList<String>();
		boolean isCombined = false;
		String combined = new String();

		for (String rawValue : rawValues) {
			if (isStartOfCombineString(rawValue)) {

				isCombined = true;
				combined = rawValue.substring(1);

			} else if (isCombined && isEndOfCombineString(rawValue)) {

				combined += " " + rawValue.substring(0, rawValue.length() - 1);
				values.add(combined);
				combined = new String();
				isCombined = false;

			} else if (isCombined) {

				combined += " " + rawValue;

			} else {

				values.add(rawValue);

			}
		}
		for(String v : values)
		System.out.println(v);
		return (String[]) values.toArray(new String[values.size()]);
	}

	private String[] prepareArray(String[] rawValues) {
		rawValues[0] = rawValues[0].substring(1);

		int indexOfLast = rawValues.length - 1;
		int endOfLast = rawValues[indexOfLast].lastIndexOf("]");
		rawValues[indexOfLast] = rawValues[indexOfLast].substring(0, endOfLast);
		return rawValues;
	}

	@Override
	public void setValue(String value) throws ArgumentsException {

		String[] rawValues = value.split(",");
		setValue(rawValues);

	}

	private boolean isStartOfCombineString(String rawValue) {
		boolean isStart = rawValue.startsWith("\"");
		isStart &= !rawValue.endsWith("\"");
		return isStart;
	}

	private boolean isEndOfCombineString(String rawValue) {
		boolean isEnd = !rawValue.startsWith("\"");
		isEnd &= rawValue.endsWith("\"");
		return isEnd;
	}

}
