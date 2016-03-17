package de.arguments.json;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import de.arguments.exceptions.JSONException;

/**
 * Basic JSONObject implementation, which only implements the minimal needed
 * functionality for this project.
 * 
 * @author Max-Philipp Schrader
 *
 */
public class JSONObject {

	private Map<String, Object> attributes;

	/**
	 * Creates empty JSONObject.
	 */
	public JSONObject() {
		attributes = new HashMap<String, Object>(6);
	}

	/**
	 * Creates JSONObject from input string.
	 * 
	 * @param raw
	 * @throws JSONException
	 */
	public JSONObject(String raw) throws JSONException {
		attributes = new HashMap<String, Object>(6);
		checkFormat(raw);
		raw = prepareString(raw);
		addValuesFromString(raw);
	}

	/**
	 * Creates JSONObject with one key-value pair.
	 * 
	 * @param key
	 * @param value
	 */
	public JSONObject(String key, Object value) {
		attributes = new HashMap<String, Object>(6);
		attributes.put(key, value);
	}

	/**
	 * Checks if the raw string starts and ends with "{" and "}".
	 * 
	 * @param raw
	 * @throws JSONException
	 */
	private void checkFormat(String raw) throws JSONException {
		raw = raw.trim();
		if (!raw.startsWith("{") || !raw.endsWith("}"))
			throw new JSONException("String not right formated!");
	}

	/**
	 * Removes the brackets and multiple occurring whitespace characters.
	 * 
	 * @param raw
	 * @return
	 */
	private String prepareString(String raw) {
		raw = raw.trim();
		raw = raw.substring(1, raw.length() - 1);
		raw = raw.replaceAll("\\s+", " ");
		return raw;
	}

	/**
	 * Add values with their keys.
	 * 
	 * @param raw
	 * @throws JSONException
	 */
	private void addValuesFromString(String raw) throws JSONException {

		String[] parts = raw.split(",");

		String value = "";
		String key = "";

		for (int i = 0; i < parts.length; i++) {

			String part = parts[i];
			String[] splitted = part.split(":", 2);

			if (splitted.length >= 2) {

				key = getKey(splitted[0]);
				value = getValue(parts, i);
				addValue(key, value);

			}

		}

	}

	/**
	 * Gets the key from raw string.
	 * 
	 * @param rawKey
	 * @return
	 * @throws JSONException
	 */
	private String getKey(String rawKey) throws JSONException {
		rawKey = rawKey.trim();

		if (!(rawKey.startsWith("\"") && rawKey.endsWith("\""))) {
			throw new JSONException("Male formated key: " + rawKey);
		}
		String key = rawKey.substring(1, rawKey.length() - 1);
		return key;
	}

	/**
	 * Gets the value of a key-value-pair starting on position j.
	 * 
	 * @param parts
	 * @param j
	 * @return
	 */
	private String getValue(String[] parts, int j) {

		String first = parts[j].split(":", 2)[1];

		if (isStartAndEnd(first)) {
			return first;
		}

		String combined = first;
		int type = getType(combined);

		for (int i = j + 1; i < parts.length; i++) {
			combined += ", " + parts[i];
			parts[i] = "";
			if (isEnd(combined, type)) {
				break;
			}
		}

		return combined;
	}

	/**
	 * Gets type of the part, such as JSONOject, JSONArray or a String
	 * containing ",".
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
	 * Checks if the part is start and end at the same time.
	 * 
	 * @param part
	 * @return
	 */
	private boolean isStartAndEnd(String part) {
		part = part.trim();

		boolean isStartEnd = false;

		isStartEnd |= part.startsWith("\"") && part.endsWith("\"");
		isStartEnd |= part.startsWith("[") && part.endsWith("]");
		isStartEnd |= part.startsWith("{") && part.endsWith("}");

		isStartEnd |= !part.startsWith("\"") && !part.startsWith("{") && !part.startsWith("[");

		return isStartEnd;
	}

	/**
	 * Checks if the part is the end of a combined string, depending of the
	 * type.
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
	 * Adds the key-value combination to this JSONObject.
	 * 
	 * @param key
	 * @param value
	 * @throws JSONException
	 */
	private void addValue(String key, String value) throws JSONException {
		value = value.trim();

		boolean worked = tryAddAsInteger(key, value);
		if (worked) {
			return;
		}

		worked = tryAddAsDouble(key, value);
		if (worked) {
			return;
		}

		worked = tryAddAsBoolean(key, value);
		if (worked) {
			return;
		}

		worked = tryAddAsJSONObject(key, value);
		if (worked) {
			return;
		}

		worked = tryAddAsJSONArray(key, value);
		if (worked) {
			return;
		}

		worked = tryAddAsString(key, value);
		if (worked) {
			return;
		}

		throw new JSONException("Could not add (" + key + ", " + value + ")!");

	}

