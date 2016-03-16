package de.arguments.optional;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentsException;

public class OptionalBooleanArrayTest {

	private OptionalBooleanArray arrayArg;

	@Before
	public void setup() throws ArgumentsException {
		Boolean[] defaultt = { true, false, false };
		arrayArg = new OptionalBooleanArray('a', "array", defaultt);
	}

	@Test
	public void getValueNotSet() throws ArgumentsException {
		Boolean[] expected = { true, false, false };
		Boolean[] actual = arrayArg.getValue();
		assertArrayEquals(expected, actual);
	}

	@Test
	public void getID() throws ArgumentsException {
		char expected = 'a';
		char actual = arrayArg.getId();
		assertEquals(expected, actual);
	}

	@Test
	public void getAlias() throws ArgumentsException {
		String expected = "array";
		String actual = arrayArg.getAlias();
		assertEquals(expected, actual);
	}

	@Test
	public void setValue() throws ArgumentsException {
		Boolean[] input = { true, false, true };
		arrayArg.setValue(input);

		Boolean[] expected = { true, false, true };
		Boolean[] actual = arrayArg.getValue();

		assertArrayEquals(expected, actual);
	}

	@Test(expected = ArgumentsException.class)
	public void setValueWrongArry1() throws ArgumentsException {
		String[] input = { "my", "new", "class]" };
		arrayArg.setValue(input);

	}

}
