package de.arguments;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import de.arguments.json.*;
import de.arguments.exceptions.ArgumentsException;

public class ArgsFactoryTest {

	private static String pathToFile;
	private static File file;
	private static JSONObject argsRaw;
	private static JSONObject requiredString;
	private static JSONObject requiredInteger;
	private static JSONObject requiredDouble;
	private static JSONObject requiredBoolean;
	private static JSONObject optionalString;
	private static JSONObject optionalInteger;
	private static JSONObject optionalBoolean;
	private static JSONObject optionalDouble;
	private static JSONObject flag;

	@BeforeClass
	public static void setupClass() {
		pathToFile = "./src/test/java/de/arguments/Input.args";
		file = new File(pathToFile);

		String identifier = "s";
		String description = "My Description";

		String type = "String";
		requiredString = new JSONObject();
		requiredString.putString("identifier", identifier);
		requiredString.putString("type", type);
		requiredString.putString("description", description);

		type = "Integer";
		identifier = "i";
		requiredInteger = new JSONObject();
		requiredInteger.putString("identifier", identifier);
		requiredInteger.putString("type", type);
		requiredInteger.putString("description", description);

		type = "Double";
		identifier = "d";
		requiredDouble = new JSONObject();
		requiredDouble.putString("identifier", identifier);
		requiredDouble.putString("type", type);
		requiredDouble.putString("description", description);

		type = "Boolean";
		identifier = "b";
		requiredBoolean = new JSONObject();
		requiredBoolean.putString("identifier", identifier);
		requiredBoolean.putString("type", type);
		requiredBoolean.putString("description", description);

		type = "String";
		identifier = "w";
		optionalString = new JSONObject();
		optionalString.putString("identifier", identifier);
		optionalString.putString("type", type);
		optionalString.putString("description", description);
		optionalString.putString("default", "Default String");

		type = "Integer";
		identifier = "v";
		optionalInteger = new JSONObject();
		optionalInteger.putString("identifier", identifier);
		optionalInteger.putString("type", type);
		optionalInteger.putInteger("default", 123);

		type = "Double";
		identifier = "y";
		optionalDouble = new JSONObject();
		optionalDouble.putString("identifier", identifier);
		optionalDouble.putString("type", type);
		optionalDouble.putString("description", description);
		optionalDouble.putDouble("default", 1.234);

		type = "Boolean";
		identifier = "x";
		optionalBoolean = new JSONObject();
		optionalBoolean.putString("identifier", identifier);
		optionalBoolean.putString("type", type);
		optionalBoolean.putString("description", description);
		optionalBoolean.putBoolean("default", true);
		optionalBoolean.putString("alias", "xtra");

		type = "Flag";
		identifier = "z";
		flag = new JSONObject();
		flag.putString("identifier", identifier);
		flag.putString("type", type);
		flag.putString("description", description);

		argsRaw = new JSONObject();
		argsRaw.putString("usage", "java -jar my.jar");
		JSONArray required = new JSONArray();
		required.append(requiredString);
		required.append(requiredBoolean);
		required.append(requiredDouble);
		required.append(requiredInteger);
		JSONArray optional = new JSONArray();
		optional.append(optionalString);
		optional.append(flag);
		optional.append(optionalDouble);
		optional.append(optionalBoolean);
		optional.append(optionalInteger);
		argsRaw.putJSONArray("required", required);
		argsRaw.putJSONArray("optional", optional);
	}

