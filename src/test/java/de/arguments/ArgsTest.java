package de.arguments;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.arguments.exceptions.ArgumentException;
import de.arguments.optional.Flag;
import de.arguments.optional.OptionalBoolean;
import de.arguments.optional.OptionalChar;
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
		
		argsOptional = new Args();	
		argsOptional.add(new OptionalBoolean('b',"bool",true));
		argsOptional.add(new OptionalDouble('d',"double",3.3));
		argsOptional.add(new OptionalInteger('i',"integer",3));
		argsOptional.add(new OptionalString('s',"string","default"));
		argsOptional.add(new Flag('f',"flag"));
		argsOptional.add(new OptionalChar('c',"char",'a'));
		
		argsRequired = new Args();
		argsRequired.add(new OptionalBoolean('b',"oBool",true));
		argsRequired.add(new OptionalDouble('d',"oDouble",3.3));
		argsRequired.add(new OptionalInteger('i',"oInteger",3));
		argsRequired.add(new OptionalString('s',"oString","default"));
		argsRequired.add(new RequiredBoolean('q',"br"));
		argsRequired.add(new RequiredDouble('l',"dr"));
		argsRequired.add(new RequiredInteger('m',"ir"));
		argsRequired.add(new RequiredString('n',"sr"));
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchBooleanValueById() throws ArgumentException{
		argsOptional.getIntegerValue('x');
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchDoubleValueById() throws ArgumentException{
		argsOptional.getIntegerValue('x');
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchIntegerValueById() throws ArgumentException{
		argsOptional.getIntegerValue('x');
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchStringValueById() throws ArgumentException{
		argsOptional.getIntegerValue('x');
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchFlagValueById() throws ArgumentException{
		argsOptional.getFlagValue('x');
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchCharValueById() throws ArgumentException{
		argsOptional.getCharValue('x');
	}
	
	
	@Test(expected = ArgumentException.class)
	public void duplicatKeyById() throws ArgumentException{
		List<Arg> arg = new ArrayList<Arg>();
		arg.add(new OptionalBoolean('b',true));
		arg.add(new OptionalDouble('b',3.3));
		new Args(arg);
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchBooleanValueSearchByString() throws ArgumentException{
		argsOptional.getIntegerValue("notFound");
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchDoubleValueSearchByString() throws ArgumentException{
		argsOptional.getIntegerValue("notFound");
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchIntegerValueSearchByString() throws ArgumentException{
		argsOptional.getIntegerValue("notFound");
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchStringValueSearchByString() throws ArgumentException{
		argsOptional.getIntegerValue("notFound");
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchFlagValueSearchByString() throws ArgumentException{
		argsOptional.getFlagValue("notFound");
	}
	
	@Test(expected = ArgumentException.class)
	public void noSuchCharValueSearchByString() throws ArgumentException{
		argsOptional.getCharValue("notFound");
	}
	
	@Test(expected = ArgumentException.class)
	public void duplicatKeyByString() throws ArgumentException{
		List<Arg> arg = new ArrayList<Arg>();
		arg.add(new OptionalBoolean('x',"same",true));
		arg.add(new OptionalDouble('b',"same",3.3));
		new Args(arg);
	}
	
	
	@Test
	public void parseOptionalBoolean() throws ArgumentException {
		String[] args = {"-b","false"};
		argsOptional.parse(args);
		
		Boolean expected = false;
		Boolean actual = argsOptional.getBooleanValue('b');
		assertEquals(expected,actual);
	}
	
	@Test
	public void parseOptionalChar() throws ArgumentException {
		String[] args = {"-c","d"};
		argsOptional.parse(args);
		
		char expected = 'd';
		char actual = argsOptional.getCharValue('c');
		assertEquals(expected,actual);
	}
	
	@Test
	public void parseOptionalDouble() throws ArgumentException {
		String[] args = {"-d","0.999"};
		argsOptional.parse(args);
		
		Double expected = 0.999;
		Double actual = argsOptional.getDoubleValue('d');
		assertEquals(expected,actual);
	}
	
	@Test
	public void parseOptionalInteger() throws ArgumentException {
		String[] args = {"-i","999"};
		argsOptional.parse(args);
		
		Integer expected = 999;
		Integer actual = argsOptional.getIntegerValue('i');
		assertEquals(expected,actual);
	}
	
	@Test
	public void parseFlag() throws ArgumentException {
		String[] args = {"-f"};
		argsOptional.parse(args);
		
		Boolean actual = argsOptional.getFlagValue('f');
		assertTrue(actual);
	}
	
	@Test
	public void parseNoFlag() throws ArgumentException {
		String[] args = {"-d",".123"};
		argsOptional.parse(args);
		
		Boolean actual = argsOptional.getFlagValue('f');
		assertFalse(actual);
	}
	
	@Test
	public void parseOptionalString() throws ArgumentException {
		String[] args = {"-s","new Value"};
		argsOptional.parse(args);
		
		String expected = "new Value";
		String actual = argsOptional.getStringValue('s');
		assertEquals(expected,actual);
	}
	
	@Test
	public void parseStringWithBlanks() throws ArgumentException {
		String[] args = {"-s","\"new","value\""};
		argsOptional.parse(args);
		
		String expected = "new value";
		String actual = argsOptional.getStringValue('s');
		assertEquals(expected,actual);
	}
	
	@Test
	public void parseOptionalBooleanByAlias() throws ArgumentException {
		String[] args = {"--bool","false"};
		argsOptional.parse(args);
		
		Boolean expected = false;
		Boolean actual = argsOptional.getBooleanValue('b');
		assertEquals(expected,actual);
	}
	
	@Test
	public void parseOptionalCharByAlias() throws ArgumentException {
		String[] args = {"--char","d"};
		argsOptional.parse(args);
		
		char expected = 'd';
		char actual = argsOptional.getCharValue('c');
		assertEquals(expected,actual);
	}
	
	@Test
	public void parseOptionalDoubleByAlias() throws ArgumentException {
		String[] args = {"--double","0.999"};
		argsOptional.parse(args);
		
		Double expected = 0.999;
		Double actual = argsOptional.getDoubleValue('d');
		assertEquals(expected,actual);
	}
	
	@Test
	public void parseOptionalIntegerbyAlias() throws ArgumentException {
		String[] args = {"--integer","999"};
		argsOptional.parse(args);
		
		Integer expected = 999;
		Integer actual = argsOptional.getIntegerValue('i');
		assertEquals(expected,actual);
	}
	
	@Test
	public void parseFlagByAlias() throws ArgumentException {
		String[] args = {"--flag"};
		argsOptional.parse(args);
		
		Boolean actual = argsOptional.getFlagValue('f');
		assertTrue(actual);
	}
	
	@Test
	public void parseNoFlagByAlias() throws ArgumentException {
		String[] args = {"--double",".123"};
		argsOptional.parse(args);
		
		Boolean actual = argsOptional.getFlagValue('f');
		assertFalse(actual);
	}
	
	@Test
	public void parseOptionalStringByAlias() throws ArgumentException {
		String[] args = {"--string","new Value"};
		argsOptional.parse(args);
		
		String expected = "new Value";
		String actual = argsOptional.getStringValue('s');
		assertEquals(expected,actual);
	}
	
	@Test
	public void parseStringWithBlanksByAlias() throws ArgumentException {
		String[] args = {"-s","\"new","value\""};
		argsOptional.parse(args);
		
		String expected = "new value";
		String actual = argsOptional.getStringValue('s');
		assertEquals(expected,actual);
	}
	
	
	@Test
	public void parseRequiredArgumentsByAlias() throws ArgumentException {
		String[] args = {"--sr","new Value","--ir","78","--br","true","--dr","0.1234"};
		argsRequired.parse(args);
		
		Boolean expectedB = true;
		Boolean actualB = argsRequired.getBooleanValue("br");
		assertEquals(expectedB,actualB);
		
		Double expectedD = 0.1234;
		Double actualD = argsRequired.getDoubleValue("dr");
		assertEquals(expectedD,actualD);
		
		Integer expectedI = 78;
		Integer actualI = argsRequired.getIntegerValue("ir");
		assertEquals(expectedI,actualI);
		
		String expectedS = "new Value";
		String actualS = argsRequired.getStringValue("sr");
		assertEquals(expectedS,actualS);
		
	}
	
	@Test(expected = ArgumentException.class)
	public void parseRequiredMissing() throws ArgumentException {
		String[] args = {"--sr","new Value","--br","true","--dr","0.1234"};
		argsRequired.parse(args);		
	}
	
	@Test
	public void parseBooleanWithoutChangeOptional() throws ArgumentException {
		String[] args = {};
		argsOptional.parse(args);
		
		Boolean expectedB = true;
		Boolean actualB = argsOptional.getBooleanValue('b');
		assertEquals(expectedB,actualB);
		
	}
	
	@Test
	public void parseCharWithoutChangeOptional() throws ArgumentException {
		String[] args = {};
		argsOptional.parse(args);
		
		char expectedC = 'a';
		char actualC = argsOptional.getCharValue('c');
		assertEquals(expectedC,actualC);
		
	}
	
	
	@Test
	public void parseDoubleWithoutChangeOptional() throws ArgumentException {
		String[] args = {};
		argsOptional.parse(args);

		
		Double expectedD = 3.3;
		Double actualD = argsOptional.getDoubleValue('d');
		assertEquals(expectedD,actualD);
		
	}
	
	@Test
	public void parseIntegerWithoutChangeOptional() throws ArgumentException {
		String[] args = {};
		argsOptional.parse(args);
		
		Integer expectedI = 3;
		Integer actualI = argsOptional.getIntegerValue('i');
		assertEquals(expectedI,actualI);
		
	}
	
	@Test
	public void parseStringWithoutChangeOptional() throws ArgumentException {
		String[] args = {};
		argsOptional.parse(args);
		
		String expectedS = "default";
		String actualS = argsOptional.getStringValue('s');
		assertEquals(expectedS,actualS);
		
	}
	
	
	
}
