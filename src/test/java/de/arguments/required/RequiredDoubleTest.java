package de.arguments.required;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentException;
import de.arguments.required.RequiredDouble;
import static org.junit.Assert.*;

public class RequiredDoubleTest {
	
	private static RequiredDouble required;

	@Before
	public void setupTests(){
		required = new RequiredDouble("i","Usage-Description");
	}
	
	@Test(expected=ArgumentException.class)
	public void setValueWrongObject() throws ArgumentException{
		required.setValue(new Object());
	}
	
	@Test
	public void setValueCorrect() throws ArgumentException {
		required.setValue(42.5);
		assertEquals(new Double(42.5), required.getValue());
		
		required.setValue(new Double(42.2));
		assertEquals(new Double(42.2), required.getValue());
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
	public void checkArgSet() throws ArgumentException{
		required.setValue(42.2);
		assertTrue(required.checkIfValueIsSet());
	}
	
	@Test
	public void checkIdentifier(){
		assertEquals("-i",required.getKey());
	}
	
	@Test
	public void checkIdentifierWithMinus(){
		required = new RequiredDouble("-i","Usage-Description");
		assertEquals("-i",required.getKey());
	}
	
}
