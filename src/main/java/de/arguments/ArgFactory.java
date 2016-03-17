package de.arguments;

import de.arguments.json.*;
import de.arguments.exceptions.ArgumentsException;
import de.arguments.exceptions.JSONException;
import de.arguments.optional.*;
import de.arguments.required.*;

/**
 * This class allows to create single Args from JSONObjects.
 * 
 * @author Max-Philipp Schrader
 *
 */
public class ArgFactory {

	public static final int REQUIRED_ARG = 0;
	public static final int OPTIONAL_ARG = 1;

	private static String type;
	private static char id;
	private static String alias;
	private static String description;

	/**
	 * Creates an Arg, which can be required or optional. <br>
	 * <br>
	 * The type parameter decides whether the Arg is optional or required. If
	 * type is 0 it is required, and if it is 1 the Arg is optional. You use the
	 * static fields of this class to define the type value.
	 * 
	 * @param rawArg
	 * @param type
	 * @return Arg
	 * @throws ArgumentsException
	 */
	public static Arg createArg(JSONObject rawArg, int type) throws ArgumentsException {
		if (type == REQUIRED_ARG) {
			return createRequiredArg(rawArg);
		}
		if (type == OPTIONAL_ARG) {
			return createOptionalArg(rawArg);
		}

		throw new ArgumentsException("Not existsing type! type = " + type);
	}

	/**
	 * Creates a RequiredArg from a JSONObject.
	 * 
	 * @param rawArg
	 * @return RequiredArg
	 * @throws ArgumentsException
	 */
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
		if (type.equals("BooleanArray")) {
			arg = new RequiredBooleanArray(id, alias);
		}
		if (arg == null) {
			throw new ArgumentsException("No such type! Type: " + type);
		}

		arg.setDescription(description);

		return arg;

	}

	/**
	 * Creates a OptionalArg from a JSONObject.
	 * 
	 * @param rawArg
	 * @return OptionalArg
	 * @throws ArgumentsException
	 */
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
			if (type.equals("BooleanArray")) {
				Boolean[] defaultt = getDefaultBooleanArray(rawArg);
				arg = new OptionalBooleanArray(id, alias, defaultt);
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

	/**
	 * Gets the double array, set in the default field.<br>
	 * <br>
	 * If the default field is not of the right type an exception will be
	 * thrown.
	 * 
	 * @param arg
	 * @return Default double array
	 * @throws ArgumentsException
	 * @throws JSONException
	 */
	private static Double[] getDefaultDoubleArray(JSONObject rawArg) throws JSONException {
		JSONArray rawDefaultt = rawArg.getJSONArray("default");
		Double[] defaultt = new Double[rawDefaultt.length()];

		for (int i = 0; i < rawDefaultt.length(); i++) {
			defaultt[i] = rawDefaultt.getDouble(i);
		}

		return defaultt;
	}

	/**
	 * Gets the char array, set in the default field.<br>
	 * <br>
	 * If the default field is not of the right type an exception will be
	 * thrown.
	 * 
	 * @param arg
	 * @return Default char array
	 * @throws ArgumentsException
	 * @throws JSONException
	 */
	private static Character[] getDefaultCharArray(JSONObject rawArg) throws JSONException {
		JSONArray rawDefaultt = rawArg.getJSONArray("default");
		Character[] defaultt = new Character[rawDefaultt.length()];

		for (int i = 0; i < rawDefaultt.length(); i++) {
			defaultt[i] = rawDefaultt.getCharacter(i);
		}

		return defaultt;
	}

	/**
	 * Gets the integer array, set in the default field.<br>
	 * <br>
	 * If the default field is not of the right type an exception will be
	 * thrown.
	 * 
	 * @param arg
	 * @return Default integer array
	 * @throws ArgumentsException
	 * @throws JSONException
	 */
	private static Integer[] getDefaultIntegerArray(JSONObject rawArg) throws JSONException {
		JSONArray rawDefaultt = rawArg.getJSONArray("default");
		Integer[] defaultt = new Integer[rawDefaultt.length()];

		for (int i = 0; i < rawDefaultt.length(); i++) {
			defaultt[i] = rawDefaultt.getInteger(i);
		}

		return defaultt;
	}

	/**
	 * Gets the boolean array, set in the default field.<br>
	 * <br>
	 * If the default field is not of the right type an exception will be
	 * thrown.
	 * 
	 * @param arg
	 * @return Default boolean array
	 * @throws ArgumentsException
	 * @throws JSONException
	 */
	private static Boolean[] getDefaultBooleanArray(JSONObject rawArg) throws JSONException {
		JSONArray rawDefaultt = rawArg.getJSONArray("default");
		Boolean[] defaultt = new Boolean[rawDefaultt.length()];

		for (int i = 0; i < rawDefaultt.length(); i++) {
			defaultt[i] = rawDefaultt.getBoolean(i);
		}

		return defaultt;
	}

	/**
	 * Gets the string array, set in the default field.<br>
	 * <br>
	 * If the default field is not of the right type an exception will be
	 * thrown.
	 * 
	 * @param arg
	 * @return Default string array
	 * @throws ArgumentsException
	 * @throws JSONException
	 */
	private static String[] getDefaultStringArray(JSONObject rawArg) throws JSONException {

		JSONArray rawDefaultt = rawArg.getJSONArray("default");
		String[] defaultt = new String[rawDefaultt.length()];

		for (int i = 0; i < rawDefaultt.length(); i++) {
			defaultt[i] = rawDefaultt.getString(i);
		}

		return defaultt;
	}

	/**
	 * Gets the Char, set in the default field.<br>
	 * <br>
	 * If the default field is not of the right type an exception will be
	 * thrown.
	 * 
	 * @param arg
	 * @return Default Char
	 * @throws ArgumentsException
	 * @throws JSONException
	 */
	private static char getDefaultChar(JSONObject arg) throws ArgumentsException, JSONException {

		String defaultt = arg.getString("default");

		if (defaultt.length() == 1) {
			return defaultt.charAt(0);
		}

		throw new ArgumentsException("Default value from " + arg + " is not of typ char");
	}

	/**
	 * Extracts all information from the JSONObject, which are of the same type
	 * for every Arg.<br>
	 * <br>
	 * These are type, id, alias and description.
	 * 
	 * @param rawArg
	 * @throws ArgumentsException
	 */
	private static void getBasicInformations(JSONObject rawArg) throws ArgumentsException {
		try {

			type = rawArg.getString("type");
			id = rawArg.getString("identifier").charAt(0);
			alias = getAlias(rawArg);
			description = getDescription(rawArg);

		} catch (JSONException e) {
			ArgumentsException ae = new ArgumentsException("Error during information retrivel! ");
			ae.setStackTrace(e.getStackTrace());
			throw ae;
		}
	}

	/**
	 * Tries to get the alias from the JSONObject. In case the alias is not set
	 * the method returns an empty string.
	 * 
	 * @param rawArg
	 * @return
	 */
	private static String getAlias(JSONObject rawArg) {
		try {

			return rawArg.getString("alias");

		} catch (JSONException e) {
			return "";
		}
	}

	/**
	 * Tries to get the description of the Arg from the JSONObject. In case the
	 * description is not set the method returns an empty string.
	 * 
	 * @param rawArg
	 * @return
	 */
	private static String getDescription(JSONObject rawArg) {
		try {

			return rawArg.getString("description");

		} catch (JSONException e) {
			return "";
		}
	}
}
