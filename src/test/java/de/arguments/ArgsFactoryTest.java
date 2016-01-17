package de.arguments;

import static org.junit.Assert.*;

import java.io.File;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import de.arguments.exceptions.ArgumentException;

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

		String identifier = "-s";
		String description = "My Description";

		String type = "String";
		requiredString = new JSONObject();
		requiredString.put("identifier", identifier);
		requiredString.put("type", type);
		requiredString.put("description", description);

		type = "Integer";
		identifier = "-i";
		requiredInteger = new JSONObject();
		requiredInteger.put("identifier", identifier);
		requiredInteger.put("type", type);
		requiredInteger.put("description", description);

		type = "Double";
		identifier = "-d";
		requiredDouble = new JSONObject();
		requiredDouble.put("identifier", identifier);
		requiredDouble.put("type", type);
		requiredDouble.put("description", description);

		type = "Boolean";
		identifier = "-b";
		requiredBoolean = new JSONObject();
		requiredBoolean.put("identifier", identifier);
		requiredBoolean.put("type", type);
		requiredBoolean.put("description", description);

		type = "String";
		identifier = "-s0";
		optionalString = new JSONObject();
		optionalString.put("identifier", identifier);
		optionalString.put("type", type);
		optionalString.put("description", description);
		optionalString.put("default", "Default String");

		type = "Integer";
		identifier = "-j";
		optionalInteger = new JSONObject();
		optionalInteger.put("identifier", identifier);
		optionalInteger.put("type", type);
		optionalInteger.put("default", 123);

		type = "Double";
		identifier = "-do";
		optionalDouble = new JSONObject();
		optionalDouble.put("identifier", identifier);
		optionalDouble.put("type", type);
		optionalDouble.put("description", description);
		optionalDouble.put("default", 1.234);

		type = "Boolean";
		identifier = "-bo";
		optionalBoolean = new JSONObject();
		optionalBoolean.put("identifier", identifier);
		optionalBoolean.put("type", type);
		optionalBoolean.put("description", description);
		optionalBoolean.put("default", true);

		type = "Flag";
		identifier = "-f";
		flag = new JSONObject();
		flag.put("identifier", identifier);
		flag.put("type", type);
		flag.put("description", description);
		
		argsRaw = new JSONObject();
		argsRaw.put("usage","java -jar my.jar");
		argsRaw.append("required", requiredString);
		argsRaw.append("required", requiredBoolean);
		argsRaw.append("required", requiredDouble);
		argsRaw.append("required", requiredInteger);
		argsRaw.append("optional",optionalString);
		argsRaw.append("optional",flag);
		argsRaw.append("optional",optionalDouble);
		argsRaw.append("optional",optionalBoolean);
		argsRaw.append("optional",optionalInteger);
	}

	@Test
	public void getArgsWithStringFromFile() throws ArgumentException {
		Args args = ArgsFactory.createArgsFromFile(file);

		String expected = "Usage: Some description e.g. java -jar my.jar"
				+ "\nRequired Arguments:"
				+ "\n\t-i Integer : another one"
				+ "\n\t-s String  : needed for what ever"
				+ "\nOptional Arguments:"
				+ "\n\t-n Integer : description (Default = 42)"
				+ "\n\t-o String  : needed for what ever (Default = \"Default string\")";
		String actual = args.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void getArgsWithFileFromFile() throws ArgumentException {
		Args args = ArgsFactory.createArgsFromFile(pathToFile);

		String expected = "Usage: Some description e.g. java -jar my.jar"
				+ "\nRequired Arguments:"
				+ "\n\t-i Integer : another one"
				+ "\n\t-s String  : needed for what ever"
				+ "\nOptional Arguments:"
				+ "\n\t-n Integer : description (Default = 42)"
				+ "\n\t-o String  : needed for what ever (Default = \"Default string\")";
		String actual = args.toString();
		assertEquals(expected, actual);
	}
	
	@Test(expected = ArgumentException.class)
	public void getArgsFileNotExisting() throws ArgumentException {
		String badPath = "./src/test/java/de/arguments/notExisting.args";
		ArgsFactory.createArgsFromFile(badPath);
	}
	
	@Test(expected = ArgumentException.class)
	public void getArgsFileWrongType() throws ArgumentException {
		String badPath = "./src/test/java/de/arguments/WrongType.json";
		ArgsFactory.createArgsFromFile(badPath);
	}
	
	@Test(expected = ArgumentException.class)
	public void getArgsFileIsDir() throws ArgumentException {
		String badPath = "./src/test/java/de/arguments/";
		ArgsFactory.createArgsFromFile(badPath);
	}
	
	@Test
	public void getArgsFromJSON() throws ArgumentException {
		Args args = ArgsFactory.createArgs(argsRaw);

		String expected = "Usage: java -jar my.jar"
				+ "\nRequired Arguments:"
				+ "\n\t-b Boolean : My Description"
				+ "\n\t-d Double  : My Description"
				+ "\n\t-i Integer : My Description"
				+ "\n\t-s String  : My Description"
				+ "\nOptional Arguments:"
				+ "\n\t-bo Boolean : My Description (Default = true)"
				+ "\n\t-do Double  : My Description (Default = 1.234)"
				+ "\n\t-f Flag     : My Description"
				+ "\n\t-j Integer  : (Default = 123)"
				+ "\n\t-s0 String  : My Description (Default = \"Default String\")";
		String actual = args.toString();

		assertEquals(expected, actual);
	}
	
	@Test(expected = ArgumentException.class)
	public void missingRequiredArgumets() throws ArgumentException {
		JSONObject argsRaw = new JSONObject();
		argsRaw.put("usage","java -jar my.jar");
		argsRaw.append("optional",optionalString);
		argsRaw.append("optional",flag);
		argsRaw.append("optional",optionalDouble);
		argsRaw.append("optional",optionalBoolean);
		argsRaw.append("optional",optionalInteger);
		ArgsFactory.createArgs(argsRaw);
	}
	
	@Test(expected = ArgumentException.class)
	public void missingOptionalArgumets() throws ArgumentException {
		JSONObject argsRaw = new JSONObject();
		argsRaw.put("usage","java -jar my.jar");
		argsRaw.append("required",optionalString);
		ArgsFactory.createArgs(argsRaw);
	}
	
	@Test(expected = ArgumentException.class)
	public void badOptional() throws ArgumentException {
		JSONObject argsRaw = new JSONObject();
		argsRaw.put("usage","java -jar my.jar");
		argsRaw.append("optional",requiredInteger);
		argsRaw.append("required",optionalString);
		ArgsFactory.createArgs(argsRaw);
	}

}
