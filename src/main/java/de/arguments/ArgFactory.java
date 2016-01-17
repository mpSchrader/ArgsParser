package de.arguments;

import org.json.JSONException;
import org.json.JSONObject;

import de.arguments.exceptions.ArgumentException;
import de.arguments.optional.*;
import de.arguments.required.*;

public class ArgFactory {

	public static final int REQUIRED_ARG = 0;
	public static final int OPTIONAL_ARG = 1;
	private static String type;
	private static String identifier;
	private static String usage;

	public static Arg createArg(String rawArg, int type)
			throws ArgumentException {

		JSONObject rawJSON = new JSONObject();

		/* Extract Values */
		String identifier = rawArg.split(" ")[0];
		String argType = rawArg.split(" ")[1];
		String additionalInfo = rawArg.split(" : ", 2)[1];
		String description = additionalInfo.split(" (Default = ")[0].trim();
		String defaultt = "";
		if (additionalInfo.contains(" (Default = ")) {
			defaultt = additionalInfo.split(" (Default = ")[1].trim();
			defaultt = defaultt.substring(0, defaultt.length() - 2);
		}

		/* Add values to JSON */
		rawJSON.put("identifier", identifier);
		rawJSON.put("type", argType);
		rawJSON.put("description", description);
		rawJSON.put("default", defaultt);

		return createArg(rawJSON, type);
	}

	public static Arg createArg(JSONObject rawArg, int type)
			throws ArgumentException {
		if (type == REQUIRED_ARG) {
			return createRequiredArg(rawArg);
		}
		if (type == OPTIONAL_ARG) {
			return createOptionalArg(rawArg);
		}

		throw new ArgumentException("Not existsing type! type = " + type);
	}

	public static RequiredArg createRequiredArg(JSONObject rawArg)
			throws ArgumentException {

		getBasicInformations(rawArg);

		RequiredArg arg = null;

		if (type.equals("String")) {
			arg = new RequiredString(identifier, usage);
		}
		if (type.equals("Integer")) {
			arg = new RequiredInteger(identifier, usage);
		}
		if (type.equals("Double")) {
			arg = new RequiredDouble(identifier, usage);
		}
		if (type.equals("Boolean")) {
			arg = new RequiredBoolean(identifier, usage);
		}
		if (arg == null) {
			throw new ArgumentException("No such type! Type: " + type);
		}

		return arg;
	}

	public static OptionalArg createOptionalArg(JSONObject rawArg)
			throws ArgumentException {
		try {
			getBasicInformations(rawArg);

			OptionalArg arg = null;

			if (type.equals("String")) {
				String defaultt = rawArg.get("default").toString();
				arg = new OptionalString(identifier, defaultt, usage);
			}
			if (type.equals("Integer")) {
				Integer defaultt = rawArg.getInt("default");
				arg = new OptionalInteger(identifier, defaultt, usage);
			}
			if (type.equals("Double")) {
				Double defaultt = rawArg.getDouble("default");
				arg = new OptionalDouble(identifier, defaultt, usage);
			}
			if (type.equals("Boolean")) {
				Boolean defaultt = rawArg.getBoolean("default");
				arg = new OptionalBoolean(identifier, defaultt, usage);
			}
			if (type.equals("Flag")) {
				arg = new Flag(identifier, usage);
			}
			if (arg == null) {
				throw new ArgumentException("No such type! Type: " + type);
			}

			return arg;
		} catch (JSONException e) {
			ArgumentException ae = new ArgumentException(
					"Error during information retrivel! ");
			ae.setStackTrace(e.getStackTrace());
			throw ae;
		}
	}

	private static void getBasicInformations(JSONObject rawArg)
			throws ArgumentException {
		try {

			type = rawArg.getString("type");
			identifier = rawArg.getString("identifier");
			usage = getUsage(rawArg);

		} catch (JSONException e) {
			ArgumentException ae = new ArgumentException(
					"Error during information retrivel! ");
			ae.setStackTrace(e.getStackTrace());
			throw ae;
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
