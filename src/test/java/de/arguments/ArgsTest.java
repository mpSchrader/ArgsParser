package de.arguments;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentsException;
import de.arguments.optional.*;
import de.arguments.required.*;

public class ArgsTest {

	private static Args argsOptional;
	private static Args argsRequired;

	@Before
	public void setup() throws ArgumentsException {

		argsOptional = new Args();
		argsOptional.add(new OptionalBoolean('b', "bool", true));
		argsOptional.add(new OptionalDouble('d', "double", 3.3));
		argsOptional.add(new OptionalInteger('i', "integer", 3));
		argsOptional.add(new OptionalString('s', "string", "default"));
		argsOptional.add(new Flag('f', "flag"));
		argsOptional.add(new OptionalChar('c', "char", 'a'));
		String[] sDefaultt = { "My", "StringArray", "new" };
		argsOptional.add(new OptionalStringArray('a', "sArray", sDefaultt));
		Integer[] iDefaultt = { -1, 42, 43 };
		argsOptional.add(new OptionalIntegerArray('e', "iArray", iDefaultt));
		Double[] dDefaultt = { -1.1, 42.42, 43.43 };
		argsOptional.add(new OptionalDoubleArray('g', "dArray", dDefaultt));

		argsRequired = new Args();
		argsRequired.add(new OptionalBoolean('b', "oBool", true));
		argsRequired.add(new OptionalDouble('d', "oDouble", 3.3));
		argsRequired.add(new OptionalInteger('i', "oInteger", 3));
		argsRequired.add(new OptionalString('s', "oString", "default"));
		argsRequired.add(new RequiredBoolean('q', "br"));
		argsRequired.add(new RequiredChar('c', "char"));
		argsRequired.add(new RequiredDouble('l', "dr"));
		argsRequired.add(new RequiredInteger('m', "ir"));
		argsRequired.add(new RequiredString('n', "sr"));
		argsRequired.add(new RequiredStringArray('a', "sArray"));
		argsRequired.add(new RequiredIntegerArray('e', "iArray"));
		argsRequired.add(new RequiredDoubleArray('g', "dArray"));
	}

	@Test
	public void constructorWithUsage() {
		Args args = new Args("My cool Description!");

		String expected = "My cool Description!";
		String actual = args.getUsage();

		assertEquals(expected, actual);
	}

