package de.arguments.optional;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentException;
import de.arguments.optional.OptionalInteger;
import static org.junit.Assert.*;

public class OptionalIntegerTest {
	
	private static OptionalInteger optional;

	@Before
	public void setupTests(){
		optional = new OptionalInteger("i",42,"Usage-Description");
	}
	
	@Test(expected=ArgumentException.class)
	public void setValueWrongObject() throws ArgumentException{
		optional.setValue(new Object());
	}
	@Test
	public void setValueCorrect() throws ArgumentException {
		optional.setValue(45);
		assertEquals(new Integer(45), optional.getValue());
		
		optional.setValue(new Integer(45));
		assertEquals(new Integer(45), optional.getValue());
	}
	
	@Test
	public void getUsage(){
		assertEquals("Usage-Description", optional.getUsage());
	}
	
	@Test
	public void setUsage(){
		optional.setDescription("Test");
		assertEquals("Test", optional.getUsage());
	}
	
	@Test
	public void getValueNotSet(){
		assertEquals(new Integer(42), optional.getValue());
	}
	
	@Test
	public void getValueSet() throws ArgumentException{
		optional.setValue(45);
		assertEquals(new Integer(45), optional.getValue());
	}
	
	@Test
	public void checkIdentifier(){
		assertEquals("-i",optional.getKey());
	}
	
	@Test
	public void checkIdentifierWithMinus(){
		optional = new OptionalInteger("-i",42,"Usage-Description");
		assertEquals("-i",optional.getKey());
	}
	
}
