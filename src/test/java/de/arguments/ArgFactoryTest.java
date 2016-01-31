package de.arguments;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.arguments.json.*;
import de.arguments.exceptions.ArgumentsException;
import de.arguments.exceptions.JSONException;
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
	public static void setupClass() throws JSONException {
		String identifier = "s";
		String alias = "a_argument";
		String description = "My Description";

		String type = "String";
		requiredString = new JSONObject();
		requiredString.putString("identifier", identifier);
		requiredString.putString("type", type);
		requiredString.putString("description", description);
		requiredString.putString("alias", alias);

		type = "Char";
		requiredChar = new JSONObject();
		requiredChar.putString("identifier", identifier);
		requiredChar.putString("type", type);
		requiredChar.putString("description", description);
		requiredChar.putString("alias", alias);
		
		type = "Integer";
		requiredInteger = new JSONObject();
		requiredInteger.putString("identifier", identifier);
		requiredInteger.putString("type", type);
		requiredInteger.putString("description", description);
		requiredInteger.putString("alias", alias);

		type = "Double";
		requiredDouble = new JSONObject();
		requiredDouble.putString("identifier", identifier);
		requiredDouble.putString("type", type);
		requiredDouble.putString("description", description);
		requiredDouble.putString("alias", alias);

		type = "Boolean";
		requiredBoolean = new JSONObject();
		requiredBoolean.putString("identifier", identifier);
		requiredBoolean.putString("type", type);
		requiredBoolean.putString("description", description);
		requiredBoolean.putString("alias", alias);
		
		type = "StringArray";
		requiredStringArray = new JSONObject();
		requiredStringArray.putString("identifier", identifier);
		requiredStringArray.putString("type", type);
		requiredStringArray.putString("description", description);
		requiredStringArray.putString("alias", alias);

		type = "String";
		optionalString = new JSONObject();
		optionalString.putString("identifier", identifier);
		optionalString.putString("type", type);
		optionalString.putString("description", description);
		optionalString.putString("default", "Default String");
		optionalString.putString("alias", alias);

		type = "Integer";
		optionalInteger = new JSONObject();
		optionalInteger.putString("identifier", identifier);
		optionalInteger.putString("type", type);
		optionalInteger.putString("description", description);
		optionalInteger.putInteger("default", 123);
		optionalInteger.putString("alias", alias);

		type = "Double";
		optionalDouble = new JSONObject();
		optionalDouble.putString("identifier", identifier);
		optionalDouble.putString("type", type);
		optionalDouble.putString("description", description);
		optionalDouble.putDouble("default", 1.234);
		optionalDouble.putString("alias", alias);

		type = "Boolean";
		optionalBoolean = new JSONObject();
		optionalBoolean.putString("identifier", identifier);
		optionalBoolean.putString("type", type);
		optionalBoolean.putString("description", description);
		optionalBoolean.putBoolean("default", true);
		optionalBoolean.putString("alias", alias);
		
		type = "Char";
		optionalChar = new JSONObject();
		optionalChar.putString("identifier", identifier);
		optionalChar.putString("type", type);
		optionalChar.putString("description", description);
		optionalChar.putString("alias", alias);
		optionalChar.putString("default", "a");
		
		type = "StringArray";
		optionalStringArray = new JSONObject();
		optionalStringArray.putString("identifier", identifier);
		optionalStringArray.putString("type", type);
		optionalStringArray.putString("description", description);
		optionalStringArray.putString("alias", alias);
		optionalStringArray.putJSONArray("default", new JSONArray("[My default,String]"));

		type = "Flag";
		flag = new JSONObject();
		flag.putString("identifier", identifier);
		flag.putString("type", type);
		flag.putString("description", description);
		flag.putString("alias", alias);
	}

	@Test
	public void requiredStringJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createRequiredArg(requiredString);
		
		String expected = "-s, --a_argument: <String> My Description";
		String actual = arg.toString();
		
		assertEquals(expected, actual);
	}

	@Test
	public void requiredIntgerJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createRequiredArg(requiredInteger);
		
		String expected = "-s, --a_argument: <Integer> My Description";
		String actual = arg.toString();
		
		assertEquals(expected, actual);
	}

	@Test
	public void requiredDoubleJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createRequiredArg(requiredDouble);
		
		String expected = "-s, --a_argument: <Double> My Description";
		String actual = arg.toString();
		
		assertEquals(expected, actual);
	}

	@Test
	public void requiredBooleanJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createRequiredArg(requiredBoolean);
		
		String expected = "-s, --a_argument: <Boolean> My Description";
		String actual = arg.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void requiredCharJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createRequiredArg(requiredChar);
		
		String expected = "-s, --a_argument: <Char> My Description";
		String actual = arg.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void requiredStringArrayJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createRequiredArg(requiredStringArray);
		
		String expected = "-s, --a_argument: <StringArray> My Description";
		String actual = arg.toString();
		
		assertEquals(expected, actual);
	}

	@Test
	public void optionalStringJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createOptionalArg(optionalString);
		
		String expected = "-s, --a_argument: <String> My Description (Default = \"Default String\")";
		String actual = arg.toString();
		
		assertEquals(expected, actual);
	}

	@Test
	public void optionalIntgerJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createOptionalArg(optionalInteger);
		
		String expected = "-s, --a_argument: <Integer> My Description (Default = 123)";
		String actual = arg.toString();
		
		assertEquals(expected, actual);
	}

	@Test
	public void optionalDoubleJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createOptionalArg(optionalDouble);
		
		String expected = "-s, --a_argument: <Double> My Description (Default = 1.234)";
		String actual = arg.toString();
		
		assertEquals(expected, actual);
	}

	@Test
	public void optionalBooleanJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createOptionalArg(optionalBoolean);
		
		String expected = "-s, --a_argument: <Boolean> My Description (Default = true)";
		String actual = arg.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void optionalCharJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createOptionalArg(optionalChar);
		
		String expected = "-s, --a_argument: <Char> My Description (Default = 'a')";
		String actual = arg.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void optionalStringArrayJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createOptionalArg(optionalStringArray);
		
		String expected = "-s, --a_argument: <StringArray> My Description (Default = [\"My default\", \"String\"])";
		String actual = arg.toString();
		
		assertEquals(expected, actual);
	}

	@Test
	public void flagJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createOptionalArg(flag);
		
		String expected = "-s, --a_argument: <Flag> My Description";
		String actual = arg.toString();
		
		assertEquals(expected, actual);
	}

	@Test
	public void genericStringJSON() throws ArgumentsException {
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
	public void genericIntgerJSON() throws ArgumentsException {
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
	public void genericDoubleJSON() throws ArgumentsException {
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
	public void genericBooleanJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createArg(optionalBoolean, OPTIONAL_ARG);
		String expected = "-s, --a_argument: <Boolean> My Description (Default = true)";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalBoolean, REQUIRED_ARG);
		expected = "-s, --a_argument: <Boolean> My Description";
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void genericflagJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createArg(flag, OPTIONAL_ARG);
		
		String expected = "-s, --a_argument: <Flag> My Description";
		String actual = arg.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test(expected = ArgumentsException.class)
	public void requiredFlagJSON() throws ArgumentsException {
		ArgFactory.createArg(flag, REQUIRED_ARG);
	}

	@Test
	public void noDescription() throws ArgumentsException {
		String type = "Double";
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.putString("identifier", "s");
		optionalDouble.putString("type", type);
		optionalDouble.putDouble("default", 1.234);

		Arg arg = ArgFactory.createArg(optionalDouble, REQUIRED_ARG);
		String expected = "-s: <Double>";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalDouble, OPTIONAL_ARG);
		expected = "-s: <Double> (Default = 1.234)";
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test(expected = ArgumentsException.class)
	public void missingTypeOptional() throws ArgumentsException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.putString("identifier", "s");
		optionalDouble.putDouble("default", 1.234);
		optionalDouble.putString("description", "description");

		ArgFactory.createArg(optionalDouble, OPTIONAL_ARG);

	}

	@Test(expected = ArgumentsException.class)
	public void missingTypeRequired() throws ArgumentsException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.putString("identifier", "s");
		optionalDouble.putString("description", "description");

		ArgFactory.createArg(optionalDouble, REQUIRED_ARG);

	}

	@Test
	public void missingDescriptionOptional() throws ArgumentsException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.putString("identifier", "s");
		optionalDouble.putString("type", "String");
		optionalDouble.putString("default", "1.234");

		ArgFactory.createArg(optionalDouble, OPTIONAL_ARG);

	}

	@Test
	public void missingDescriptionRequired() throws ArgumentsException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.putString("identifier", "s");
		optionalDouble.putString("type", "String");
		optionalDouble.putString("default", "1.234");

		Arg arg = ArgFactory.createArg(optionalDouble, OPTIONAL_ARG);
		
		String expected = "-s: <String> (Default = \"1.234\")";
		String actual = arg.toString();
		assertEquals(expected, actual);

	}

	@Test(expected = ArgumentsException.class)
	public void missingIdentifierOptional() throws ArgumentsException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.putString("type", "String");
		optionalDouble.putDouble("default", 1.234);
		optionalDouble.putString("description", "description");

		ArgFactory.createArg(optionalDouble, OPTIONAL_ARG);
	}

	@Test(expected = ArgumentsException.class)
	public void missingIdentifierRequired() throws ArgumentsException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.putString("type", "string");
		optionalDouble.putString("description", "description");

		ArgFactory.createArg(optionalDouble, REQUIRED_ARG);
	}

}
