package de.arguments.optional;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import de.arguments.array.*;
import de.arguments.exceptions.ArgumentException;

public class OptionalStringArrayTest {

	private OptionalStringArray arrayArg;

	@Before
	public void setup() throws ArgumentException{
		String[] defaultt = {"Hey","ho"};
		arrayArg = new OptionalStringArray('a',"array",defaultt);
	}
	
	@Test
	public void getValueNotSet() throws ArgumentException{
		String[] expected = {"Hey","ho"};
		String [] actual = arrayArg.getValue();
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void getID() throws ArgumentException{
		char expected = 'a';
		char actual = arrayArg.getId();
		assertEquals(expected,actual);
	}
	
	@Test
	public void getAlias() throws ArgumentException{
		String expected = "array";
		String actual = arrayArg.getAlias();
		assertEquals(expected,actual);
	}
	
	@Test
	public void setValue() throws ArgumentException{
		String[] input = {"[my","new","class]"};
		arrayArg.setValue(input);
		String[] expected = {"my","new","class"};
		String[] actual = arrayArg.getValue();

		assertArrayEquals(expected,actual);
	}
	
	@Test
	public void setValueStringWithBlanks() throws ArgumentException{
		String[] input = {"[\"my","new\"","class]"};
		arrayArg.setValue(input);
		String[] expected = {"my new","class"};
		String[] actual = arrayArg.getValue();
		System.out.println(Arrays.toString(actual));
		assertArrayEquals(expected,actual);
	}
	
	@Test(expected = ArgumentException.class)
	public void setValueWrongArry1() throws ArgumentException{
		String[] input = {"my","new","class]"};
		arrayArg.setValue(input);
		String[] expected = {"my","new","class"};
		String[] actual = arrayArg.getValue();

		assertArrayEquals(expected,actual);
	}
	@Test(expected = ArgumentException.class)
	public void setValueWrongArry2() throws ArgumentException{
		String[] input = {"[my","new","class"};
		arrayArg.setValue(input);
		String[] expected = {"my","new","class"};
		String[] actual = arrayArg.getValue();

		assertArrayEquals(expected,actual);
	}
}
