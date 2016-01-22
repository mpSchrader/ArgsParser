package de.arguments.required;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentsException;

public class RequiredStringArrayTest {

	private RequiredStringArray arrayArg;

	@Before
	public void setup() throws ArgumentsException{
		arrayArg = new RequiredStringArray('a',"array");
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
		String[] input = {"[my","new","class]"};
		arrayArg.setValue(input);
		String[] expected = {"my","new","class"};
		String[] actual = arrayArg.getValue();

		assertArrayEquals(expected,actual);
	}
	
	@Test
	public void setValueStringWithBlanks() throws ArgumentsException{
		String[] input = {"[\"my","new\"","class]"};
		arrayArg.setValue(input);
		String[] expected = {"my new","class"};
		String[] actual = arrayArg.getValue();
		System.out.println(Arrays.toString(actual));
		assertArrayEquals(expected,actual);
	}
	
	@Test(expected = ArgumentsException.class)
	public void setValueWrongArry1() throws ArgumentsException{
		String[] input = {"my","new","class]"};
		arrayArg.setValue(input);
		String[] expected = {"my","new","class"};
		String[] actual = arrayArg.getValue();

		assertArrayEquals(expected,actual);
	}
	@Test(expected = ArgumentsException.class)
	public void setValueWrongArry2() throws ArgumentsException{
		String[] input = {"[my","new","class"};
		arrayArg.setValue(input);
		String[] expected = {"my","new","class"};
		String[] actual = arrayArg.getValue();

		assertArrayEquals(expected,actual);
	}
}
