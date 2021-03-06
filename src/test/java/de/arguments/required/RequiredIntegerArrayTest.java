package de.arguments.required;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentsException;

public class RequiredIntegerArrayTest {

	private RequiredDoubleArray arrayArg;

	@Before
	public void setup() throws ArgumentsException{
		arrayArg = new RequiredDoubleArray('a',"array");
	}
	
	@Test(expected = ArgumentsException.class)
	public void getValueNotSet() throws ArgumentsException{
		arrayArg.getValue();
	}
	
	@Test
	public void getID() throws ArgumentsException{
		char expected = 'a';
		char actual = arrayArg.getId();
		assertEquals(expected,actual);
	}
	
	@Test
	public void getAlias() throws ArgumentsException{
		String expected = "array";
		String actual = arrayArg.getAlias();
		assertEquals(expected,actual);
	}
	
	@Test
	public void setValue() throws ArgumentsException{
		Double[] input = {42.0, 48.34};
		arrayArg.setValue(input);
		
		Double[] expected = {42.0, 48.34};
		Double[] actual = arrayArg.getValue();

		assertArrayEquals(expected,actual);
	}
	
	@Test(expected = ArgumentsException.class)
	public void setValueWrongArry() throws ArgumentsException{
		String[] input = {"[my","new","class"};
		arrayArg.setValue((Object)input);
	}
	
}