	@Test
	public void getArgsWithStringFromFile() throws ArgumentsException {
		Args args = ArgsFactory.createArgsFromFile(file);

		String expected = "Usage: Some description e.g. java -jar my.jar"
				+ "\nRequired Arguments:"
				+ "\n\t-i, --my_int: <Integer> another one"
				+ "\n\t-s: <String> needed for what ever"
				+ "\nOptional Arguments:"
				+ "\n\t-a, --my_array: <StringArray> description "
				+"(Default = [\"My Array\", \"is\", \"a\", \"StringArray\"])"
				+ "\n\t-n: <Integer> description (Default = 42)"
				+ "\n\t-o: <String> needed for what ever (Default = \"Default string\")";
		String actual = args.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void getArgsWithFileFromFile() throws ArgumentsException {
		Args args = ArgsFactory.createArgsFromFile(pathToFile);

		String expected = "Usage: Some description e.g. java -jar my.jar"
				+ "\nRequired Arguments:"
				+ "\n\t-i, --my_int: <Integer> another one"
				+ "\n\t-s: <String> needed for what ever"
				+ "\nOptional Arguments:"
				+ "\n\t-a, --my_array: <StringArray> description "
				+"(Default = [\"My Array\", \"is\", \"a\", \"StringArray\"])"
				+ "\n\t-n: <Integer> description (Default = 42)"
				+ "\n\t-o: <String> needed for what ever (Default = \"Default string\")";
		String actual = args.toString();
		assertEquals(expected, actual);
	}

	@Test(expected = ArgumentsException.class)
	public void getArgsFileNotExisting() throws ArgumentsException {
		String badPath = "./src/test/java/de/arguments/notExisting.args";
		ArgsFactory.createArgsFromFile(badPath);
	}

	@Test(expected = ArgumentsException.class)
	public void getArgsFileWrongType() throws ArgumentsException {
		String badPath = "./src/test/java/de/arguments/WrongType.json";
		ArgsFactory.createArgsFromFile(badPath);
	}

	@Test(expected = ArgumentsException.class)
	public void getArgsFileIsDir() throws ArgumentsException {
		String badPath = "./src/test/java/de/arguments/";
		ArgsFactory.createArgsFromFile(badPath);
	}

	@Test
	public void getArgsFromJSON() throws ArgumentsException {
		Args args = ArgsFactory.createArgs(argsRaw);

		String expected = "Usage: java -jar my.jar"
				+ "\nRequired Arguments:"
				+ "\n\t-b: <Boolean> My Description"
				+ "\n\t-d: <Double> My Description"
				+ "\n\t-i: <Integer> My Description"
				+ "\n\t-s: <String> My Description"
				+ "\nOptional Arguments:"
				+ "\n\t-v: <Integer> (Default = 123)"
				+ "\n\t-w: <String> My Description (Default = \"Default String\")"
				+ "\n\t-x, --xtra: <Boolean> My Description (Default = true)"
				+ "\n\t-y: <Double> My Description (Default = 1.234)"
				+ "\n\t-z: <Flag> My Description";
		String actual = args.toString();

		assertEquals(expected, actual);
	}

	@Test(expected = ArgumentsException.class)
	public void missingRequiredArgumets() throws ArgumentsException {
		JSONObject argsRaw = new JSONObject();
		argsRaw.putString("usage", "java -jar my.jar");
		JSONArray optional = new JSONArray();
		optional.append(optionalString);
		optional.append(flag);
		optional.append(optionalDouble);
		optional.append(optionalBoolean);
		optional.append(optionalInteger);
		argsRaw.putJSONArray("optional", optional);
		
		ArgsFactory.createArgs(argsRaw);
	}

	@Test(expected = ArgumentsException.class)
	public void missingOptionalArgumets() throws ArgumentsException {
		JSONObject argsRaw = new JSONObject();
		argsRaw.putString("usage", "java -jar my.jar");
		JSONArray optional = new JSONArray();
		optional.append(requiredString);
		argsRaw.putJSONArray("required", optional);
		
		ArgsFactory.createArgs(argsRaw);
	}

	@Test(expected = ArgumentsException.class)
	public void badOptional() throws ArgumentsException {
		JSONObject argsRaw = new JSONObject();
		argsRaw.putString("usage", "java -jar my.jar");
		JSONArray required = new JSONArray();
		required.append(requiredString);
		JSONArray optional = new JSONArray();
		optional.append(requiredString);
		argsRaw.putJSONArray("optional", optional);
		argsRaw.putJSONArray("required", required);
		
		ArgsFactory.createArgs(argsRaw);
	}

}
