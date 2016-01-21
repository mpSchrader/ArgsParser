package de.arguments.required;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import de.arguments.array.*;
import de.arguments.exceptions.ArgumentException;

public class RequiredStringArrayTest {

	private RequiredStringArray arrayArg;

	@Before
	public void setup() throws ArgumentException{
		arrayArg = new RequiredStringArray('a',"array");
	}
	
	@Test(expected = ArgumentException.class)
	public void getValueNotSet() throws ArgumentException{
		arrayArg.getValue();
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
