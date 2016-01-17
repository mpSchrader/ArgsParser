package de.arguments.optional;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentException;
import de.arguments.optional.OptionalBoolean;
import static org.junit.Assert.*;

public class OptionalBooleanTest {
	
	private static OptionalBoolean optional;

	@Before
	public void setupTests(){
		optional = new OptionalBoolean('i',"alias",false);
		optional.setDescription("Usage-Description");
	}
	
	@Test(expected=ArgumentException.class)
	public void setValueWrongObject() throws ArgumentException{
		optional.setValue(new Object());
	}
	
	@Test
	public void setValueCorrect() throws ArgumentException {
		optional.setValue(true);
		assertEquals(new Boolean(true), optional.getValue());
		
		optional.setValue(new Boolean(false));
		assertEquals(false, optional.getValue());
	}
	
	@Test
	public void getAlias(){
		assertEquals("alias", optional.getAlias());
	}
	
	@Test
	public void getUsage(){
		assertEquals("Usage-Description", optional.getDescription());
	}
	
	@Test
	public void setUsage(){
		optional.setDescription("Test");
		assertEquals("Test", optional.getDescription());
	}
	
	@Test
	public void getValueNotSet(){
		assertFalse(optional.getValue());
	}
	
	@Test
	public void getValueSet() throws ArgumentException{
		optional.setValue(true);
		assertTrue(optional.getValue());
	}
	
	@Test
	public void checkIdentifier(){
		assertEquals('i',optional.getId());
	}
	
	
}
