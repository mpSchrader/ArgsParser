package de.arguments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.arguments.array.OptionalStringArray;
import de.arguments.array.RequiredStringArray;
import de.arguments.exceptions.ArgumentException;
import de.arguments.optional.*;
import de.arguments.required.*;

public class ArgFactory {

	public static final int REQUIRED_ARG = 0;
	public static final int OPTIONAL_ARG = 1;
	private static String type;
	private static char id;
	private static String alias;
	private static String description;

	public static Arg createArg(String rawArg, int type)
			throws ArgumentException {

		JSONObject rawJSON = new JSONObject();

		/* Extract Values */
		String identifier = rawArg.split(", ")[0].replaceAll("-", "");
		String alias = rawArg.split(", ")[1].replaceAll("-", "");
		String argType = rawArg.split(" ")[3];
		String additionalInfo = rawArg.split(" : ", 2)[1];
		String description = additionalInfo.split(" (Default = ")[0].trim();
		String defaultt = "";
		if (additionalInfo.contains(" (Default = ")) {
			defaultt = additionalInfo.split(" (Default = ")[1].trim();
			defaultt = defaultt.substring(0, defaultt.length() - 2);
		}

		/* Add values to JSON */
		rawJSON.put("identifier", identifier);
		rawJSON.put("alias", alias);
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
		if (arg == null) {
			throw new ArgumentException("No such type! Type: " + type);
		}

		arg.setDescription(description);
		
		return arg;
		
	}

	public static OptionalArg createOptionalArg(JSONObject rawArg)
			throws ArgumentException {
		try {
			getBasicInformations(rawArg);

			OptionalArg arg = null;

			if (type.equals("String")) {
				String defaultt = rawArg.get("default").toString();
				arg = new OptionalString(id, alias, defaultt);
			}
			if (type.equals("Integer")) {
				Integer defaultt = rawArg.getInt("default");
				arg = new OptionalInteger(id, alias,defaultt);
			}
			if (type.equals("Double")) {
				Double defaultt = rawArg.getDouble("default");
				arg = new OptionalDouble(id, alias,defaultt);
			}
			if (type.equals("Boolean")) {
				Boolean defaultt = rawArg.getBoolean("default");
				arg = new OptionalBoolean(id,alias, defaultt);
			}
			
			if (type.equals("Char")) {
				char defaultt = getDefaultChar(rawArg);
				arg = new OptionalChar(id,alias, defaultt);
			}
			if (type.equals("Flag")) {
				arg = new Flag(id, alias);
			}
			if (type.equals("StringArray")) {
				String[] defaultt = getDefaultStringArray(rawArg);
				arg = new OptionalStringArray(id, alias,defaultt);
			}
			if (arg == null) {
				throw new ArgumentException("No such type! Type: " + type);
			}
			
			arg.setDescription(description);

			return arg;
			
		} catch (JSONException e) {
			ArgumentException ae = new ArgumentException(
					"Error during information retrivel! ");
			ae.setStackTrace(e.getStackTrace());
			throw ae;
		}
	}

	private static String[] getDefaultStringArray(JSONObject rawArg) {
		JSONArray rawDefaultt = rawArg.getJSONArray("default");
		String[] defaultt = new String[rawDefaultt.length()];
		
		for (int i = 0; i < rawDefaultt.length(); i++){
			defaultt[i] = rawDefaultt.getString(i);
		}
		
		return defaultt;
	}

	private static char getDefaultChar(JSONObject arg) throws ArgumentException {
		
		String defaultt = arg.getString("default");
		if (defaultt.length() == 1){
			return defaultt.charAt(0);
		}
		
		throw new ArgumentException("Default value from "+arg+" is not of typ char");
	}

	private static void getBasicInformations(JSONObject rawArg)
			throws ArgumentException {
		try {

			type = rawArg.getString("type");
			System.out.println(rawArg);
			System.out.println(rawArg.getString("identifier"));
			id = rawArg.getString("identifier").charAt(0);
			alias = getAlias(rawArg);
			description = getUsage(rawArg);

		} catch (JSONException e) {
			ArgumentException ae = new ArgumentException(
					"Error during information retrivel! ");
			ae.setStackTrace(e.getStackTrace());
			throw ae;
		}
	}

	private static String getAlias(JSONObject rawArg){
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
