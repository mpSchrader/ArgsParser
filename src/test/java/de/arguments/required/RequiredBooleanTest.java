package de.arguments.required;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentException;
import de.arguments.required.RequiredBoolean;
import static org.junit.Assert.*;

public class RequiredBooleanTest {
	
	private static RequiredBoolean required;

	@Before
	public void setupTests(){
		required = new RequiredBoolean('i',"alias");
		required.setDescription("Usage-Description");
	}
	
	@Test(expected=ArgumentException.class)
	public void setValueWrongObject() throws ArgumentException{
		required.setValue(new Object());
	}
	
	@Test
	public void setValueCorrect() throws ArgumentException {
		required.setValue(true);
		assertEquals(new Boolean(true), required.getValue());
		
		required.setValue(new Boolean(false));
		assertEquals(false, required.getValue());
	}
	
	@Test
	public void getAlias(){
		assertEquals("alias", required.getAlias());
	}
	
	@Test
	public void getAliasNot(){
		required = new RequiredBoolean('i');
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
		assertFalse(required.valueSet());
	}
	
	@Test
	public void checkArgSet() throws ArgumentException{
		required.setValue(true);
		assertTrue(required.valueSet());
	}
	
	@Test
	public void checkIdentifier(){
		assertEquals('i',required.getId());
	}
	
}
