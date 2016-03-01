package de.arguments;

import de.arguments.json.*;
import de.arguments.exceptions.ArgumentsException;
import de.arguments.exceptions.JSONException;
import de.arguments.optional.*;
import de.arguments.required.*;

public class ArgFactory {

	public static final int REQUIRED_ARG = 0;
	public static final int OPTIONAL_ARG = 1;

	private static String type;
	private static char id;
	private static String alias;
	private static String description;

	public static Arg createArg(JSONObject rawArg, int type) throws ArgumentsException {
		if (type == REQUIRED_ARG) {
			return createRequiredArg(rawArg);
		}
		if (type == OPTIONAL_ARG) {
			return createOptionalArg(rawArg);
		}

		throw new ArgumentsException("Not existsing type! type = " + type);
	}

	public static RequiredArg createRequiredArg(JSONObject rawArg) throws ArgumentsException {

		getBasicInformations(rawArg);

		RequiredArg arg = null;

		if (type.equals("String")) {
			arg = new RequiredString(id, alias);
		}
		if (type.equals("Integer")) {
			arg = new RequiredInteger(id, alias);
		}
		if (type.equals("Double")) {
			arg = new RequiredDouble(id, alias);
		}
		if (type.equals("Boolean")) {
			arg = new RequiredBoolean(id, alias);
		}
		if (type.equals("Char")) {
			arg = new RequiredChar(id, alias);
		}
		if (type.equals("StringArray")) {
			arg = new RequiredStringArray(id, alias);
		}
		if (type.equals("CharArray")) {
			arg = new RequiredCharArray(id, alias);
		}
		if (type.equals("IntegerArray")) {
			arg = new RequiredIntegerArray(id, alias);
		}
		if (type.equals("DoubleArray")) {
			arg = new RequiredDoubleArray(id, alias);
		}
		if (arg == null) {
			throw new ArgumentsException("No such type! Type: " + type);
		}

		arg.setDescription(description);

		return arg;

	}

	public static OptionalArg createOptionalArg(JSONObject rawArg) throws ArgumentsException {
		try {

			getBasicInformations(rawArg);
			
			OptionalArg arg = null;

			if (type.equals("String")) {
				String defaultt = rawArg.getString("default");
				arg = new OptionalString(id, alias, defaultt);
			}
			if (type.equals("Integer")) {
				Integer defaultt = rawArg.getInteger("default");
				arg = new OptionalInteger(id, alias, defaultt);
			}
			if (type.equals("Double")) {
				Double defaultt = rawArg.getDouble("default");
				arg = new OptionalDouble(id, alias, defaultt);
			}
			if (type.equals("Boolean")) {
				Boolean defaultt = rawArg.getBoolean("default");
				arg = new OptionalBoolean(id, alias, defaultt);
			}

			if (type.equals("Char")) {
				char defaultt = getDefaultChar(rawArg);
				arg = new OptionalChar(id, alias, defaultt);
			}
			if (type.equals("Flag")) {
				arg = new Flag(id, alias);
			}
			if (type.equals("StringArray")) {
				String[] defaultt = getDefaultStringArray(rawArg);
				arg = new OptionalStringArray(id, alias, defaultt);
			}
			if (type.equals("CharArray")) {
				Character[] defaultt = getDefaultCharArray(rawArg);
				arg = new OptionalCharArray(id, alias, defaultt);
			}
			if (type.equals("IntegerArray")) {
				Integer[] defaultt = getDefaultIntegerArray(rawArg);
				arg = new OptionalIntegerArray(id, alias, defaultt);
			}
			if (type.equals("DoubleArray")) {
				Double[] defaultt = getDefaultDoubleArray(rawArg);
				arg = new OptionalDoubleArray(id, alias, defaultt);
			}
			if (arg == null) {
				throw new ArgumentsException("No such type! Type: " + type);
			}

			arg.setDescription(description);

			return arg;

		} catch (JSONException e) {
			ArgumentsException ae = new ArgumentsException("Error during information retrivel! ");
			ae.setStackTrace(e.getStackTrace());
			throw ae;
		}
	}

	private static Double[] getDefaultDoubleArray(JSONObject rawArg) throws JSONException {
		JSONArray rawDefaultt = rawArg.getJSONArray("default");
		Double[] defaultt = new Double[rawDefaultt.length()];

		for (int i = 0; i < rawDefaultt.length(); i++) {
			defaultt[i] = rawDefaultt.getDouble(i);
		}

		return defaultt;
	}
	
	private static Character[] getDefaultCharArray(JSONObject rawArg) throws JSONException {
		JSONArray rawDefaultt = rawArg.getJSONArray("default");
		Character[] defaultt = new Character[rawDefaultt.length()];

		for (int i = 0; i < rawDefaultt.length(); i++) {
			defaultt[i] = rawDefaultt.getCharacter(i);
		}

		return defaultt;
	}

	private static Integer[] getDefaultIntegerArray(JSONObject rawArg) throws JSONException {
		JSONArray rawDefaultt = rawArg.getJSONArray("default");
		Integer[] defaultt = new Integer[rawDefaultt.length()];

		for (int i = 0; i < rawDefaultt.length(); i++) {
			defaultt[i] = rawDefaultt.getInteger(i);
		}

		return defaultt;
	}

	private static String[] getDefaultStringArray(JSONObject rawArg) throws JSONException {

		JSONArray rawDefaultt = rawArg.getJSONArray("default");
		String[] defaultt = new String[rawDefaultt.length()];

		for (int i = 0; i < rawDefaultt.length(); i++) {
			defaultt[i] = rawDefaultt.getString(i);
		}

		return defaultt;
	}

	private static char getDefaultChar(JSONObject arg) throws ArgumentsException, JSONException {

		String defaultt = arg.getString("default");

		if (defaultt.length() == 1) {
			return defaultt.charAt(0);
		}

		throw new ArgumentsException("Default value from " + arg + " is not of typ char");
	}

	private static void getBasicInformations(JSONObject rawArg) throws ArgumentsException {
		try {

			type = rawArg.getString("type");
			id = rawArg.getString("identifier").charAt(0);
			alias = getAlias(rawArg);
			description = getUsage(rawArg);

		} catch (JSONException e) {
			ArgumentsException ae = new ArgumentsException("Error during information retrivel! ");
			ae.setStackTrace(e.getStackTrace());
			throw ae;
		}
	}

	private static String getAlias(JSONObject rawArg) {
		try {

			return rawArg.getString("alias");

		} catch (JSONException e) {
			return "";
		}
	}

	private static String getUsage(JSONObject rawArg) {
		try {

			return rawArg.getString("description");

		} catch (JSONException e) {
			return "";
		}
	}
}
