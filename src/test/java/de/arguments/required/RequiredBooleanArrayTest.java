package de.arguments.required;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentsException;

public class RequiredBooleanArrayTest {

	private RequiredBooleanArray arrayArg;

	@Before
	public void setup() throws ArgumentsException {
		arrayArg = new RequiredBooleanArray('a', "array");
	}

	@Test(expected = ArgumentsException.class)
	public void getValueNotSet() throws ArgumentsException {
		arrayArg.getValue();
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
		Boolean[] input = { true, true };
		arrayArg.setValue(input);
		Boolean[] expected = { true, true };
		Boolean[] actual = arrayArg.getValue();

		assertArrayEquals(expected, actual);
	}

	@Test(expected = ArgumentsException.class)
	public void setValueWrongArry() throws ArgumentsException {
		String[] input = { "[my", "new", "class" };
		arrayArg.setValue((Object) input);
	}

}
