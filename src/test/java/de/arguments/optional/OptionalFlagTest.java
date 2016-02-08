package de.arguments.optional;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentsException;
import static org.junit.Assert.*;

public class OptionalFlagTest {
	
	private static Flag optional;

	@Before
	public void setupTests(){
		optional = new Flag('i',"alias");
		optional.setDescription("Usage-Description");
	}
	
	@Test(expected=ArgumentsException.class)
	public void setValueWrongObject() throws ArgumentsException{
		optional.setValue(new Object());
	}
	
	@Test
	public void setValueCorrect() throws ArgumentsException {
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
	public void getValueNotSet() throws ArgumentsException{
		assertFalse(optional.getValue());
	}
	
	@Test
	public void getValueSet() throws ArgumentsException{
		optional.setValue(true);
		assertTrue(optional.getValue());
	}
	
	@Test
	public void checkIdentifier(){
		assertEquals('i',optional.getId());
	}
	
	
}
