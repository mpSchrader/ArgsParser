package de.arguments.required;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentsException;
import static org.junit.Assert.*;

public class RequiredCharTest {
	
	private static RequiredChar required;

	@Before
	public void setupTests(){
		required = new RequiredChar('i',"alias");
		required.setDescription("Usage-Description");
	}
	
	@Test(expected=ArgumentsException.class)
	public void setValueWrongObject() throws ArgumentsException{
		required.setValue(new Object());
	}
	
	@Test
	public void setValueCorrect() throws ArgumentsException {
		required.setValue('a');
		assertEquals(new Character('a'), required.getValue());
		
		required.setValue(new Character('r'));
		assertEquals(new Character('r'), required.getValue());
	}
	
	@Test
	public void getAlias(){
		assertEquals("alias", required.getAlias());
	}
	
	@Test
	public void getAliasNot(){
		required = new RequiredChar('i');
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
		required.setValue('a');
		assertTrue(required.valueSet());
	}
	
	@Test
	public void checkIdentifier(){
		assertEquals('i',required.getId());
	}
	
}
