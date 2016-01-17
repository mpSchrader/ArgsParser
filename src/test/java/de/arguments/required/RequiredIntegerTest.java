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
		required = new RequiredInteger("i","Usage-Description");
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
	public void getUsage(){
		assertEquals("Usage-Description", required.getUsage());
	}
	
	@Test
	public void setUsage(){
		required.setDescription("Test");
		assertEquals("Test", required.getUsage());
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
		assertEquals("-i",required.getKey());
	}
	
	@Test
	public void checkIdentifierWithMinus(){
		required = new RequiredInteger("-i","Usage-Description");
		assertEquals("-i",required.getKey());
	}
	
}
