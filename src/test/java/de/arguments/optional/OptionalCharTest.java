package de.arguments.optional;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentsException;
import static org.junit.Assert.*;

public class OptionalCharTest {
	
	private static OptionalChar optional;

	@Before
	public void setupTests(){
		optional = new OptionalChar('i',"alias",'a');
		optional.setDescription("Usage-Description");
	}
	
	@Test(expected=ArgumentsException.class)
	public void setValueWrongObject() throws ArgumentsException{
		optional.setValue(new Object());
	}
	
	@Test
	public void setValueCorrect() throws ArgumentsException {
		optional.setValue('a');
		assertEquals(new Character('a'), optional.getValue());
		
		optional.setValue('x');
		assertEquals(new Character('x'), optional.getValue());
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
		assertEquals(new Character('a'),optional.getValue());
	}
	
	@Test
	public void getValueSet() throws ArgumentsException{
		optional.setValue('x');
		assertEquals(new Character('x'),optional.getValue());
	}
	
	@Test
	public void checkIdentifier(){
		assertEquals('i',optional.getId());
	}
	
	
}
