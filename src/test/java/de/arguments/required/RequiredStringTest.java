package de.arguments.required;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentsException;
import de.arguments.required.RequiredString;
import static org.junit.Assert.*;

public class RequiredStringTest {
	
	private static RequiredString required;

	@Before
	public void setupTests(){
		required = new RequiredString('i',"alias");
		required.setDescription("Usage-Description");
	}
	
	@Test(expected=ArgumentsException.class)
	public void setValueWrongObject() throws ArgumentsException{
		required.setValue(new Object());
	}
	
	@Test
	public void setValueCorrect() throws ArgumentsException {
		required.setValue("42.5");
		assertEquals("42.5", required.getValue());
		
		required.setValue(new String("42.2"));
		assertEquals(new String("42.2"), required.getValue());
	}
	
	@Test
	public void getAlias(){
		assertEquals("alias", required.getAlias());
	}
	
	@Test
	public void getAliasNot(){
		required = new RequiredString('i');
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
	public void checkArgSet() throws ArgumentsException{
		required.setValue("42.2");
		assertTrue(required.valueSet());
	}
	
	@Test
	public void checkIdentifier(){
		assertEquals('i',required.getId());
	}
	
	
}
