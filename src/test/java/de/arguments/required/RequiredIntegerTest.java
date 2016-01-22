package de.arguments.required;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentsException;
import de.arguments.required.RequiredInteger;
import static org.junit.Assert.*;

public class RequiredIntegerTest {
	
	private static RequiredInteger required;

	@Before
	public void setupTests(){
		required = new RequiredInteger('i',"alias");
		required.setDescription("Usage-Description");
	}
	
	@Test(expected=ArgumentsException.class)
	public void setValueWrongObject() throws ArgumentsException{
		required.setValue(new Object());
	}
	
	@Test
	public void setValueCorrect() throws ArgumentsException{
		required.setValue(42);
		assertEquals(new Integer(42), required.getValue());
		
		required.setValue(new Integer(42));
		assertEquals(new Integer(42), required.getValue());
	}
	
	@Test
	public void getAlias(){
		assertEquals("alias", required.getAlias());
	}
	
	@Test
	public void getAliasNot(){
		required = new RequiredInteger('i');
		assertEquals("", required.getAlias());
	}
	
	@Test
	public void getUsage(){
		assertEquals("Usage-Description", required.getDescription());
	}
	
	@Test
	public void setUsage(){
		required.setDescription("Test");
		assertEquals("Test", required.getDescription());
	}
	
	@Test(expected = ArgumentsException.class)
	public void getValueNotSet() throws ArgumentsException{
		required.getValue();
	}
	
	@Test
	public void checkArgNotSet(){
		assertFalse(required.valueSet());
	}
	
	@Test
	public void checkArgSet(){
		required.setValue(42);
		assertTrue(required.valueSet());
	}
	
	@Test
	public void checkIdentifier(){
		assertEquals('i',required.getId());
	}
	
}
