package de.arguments;

import static org.junit.Assert.*;

import java.io.File;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

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
		requiredString.put("identifier", identifier);
		requiredString.put("type", type);
		requiredString.put("description", description);

		type = "Integer";
		identifier = "i";
		requiredInteger = new JSONObject();
		requiredInteger.put("identifier", identifier);
		requiredInteger.put("type", type);
		requiredInteger.put("description", description);

		type = "Double";
		identifier = "d";
		requiredDouble = new JSONObject();
		requiredDouble.put("identifier", identifier);
		requiredDouble.put("type", type);
		requiredDouble.put("description", description);

		type = "Boolean";
		identifier = "b";
		requiredBoolean = new JSONObject();
		requiredBoolean.put("identifier", identifier);
		requiredBoolean.put("type", type);
		requiredBoolean.put("description", description);

		type = "String";
		identifier = "w";
		optionalString = new JSONObject();
		optionalString.put("identifier", identifier);
		optionalString.put("type", type);
		optionalString.put("description", description);
		optionalString.put("default", "Default String");

		type = "Integer";
		identifier = "v";
		optionalInteger = new JSONObject();
		optionalInteger.put("identifier", identifier);
		optionalInteger.put("type", type);
		optionalInteger.put("default", 123);

		type = "Double";
		identifier = "y";
		optionalDouble = new JSONObject();
		optionalDouble.put("identifier", identifier);
		optionalDouble.put("type", type);
		optionalDouble.put("description", description);
		optionalDouble.put("default", 1.234);

		type = "Boolean";
		identifier = "x";
		optionalBoolean = new JSONObject();
		optionalBoolean.put("identifier", identifier);
		optionalBoolean.put("type", type);
		optionalBoolean.put("description", description);
		optionalBoolean.put("default", true);
		optionalBoolean.put("alias", "xtra");

		type = "Flag";
		identifier = "z";
		flag = new JSONObject();
		flag.put("identifier", identifier);
		flag.put("type", type);
		flag.put("description", description);

		argsRaw = new JSONObject();
		argsRaw.put("usage", "java -jar my.jar");
		argsRaw.append("required", requiredString);
		argsRaw.append("required", requiredBoolean);
		argsRaw.append("required", requiredDouble);
		argsRaw.append("required", requiredInteger);
		argsRaw.append("optional", optionalString);
		argsRaw.append("optional", flag);
		argsRaw.append("optional", optionalDouble);
		argsRaw.append("optional", optionalBoolean);
		argsRaw.append("optional", optionalInteger);
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
		argsRaw.put("usage", "java -jar my.jar");
		argsRaw.append("optional", optionalString);
		argsRaw.append("optional", flag);
		argsRaw.append("optional", optionalDouble);
		argsRaw.append("optional", optionalBoolean);
		argsRaw.append("optional", optionalInteger);
		ArgsFactory.createArgs(argsRaw);
	}

	@Test(expected = ArgumentsException.class)
	public void missingOptionalArgumets() throws ArgumentsException {
		JSONObject argsRaw = new JSONObject();
		argsRaw.put("usage", "java -jar my.jar");
		argsRaw.append("required", optionalString);
		ArgsFactory.createArgs(argsRaw);
	}

	@Test(expected = ArgumentsException.class)
	public void badOptional() throws ArgumentsException {
		JSONObject argsRaw = new JSONObject();
		argsRaw.put("usage", "java -jar my.jar");
		argsRaw.append("optional", requiredInteger);
		argsRaw.append("required", optionalString);
		ArgsFactory.createArgs(argsRaw);
	}

}
