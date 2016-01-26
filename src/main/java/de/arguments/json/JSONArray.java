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

	public JSONArray() {
		values = new ArrayList<Object>();
	}

	public JSONArray(String raw) throws JSONException {
		values = new ArrayList<Object>();
		checkFormat(raw);
		addValuesFromString(raw);
	}

	private void checkFormat(String raw) throws JSONException {
		raw = raw.trim();
		if (!raw.startsWith("[") || !raw.endsWith("]"))
			throw new JSONException("");

	}

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

	private int getType(String part) {
		part.trim();
		if (part.startsWith("\""))
			return 1;
		if (part.startsWith("["))
			return 2;
		if (part.startsWith("{"))
			return 3;
		return -1;
	}

	private boolean isStart(String part) {
		return part.startsWith("\"") || part.startsWith("[")
				|| part.startsWith("{");
	}
	
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

	private void addValue(String part) throws JSONException {
		part = part.trim();
		boolean worked = tryAddAsInteger(part);
		if (worked) {
			return;
		}

		worked = tryAddAsDouble(part);
		if (worked) {
			return;
		}

		worked = tryAddAsBoolean(part);
		if (worked) {
			return;
		}

		worked = tryAddAsJSONObject(part);
		if (worked) {
			return;
		}

		worked = tryAddAsJSONArray(part);
		if (worked) {
			return;
		}

		values.add(part);

	}

	private boolean tryAddAsInteger(String part) {
		try {

			Integer value = new Integer(part);
			append(value);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	private boolean tryAddAsDouble(String part) {
		try {

			Double value = new Double(part);
			append(value);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	private boolean tryAddAsBoolean(String part) {

		if (part.equalsIgnoreCase("true") || part.equalsIgnoreCase("false")) {
			Boolean value = new Boolean(part);
			append(value);
			return true;
		}

		return false;

	}

	private boolean tryAddAsJSONObject(String part) {
		try {

			JSONObject value = new JSONObject(part);
			append(value);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	private boolean tryAddAsJSONArray(String part) {
		try {

			JSONArray value = new JSONArray(part);
			append(value);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public void append(Object value) {
		values.add(value);
	}

	public void append(Boolean value) {
		values.add(value);
	}

	public void append(int value) {
		values.add(value);
	}

	public void append(Double value) {
		values.add(value);
	}

	public void append(String value) {
		values.add(value);
	}

	public void append(JSONObject value) {
		values.add(value);
	}

	public void append(JSONArray value) {
		values.add(value);
	}

	public Object get(int i) throws JSONException {
		checkIndex(i);
		return values.get(i);
	}

	public Boolean getBoolean(int i) throws JSONException {
		Object value = get(i);

		if (value instanceof Boolean)
			return (Boolean) values.get(i);

		throw new JSONException("Element " + i + " is not a Boolean!");
	}

	public Integer getInteger(int i) throws JSONException {
		Object value = get(i);

		if (value instanceof Integer)
			return (Integer) values.get(i);

		throw new JSONException("Element " + i + " is not a Integer!");
	}

	public Double getDouble(int i) throws JSONException {
		Object value = get(i);

		if (value instanceof Double)
			return (Double) values.get(i);

		throw new JSONException("Element " + i + " is not a Double!");
	}

	public String getString(int i) throws JSONException {
		Object value = get(i);

		if (value instanceof String)
			return (String) values.get(i);

		throw new JSONException("Element " + i + " is not a String!");
	}

	public JSONObject getJSONObject(int i) throws JSONException {
		Object value = get(i);

		if (value instanceof JSONObject)
			return (JSONObject) values.get(i);

		throw new JSONException("Element " + i + " is not a JSONObject!");
	}

	public JSONArray getJSONArray(int i) throws JSONException {
		Object value = get(i);

		if (value instanceof JSONArray)
			return (JSONArray) values.get(i);

		throw new JSONException("Element " + i + " is not a JSONArray!");
	}

	private void checkIndex(int i) throws JSONException {
		if (i >= values.size())
			throw new JSONException("Array out of bounds. " + i);
	}

	public boolean equals(Object obj) {

		if (!(obj instanceof JSONArray)) {
			return false;
		}

		return this.values.equals(((JSONArray) obj).values);
	}

	public int hashCode() {
		return this.values.hashCode();
	}

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

	public int length() {
		return values.size();
	}
}
