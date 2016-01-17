package de.arguments;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import de.arguments.exceptions.ArgumentException;
import static de.arguments.ArgFactory.*;

public class ArgFactoryTest {

	private static JSONObject requiredString;
	private static JSONObject requiredInteger;
	private static JSONObject requiredDouble;
	private static JSONObject requiredBoolean;
	private static JSONObject optionalString;
	private static JSONObject optionalInteger;
	private static JSONObject optionalDouble;
	private static JSONObject optionalBoolean;
	private static JSONObject flag;

	@BeforeClass
	public static void setupClass() {
		String identifier = "-s";
		String description = "My Description";

		String type = "String";
		requiredString = new JSONObject();
		requiredString.put("identifier", identifier);
		requiredString.put("type", type);
		requiredString.put("description", description);

		type = "Integer";
		requiredInteger = new JSONObject();
		requiredInteger.put("identifier", identifier);
		requiredInteger.put("type", type);
		requiredInteger.put("description", description);

		type = "Double";
		requiredDouble = new JSONObject();
		requiredDouble.put("identifier", identifier);
		requiredDouble.put("type", type);
		requiredDouble.put("description", description);

		type = "Boolean";
		requiredBoolean = new JSONObject();
		requiredBoolean.put("identifier", identifier);
		requiredBoolean.put("type", type);
		requiredBoolean.put("description", description);

		type = "String";
		optionalString = new JSONObject();
		optionalString.put("identifier", identifier);
		optionalString.put("type", type);
		optionalString.put("description", description);
		optionalString.put("default", "Default String");

		type = "Integer";
		optionalInteger = new JSONObject();
		optionalInteger.put("identifier", identifier);
		optionalInteger.put("type", type);
		optionalInteger.put("description", description);
		optionalInteger.put("default", 123);

		type = "Double";
		optionalDouble = new JSONObject();
		optionalDouble.put("identifier", identifier);
		optionalDouble.put("type", type);
		optionalDouble.put("description", description);
		optionalDouble.put("default", 1.234);

		type = "Boolean";
		optionalBoolean = new JSONObject();
		optionalBoolean.put("identifier", identifier);
		optionalBoolean.put("type", type);
		optionalBoolean.put("description", description);
		optionalBoolean.put("default", true);

		type = "Flag";
		flag = new JSONObject();
		flag.put("identifier", identifier);
		flag.put("type", type);
		flag.put("description", description);
	}

	@Test
	public void requiredStringJSON() throws ArgumentException {
		Arg arg = ArgFactory.createRequiredArg(requiredString);
		String expected = "-s String : My Description";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void requiredIntgerJSON() throws ArgumentException {
		Arg arg = ArgFactory.createRequiredArg(requiredInteger);
		String expected = "-s Integer : My Description";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void requiredDoubleJSON() throws ArgumentException {
		Arg arg = ArgFactory.createRequiredArg(requiredDouble);
		String expected = "-s Double : My Description";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void requiredBooleanJSON() throws ArgumentException {
		Arg arg = ArgFactory.createRequiredArg(requiredBoolean);
		String expected = "-s Boolean : My Description";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void requiredCharJSON() throws ArgumentException {
		Arg arg = ArgFactory.createRequiredArg(requiredBoolean);
		String expected = "-s Char : My Description";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void optionalStringJSON() throws ArgumentException {
		Arg arg = ArgFactory.createOptionalArg(optionalString);
		String expected = "-s String : My Description (Default = \"Default String\")";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void optionalIntgerJSON() throws ArgumentException {
		Arg arg = ArgFactory.createOptionalArg(optionalInteger);
		String expected = "-s Integer : My Description (Default = 123)";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void optionalDoubleJSON() throws ArgumentException {
		Arg arg = ArgFactory.createOptionalArg(optionalDouble);
		String expected = "-s Double : My Description (Default = 1.234)";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void optionalBooleanJSON() throws ArgumentException {
		Arg arg = ArgFactory.createOptionalArg(optionalBoolean);
		String expected = "-s Boolean : My Description (Default = true)";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void optionalCharJSON() throws ArgumentException {
		Arg arg = ArgFactory.createOptionalArg(optionalBoolean);
		String expected = "-s Char : My Description (Default = 'a')";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void flagJSON() throws ArgumentException {
		Arg arg = ArgFactory.createOptionalArg(flag);
		String expected = "-s Flag : My Description";
		String actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void genericStringJSON() throws ArgumentException {
		Arg arg = ArgFactory.createArg(optionalString, OPTIONAL_ARG);
		String expected = "-s String : My Description (Default = \"Default String\")";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalString, REQUIRED_ARG);
		expected = "-s String : My Description";
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void genericIntgerJSON() throws ArgumentException {
		Arg arg = ArgFactory.createArg(optionalInteger, OPTIONAL_ARG);
		String expected = "-s Integer : My Description (Default = 123)";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalInteger, REQUIRED_ARG);
		expected = "-s Integer : My Description";
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void genericDoubleJSON() throws ArgumentException {
		Arg arg = ArgFactory.createArg(optionalDouble, OPTIONAL_ARG);
		String expected = "-s Double : My Description (Default = 1.234)";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalDouble, REQUIRED_ARG);
		expected = "-s Double : My Description";
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void genericBooleanJSON() throws ArgumentException {
		Arg arg = ArgFactory.createArg(optionalBoolean, OPTIONAL_ARG);
		String expected = "-s Boolean : My Description (Default = true)";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalBoolean, REQUIRED_ARG);
		expected = "-s Boolean : My Description";
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test(expected = ArgumentException.class)
	public void genericflagJSON() throws ArgumentException {
		Arg arg = ArgFactory.createArg(flag, OPTIONAL_ARG);
		String expected = "-s Flag : My Description";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(flag, REQUIRED_ARG);
	}

	@Test
	public void noDescription() throws ArgumentException {
		String type = "Double";
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.put("identifier", "-s");
		optionalDouble.put("type", type);
		optionalDouble.put("default", 1.234);

		Arg arg = ArgFactory.createArg(optionalDouble, REQUIRED_ARG);
		String expected = "-s Double";
		String actual = arg.toString();
		assertEquals(expected, actual);

		arg = ArgFactory.createArg(optionalDouble, OPTIONAL_ARG);
		expected = "-s Double : (Default = 1.234)";
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test(expected = ArgumentException.class)
	public void missingTypeOptional() throws ArgumentException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.put("identifier", "-s");
		optionalDouble.put("default", 1.234);
		optionalDouble.put("description", "description");

		ArgFactory.createArg(optionalDouble, OPTIONAL_ARG);

	}

	@Test(expected = ArgumentException.class)
	public void missingTypeRequired() throws ArgumentException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.put("identifier", "-s");
		optionalDouble.put("description", "description");

		ArgFactory.createArg(optionalDouble, REQUIRED_ARG);

	}

	@Test
	public void missingDescriptionOptional() throws ArgumentException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.put("identifier", "-s");
		optionalDouble.put("type", "String");
		optionalDouble.put("default", 1.234);

		ArgFactory.createArg(optionalDouble, OPTIONAL_ARG);

	}

	@Test
	public void missingDescriptionRequired() throws ArgumentException {
		JSONObject optionalDouble = new JSONObject();
		optionalDouble.put("identifier", "-s");
		optionalDouble.put("type", "String");
		optionalDouble.put("default", 1.234);

		Arg arg = ArgFactory.createArg(optionalDouble, OPTIONAL_ARG);

		String expected = "-s String : (Default = \"1.234\")";
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
