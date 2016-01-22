package de.arguments;

import static org.junit.Assert.*;

import org.junit.Test;

import de.arguments.exceptions.ArgumentsException;
import de.arguments.optional.Flag;
import de.arguments.optional.OptionalBoolean;
import de.arguments.optional.OptionalDouble;
import de.arguments.optional.OptionalInteger;
import de.arguments.optional.OptionalString;
import de.arguments.optional.OptionalStringArray;
import de.arguments.required.RequiredBoolean;
import de.arguments.required.RequiredChar;
import de.arguments.required.RequiredDouble;
import de.arguments.required.RequiredInteger;
import de.arguments.required.RequiredString;
import de.arguments.required.RequiredStringArray;

public class DescriptionsTest {

	@Test
	public void checkOptionalBooleanToString() {
		String expected = "-b: <Boolean> This is a optional Boolean (Default = true)";
		Arg arg = new OptionalBoolean('b', true);
		arg.setDescription("This is a optional Boolean");
		String actual = (arg).toString();
		assertEquals(expected, actual);

		expected = "-b, --bold: <Boolean> This is a optional Boolean (Default = true)";
		arg = new OptionalBoolean('b', "bold", true);
		arg.setDescription("This is a optional Boolean");
		actual = (arg).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkOptionalDoubleToString() {
		String expected = "-d: <Double> This is a optional Double (Default = 1.234)";
		Arg arg = new OptionalDouble('d', 1.234);
		arg.setDescription("This is a optional Double");
		String actual = (arg).toString();
		assertEquals(expected, actual);

		expected = "-d, --down: <Double> This is a optional Double (Default = 1.234)";
		arg = new OptionalDouble('d', "down", 1.234);
		arg.setDescription("This is a optional Double");
		actual = (arg).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkOptionalIntegerToString() {
		String expected = "-i: <Integer> This is a optional Integer (Default = 123)";
		Arg arg = new OptionalInteger('i', 123);
		arg.setDescription("This is a optional Integer");
		String actual = (arg).toString();
		assertEquals(expected, actual);

		expected = "-i, --long: <Integer> This is a optional Integer (Default = 123)";
		arg = new OptionalInteger('i', "long", 123);
		arg.setDescription("This is a optional Integer");
		actual = (arg).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkOptionalStringToString() {
		String expected = "-s, --string: <String> This is a optional String (Default = \"default\")";
		Arg arg = new OptionalString('s', "string", "default");
		arg.setDescription("This is a optional String");
		String actual = arg.toString();
		assertEquals(expected, actual);

		expected = "-s: <String> This is a optional String (Default = \"default\")";
		arg = new OptionalString('s', "default");
		arg.setDescription("This is a optional String");
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkRequiredBooleanToString() {
		String expected = "-b: <Boolean> This is a required Boolean";
		Arg arg = new RequiredBoolean('b');
		arg.setDescription("This is a required Boolean");
		String actual = arg.toString();
		assertEquals(expected, actual);

		expected = "-b, --a_boolean: <Boolean> This is a required Boolean";
		arg = new RequiredBoolean('b', "a boolean");
		arg.setDescription("This is a required Boolean");
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkRequiredDoubleToString() {
		String expected = "-d, --double: <Double> This is a required Double";
		Arg arg = new RequiredDouble('d', "double");
		arg.setDescription("This is a required Double");
		String actual = (arg).toString();
		assertEquals(expected, actual);

		expected = "-d: <Double> This is a required Double";
		arg = new RequiredDouble('d');
		arg.setDescription("This is a required Double");
		actual = (arg).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkRequiredIntegerToString() {
		String expected = "-i, --myInt: <Integer> This is a required Integer";
		Arg arg = new RequiredInteger('i', "myInt");
		arg.setDescription("This is a required Integer");
		String actual = (arg).toString();
		assertEquals(expected, actual);

		expected = "-i: <Integer> This is a required Integer";
		arg = new RequiredInteger('i');
		arg.setDescription("This is a required Integer");
		actual = (arg).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkRequiredCharToString() {
		String expected = "-i, --myChar: <Char> This is a required Char";
		Arg arg = new RequiredChar('i', "myChar");
		arg.setDescription("This is a required Char");
		String actual = (arg).toString();
		assertEquals(expected, actual);

		expected = "-i: <Char> This is a required Char";
		arg = new RequiredChar('i');
		arg.setDescription("This is a required Char");
		actual = (arg).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkRequiredStringToString() {
		String expected = "-s, --myString: <String> This is a required String";
		Arg arg = new RequiredString('s', "myString");
		arg.setDescription("This is a required String");
		String actual = (arg).toString();
		assertEquals(expected, actual);

		expected = "-s: <String> This is a required String";
		arg = new RequiredString('s');
		arg.setDescription("This is a required String");
		actual = (arg).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkOptionalBooleanToStringNoDiscr() {
		String expected = "-b: <Boolean> (Default = true)";
		Arg arg = new OptionalBoolean('b', true);
		String actual = (arg).toString();
		assertEquals(expected, actual);

		expected = "-b, --bold: <Boolean> (Default = true)";
		arg = new OptionalBoolean('b', "bold", true);
		actual = (arg).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkOptionalDoubleToStringNoDiscr() {
		String expected = "-d: <Double> (Default = 1.234)";
		Arg arg = new OptionalDouble('d', 1.234);
		String actual = (arg).toString();
		assertEquals(expected, actual);

		expected = "-d, --down: <Double> (Default = 1.234)";
		arg = new OptionalDouble('d', "down", 1.234);
		actual = (arg).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkOptionalIntegerToStringNoDiscr() {
		String expected = "-i: <Integer> (Default = 123)";
		Arg arg = new OptionalInteger('i', 123);
		String actual = (arg).toString();
		assertEquals(expected, actual);

		expected = "-i, --long: <Integer> (Default = 123)";
		arg = new OptionalInteger('i', "long", 123);
		actual = (arg).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkOptionalStringToStringNoDiscr() {
		String expected = "-s, --string: <String> (Default = \"default\")";
		Arg arg = new OptionalString('s', "string", "default");
		String actual = arg.toString();
		assertEquals(expected, actual);

		expected = "-s: <String> (Default = \"default\")";
		arg = new OptionalString('s', "default");
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkRequiredBooleanToStringNoDiscr() {
		String expected = "-b: <Boolean>";
		Arg arg = new RequiredBoolean('b');
		String actual = arg.toString();
		assertEquals(expected, actual);

		expected = "-b, --a_boolean: <Boolean>";
		arg = new RequiredBoolean('b', "a boolean");
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkRequiredDoubleToStringNoDiscr() {
		String expected = "-d, --double: <Double>";
		Arg arg = new RequiredDouble('d', "double");
		String actual = (arg).toString();
		assertEquals(expected, actual);

		expected = "-d: <Double>";
		arg = new RequiredDouble('d');
		actual = (arg).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkRequiredIntegerToStringNoDiscr() {
		String expected = "-i, --myInt: <Integer>";
		Arg arg = new RequiredInteger('i', "myInt");
		String actual = (arg).toString();
		assertEquals(expected, actual);

		expected = "-i: <Integer>";
		arg = new RequiredInteger('i');
		actual = (arg).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkRequiredStringToStringNoDiscr() {
		String expected = "-s, --myString: <String>";
		Arg arg = new RequiredString('s', "myString");
		String actual = (arg).toString();
		assertEquals(expected, actual);

		expected = "-s: <String>";
		arg = new RequiredString('s');
		actual = (arg).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkRequiredCharToStringNoDiscr() {
		String expected = "-i, --myChar: <Char>";
		Arg arg = new RequiredChar('i', "myChar");
		String actual = (arg).toString();
		assertEquals(expected, actual);

		expected = "-i: <Char>";
		arg = new RequiredChar('i');
		actual = (arg).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkFlagToStringNoDiscr() {
		String expected = "-f, --myFlag: <Flag>";
		Arg arg = new Flag('f', "myFlag");
		String actual = arg.toString();
		assertEquals(expected, actual);

		expected = "-f: <Flag>";
		arg = new Flag('f');
		actual = arg.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void checkRequiredStringArrayToStringNoDiscr() throws ArgumentsException {
		String expected = "-a, --myArray: <StringArray>";
		Arg arg = new RequiredStringArray('a', "myArray");
		String actual = (arg).toString();
		assertEquals(expected, actual);

		expected = "-a: <StringArray>";
		arg = new RequiredStringArray('a');
		actual = (arg).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkStringArrayToStringNoDiscr() throws ArgumentsException {
		String expected = "-a, --myArray: <StringArray> (Default = [\"default\", \"values\", \"Hello, World\"])";
		String[] defaultt = {"default", "values","Hello, World"};
		Arg arg = new OptionalStringArray('a', "myArray",defaultt);
		String actual = arg.toString();
		assertEquals(expected, actual);

		expected = "-a: <StringArray> (Default = [\"default\", \"values\", \"Hello, World\"])";
		arg = new OptionalStringArray('a',defaultt);
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkRequiredStringArrayToString() throws ArgumentsException {
		String expected = "-a, --myArray: <StringArray> My Description";
		Arg arg = new RequiredStringArray('a', "myArray");
		arg.setDescription("My Description");
		String actual = (arg).toString();
		assertEquals(expected, actual);

		expected = "-a: <StringArray> My Description";
		arg = new RequiredStringArray('a');
		arg.setDescription("My Description");
		actual = (arg).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkStringArrayToString() throws ArgumentsException {
		String expected = "-a, --myArray: <StringArray> My Description (Default = [\"default\", \"values\", \"Hello, World\"])";
		String[] defaultt = {"default", "values","Hello, World"};
		Arg arg = new OptionalStringArray('a', "myArray",defaultt);
		arg.setDescription("My Description");
		String actual = arg.toString();
		assertEquals(expected, actual);

		expected = "-a: <StringArray> My Description (Default = [\"default\", \"values\", \"Hello, World\"])";
		arg = new OptionalStringArray('a',defaultt);
		arg.setDescription("My Description");
		actual = arg.toString();
		assertEquals(expected, actual);
	}

	
	@Test
	public void checkArgumentsToString() throws ArgumentsException {
		// TODO
	}

	@Test
	public void checkUsageToString() throws ArgumentsException {
		// TODO
	}

	@Test
	public void checkArgsToString() throws ArgumentsException {
		// TODO
	}
}
