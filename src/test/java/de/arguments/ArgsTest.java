package de.arguments;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentException;
import de.arguments.optional.Flag;
import de.arguments.optional.OptionalBoolean;
import de.arguments.optional.OptionalDouble;
import de.arguments.optional.OptionalInteger;
import de.arguments.optional.OptionalString;
import de.arguments.required.RequiredBoolean;
import de.arguments.required.RequiredDouble;
import de.arguments.required.RequiredInteger;
import de.arguments.required.RequiredString;

public class ArgsTest {

	private static Args argsOptional;
	private static Args argsRequired;

	@Before
	public void setup() throws ArgumentException {
		
		List<Arg> arg = new ArrayList<Arg>();
		arg.add(new OptionalBoolean("b",true));
		arg.add(new OptionalDouble("d",3.3));
		arg.add(new OptionalInteger("i",3));
		arg.add(new OptionalString("s","default"));
		arg.add(new Flag("f"));
		argsOptional = new Args(arg);
		
		arg = new ArrayList<Arg>();
		arg.add(new OptionalBoolean("b",true));
		arg.add(new OptionalDouble("d",3.3));
		arg.add(new OptionalInteger("i",3));
		arg.add(new OptionalString("s","default"));
		arg.add(new RequiredBoolean("br"));
		arg.add(new RequiredDouble("dr"));
		arg.add(new RequiredInteger("ir"));
		arg.add(new RequiredString("sr"));
		argsRequired = new Args(arg);
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchBooleanValue() throws ArgumentException{
		argsOptional.getIntegerValue("n");
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchDoubleValue() throws ArgumentException{
		argsOptional.getIntegerValue("n");
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchIntegerValue() throws ArgumentException{
		argsOptional.getIntegerValue("n");
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchStringValue() throws ArgumentException{
		argsOptional.getIntegerValue("n");
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchFlagValue() throws ArgumentException{
		argsOptional.getFlagValue("n");
	}
	
	@Test(expected = ArgumentException.class)
	public void duplicatKey() throws ArgumentException{
		List<Arg> arg = new ArrayList<Arg>();
		arg.add(new OptionalBoolean("b",true));
		arg.add(new OptionalDouble("b",3.3));
		new Args(arg);
	}
	
	@Test
	public void parseOptionalBoolean() throws ArgumentException {
		String[] args = {"-b","false"};
		argsOptional.parseArgs(args);
		
		Boolean expected = false;
		Boolean actual = argsOptional.getBooleanValue("-b");
		assertEquals(expected,actual);
	}
	
	@Test
	public void parseOptionalDouble() throws ArgumentException {
		String[] args = {"-d","0.999"};
		argsOptional.parseArgs(args);
		
		Double expected = 0.999;
		Double actual = argsOptional.getDoubleValue("-d");
		assertEquals(expected,actual);
	}
	
	@Test
	public void parseOptionalInteger() throws ArgumentException {
		String[] args = {"-i","999"};
		argsOptional.parseArgs(args);
		
		Integer expected = 999;
		Integer actual = argsOptional.getIntegerValue("-i");
		assertEquals(expected,actual);
	}
	
	@Test
	public void parseFlag() throws ArgumentException {
		String[] args = {"-f"};
		argsOptional.parseArgs(args);
		
		Boolean actual = argsOptional.getFlagValue("-f");
		assertTrue(actual);
	}
	
	@Test
	public void parseNoFlag() throws ArgumentException {
		String[] args = {"-d",".123"};
		argsOptional.parseArgs(args);
		
		Boolean actual = argsOptional.getFlagValue("-f");
		assertFalse(actual);
	}
	
	@Test
	public void parseOptionalString() throws ArgumentException {
		String[] args = {"-s","new Value"};
		argsOptional.parseArgs(args);
		
		String expected = "new Value";
		String actual = argsOptional.getStringValue("-s");
		assertEquals(expected,actual);
	}
	
	@Test
	public void parseStringWithBlanks() throws ArgumentException {
		String[] args = {"-s","\"new","value\""};
		argsOptional.parseArgs(args);
		
		String expected = "new value";
		String actual = argsOptional.getStringValue("-s");
		assertEquals(expected,actual);
	}
	
	@Test
	public void parseRequiredArguments() throws ArgumentException {
		String[] args = {"-sr","new Value","-ir","78","-br","true","-dr","0.1234"};
		argsRequired.parseArgs(args);
		
		Boolean expectedB = true;
		Boolean actualB = argsRequired.getBooleanValue("-br");
		assertEquals(expectedB,actualB);
		
		Double expectedD = 0.1234;
		Double actualD = argsRequired.getDoubleValue("-dr");
		assertEquals(expectedD,actualD);
		
		Integer expectedI = 78;
		Integer actualI = argsRequired.getIntegerValue("-ir");
		assertEquals(expectedI,actualI);
		
		String expectedS = "new Value";
		String actualS = argsRequired.getStringValue("-sr");
		assertEquals(expectedS,actualS);
		
	}
	
	@Test(expected = ArgumentException.class)
	public void parseRequiredMissing() throws ArgumentException {
		String[] args = {"-sr","new Value","-br","true","-dr","0.1234"};
		argsRequired.parseArgs(args);		
	}
	
	@Test
	public void parseWithoutChangeOptional() throws ArgumentException {
		String[] args = {};
		argsOptional.parseArgs(args);
		
		Boolean expectedB = true;
		Boolean actualB = argsOptional.getBooleanValue("-b");
		assertEquals(expectedB,actualB);
		
		Double expectedD = 3.3;
		Double actualD = argsOptional.getDoubleValue("-d");
		assertEquals(expectedD,actualD);
		
		Integer expectedI = 3;
		Integer actualI = argsOptional.getIntegerValue("-i");
		assertEquals(expectedI,actualI);
		
		String expectedS = "default";
		String actualS = argsOptional.getStringValue("-s");
		assertEquals(expectedS,actualS);
		
	}
	
	
	
}
