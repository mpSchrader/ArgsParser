package de.arguments.optional;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentsException;

public class OptionalIntegerArrayTest {

	private OptionalIntegerArray arrayArg;

	@Before
	public void setup() throws ArgumentsException {
		Integer[] defaultt = { -1, 42 };
		arrayArg = new OptionalIntegerArray('a', "array", defaultt);
	}

	@Test
	public void getValueNotSet() throws ArgumentsException {
		Integer[] expected = { -1, 42 };
		Integer[] actual = arrayArg.getValue();
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
		Integer[] input = { 1, -42, 8 };
		arrayArg.setValue(input);

		Integer[] expected = { 1, -42, 8 };
		Integer[] actual = arrayArg.getValue();

		assertArrayEquals(expected, actual);
	}

	@Test(expected = ArgumentsException.class)
	public void setValueWrongArry1() throws ArgumentsException {
		String[] input = { "my", "new", "class]" };
		arrayArg.setValue(input);

	}
	
}
