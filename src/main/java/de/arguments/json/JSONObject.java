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

	public JSONObject() {
		attributes = new HashMap<String, Object>(6);
	}

	public JSONObject(String raw) throws JSONException {
		attributes = new HashMap<String, Object>(6);
		checkFormat(raw);
		raw = prepareString(raw);
		addValuesFromString(raw);
	}

	public JSONObject(String key, Object value) {
		attributes = new HashMap<String, Object>(6);
		attributes.put(key, value);
	}

	private void checkFormat(String raw) throws JSONException {
		raw = raw.trim();
		if (!raw.startsWith("{") || !raw.endsWith("}"))
			throw new JSONException("String not right formated!");
	}

	private String prepareString(String raw) {
		raw = raw.trim();
		raw = raw.substring(1, raw.length() - 1);
		raw = raw.replaceAll("\\s+", " ");
		return raw;
	}

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

	private String getKey(String rawKey) throws JSONException {
		rawKey = rawKey.trim();

		if (!(rawKey.startsWith("\"") && rawKey.endsWith("\""))) {
			throw new JSONException("Male formated key: " + rawKey);
		}
		String key = rawKey.substring(1, rawKey.length() - 1);
		return key;
	}

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

	private boolean isStartAndEnd(String part) {
		part = part.trim();

		boolean isStartEnd = false;

		isStartEnd |= part.startsWith("\"") && part.endsWith("\"");
		isStartEnd |= part.startsWith("[") && part.endsWith("]");
		isStartEnd |= part.startsWith("{") && part.endsWith("}");

		isStartEnd |= !part.startsWith("\"") && !part.startsWith("{")
				&& !part.startsWith("[");

		return isStartEnd;
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

	private void addValue(String key, String part) throws JSONException {
		part = part.trim();

		boolean worked = tryAddAsInteger(key, part);
		if (worked) {
			return;
		}

		worked = tryAddAsDouble(key, part);
		if (worked) {
			return;
		}

		worked = tryAddAsBoolean(key, part);
		if (worked) {
			return;
		}

		worked = tryAddAsJSONObject(key, part);
		if (worked) {
			return;
		}

		worked = tryAddAsJSONArray(key, part);
		if (worked) {
			return;
		}

		worked = tryAddAsString(key, part);
		if (worked) {
			return;
		}

		throw new JSONException("Could not add (" + key + ", " + part + ")!");

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

	public void putString(String key, String value) {
		attributes.put(key, value);
	}

	public String getString(String key) throws JSONException {

		Object value = attributes.get(key);

		if (value instanceof String) {
			return (String) value;
		}

		throw new JSONException("No String for key " + key);
	}

	public void putInteger(String key, Integer value) {
		attributes.put(key, value);
	}

	public Integer getInteger(String key) throws JSONException {
		Object value = attributes.get(key);

		if (value instanceof Integer) {
			return (Integer) value;
		}

		throw new JSONException("No Integer for key " + key);
	}

	public void putDouble(String key, Double value) {
		attributes.put(key, value);
	}

	public Double getDouble(String key) throws JSONException {
		Object value = attributes.get(key);

		if (value instanceof Double) {
			return (Double) value;
		}

		throw new JSONException("No Double for key " + key);
	}

	public void putJSONObject(String key, JSONObject value) {
		attributes.put(key, value);
	}

	public JSONObject getJSONObject(String key) throws JSONException {
		Object value = attributes.get(key);

		if (value instanceof JSONObject) {
			return (JSONObject) value;
		}

		throw new JSONException("No JSONObject for key " + key);
	}

	public void putJSONArray(String key, JSONArray value) {
		attributes.put(key, value);
	}

	public JSONArray getJSONArray(String key) throws JSONException {
		Object value = attributes.get(key);

		if (value instanceof JSONArray) {
			return (JSONArray) value;
		}

		throw new JSONException("No JSONArray for key " + key);
	}

	public void putBoolean(String key, Boolean value) {
		attributes.put(key, value);
	}

	public Boolean getBoolean(String key) throws JSONException {
		Object value = attributes.get(key);

		if (value instanceof Boolean) {
			return (Boolean) value;
		}

		throw new JSONException("No Boolean for key " + key);
	}

	public boolean equals(Object obj) {
//		System.out.println("#########################");
//		System.out.println(this);
//		System.out.println(obj);
//		System.out.println(">>>>>>>>>>>>>>>>>>");
		if (!(obj instanceof JSONObject)) {
			return false;
		}
//		boolean result = true;
//		Set<String> keyThis = attributes.keySet();
//		Set<String> keyObj = ((JSONObject) obj).attributes.keySet();
//		System.out.println("\nkeySets: " + keyThis.equals(keyObj));
//		result &= keyThis.equals(keyObj);
//		for (String key : keyThis) {System.out.print(key+" ");}
//		System.out.println();
//		for (String key : keyThis) {
//			System.out.println("---------------------");
//			System.out.println("KEY: " + key);
//			System.out.println(attributes.get(key).getClass() + ": "
//					+ attributes.get(key));
//			System.out
//					.println(((JSONObject) obj).attributes.get(key).getClass()
//							+ ": " + ((JSONObject) obj).attributes.get(key));
//			System.out.println(((JSONObject) obj).attributes.get(key).equals(
//					attributes.get(key)));
//			result &= ((JSONObject) obj).attributes.get(key).equals(
//					attributes.get(key));
//		}
		// JSONArray thiz = (JSONArray) attributes.get("key");
		// JSONArray objZ = (JSONArray) ((JSONObject)
		// obj).attributes.get("key");
		// for (int i = 0; i < thiz.length(); i++) {
		// try {
		// System.out.println(i + ": " + thiz.get(i).toString() + " "
		// + thiz.get(i).getClass());
		// System.out.println(i + ": " + objZ.get(i).toString() + " "
		// + objZ.get(i).getClass());
		// // System.out
		// // .println("Equals: " + thiz.get(i).equals(objZ.get(i)));
		// } catch (JSONException e) {
		// e.printStackTrace();
		// }
		// }
		// System.out.println("RESULT: "+result);
		// if (!result){
		// System.out.println(this);
		// System.out.println(obj);
		//	}
		return this.attributes.equals(((JSONObject) obj).attributes);
	}

	public int hashCode() {
		return attributes.hashCode();
	}

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

	private Set<String> getSortedKeys() {
		Set<String> rawKeys = attributes.keySet();
		SortedSet<String> keys = new TreeSet<String>();
		for (String raw : rawKeys) {
			keys.add(raw);
		}
		return keys;
	}
}
