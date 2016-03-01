package de.arguments.optional;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentsException;

public class OptionalCharArrayTest {

	private OptionalCharArray arrayArg;

	@Before
	public void setup() throws ArgumentsException {
		Character[] defaultt = { 'a', 'c' };
		arrayArg = new OptionalCharArray('a', "array", defaultt);
	}

	@Test
	public void getValueNotSet() throws ArgumentsException {
		Character[] expected = { 'a', 'c' };
		Character[] actual = arrayArg.getValue();
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
		Character[] input = { 'd', 'f', 'g' };
		arrayArg.setValue(input);

		Character[] expected = { 'd', 'f', 'g' };
		Character[] actual = arrayArg.getValue();

		assertArrayEquals(expected, actual);
	}

	@Test(expected = ArgumentsException.class)
	public void setValueWrongArry1() throws ArgumentsException {
		String[] input = { "my", "new", "class]" };
		arrayArg.setValue(input);

	}

}
