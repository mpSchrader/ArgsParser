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
	private static JSONObject requiredCharArray;
	private static JSONObject requiredIntegerArray;
	private static JSONObject requiredDoubleArray;
	private static JSONObject requiredBooleanArray;

	private static JSONObject optionalString;
	private static JSONObject optionalInteger;
	private static JSONObject optionalDouble;
	private static JSONObject optionalBoolean;
	private static JSONObject flag;
	private static JSONObject optionalChar;
	private static JSONObject optionalStringArray;
	private static JSONObject optionalCharArray;
	private static JSONObject optionalIntegerArray;
	private static JSONObject optionalDoubleArray;
	private static JSONObject optionalBooleanArray;

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

		type = "CharArray";
		requiredCharArray = new JSONObject();
		requiredCharArray.putString("identifier", identifier);
		requiredCharArray.putString("type", type);
		requiredCharArray.putString("description", description);
		requiredCharArray.putString("alias", alias);

		type = "IntegerArray";
		requiredIntegerArray = new JSONObject();
		requiredIntegerArray.putString("identifier", identifier);
		requiredIntegerArray.putString("type", type);
		requiredIntegerArray.putString("description", description);
		requiredIntegerArray.putString("alias", alias);

		type = "DoubleArray";
		requiredDoubleArray = new JSONObject();
		requiredDoubleArray.putString("identifier", identifier);
		requiredDoubleArray.putString("type", type);
		requiredDoubleArray.putString("description", description);
		requiredDoubleArray.putString("alias", alias);
		
		type = "BooleanArray";
		requiredBooleanArray = new JSONObject();
		requiredBooleanArray.putString("identifier", identifier);
		requiredBooleanArray.putString("type", type);
		requiredBooleanArray.putString("description", description);
		requiredBooleanArray.putString("alias", alias);

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

		type = "CharArray";
		optionalCharArray = new JSONObject();
		optionalCharArray.putString("identifier", identifier);
		optionalCharArray.putString("type", type);
		optionalCharArray.putString("description", description);
		optionalCharArray.putString("alias", alias);
		optionalCharArray.putJSONArray("default", new JSONArray("[a,c]"));

		type = "IntegerArray";
		optionalIntegerArray = new JSONObject();
		optionalIntegerArray.putString("identifier", identifier);
		optionalIntegerArray.putString("type", type);
		optionalIntegerArray.putString("description", description);
		optionalIntegerArray.putString("alias", alias);
		optionalIntegerArray.putJSONArray("default", new JSONArray("[-1,42,43]"));

		type = "DoubleArray";
		optionalDoubleArray = new JSONObject();
		optionalDoubleArray.putString("identifier", identifier);
		optionalDoubleArray.putString("type", type);
		optionalDoubleArray.putString("description", description);
		optionalDoubleArray.putString("alias", alias);
		optionalDoubleArray.putJSONArray("default", new JSONArray("[-1.1,42.42,43.43]"));

		type = "BooleanArray";
		optionalBooleanArray = new JSONObject();
		optionalBooleanArray.putString("identifier", identifier);
		optionalBooleanArray.putString("type", type);
		optionalBooleanArray.putString("description", description);
		optionalBooleanArray.putString("alias", alias);
		optionalBooleanArray.putJSONArray("default", new JSONArray("[true,false,true]"));

		
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
	public void requiredIntegerJSON() throws ArgumentsException {
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
	public void requiredBooleanArrayJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createRequiredArg(requiredBooleanArray);

		String expected = "-s, --a_argument: <BooleanArray> My Description";
		String actual = arg.toString();

		assertEquals(expected, actual);
	}
	
	@Test
	public void requiredCharArrayJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createRequiredArg(requiredCharArray);

		String expected = "-s, --a_argument: <CharArray> My Description";
		String actual = arg.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void requiredIntegerArrayJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createRequiredArg(requiredIntegerArray);

		String expected = "-s, --a_argument: <IntegerArray> My Description";
		String actual = arg.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void requiredDoubleArrayJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createRequiredArg(requiredDoubleArray);

		String expected = "-s, --a_argument: <DoubleArray> My Description";
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

	@Test(expected = ArgumentsException.class)
	public void optionalCharWorngDefaultJSON() throws ArgumentsException {

		JSONObject optionalChar = new JSONObject();
		optionalChar.putString("identifier", "s");
		optionalChar.putString("type", "Char");
		optionalChar.putString("description", "My Description");
		optionalChar.putString("alias", "a_argument");
		optionalChar.putString("default", "ca");
		ArgFactory.createOptionalArg(optionalChar);

	}

	@Test
	public void optionalStringArrayJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createOptionalArg(optionalStringArray);

		String expected = "-s, --a_argument: <StringArray> My Description (Default = [\"My default\", \"String\"])";
		String actual = arg.toString();

		assertEquals(expected, actual);
	}
	
	@Test
	public void optionalBooleanArrayJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createOptionalArg(optionalBooleanArray);

		String expected = "-s, --a_argument: <BooleanArray> My Description (Default = [true, false, true])";
		String actual = arg.toString();

		assertEquals(expected, actual);
	}
	
	@Test
	public void optionalCharArrayJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createOptionalArg(optionalCharArray);

		String expected = "-s, --a_argument: <CharArray> My Description (Default = [a, c])";
		String actual = arg.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void optionalIntegerArrayJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createOptionalArg(optionalIntegerArray);

		String expected = "-s, --a_argument: <IntegerArray> My Description (Default = [-1, 42, 43])";
		String actual = arg.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void optionalDoubleArrayJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createOptionalArg(optionalDoubleArray);

		String expected = "-s, --a_argument: <DoubleArray> My Description (Default = [-1.1, 42.42, 43.43])";
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
	public void genericIntegerJSON() throws ArgumentsException {
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
	public void genericStringArrayJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createArg(optionalStringArray, OPTIONAL_ARG);
		String expected = "-s, --a_argument: <StringArray> My Description (Default = [\"My default\", \"String\"])";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalStringArray, REQUIRED_ARG);
		expected = "-s, --a_argument: <StringArray> My Description";
		actual = arg.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void genericBooleanArrayJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createArg(optionalBooleanArray, OPTIONAL_ARG);
		String expected = "-s, --a_argument: <BooleanArray> My Description (Default = [true, false, true])";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalBooleanArray, REQUIRED_ARG);
		expected = "-s, --a_argument: <BooleanArray> My Description";
		actual = arg.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void genericCharArrayJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createArg(optionalCharArray, OPTIONAL_ARG);
		String expected = "-s, --a_argument: <CharArray> My Description (Default = [a, c])";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalCharArray, REQUIRED_ARG);
		expected = "-s, --a_argument: <CharArray> My Description";
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void genericIntegerArrayJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createArg(optionalIntegerArray, OPTIONAL_ARG);
		String expected = "-s, --a_argument: <IntegerArray> My Description (Default = [-1, 42, 43])";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalIntegerArray, REQUIRED_ARG);
		expected = "-s, --a_argument: <IntegerArray> My Description";
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void genericDoubleArrayJSON() throws ArgumentsException {
		Arg arg = ArgFactory.createArg(optionalDoubleArray, OPTIONAL_ARG);
		String expected = "-s, --a_argument: <DoubleArray> My Description (Default = [-1.1, 42.42, 43.43])";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalDoubleArray, REQUIRED_ARG);
		expected = "-s, --a_argument: <DoubleArray> My Description";
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

	@Test(expected = ArgumentsException.class)
	public void requiredWrongTypeJSON() throws ArgumentsException {
		ArgFactory.createArg(flag, -10);
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
	public void wrongTypeOptional() throws ArgumentsException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.putString("type", "s");
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
