package de.arguments;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.arguments.exceptions.ArgumentException;
import de.arguments.optional.Flag;
import de.arguments.optional.OptionalBoolean;
import de.arguments.optional.OptionalDouble;
import de.arguments.optional.OptionalInteger;
import de.arguments.optional.OptionalString;
import de.arguments.required.RequiredBoolean;
import de.arguments.required.RequiredChar;
import de.arguments.required.RequiredDouble;
import de.arguments.required.RequiredInteger;
import de.arguments.required.RequiredString;

public class DescriptionsTest {

	@Test
	public void checkOptionalBooleanToString() {
		String expected = "-b: <Boolean> This is a optional Boolean (Default = true)";
		String actual = (new OptionalBoolean('b', true)).toString();
		assertEquals(expected, actual);

		expected = "-b, --bold: <Boolean> This is a optional Boolean (Default = true)";
		actual = (new OptionalBoolean('b', "bold", true)).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkOptionalDoubleToString() {
		String expected = "-d: <Double> This is a optional Double (Default = 1.234)";
		String actual = (new OptionalDouble('d', 1.234)).toString();
		assertEquals(expected, actual);

		expected = "-d, --down: <Double> This is a optional Double (Default = 1.234)";
		actual = (new OptionalDouble('d', "down", 1.234)).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkOptionalIntegerToString() {
		String expected = "-i: <Integer> This is a optional Integer (Default = 123)";
		String actual = (new OptionalInteger('i', 123)).toString();
		assertEquals(expected, actual);

		expected = "-i, --long: <Integer> This is a optional Integer (Default = 123)";
		actual = (new OptionalInteger('i', "long", 123)).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkOptionalStringToString() {
		String expected = "-s, --string: <String> This is a optional String (Default = \"default\")";
		String actual = (new OptionalString('s', "string",
				"This is a optional String")).toString();
		assertEquals(expected, actual);

		expected = "-s: <String> This is a optional String (Default = \"default\")";
		actual = (new OptionalString('s', "This is a optional String"))
				.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void checkRequiredBooleanToString() {
		String expected = "-b: <Boolean> This is a required Boolean";
		Arg arg =new RequiredBoolean('b');
		arg.setDescription("This is a required Boolean");
		String actual = arg
				.toString();
		assertEquals(expected, actual);
		
		expected = "-b, --a_boolean: <Boolean> This is a required Boolean";
		arg = new RequiredBoolean('b', "a boolean");
		arg.setDescription("This is a required Boolean");
		actual = arg
				.toString();
		assertEquals(expected, actual);
	}

//	@Test
//	public void checkRequiredDoubleToString() {
//		String expected = "-d Double : This is a required Double";
//		String actual = (new RequiredDouble("d", "This is a required Double"))
//				.toString();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void checkRequiredIntegerToString() {
//		String expected = "-i Integer : This is a required Integer";
//		String actual = (new RequiredInteger("i", "This is a required Integer"))
//				.toString();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void checkRequiredCharToString() {
//		String expected = "-i Char : This is a required Char";
//		String actual = (new RequiredChar("i", "This is a required Char"))
//				.toString();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void checkRequiredStringToString() {
//		String expected = "-s String : This is a required String";
//		String actual = (new RequiredString("s", "This is a required String"))
//				.toString();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void checkOptionalBooleanToStringNoDiscr() {
//		String expected = "-b Boolean : (Default = true)";
//		String actual = (new OptionalBoolean("b", true)).toString();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void checkOptionalDoubleToStringNoDiscr() {
//		String expected = "-d Double : (Default = 1.234)";
//		String actual = (new OptionalDouble("d", 1.234)).toString();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void checkOptionalIntegerToStringNoDiscr() {
//		String expected = "-i Integer : (Default = 123)";
//		String actual = (new OptionalInteger("i", 123)).toString();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void checkOptionalStringToStringNoDiscr() {
//		String expected = "-s String : (Default = \"default\")";
//		String actual = (new OptionalString("s", "default")).toString();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void checkRequiredBooleanToStringNoDiscr() {
//		String expected = "-b Boolean";
//		String actual = (new RequiredBoolean("b")).toString();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void checkRequiredDoubleToStringNoDiscr() {
//		String expected = "-d Double";
//		String actual = (new RequiredDouble("d")).toString();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void checkRequiredIntegerToStringNoDiscr() {
//		String expected = "-i Integer";
//		String actual = (new RequiredInteger("i")).toString();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void checkRequiredStringToStringNoDiscr() {
//		String expected = "-s String";
//		String actual = (new RequiredString("s")).toString();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void checkRequiredCharToStringNoDiscr() {
//		String expected = "-i Char";
//		String actual = (new RequiredChar("i")).toString();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void checkFlagToStringNoDiscr() {
//		String expected = "-f Flag";
//		String actual = (new Flag("f")).toString();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void checkArgumentsToString() throws ArgumentException {
//		/* Setup args */
//		List<Arg> arg = new ArrayList<Arg>();
//		arg.add(new RequiredInteger("ir", "This is a required Integer"));
//		arg.add(new OptionalBoolean("b", true, "This is a optional Boolean"));
//		arg.add(new OptionalDouble("d", 1.234, "This is a optional Double"));
//		arg.add(new RequiredBoolean("br", "This is a required Boolean"));
//		arg.add(new RequiredDouble("dr", "This is a required Double"));
//		arg.add(new OptionalInteger("i", 123, "This is a optional Integer"));
//		arg.add(new OptionalInteger("n", 123));
//		arg.add(new OptionalString("s", "default", "This is a optional String"));
//		arg.add(new Flag("f", "This is a Flag"));
//		arg.add(new RequiredString("sr", "This is a required String"));
//		arg.add(new RequiredString("nr"));
//		Args args = new Args(arg);
//
//		String expected = "Required Arguments:"
//				+ "\n\t-br Boolean : This is a required Boolean"
//				+ "\n\t-dr Double  : This is a required Double"
//				+ "\n\t-ir Integer : This is a required Integer"
//				+ "\n\t-nr String"
//				+ "\n\t-sr String  : This is a required String"
//				+ "\nOptional Arguments:"
//				+ "\n\t-b Boolean : This is a optional Boolean (Default = true)"
//				+ "\n\t-d Double  : This is a optional Double (Default = 1.234)"
//				+ "\n\t-f Flag    : This is a Flag"
//				+ "\n\t-i Integer : This is a optional Integer (Default = 123)"
//				+ "\n\t-n Integer : (Default = 123)"
//				+ "\n\t-s String  : This is a optional String (Default = \"default\")";
//		String actual = args.argumentsToString();
//
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void checkUsageToString() throws ArgumentException {
//		/* Setup args */
//		List<Arg> arg = new ArrayList<Arg>();
//		arg.add(new RequiredInteger("ir", "This is a required Integer"));
//		arg.add(new OptionalBoolean("b", true, "This is a optional Boolean"));
//		arg.add(new OptionalDouble("d", 1.234, "This is a optional Double"));
//		arg.add(new RequiredBoolean("br", "This is a required Boolean"));
//		arg.add(new RequiredDouble("dr", "This is a required Double"));
//		arg.add(new OptionalInteger("i", 123, "This is a optional Integer"));
//		arg.add(new OptionalInteger("n", 123));
//		arg.add(new OptionalString("s", "default", "This is a optional String"));
//		arg.add(new Flag("f", "This is a Flag"));
//		arg.add(new RequiredString("sr", "This is a required String"));
//		arg.add(new RequiredString("nr"));
//		Args args = new Args(arg, "java -jar run.jar -s this");
//
//		String expected = "java -jar run.jar -s this";
//		String actual = args.usageToString();
//
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void checkArgsToString() throws ArgumentException {
//		/* Setup args */
//		List<Arg> arg = new ArrayList<Arg>();
//		arg.add(new RequiredInteger("ir", "This is a required Integer"));
//		arg.add(new OptionalBoolean("b", true, "This is a optional Boolean"));
//		arg.add(new OptionalDouble("d", 1.234, "This is a optional Double"));
//		arg.add(new RequiredBoolean("br", "This is a required Boolean"));
//		arg.add(new RequiredDouble("dr", "This is a required Double"));
//		arg.add(new OptionalInteger("i", 123, "This is a optional Integer"));
//		arg.add(new OptionalInteger("n", 123));
//		arg.add(new OptionalString("s", "default", "This is a optional String"));
//		arg.add(new Flag("f", "This is a Flag"));
//		arg.add(new RequiredString("sr", "This is a required String"));
//		arg.add(new RequiredString("nr"));
//		Args args = new Args(arg, "java -jar run.jar -s this");
//
//		String expected = "Usage: java -jar run.jar -s this"
//				+ "\nRequired Arguments:"
//				+ "\n\t-br Boolean : This is a required Boolean"
//				+ "\n\t-dr Double  : This is a required Double"
//				+ "\n\t-ir Integer : This is a required Integer"
//				+ "\n\t-nr String"
//				+ "\n\t-sr String  : This is a required String"
//				+ "\nOptional Arguments:"
//				+ "\n\t-b Boolean : This is a optional Boolean (Default = true)"
//				+ "\n\t-d Double  : This is a optional Double (Default = 1.234)"
//				+ "\n\t-f Flag    : This is a Flag"
//				+ "\n\t-i Integer : This is a optional Integer (Default = 123)"
//				+ "\n\t-n Integer : (Default = 123)"
//				+ "\n\t-s String  : This is a optional String (Default = \"default\")";
//		String actual = args.toString();
//
//		assertEquals(expected, actual);
//	}
}
