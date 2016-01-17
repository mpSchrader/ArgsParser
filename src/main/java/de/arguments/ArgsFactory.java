package de.arguments;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.arguments.exceptions.ArgumentException;

public class ArgsFactory {

	public static Args createArgs(JSONObject argsRaw) throws ArgumentException {
		String usage = getUsage(argsRaw);
		List<Arg> arguments = getArguments(argsRaw);
		Args args = new Args(arguments, usage);
		return args;
	}

	public static Args createArgsFromFile(String pathToFile)
			throws ArgumentException {
		File file = new File(pathToFile);
		return createArgsFromFile(file);
	}

	public static Args createArgsFromFile(File input) throws ArgumentException {

		checkFile(input);

		JSONObject argsRaw = createJSONFromFile(input);
		Args args = createArgs(argsRaw);

		return args;
	}

	private static void checkFile(File file) throws ArgumentException {
		if (!file.exists()) {
			throw new ArgumentException("File does not exists! File: "
					+ file.getAbsolutePath());
		}

		if (file.isDirectory()) {
			throw new ArgumentException("File is a Directory! File: "
					+ file.getAbsolutePath());
		}

		if (!file.canRead()) {
			throw new ArgumentException("File can not be read! File: "
					+ file.getAbsolutePath());
		}

		if (!file.getName().endsWith(".args")) {
			throw new ArgumentException(
					"Wrong file type! Expected .args File: "
							+ file.getAbsolutePath());
		}

	}

	private static JSONObject createJSONFromFile(File input)
			throws ArgumentException {
		try {

			Path path = Paths.get(input.toURI());
			List<String> lines = Files.readAllLines(path);

			String jsonRaw = "";
			for (String line : lines) {
				jsonRaw += "\n" + line;
			}

			JSONObject json = new JSONObject(jsonRaw);
			return json;

		} catch (IOException e) {
			throw new ArgumentException(
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
			throws ArgumentException {
		try {

			List<Arg> arguments = new ArrayList<Arg>();
			List<Arg> required = getRequiredArguments(argsRaw);
			List<Arg> optional = getOptionalArguments(argsRaw);
			arguments.addAll(required);
			arguments.addAll(optional);
			return arguments;

		} catch (JSONException e) {
			ArgumentException ae = new ArgumentException(
					"Error during reading arguments");
			ae.setStackTrace(e.getStackTrace());
			throw ae;
		}
	}

	private static List<Arg> getRequiredArguments(JSONObject argsRaw)
			throws ArgumentException {

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
			throws ArgumentException {

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
