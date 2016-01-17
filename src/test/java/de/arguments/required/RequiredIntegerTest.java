package de.arguments.required;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentException;
import de.arguments.required.RequiredInteger;
import static org.junit.Assert.*;

public class RequiredIntegerTest {
	
	private static RequiredInteger required;

	@Before
	public void setupTests(){
		required = new RequiredInteger('i',"alias");
		required.setDescription("Usage-Description");
	}
	
	@Test(expected=ArgumentException.class)
	public void setValueWrongObject() throws ArgumentException{
		required.setValue(new Object());
	}
	
	@Test
	public void setValueCorrect() throws ArgumentException{
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
	
	@Test(expected = ArgumentException.class)
	public void getValueNotSet() throws ArgumentException{
		required.getValue();
	}
	
	@Test
	public void checkArgNotSet(){
		assertFalse(required.checkIfValueIsSet());
	}
	
	@Test
	public void checkArgSet(){
		required.setValue(42);
		assertTrue(required.checkIfValueIsSet());
	}
	
	@Test
	public void checkIdentifier(){
		assertEquals('i',required.getId());
	}
	
}
