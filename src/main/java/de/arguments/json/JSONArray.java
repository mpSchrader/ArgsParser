package de.arguments.json;

import java.util.ArrayList;

import de.arguments.exceptions.JSONException;

/**
 * Basic JSONArray implementation, which only implements the minimal needed
 * functionality for this project.
 * 
 * @author Max-Philipp Schrader
 *
 */
public class JSONArray {

	private ArrayList<Object> values;

	/**
	 * Creates an empty JSONArray.
	 */
	public JSONArray() {
		values = new ArrayList<Object>();
	}

	/**
	 * Creates a JSONArray from a string.
	 * 
	 * @param raw
	 * @throws JSONException
	 */
	public JSONArray(String raw) throws JSONException {
		values = new ArrayList<Object>();
		checkFormat(raw);
		addValuesFromString(raw);
	}

	/**
	 * Checks if the array is surrounded by "[" and "]".
	 * 
	 * @param raw
	 * @throws JSONException
	 */
	private void checkFormat(String raw) throws JSONException {
		raw = raw.trim();
		if (!raw.startsWith("[") || !raw.endsWith("]"))
			throw new JSONException("");

	}

	/**
	 * Splits the raw string by ",". <br>
	 * <br>
	 * Allows to contain a longer string with "," inside if the the string is
	 * surrounded by ".
	 * 
	 * @param raw
	 * @throws JSONException
	 */
	private void addValuesFromString(String raw) throws JSONException {
		raw = raw.substring(1, raw.length() - 1);
		String[] parts = raw.split(",");

		boolean isCombined = false;
		int type = -1;
		String combined = "";

		for (int i = 0; i < parts.length; i++) {
			String part = parts[i];

			if (!isCombined && isStart(part)) {
				type = getType(part);
				isCombined = true;
				combined = part;
			} else if (isCombined) {
				combined += "," + part;
			} else {
				addValue(part);
			}

			if (isCombined && isEnd(combined, type)) {
				addValue(combined);
				isCombined = false;
			}

		}

	}

	/**
	 * Gets the type of the part.<br>
	 * <br>
	 * This type indicates whether it is the start of a longer string ", an
	 * JSONOject { or an JSONArray [.
	 * 
	 * @param part
	 * @return
	 */
	private int getType(String part) {
		part = part.trim();
		if (part.startsWith("\""))
			return 1;
		if (part.startsWith("["))
			return 2;
		if (part.startsWith("{"))
			return 3;
		return -1;
	}

	/**
	 * Checks if this part is the beginning of an complex object like a long
	 * string containing ",", an JSONObject or an JSONArray.
	 * 
	 * @param part
	 * @return
	 */
	private boolean isStart(String part) {
		part = part.trim();
		return part.startsWith("\"") || part.startsWith("[") || part.startsWith("{");
	}

	/**
	 * Checks if this part is the end of an complex object like a long string
	 * containing ",", an JSONObject or an JSONArray.
	 * 
	 * @param part
	 * @param type
	 * @return
	 */
	private boolean isEnd(String part, int type) {
		part.trim();
		if (type == 1)
			return part.endsWith("\"");
		if (type == 2)
			return part.endsWith("]");
		if (type == 3)
			return part.endsWith("}");
		return false;
	}

	/**
	 * Adds value to this JSONArray.
	 * 
	 * @param value
	 * @throws JSONException
	 */
	private void addValue(String value) throws JSONException {
		value = value.trim();

		boolean worked = tryAddAsInteger(value);
		if (worked) {
			return;
		}

		worked = tryAddAsDouble(value);
		if (worked) {
			return;
		}

		worked = tryAddAsBoolean(value);
		if (worked) {
			return;
		}

		worked = tryAddAsJSONObject(value);
		if (worked) {
			return;
		}

		worked = tryAddAsJSONArray(value);
		if (worked) {
			return;
		}

		addAsString(value);

	}

