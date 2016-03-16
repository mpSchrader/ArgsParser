package de.arguments;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import de.arguments.json.*;
import de.arguments.exceptions.ArgumentsException;
import de.arguments.exceptions.JSONException;

public class ArgsFactory {

	protected static Args createArgs(JSONObject argsRaw) throws ArgumentsException {
		String usage = getUsage(argsRaw);
		List<Arg> arguments = getArguments(argsRaw);
		Args args = new Args(arguments, usage);
		return args;
	}

	public static Args createArgsFromFile(String pathToFile)
			throws ArgumentsException {
		File file = new File(pathToFile);
		return createArgsFromFile(file);
	}

	public static Args createArgsFromFile(File input) throws ArgumentsException {

		checkFile(input);

		JSONObject argsRaw = createJSONFromFile(input);
		Args args = createArgs(argsRaw);

		return args;
	}

	private static void checkFile(File file) throws ArgumentsException {
		if (!file.exists()) {
			throw new ArgumentsException("File does not exists! File: "
					+ file.getAbsolutePath());
		}

		if (file.isDirectory()) {
			throw new ArgumentsException("File is a Directory! File: "
					+ file.getAbsolutePath());
		}

		if (!file.canRead()) {
			throw new ArgumentsException("File can not be read! File: "
					+ file.getAbsolutePath());
		}

	}

	private static JSONObject createJSONFromFile(File input)
			throws ArgumentsException {
		try {

			Path path = Paths.get(input.toURI());
			Charset cs = Charset.defaultCharset();
			List<String> lines = Files.readAllLines(path, cs);

			String jsonRaw = "";
			for (String line : lines) {
				jsonRaw += " "+line;
			}

			JSONObject json = new JSONObject(jsonRaw);
			return json;

		} catch (IOException | JSONException  e) {
			throw new ArgumentsException(
					"Error during converting File to JSON! File: "
							+ input.getAbsolutePath());
		}
	}

	private static String getUsage(JSONObject argsRaw) {
		try {

			return argsRaw.getString("usage");

		} catch (JSONException e) {
			return "";
		}
	}

	private static List<Arg> getArguments(JSONObject argsRaw)
			throws ArgumentsException {
		try {

			List<Arg> arguments = new ArrayList<Arg>();
			List<Arg> required = getRequiredArguments(argsRaw);
			List<Arg> optional = getOptionalArguments(argsRaw);
			arguments.addAll(required);
			arguments.addAll(optional);
			return arguments;

		} catch (JSONException e) {
			ArgumentsException ae = new ArgumentsException(
					"Error during reading arguments");
			ae.setStackTrace(e.getStackTrace());
			throw ae;
		}
	}

	private static List<Arg> getRequiredArguments(JSONObject argsRaw)
			throws ArgumentsException, JSONException {

		JSONArray requiredArgsRaw = argsRaw.getJSONArray("required");
		List<Arg> args = new ArrayList<Arg>();

		for (int i = 0; i < requiredArgsRaw.length(); i++) {
			JSONObject rawArg = requiredArgsRaw.getJSONObject(i);
			Arg arg = ArgFactory.createRequiredArg(rawArg);
			args.add(arg);
		}
		
		return args;
	}

	private static List<Arg> getOptionalArguments(JSONObject argsRaw)
			throws ArgumentsException, JSONException {

		JSONArray optionalArgsRaw = argsRaw.getJSONArray("optional");
		List<Arg> args = new ArrayList<Arg>();

		for (int i = 0; i < optionalArgsRaw.length(); i++) {
			JSONObject rawArg = optionalArgsRaw.getJSONObject(i);
			Arg arg = ArgFactory.createOptionalArg(rawArg);
			args.add(arg);
		}

		return args;
	}

}
