package de.arguments.optional;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentException;
import de.arguments.optional.OptionalDouble;
import static org.junit.Assert.*;

public class OptionalDoubleTest {
	
	private static OptionalDouble optional;

	@Before
	public void setupTests(){
		optional = new OptionalDouble('i',"alias",42.5);
		optional.setDescription("Usage-Description");
	}
	
	@Test(expected=ArgumentException.class)
	public void setValueWrongObject() throws ArgumentException{
		optional.setValue(new Object());
	}
	
	@Test
	public void setValueCorrect() throws ArgumentException {
		optional.setValue(42.0);
		assertEquals(new Double(42.0), optional.getValue());
		
		optional.setValue(new Double(42.0));
		assertEquals(new Double(42.0), optional.getValue());
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
		assertEquals(new Double(42.5), optional.getValue());
	}
	
	@Test
	public void getValueSet() throws ArgumentException{
		optional.setValue(42.5);
		assertEquals(new Double(42.5), optional.getValue());
	}
	
	@Test
	public void checkIdentifier(){
		assertEquals('i',optional.getId());
	}
	
	
}
