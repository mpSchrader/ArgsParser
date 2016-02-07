package de.arguments.optional;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentsException;

public class OptionalDoubleArrayTest {

	private OptionalDoubleArray arrayArg;

	@Before
	public void setup() throws ArgumentsException {
		Double[] defaultt = { -1.23, 42.46 };
		arrayArg = new OptionalDoubleArray('a', "array", defaultt);
	}

	@Test
	public void getValueNotSet() throws ArgumentsException {
		Double[] expected = { -1.23, 42.46 };
		Double[] actual = arrayArg.getValue();
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
		Double[] input = { 1.01, -42.23, 8.123 };
		arrayArg.setValue(input);

		Double[] expected = { 1.01, -42.23, 8.123 };
		Double[] actual = arrayArg.getValue();

		assertArrayEquals(expected, actual);
	}

	@Test(expected = ArgumentsException.class)
	public void setValueWrongArry1() throws ArgumentsException {
		String[] input = { "my", "new", "class]" };
		arrayArg.setValue(input);

	}
	
}
