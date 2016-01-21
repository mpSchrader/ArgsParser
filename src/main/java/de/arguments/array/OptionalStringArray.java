package de.arguments.array;

import java.util.ArrayList;
import java.util.List;

import de.arguments.exceptions.ArgumentException;

public class OptionalStringArray extends OptionalArray {

	public OptionalStringArray(char id, String[] defaultt) throws ArgumentException {
		super(id);
		this.defaultt = defaultt;
	}

	public OptionalStringArray(char id, String alias, String[] defaultt) throws ArgumentException {
		super(id, alias);
		this.defaultt = defaultt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String[] getValue() throws ArgumentException {

		if (valueNotSet()) {
			return (String[]) defaultt;
		}

		return (String[]) value;
	}

	@Override
	public void setValue(Object value) throws ArgumentException {
		
		if (!(value instanceof String[])) {
			throw new ArgumentException("Object " + value
					+ " is not a String[]!");
		}

		String[] rawValues = (String[]) value;
		List<String> values = new ArrayList<String>();
		boolean isCombined = false;
		String combined = new String();

		checkArrayStructure(rawValues);
		prepareArray(rawValues);

		for (String rawValue : rawValues) {
			if (isStartOfCombineString(rawValue)) {
				
				isCombined = true;
				combined = rawValue.substring(1);
				
			} else if (isCombined && isEndOfCombineString(rawValue)) {
				
				combined += " " + rawValue.substring(0, rawValue.length()-1);			
				values.add(combined);
				combined = new String();
				isCombined = false;
				
			} else if (isCombined) {
				
				combined += " "+rawValue;
				
			} else {
				
				values.add(rawValue);
				
			}
		}
		for (String rawValue : values) {
			System.out.println(rawValue);
		}
		this.value = (String[]) values.toArray(new String[values.size()]);


	}

	private String[] prepareArray(String[] rawValues) {
		rawValues[0] = rawValues[0].substring(1);

		int indexOfLast = rawValues.length - 1;
		int endOfLast = rawValues[indexOfLast].lastIndexOf("]");
		rawValues[indexOfLast] = rawValues[indexOfLast].substring(0, endOfLast);
		return rawValues;
	}

	@Override
	public void setValue(String value) throws ArgumentException {

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


	@SuppressWarnings("unchecked")
	@Override
	public String[] getDefault() {
		return (String[])defaultt;
	}


}
