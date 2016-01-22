package de.arguments;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import de.arguments.exceptions.ArgumentException;
import static de.arguments.ArgFactory.*;

public class ArgFactoryTest {

	private static JSONObject requiredString;
	private static JSONObject requiredChar;
	private static JSONObject requiredInteger;
	private static JSONObject requiredDouble;
	private static JSONObject requiredBoolean;
	private static JSONObject requiredStringArray;

	
	private static JSONObject optionalString;
	private static JSONObject optionalInteger;
	private static JSONObject optionalDouble;
	private static JSONObject optionalBoolean;
	private static JSONObject flag;
	private static JSONObject optionalChar;
	private static JSONObject optionalStringArray;

	@BeforeClass
	public static void setupClass() {
		String identifier = "s";
		String alias = "a_argument";
		String description = "My Description";

		String type = "String";
		requiredString = new JSONObject();
		requiredString.put("identifier", identifier);
		requiredString.put("type", type);
		requiredString.put("description", description);
		requiredString.put("alias", alias);

		type = "Char";
		requiredChar = new JSONObject();
		requiredChar.put("identifier", identifier);
		requiredChar.put("type", type);
		requiredChar.put("description", description);
		requiredChar.put("alias", alias);
		
		type = "Integer";
		requiredInteger = new JSONObject();
		requiredInteger.put("identifier", identifier);
		requiredInteger.put("type", type);
		requiredInteger.put("description", description);
		requiredInteger.put("alias", alias);

		type = "Double";
		requiredDouble = new JSONObject();
		requiredDouble.put("identifier", identifier);
		requiredDouble.put("type", type);
		requiredDouble.put("description", description);
		requiredDouble.put("alias", alias);

		type = "Boolean";
		requiredBoolean = new JSONObject();
		requiredBoolean.put("identifier", identifier);
		requiredBoolean.put("type", type);
		requiredBoolean.put("description", description);
		requiredBoolean.put("alias", alias);
		
		type = "StringArray";
		requiredStringArray = new JSONObject();
		requiredStringArray.put("identifier", identifier);
		requiredStringArray.put("type", type);
		requiredStringArray.put("description", description);
		requiredStringArray.put("alias", alias);

		type = "String";
		optionalString = new JSONObject();
		optionalString.put("identifier", identifier);
		optionalString.put("type", type);
		optionalString.put("description", description);
		optionalString.put("default", "Default String");
		optionalString.put("alias", alias);

		type = "Integer";
		optionalInteger = new JSONObject();
		optionalInteger.put("identifier", identifier);
		optionalInteger.put("type", type);
		optionalInteger.put("description", description);
		optionalInteger.put("default", 123);
		optionalInteger.put("alias", alias);

		type = "Double";
		optionalDouble = new JSONObject();
		optionalDouble.put("identifier", identifier);
		optionalDouble.put("type", type);
		optionalDouble.put("description", description);
		optionalDouble.put("default", 1.234);
		optionalDouble.put("alias", alias);

		type = "Boolean";
		optionalBoolean = new JSONObject();
		optionalBoolean.put("identifier", identifier);
		optionalBoolean.put("type", type);
		optionalBoolean.put("description", description);
		optionalBoolean.put("default", true);
		optionalBoolean.put("alias", alias);
		
		type = "Char";
		optionalChar = new JSONObject();
		optionalChar.put("identifier", identifier);
		optionalChar.put("type", type);
		optionalChar.put("description", description);
		optionalChar.put("alias", alias);
		optionalChar.put("default", "a");
		
		type = "StringArray";
		optionalStringArray = new JSONObject();
		optionalStringArray.put("identifier", identifier);
		optionalStringArray.put("type", type);
		optionalStringArray.put("description", description);
		optionalStringArray.put("alias", alias);
		optionalStringArray.put("default", new JSONArray("[My default,String]"));

		type = "Flag";
		flag = new JSONObject();
		flag.put("identifier", identifier);
		flag.put("type", type);
		flag.put("description", description);
		flag.put("alias", alias);
	}

