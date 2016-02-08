package de.arguments.optional;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentsException;

public class OptionalStringArrayTest {

	private OptionalStringArray arrayArg;

	@Before
	public void setup() throws ArgumentsException{
		String[] defaultt = {"Hey","ho"};
		arrayArg = new OptionalStringArray('a',"array",defaultt);
	}
	
	@Test
	public void getValueNotSet() throws ArgumentsException{
		String[] expected = {"Hey","ho"};
		String [] actual = arrayArg.getValue();
		assertArrayEquals(expected, actual);
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
		
		String[] expected = {"[my","new","class]"};
		String[] actual = arrayArg.getValue();

		assertArrayEquals(expected,actual);
	}
	
	@Test(expected = ArgumentsException.class)
	public void setValueWrongType() throws ArgumentsException{
		Object input = new Object();
		arrayArg.setValue(input);
	}
	
	
	@Test
	public void setValueStringWithBlanks() throws ArgumentsException{
		String[] input = {"\"my","new\"","class"};
		arrayArg.setValue(input);
		
		String[] expected = {"\"my" ,"new\"","class"};
		String[] actual = arrayArg.getValue();

		assertArrayEquals(expected,actual);
	}
	
	@Test
	public void setValueWrongArry1() throws ArgumentsException{
		String[] input = {"my","new","class]"};
		arrayArg.setValue(input);
		
		String[] expected = {"my","new","class]"};
		String[] actual = arrayArg.getValue();

		assertArrayEquals(expected,actual);
	}
	@Test
	public void setValueWrongArry2() throws ArgumentsException{
		String[] input = {"[my","new","class"};
		arrayArg.setValue(input);
		
		String[] expected = {"[my","new","class"};
		String[] actual = arrayArg.getValue();

		assertArrayEquals(expected,actual);
	}
}
