package de.arguments.optional;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentException;
import de.arguments.optional.OptionalString;
import static org.junit.Assert.*;

public class OptionalStringTest {
	
	private static OptionalString optional;

	@Before
	public void setupTests(){
		optional = new OptionalString('i',"alias","default sentece");
		optional.setDescription("Usage-Description");
	}
	
	@Test(expected=ArgumentException.class)
	public void setValueWrongObject() throws ArgumentException{
		optional.setValue(new Object());
	}
	
	@Test
	public void setValueCorrect() throws ArgumentException {
		optional.setValue("#raute");
		assertEquals("#raute", optional.getValue());
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
		assertEquals("default sentece", optional.getValue());
	}
	
	@Test
	public void checkIdentifier(){
		assertEquals('i',optional.getId());
	}
	
	
}