	@Test
	public void requiredStringJSON() throws ArgumentException {
		Arg arg = ArgFactory.createRequiredArg(requiredString);
		String expected = "-s, --a_argument: <String> My Description";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void requiredIntgerJSON() throws ArgumentException {
		Arg arg = ArgFactory.createRequiredArg(requiredInteger);
		String expected = "-s, --a_argument: <Integer> My Description";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void requiredDoubleJSON() throws ArgumentException {
		Arg arg = ArgFactory.createRequiredArg(requiredDouble);
		String expected = "-s, --a_argument: <Double> My Description";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void requiredBooleanJSON() throws ArgumentException {
		Arg arg = ArgFactory.createRequiredArg(requiredBoolean);
		String expected = "-s, --a_argument: <Boolean> My Description";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void requiredCharJSON() throws ArgumentException {
		Arg arg = ArgFactory.createRequiredArg(requiredChar);
		String expected = "-s, --a_argument: <Char> My Description";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void requiredStringArrayJSON() throws ArgumentException {
		Arg arg = ArgFactory.createRequiredArg(requiredStringArray);
		String expected = "-s, --a_argument: <StringArray> My Description";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void optionalStringJSON() throws ArgumentException {
		Arg arg = ArgFactory.createOptionalArg(optionalString);
		String expected = "-s, --a_argument: <String> My Description (Default = \"Default String\")";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void optionalIntgerJSON() throws ArgumentException {
		Arg arg = ArgFactory.createOptionalArg(optionalInteger);
		String expected = "-s, --a_argument: <Integer> My Description (Default = 123)";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void optionalDoubleJSON() throws ArgumentException {
		Arg arg = ArgFactory.createOptionalArg(optionalDouble);
		String expected = "-s, --a_argument: <Double> My Description (Default = 1.234)";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void optionalBooleanJSON() throws ArgumentException {
		Arg arg = ArgFactory.createOptionalArg(optionalBoolean);
		String expected = "-s, --a_argument: <Boolean> My Description (Default = true)";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void optionalCharJSON() throws ArgumentException {
		Arg arg = ArgFactory.createOptionalArg(optionalChar);
		String expected = "-s, --a_argument: <Char> My Description (Default = 'a')";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void optionalStringArrayJSON() throws ArgumentException {
		System.out.println(optionalStringArray);
		Arg arg = ArgFactory.createOptionalArg(optionalStringArray);
		String expected = "-s, --a_argument: <StringArray> My Description (Default = [\"My default\", \"String\"])";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void flagJSON() throws ArgumentException {
		Arg arg = ArgFactory.createOptionalArg(flag);
		String expected = "-s, --a_argument: <Flag> My Description";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void genericStringJSON() throws ArgumentException {
		Arg arg = ArgFactory.createArg(optionalString, OPTIONAL_ARG);
		String expected = "-s, --a_argument: <String> My Description (Default = \"Default String\")";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalString, REQUIRED_ARG);
		expected = "-s, --a_argument: <String> My Description";
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void genericIntgerJSON() throws ArgumentException {
		Arg arg = ArgFactory.createArg(optionalInteger, OPTIONAL_ARG);
		String expected = "-s, --a_argument: <Integer> My Description (Default = 123)";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalInteger, REQUIRED_ARG);
		expected = "-s, --a_argument: <Integer> My Description";
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void genericDoubleJSON() throws ArgumentException {
		Arg arg = ArgFactory.createArg(optionalDouble, OPTIONAL_ARG);
		String expected = "-s, --a_argument: <Double> My Description (Default = 1.234)";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalDouble, REQUIRED_ARG);
		expected = "-s, --a_argument: <Double> My Description";
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void genericBooleanJSON() throws ArgumentException {
		Arg arg = ArgFactory.createArg(optionalBoolean, OPTIONAL_ARG);
		String expected = "-s, --a_argument: <Boolean> My Description (Default = true)";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalBoolean, REQUIRED_ARG);
		expected = "-s, --a_argument: <Boolean> My Description";
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test(expected = ArgumentException.class)
	public void genericflagJSON() throws ArgumentException {
		Arg arg = ArgFactory.createArg(flag, OPTIONAL_ARG);
		String expected = "-s, --a_argument: <Flag> My Description";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(flag, REQUIRED_ARG);
	}

	@Test
	public void noDescription() throws ArgumentException {
		String type = "Double";
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.put("identifier", "s");
		optionalDouble.put("type", type);
		optionalDouble.put("default", 1.234);

		Arg arg = ArgFactory.createArg(optionalDouble, REQUIRED_ARG);
		String expected = "-s: <Double>";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalDouble, OPTIONAL_ARG);
		expected = "-s: <Double> (Default = 1.234)";
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test(expected = ArgumentException.class)
	public void missingTypeOptional() throws ArgumentException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.put("identifier", "s");
		optionalDouble.put("default", 1.234);
		optionalDouble.put("description", "description");

		ArgFactory.createArg(optionalDouble, OPTIONAL_ARG);

	}

	@Test(expected = ArgumentException.class)
	public void missingTypeRequired() throws ArgumentException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.put("identifier", "s");
		optionalDouble.put("description", "description");

		ArgFactory.createArg(optionalDouble, REQUIRED_ARG);

	}

	@Test
	public void missingDescriptionOptional() throws ArgumentException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.put("identifier", "s");
		optionalDouble.put("type", "String");
		optionalDouble.put("default", 1.234);

		ArgFactory.createArg(optionalDouble, OPTIONAL_ARG);

	}

	@Test
	public void missingDescriptionRequired() throws ArgumentException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.put("identifier", "s");
		optionalDouble.put("type", "String");
		optionalDouble.put("default", 1.234);

		Arg arg = ArgFactory.createArg(optionalDouble, OPTIONAL_ARG);

		String expected = "-s: <String> (Default = \"1.234\")";
		String actual = arg.toString();
		assertEquals(expected, actual);

	}

	@Test(expected = ArgumentException.class)
	public void missingIdentifierOptional() throws ArgumentException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.put("type", "String");
		optionalDouble.put("default", 1.234);
		optionalDouble.put("description", "description");

		ArgFactory.createArg(optionalDouble, OPTIONAL_ARG);
	}

	@Test(expected = ArgumentException.class)
	public void missingIdentifierRequired() throws ArgumentException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.put("type", "string");
		optionalDouble.put("description", "description");

		ArgFactory.createArg(optionalDouble, REQUIRED_ARG);
	}

}
