package de.arguments;

import static org.junit.Assert.*;
import org.junit.Test;

import de.arguments.exceptions.ArgumentsException;
import de.arguments.exceptions.JSONException;

public class ExceptionTest {

	@Test
	public void getMessageArgumentsException(){
		try{
			
			throw new ArgumentsException("Test Message");
			
		} catch (ArgumentsException e){
			assertEquals("Test Message", e.getMessage());
		}
	}

	@Test
	public void getMessageJSONException(){
		try{
			
			throw new JSONException("Test Message");
			
		} catch (JSONException e){
			assertEquals("Test Message", e.getMessage());
		}
	}
	
}