	private boolean tryAddAsInteger(String key, String part) {
		try {

			Integer value = new Integer(part);
			putInteger(key, value);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	private boolean tryAddAsDouble(String key, String part) {
		try {

			Double value = new Double(part);
			putDouble(key, value);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	private boolean tryAddAsBoolean(String key, String part) {

		if (part.equalsIgnoreCase("true") || part.equalsIgnoreCase("false")) {
			Boolean value = new Boolean(part);
			putBoolean(key, value);
			return true;
		}

		return false;

	}

	private boolean tryAddAsJSONObject(String key, String part) {
		try {

			JSONObject value = new JSONObject(part);
			putJSONObject(key, value);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	private boolean tryAddAsJSONArray(String key, String part) {
		try {

			JSONArray value = new JSONArray(part);
			putJSONArray(key, value);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	private boolean tryAddAsString(String key, String part) {

		if (part.startsWith("\"") && part.endsWith("\"")) {
			String value = part.substring(1, part.length() - 1);
			putString(key, value);
			return true;
		}

		return false;
	}

	/**
	 * Adds the given key-value pair.
	 * 
	 * @param key
	 * @param value
	 */
	public void putString(String key, String value) {
		attributes.put(key, value);
	}

	/**
	 * Gets string value for this key. <br>
	 * <br>
	 * Throws an exception if the value is of the wrong type.
	 * 
	 * @param key
	 * @return value
	 * @throws JSONException
	 */
	public String getString(String key) throws JSONException {

		Object value = attributes.get(key);

		if (value instanceof String) {
			return (String) value;
		}

		throw new JSONException("No String for key " + key);
	}

	/**
	 * Adds the given key-value pair.
	 * 
	 * @param key
	 * @param value
	 */
	public void putInteger(String key, Integer value) {
		attributes.put(key, value);
	}


	/**
	 * Gets integer value for this key. <br>
	 * <br>
	 * Throws an exception if the value is of the wrong type.
	 * 
	 * @param key
	 * @return value
	 * @throws JSONException
	 */
	public Integer getInteger(String key) throws JSONException {
		Object value = attributes.get(key);

		if (value instanceof Integer) {
			return (Integer) value;
		}

		throw new JSONException("No Integer for key " + key);
	}

	/**
	 * Adds the given key-value pair.
	 * 
	 * @param key
	 * @param value
	 */
	public void putDouble(String key, Double value) {
		attributes.put(key, value);
	}


	/**
	 * Gets double value for this key. <br>
	 * <br>
	 * Throws an exception if the value is of the wrong type.
	 * 
	 * @param key
	 * @return value
	 * @throws JSONException
	 */
	public Double getDouble(String key) throws JSONException {
		Object value = attributes.get(key);

		if (value instanceof Double) {
			return (Double) value;
		}

		throw new JSONException("No Double for key " + key);
	}

	/**
	 * Adds the given key-value pair.
	 * 
	 * @param key
	 * @param value
	 */
	public void putJSONObject(String key, JSONObject value) {
		attributes.put(key, value);
	}


	/**
	 * Gets JSONObject for this key. <br>
	 * <br>
	 * Throws an exception if the value is of the wrong type.
	 * 
	 * @param key
	 * @return value
	 * @throws JSONException
	 */
	public JSONObject getJSONObject(String key) throws JSONException {
		Object value = attributes.get(key);

		if (value instanceof JSONObject) {
			return (JSONObject) value;
		}

		throw new JSONException("No JSONObject for key " + key);
	}

	/**
	 * Adds the given key-value pair.
	 * 
	 * @param key
	 * @param value
	 */
	public void putJSONArray(String key, JSONArray value) {
		attributes.put(key, value);
	}


	/**
	 * Gets JSONArray for this key. <br>
	 * <br>
	 * Throws an exception if the value is of the wrong type.
	 * 
	 * @param key
	 * @return value
	 * @throws JSONException
	 */
	public JSONArray getJSONArray(String key) throws JSONException {
		Object value = attributes.get(key);

		if (value instanceof JSONArray) {
			return (JSONArray) value;
		}

		throw new JSONException("No JSONArray for key " + key);
	}

	/**
	 * Adds the given key-value pair.
	 * 
	 * @param key
	 * @param value
	 */
	public void putBoolean(String key, Boolean value) {
		attributes.put(key, value);
	}


	/**
	 * Gets boolean for this key. <br>
	 * <br>
	 * Throws an exception if the value is of the wrong type.
	 * 
	 * @param key
	 * @return value
	 * @throws JSONException
	 */
	public Boolean getBoolean(String key) throws JSONException {
		Object value = attributes.get(key);

		if (value instanceof Boolean) {
			return (Boolean) value;
		}

		throw new JSONException("No Boolean for key " + key);
	}

	/**
	 * 
	 */
	public boolean equals(Object obj) {

		if (!(obj instanceof JSONObject)) {
			return false;
		}

		return this.attributes.equals(((JSONObject) obj).attributes);
	}

	/**
	 * 
	 */
	public int hashCode() {
		return attributes.hashCode();
	}

	/**
	 * 
	 */
	public String toString() {
		if (attributes.size() == 0) {
			return "{}";
		}

		String output = "{";
		Set<String> keys = getSortedKeys();

		for (String key : keys) {

			output += "\"" + key + "\" : ";
			Object value = attributes.get(key);
			if (value instanceof String) {
				output += "\"" + value + "\"";
			} else {
				output += value;
			}

			output += ", ";
		}

		output = output.substring(0, output.length() - 2) + "}";

		return output;
	}

	/**
	 * Sort keys in alphabetical order.
	 * 
	 * @return
	 */
	private Set<String> getSortedKeys() {
		Set<String> rawKeys = attributes.keySet();
		SortedSet<String> keys = new TreeSet<String>();
		for (String raw : rawKeys) {
			keys.add(raw);
		}
		return keys;
	}
}