	@Test
	public void emptyUsageToString() {
		argsOptional.setUsage("");

		String expected = "Required Arguments:" + "\nOptional Arguments:" + "\n\t-b, --bool: <Boolean> (Default = true)"
				+ "\n\t-d, --double: <Double> (Default = 3.3)" + "\n\t-i, --integer: <Integer> (Default = 3)"
				+ "\n\t-s, --string: <String> (Default = \"default\")" + "\n\t-f, --flag: <Flag>"
				+ "\n\t-c, --char: <Char> (Default = 'a')"
				+ "\n\t-a, --sArray: <StringArray> (Default = [\"My\", \"StringArray\", \"new\"])"
				+ "\n\t-e, --iArray: <IntegerArray> (Default = [-1, 42, 43])"
				+ "\n\t-g, --dArray: <DoubleArray> (Default = [-1.1, 42.42, 43.43])";
		String actual = argsOptional.toString();

		assertEquals(expected, actual);
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchBooleanValueById() throws ArgumentsException {
		argsOptional.getIntegerValue('x');
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchDoubleValueById() throws ArgumentsException {
		argsOptional.getIntegerValue('x');
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchIntegerValueById() throws ArgumentsException {
		argsOptional.getIntegerValue('x');
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchStringValueById() throws ArgumentsException {
		argsOptional.getStringValue('x');
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchStringArrayValueById() throws ArgumentsException {
		argsOptional.getStringArrayValue('x');
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchIntegerArrayValueById() throws ArgumentsException {
		argsOptional.getIntegerArrayValue('x');
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchDoubleArrayValueById() throws ArgumentsException {
		argsOptional.getDoubleArrayValue('x');
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchFlagValueById() throws ArgumentsException {
		argsOptional.getFlagValue('x');
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchCharValueById() throws ArgumentsException {
		argsOptional.getCharValue('x');
	}

	@Test(expected = ArgumentsException.class)
	public void duplicatKeyById() throws ArgumentsException {
		List<Arg> arg = new ArrayList<Arg>();
		arg.add(new OptionalBoolean('b', true));
		arg.add(new OptionalDouble('b', 3.3));
		new Args(arg);
	}

	@Test(expected = ArgumentsException.class)
	public void duplicateKeyById() throws ArgumentsException {

		Args localArg = new Args();
		localArg.add(new OptionalBoolean('b', true));
		localArg.add(new OptionalDouble('b', 3.3));
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchBooleanValueSearchByString() throws ArgumentsException {
		argsOptional.getBooleanValue("notFound");
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchDoubleValueSearchByString() throws ArgumentsException {
		argsOptional.getDoubleValue("notFound");
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchIntegerValueSearchByString() throws ArgumentsException {
		argsOptional.getIntegerValue("notFound");
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchStringValueSearchByString() throws ArgumentsException {
		argsOptional.getStringValue("notFound");
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchFlagValueSearchByString() throws ArgumentsException {
		argsOptional.getFlagValue("notFound");
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchCharValueSearchByString() throws ArgumentsException {
		argsOptional.getCharValue("notFound");
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchStringArrayValueSearchByString() throws ArgumentsException {
		argsOptional.getStringArrayValue("notFound");
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchIntegerArrayValueSearchByString() throws ArgumentsException {
		argsOptional.getIntegerArrayValue("notFound");
	}

	@Test(expected = ArgumentsException.class)
	public void noSuchDoubleArrayValueSearchByString() throws ArgumentsException {
		argsOptional.getDoubleArrayValue("notFound");
	}
	
	@Test(expected = ArgumentsException.class)
	public void wrongTypeBoolean() throws ArgumentsException {
		argsOptional.getBooleanValue('d');
	}
	
	@Test(expected = ArgumentsException.class)
	public void wrongTypeDouble() throws ArgumentsException {
		argsOptional.getDoubleValue('b');
	}
	
	@Test(expected = ArgumentsException.class)
	public void wrongTypeString() throws ArgumentsException {
		argsOptional.getStringValue('d');
	}
	
	@Test(expected = ArgumentsException.class)
	public void wrongTypeChar() throws ArgumentsException {
		argsOptional.getCharValue('d');
	}
	
	@Test(expected = ArgumentsException.class)
	public void wrongTypeInteger() throws ArgumentsException {
		argsOptional.getIntegerValue('d');
	}
	
	@Test(expected = ArgumentsException.class)
	public void wrongTypeIntegerArray() throws ArgumentsException {
		argsOptional.getIntegerArrayValue('d');
	}
	
	@Test(expected = ArgumentsException.class)
	public void wrongTypeStringArray() throws ArgumentsException {
		argsOptional.getStringArrayValue('d');
	}
	
	@Test(expected = ArgumentsException.class)
	public void wrongTypeDoubleArray() throws ArgumentsException {
		argsOptional.getDoubleArrayValue('d');
	}
	
	@Test(expected = ArgumentsException.class)
	public void wrongTypeFlag() throws ArgumentsException {
		argsOptional.getFlagValue('d');
	}


	@Test(expected = ArgumentsException.class)
	public void duplicatKeyByString() throws ArgumentsException {
		List<Arg> arg = new ArrayList<Arg>();
		arg.add(new OptionalBoolean('x', "same", true));
		arg.add(new OptionalDouble('b', "same", 3.3));
		new Args(arg);
	}

	@Test(expected = ArgumentsException.class)
	public void parseNoAttributeId() throws ArgumentsException {
		String[] args = { "-ä" };
		argsOptional.parse(args);
	}
	
	@Test(expected = ArgumentsException.class)
	public void parseNoAttributeAlias() throws ArgumentsException {
		String[] args = { "--notExisting" };
		argsOptional.parse(args);
	}
	
	@Test(expected = ArgumentsException.class)
	public void parseMissingValue() throws ArgumentsException {
		String[] args = { "-b"};
		argsOptional.parse(args);
	}
	
	@Test(expected = ArgumentsException.class)
	public void parseArrayWrongStart() throws ArgumentsException {
		String[] args = { "--iArray","1"};
		argsOptional.parse(args);
	}
	
	@Test
	public void parseOptionalBoolean() throws ArgumentsException {
		String[] args = { "-b", "false" };
		argsOptional.parse(args);

		Boolean expected = false;
		Boolean actual = argsOptional.getBooleanValue('b');
		assertEquals(expected, actual);
	}

	@Test
	public void parseOptionalChar() throws ArgumentsException {
		String[] args = { "-c", "d" };
		argsOptional.parse(args);

		char expected = 'd';
		char actual = argsOptional.getCharValue('c');
		assertEquals(expected, actual);
	}

	@Test
	public void parseOptionalDouble() throws ArgumentsException {
		String[] args = { "-d", "0.999" };
		argsOptional.parse(args);

		Double expected = 0.999;
		Double actual = argsOptional.getDoubleValue('d');
		assertEquals(expected, actual);
	}

	@Test
	public void parseOptionalInteger() throws ArgumentsException {
		String[] args = { "-i", "999" };
		argsOptional.parse(args);

		Integer expected = 999;
		Integer actual = argsOptional.getIntegerValue('i');
		assertEquals(expected, actual);
	}

	@Test
	public void parseFlag() throws ArgumentsException {
		String[] args = { "-f" };
		argsOptional.parse(args);

		Boolean actual = argsOptional.getFlagValue('f');
		assertTrue(actual);
	}

	@Test
	public void parseNoFlag() throws ArgumentsException {
		String[] args = { "-d", ".123" };
		argsOptional.parse(args);

		Boolean actual = argsOptional.getFlagValue('f');
		assertFalse(actual);
	}

	@Test
	public void parseOptionalString() throws ArgumentsException {
		String[] args = { "-s", "new Value" };
		argsOptional.parse(args);

		String expected = "new Value";
		String actual = argsOptional.getStringValue('s');
		assertEquals(expected, actual);
	}

	@Test
	public void parseStringWithBlanks() throws ArgumentsException {
		String[] args = { "-s", "\"new", "value\"" };
		argsOptional.parse(args);

		String expected = "new value";
		String actual = argsOptional.getStringValue('s');
		assertEquals(expected, actual);
	}

	@Test
	public void parseOptionalBooleanByAlias() throws ArgumentsException {
		String[] args = { "--bool", "false" };
		argsOptional.parse(args);

		Boolean expected = false;
		Boolean actual = argsOptional.getBooleanValue('b');
		assertEquals(expected, actual);
	}

	@Test
	public void parseOptionalCharByAlias() throws ArgumentsException {
		String[] args = { "--char", "d" };
		argsOptional.parse(args);

		char expected = 'd';
		char actual = argsOptional.getCharValue("char");
		assertEquals(expected, actual);
	}

	@Test
	public void parseOptionalDoubleByAlias() throws ArgumentsException {
		String[] args = { "--double", "0.999" };
		argsOptional.parse(args);

		Double expected = 0.999;
		Double actual = argsOptional.getDoubleValue('d');
		assertEquals(expected, actual);
	}

	@Test
	public void parseOptionalIntegerbyAlias() throws ArgumentsException {
		String[] args = { "--integer", "999" };
		argsOptional.parse(args);

		Integer expected = 999;
		Integer actual = argsOptional.getIntegerValue('i');
		assertEquals(expected, actual);
	}

	@Test
	public void parseFlagByAlias() throws ArgumentsException {
		String[] args = { "--flag" };
		argsOptional.parse(args);

		Boolean actual = argsOptional.getFlagValue('f');
		assertTrue(actual);
	}

	@Test
	public void parseNoFlagByAlias() throws ArgumentsException {
		String[] args = { "--double", ".123" };
		argsOptional.parse(args);

		Boolean actual = argsOptional.getFlagValue("flag");
		assertFalse(actual);
	}

	@Test
	public void parseOptionalStringByAlias() throws ArgumentsException {
		String[] args = { "--string", "new Value" };
		argsOptional.parse(args);

		String expected = "new Value";
		String actual = argsOptional.getStringValue('s');
		assertEquals(expected, actual);
	}

	@Test
	public void parseStringWithBlanksByAlias() throws ArgumentsException {
		String[] args = { "-s", "\"new", "value\"" };
		argsOptional.parse(args);

		String expected = "new value";
		String actual = argsOptional.getStringValue('s');
		assertEquals(expected, actual);
	}

	@Test
	public void parseOptionalStringArrayByAlias() throws ArgumentsException {
		String[] args = { "--sArray", "[new Value,", "good]" };
		argsOptional.parse(args);

		String[] expected = { "new Value", "good" };
		String[] actual = argsOptional.getStringArrayValue('a');
		assertArrayEquals(expected, actual);
	}

	@Test
	public void parseStringArrayWithComma() throws ArgumentsException {
		String[] args = { "--sArray", "[new,Value,", "good]" };
		argsOptional.parse(args);

		String[] expected = { "new", "Value", "good" };
		String[] actual = argsOptional.getStringArrayValue('a');
		assertArrayEquals(expected, actual);
	}

	@Test
	public void parseOptionalStringArrayByAliasSplitted() throws ArgumentsException {
		String[] args = { "--sArray", "[new", "Value,", "good]" };
		argsOptional.parse(args);

		String[] expected = { "new", "Value", "good" };
		String[] actual = argsOptional.getStringArrayValue('a');
		assertArrayEquals(expected, actual);
	}

	@Test
	public void parseStringArrayWithBlanksByAlias() throws ArgumentsException {
		String[] args = { "--sArray", "[\"new", "Value\"", "good]" };
		argsOptional.parse(args);

		String[] expected = { "\"new Value\"", "good" };
		String[] actual = argsOptional.getStringArrayValue('a');
		assertArrayEquals(expected, actual);
	}

	@Test
	public void parseStringArrayWithDoubleBlanksByAlias() throws ArgumentsException {
		String[] args = { "--sArray", "[\"new", "Value\"", "\"new", "Value\"", "good]" };
		argsOptional.parse(args);

		String[] expected = { "\"new Value\"", "\"new Value\"", "good" };
		String[] actual = argsOptional.getStringArrayValue('a');
		assertArrayEquals(expected, actual);
	}

	@Test
	public void parseOptionalIntegerArrayByAliasSplitted() throws ArgumentsException {
		String[] args = { "--iArray", "[4", "5,", "-6]" };
		argsOptional.parse(args);

		Integer[] expected = { 4, 5, -6 };
		Integer[] actual = argsOptional.getIntegerArrayValue('e');
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void parseOptionalIntegerArrayByAliasOneString() throws ArgumentsException {
		String[] args = { "--iArray", "[4,5,-6]" };
		argsOptional.parse(args);

		Integer[] expected = { 4, 5, -6 };
		Integer[] actual = argsOptional.getIntegerArrayValue("iArray");
		assertArrayEquals(expected, actual);
	}

	@Test
	public void parseOptionalDoubleArrayByAliasSplitted() throws ArgumentsException {
		String[] args = { "--dArray", "[-1.1,", "42.42,", "43.43]" };
		argsOptional.parse(args);

		Double[] expected = { -1.1, 42.42, 43.43 };
		Double[] actual = argsOptional.getDoubleArrayValue('g');
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void parseOptionalDoubleArrayByAliasOneString() throws ArgumentsException {
		String[] args = { "--dArray", "[-1.1,42.42,43.43]" };
		argsOptional.parse(args);

		Double[] expected = { -1.1, 42.42, 43.43 };
		Double[] actual = argsOptional.getDoubleArrayValue("dArray");
		assertArrayEquals(expected, actual);
	}

	@Test
	public void parseRequiredArgumentsByAlias() throws ArgumentsException {
		String[] args = { "--sr", "new Value", "--ir", "78", "--br", "true", "--dr", "0.1234", "-e", "[3", "4", "-6]",
				"-a", "[My", "array]", "--dArray", "[3.3", "4.4", "-6.6]", "-c", "a" };
		argsRequired.parse(args);

		Boolean expectedB = true;
		Boolean actualB = argsRequired.getBooleanValue("br");
		assertEquals(expectedB, actualB);

		Character expectedC = 'a';
		Character actualC = argsRequired.getCharValue('c');
		assertEquals(expectedC, actualC);

		Double expectedD = 0.1234;
		Double actualD = argsRequired.getDoubleValue("dr");
		assertEquals(expectedD, actualD);

		Integer expectedI = 78;
		Integer actualI = argsRequired.getIntegerValue("ir");
		assertEquals(expectedI, actualI);

		String expectedS = "new Value";
		String actualS = argsRequired.getStringValue("sr");
		assertEquals(expectedS, actualS);

		String[] expectedSA = { "My", "array" };
		String[] actualSA = argsRequired.getStringArrayValue('a');
		assertArrayEquals(expectedSA, actualSA);

		Integer[] expectedIA = { 3, 4, -6 };
		Integer[] actualIA = argsRequired.getIntegerArrayValue('e');
		assertArrayEquals(expectedIA, actualIA);

		Double[] expectedDA = { 3.3, 4.4, -6.6 };
		Double[] actualDA = argsRequired.getDoubleArrayValue('g');
		assertArrayEquals(expectedDA, actualDA);

	}

	@Test
	public void parseStringArrayWithBracesInsideChange() throws ArgumentsException {
		String[] args = { "-a", "[\"My", "StringArray]\"", "new]" };
		Args argmnts = new Args();
		argmnts.add(new RequiredStringArray('a'));

		argmnts.parse(args);

		String[] expectedC = { "\"My StringArray]\"", "new" };
		String[] actualC = argmnts.getStringArrayValue('a');

		assertArrayEquals(expectedC, actualC);

	}

	@Test(expected = ArgumentsException.class)
	public void parseRequiredMissing() throws ArgumentsException {
		String[] args = { "--sr", "new Value", "--br", "true", "--dr", "0.1234" };
		argsRequired.parse(args);
	}

	@Test
	public void parseBooleanWithoutChangeOptional() throws ArgumentsException {
		String[] args = {};
		argsOptional.parse(args);

		Boolean expectedB = true;
		Boolean actualB = argsOptional.getBooleanValue('b');
		assertEquals(expectedB, actualB);

	}

	@Test
	public void parseCharWithoutChangeOptional() throws ArgumentsException {
		String[] args = {};
		argsOptional.parse(args);

		char expectedC = 'a';
		char actualC = argsOptional.getCharValue('c');
		assertEquals(expectedC, actualC);

	}

	@Test
	public void parseStringArrayWithoutChangeOptional() throws ArgumentsException {
		String[] args = {};
		argsOptional.parse(args);

		String[] expectedC = { "My", "StringArray", "new" };
		String[] actualC = argsOptional.getStringArrayValue('a');
		assertArrayEquals(expectedC, actualC);

	}
	
	@Test
	public void parseStringArrayGetWithAlias() throws ArgumentsException {
		String[] args = {};
		argsOptional.parse(args);

		String[] expectedC = { "My", "StringArray", "new" };
		String[] actualC = argsOptional.getStringArrayValue("sArray");
		assertArrayEquals(expectedC, actualC);

	}

	@Test
	public void parseIntegerArrayWithoutChangeOptional() throws ArgumentsException {
		String[] args = {};
		argsOptional.parse(args);

		Integer[] expectedC = { -1, 42, 43 };
		Integer[] actualC = argsOptional.getIntegerArrayValue('e');
		assertArrayEquals(expectedC, actualC);

	}

	@Test
	public void parseStringArrayWithBracesInsideChangeOptional() throws ArgumentsException {
		String[] args = { "-a", "[\"My", "StringArray]\"", "new]" };
		String[] defaultt = { "def" };
		Args argmnts = new Args();
		argmnts.add(new OptionalStringArray('a', defaultt));

		argmnts.parse(args);

		String[] expectedC = { "\"My StringArray]\"", "new" };
		String[] actualC = argmnts.getStringArrayValue('a');

		assertArrayEquals(expectedC, actualC);

	}

	@Test
	public void parseDoubleWithoutChangeOptional() throws ArgumentsException {
		String[] args = {};
		argsOptional.parse(args);

		Double expectedD = 3.3;
		Double actualD = argsOptional.getDoubleValue('d');
		assertEquals(expectedD, actualD);

	}

	@Test
	public void parseIntegerWithoutChangeOptional() throws ArgumentsException {
		String[] args = {};
		argsOptional.parse(args);

		Integer expectedI = 3;
		Integer actualI = argsOptional.getIntegerValue('i');
		assertEquals(expectedI, actualI);

	}

	@Test
	public void parseStringWithoutChangeOptional() throws ArgumentsException {
		String[] args = {};
		argsOptional.parse(args);

		String expectedS = "default";
		String actualS = argsOptional.getStringValue('s');
		assertEquals(expectedS, actualS);

	}
}