	/**
	 * Tries to add this part as an Integer. <br>
	 * <br>
	 * Returns true if this worked.
	 * 
	 * @param part
	 * @return
	 */
	private boolean tryAddAsInteger(String part) {
		try {

			Integer value = new Integer(part);
			append(value);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Tries to add this part as a Double. <br>
	 * <br>
	 * Returns true if this worked.
	 * 
	 * @param part
	 * @return
	 */
	private boolean tryAddAsDouble(String part) {
		try {

			Double value = new Double(part);
			append(value);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Tries to add this part as a Boolean. <br>
	 * <br>
	 * Returns true if this worked.
	 * 
	 * @param part
	 * @return
	 */
	private boolean tryAddAsBoolean(String part) {

		if (part.equalsIgnoreCase("true") || part.equalsIgnoreCase("false")) {
			Boolean value = new Boolean(part);
			append(value);
			return true;
		}

		return false;

	}

	/**
	 * Tries to add this part as an JSONObject. <br>
	 * <br>
	 * Returns true if this worked.
	 * 
	 * @param part
	 * @return
	 */
	private boolean tryAddAsJSONObject(String part) {
		try {

			JSONObject value = new JSONObject(part);
			append(value);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Tries to add this part as an JSONArray. <br>
	 * <br>
	 * Returns true if this worked.
	 * 
	 * @param part
	 * @return
	 */
	private boolean tryAddAsJSONArray(String part) {
		try {

			JSONArray value = new JSONArray(part);
			append(value);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Adds this part as a string.
	 * 
	 * @param part
	 * @return
	 */
	private void addAsString(String part) {
		values.add(part);
	}

	/**
	 * Appends the object to this JSONArray.
	 * 
	 * @param value
	 */
	public void append(Object value) {
		values.add(value);
	}

	/**
	 * Appends the boolean to this JSONArray.
	 * 
	 * @param value
	 */
	public void append(Boolean value) {
		values.add(value);
	}

	/**
	 * Appends the integer to this JSONArray.
	 * 
	 * @param value
	 */
	public void append(int value) {
		values.add(value);
	}

	/**
	 * Appends the double to this JSONArray.
	 * 
	 * @param value
	 */
	public void append(Double value) {
		values.add(value);
	}

	/**
	 * Appends the character to this JSONArray.
	 * 
	 * @param value
	 */
	public void append(Character value) {
		values.add(value);
	}

	/**
	 * Appends the char to this JSONArray.
	 * 
	 * @param value
	 */
	public void append(char value) {
		values.add(new Character(value));
	}

	/**
	 * Appends the string to this JSONArray.
	 * 
	 * @param value
	 */
	public void append(String value) {
		values.add(value);
	}

	/**
	 * Appends the JSONObject to this JSONArray.
	 * 
	 * @param value
	 */
	public void append(JSONObject value) {
		values.add(value);
	}

	/**
	 * Appends another JSONArray to this JSONArray.
	 * 
	 * @param value
	 */
	public void append(JSONArray value) {
		values.add(value);
	}

	/**
	 * Gets Object at position i.<br>
	 * <br>
	 * Throws an exception if the position is out of bounds or the type is
	 * wrong.
	 * 
	 * @param i
	 * @return searched object
	 * @throws JSONException
	 */
	public Object get(int i) throws JSONException {
		checkIndex(i);
		return values.get(i);
	}

	/**
	 * Gets boolean at position i.<br>
	 * <br>
	 * Throws an exception if the position is out of bounds or the type is
	 * wrong.
	 * 
	 * @param i
	 * @return searched boolean
	 * @throws JSONException
	 */
	public Boolean getBoolean(int i) throws JSONException {
		Object value = get(i);

		if (value instanceof Boolean)
			return (Boolean) values.get(i);

		throw new JSONException("Element " + i + " is not a Boolean!");
	}

	/**
	 * Gets integer at position i.<br>
	 * <br>
	 * Throws an exception if the position is out of bounds or the type is
	 * wrong.
	 * 
	 * @param i
	 * @return searched integer
	 * @throws JSONException
	 */
	public Integer getInteger(int i) throws JSONException {
		Object value = get(i);

		if (value instanceof Integer)
			return (Integer) values.get(i);

		throw new JSONException("Element " + i + " is not a Integer!");
	}

	/**
	 * Gets character at position i.<br>
	 * <br>
	 * Throws an exception if the position is out of bounds or the type is
	 * wrong.
	 * 
	 * @param i
	 * @return searched character
	 * @throws JSONException
	 */
	public Character getCharacter(int i) throws JSONException {
		Object value = get(i);

		if (value instanceof Character)
			return (Character) values.get(i);

		if (isCharString(value))
			return ((String) values.get(i)).charAt(0);

		throw new JSONException("Element " + i + " is not a Character!");
	}

	/**
	 * Checks if the object is a String of length 1 and can be interpreted as
	 * char.
	 * 
	 * @param value
	 * @return
	 */
	private boolean isCharString(Object value) {
		if (value instanceof String)
			return ((String) value).length() == 1;
		return false;
	}

	/**
	 * Gets double at position i.<br>
	 * <br>
	 * Throws an exception if the position is out of bounds or the type is
	 * wrong.
	 * 
	 * @param i
	 * @return searched double
	 * @throws JSONException
	 */
	public Double getDouble(int i) throws JSONException {
		Object value = get(i);

		if (value instanceof Double)
			return (Double) values.get(i);

		throw new JSONException("Element " + i + " is not a Double!");
	}

	/**
	 * Gets string at position i.<br>
	 * <br>
	 * Throws an exception if the position is out of bounds or the type is
	 * wrong.
	 * 
	 * @param i
	 * @return searched string
	 * @throws JSONException
	 */
	public String getString(int i) throws JSONException {
		Object value = get(i);

		if (value instanceof String)
			return (String) values.get(i);

		throw new JSONException("Element " + i + " is not a String!");
	}

	/**
	 * Gets JSONObject at position i.<br>
	 * <br>
	 * Throws an exception if the position is out of bounds or the type is
	 * wrong.
	 * 
	 * @param i
	 * @return searched JSONObject
	 * @throws JSONException
	 */
	public JSONObject getJSONObject(int i) throws JSONException {
		Object value = get(i);

		if (value instanceof JSONObject)
			return (JSONObject) values.get(i);

		throw new JSONException("Element " + i + " is not a JSONObject!");
	}

	/**
	 * Gets JSONArray at position i.<br>
	 * <br>
	 * Throws an exception if the position is out of bounds or the type is
	 * wrong.
	 * 
	 * @param i
	 * @return searched JSONArray
	 * @throws JSONException
	 */
	public JSONArray getJSONArray(int i) throws JSONException {
		Object value = get(i);

		if (value instanceof JSONArray)
			return (JSONArray) values.get(i);

		throw new JSONException("Element " + i + " is not a JSONArray!");
	}

	/**
	 * Checks if the index i matches the length of the array.
	 * 
	 * @param i
	 * @throws JSONException
	 */
	private void checkIndex(int i) throws JSONException {
		if (i >= values.size())
			throw new JSONException("Array out of bounds. " + i);
	}

	/**
	 * 
	 */
	public boolean equals(Object obj) {

		if (!(obj instanceof JSONArray)) {
			return false;
		}

		return this.values.equals(((JSONArray) obj).values);
	}

	/**
	 * 
	 */
	public int hashCode() {
		return this.values.hashCode();
	}

	/**
	 * 
	 */
	public String toString() {

		if (length() == 0) {
			return "[]";
		}

		String output = "[";

		for (Object v : values) {
			output += v + ", ";
		}

		output = output.substring(0, output.length() - 2) + "]";

		return output;
	}

	/**
	 * Gets length of the Array.
	 * 
	 * @return array length
	 */
	public int length() {
		return values.size();
	}
}
