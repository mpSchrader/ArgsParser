package de.arguments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.arguments.exceptions.ArgumentsException;
import de.arguments.optional.*;
import de.arguments.required.*;

/**
 * Provides the functionality to the outside.
 * 
 * @author Max-Philipp Schrader
 *
 */
public class Args {

	private List<Arg> args = new ArrayList<Arg>();
	private String usage = "";

	/**
	 * 
	 */
	public Args() {
	}

	/**
	 * 
	 * @param usage
	 */
	public Args(String usage) {
		this.usage = usage;
	}

	/**
	 * 
	 * @param args
	 * @throws ArgumentsException
	 */
	protected Args(List<Arg> args) throws ArgumentsException {
		this.args = args;
		Collections.sort(this.args);
		checkArgs();
	}

	/**
	 * 
	 * @param args
	 * @param usage
	 * @throws ArgumentsException
	 */
	protected Args(List<Arg> args, String usage) throws ArgumentsException {
		this(args);
		this.usage = usage;
	}

	/**
	 * Checks whether every ID and alias is unique.
	 * 
	 * @throws ArgumentsException
	 */
	private void checkArgs() throws ArgumentsException {
		checkIDs();
		checkAlias();
	}

	/**
	 * Checks whether every ID is unique.
	 * 
	 * @throws ArgumentsException
	 */
	private void checkIDs() throws ArgumentsException {
		String keys = "";

		for (Arg arg : args) {

			String key = " " + arg.getId() + " ";
			if (keys.contains(key)) {
				throw new ArgumentsException("Duplicate Key: " + key);
			}
			keys += key;

		}
	}

	/**
	 * Checks whether every alias is unique.
	 * 
	 * @throws ArgumentsException
	 */
	private void checkAlias() throws ArgumentsException {
		String keys = "";

		for (Arg arg : args) {

			if (arg.getAlias().equals("")) {
				continue;
			}

			String key = " " + arg.getAlias() + " ";
			if (keys.contains(key)) {
				throw new ArgumentsException("Duplicate Key: " + key);
			}
			keys += key;

		}
	}

	/**
	 * Checks whether the ID and alias already taken unique.
	 * 
	 * @throws ArgumentsException
	 */
	private void checkArg(Arg arg) throws ArgumentsException {
		checkID(arg.getId());
		checkAlias(arg.getAlias());
	}

	/**
	 * Checks if the given id is already taken.
	 * 
	 * @param id
	 * @throws ArgumentsException
	 */
	private void checkID(char id) throws ArgumentsException {

		for (Arg arg : args) {
			if (id == arg.getId())
				throw new ArgumentsException("Id '" + id + "' is already taken.");
		}

	}

	/**
	 * Checks if the given alias is already taken.
	 * 
	 * @param alias
	 * @throws ArgumentsException
	 */
	private void checkAlias(String alias) throws ArgumentsException {
		for (Arg arg : args) {
			if (alias.equals(arg.alias))
				throw new ArgumentsException("alias '" + alias + "' is already taken.");
		}
	}

	/**
	 * Adds a given Arg.<br>
	 * <br>
	 * In case the id or alias of this Arg is already taken, an exception will
	 * be thrown.
	 * 
	 * @param arg
	 * @throws ArgumentsException
	 */
	public void add(Arg arg) throws ArgumentsException {
		checkArg(arg);
		this.args.add(arg);
	}

	/**
	 * Parses the passed args.<br>
	 * <br>
	 * In case not all required arguments could be set, an exception will be
	 * thrown.
	 * 
	 * @param args
	 * @throws ArgumentsException
	 */
	public void parse(String[] args) throws ArgumentsException {
		/* Delegating the parsing to the parser class */
		Parser parser = new Parser(this.args, args);
		parser.parse();
	}

	/**
	 * Searches for a specific Arg by Id. <br>
	 * <br>
	 * In case the id does not exist an exception will be thrown
	 * 
	 * @param id
	 * @return Arg
	 * @throws ArgumentsException
	 */
	private Arg findArg(char id) throws ArgumentsException {
		for (Arg arg : args) {
			if (arg.getId() == id) {
				return arg;
			}
		}
		throw new ArgumentsException("No such argument! id: " + Character.toString(id));
	}

	/**
	 * Searches a specific Arg by his alias.<br>
	 * <br>
	 * In case the alias does not exist an exception will be thrown.
	 * 
	 * @param alias
	 * @return Arg
	 * @throws ArgumentsException
	 */
	private Arg findArg(String alias) throws ArgumentsException {

		for (Arg arg : args) {
			if (arg.getAlias().equals(alias)) {
				return arg;
			}
		}
		throw new ArgumentsException("No such argument! alias: " + alias);
	}

	/**
	 * Returns the boolean value of an Arg.<br>
	 * <br>
	 * In case the id does not exist or the Arg is of another type, an exception
	 * will be thrown.
	 * 
	 * @param id
	 * @return value
	 * @throws ArgumentsException
	 */
	public Boolean getBooleanValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getBoolean(arg);
	}

	/**
	 * Returns the boolean value of an Arg.<br>
	 * <br>
	 * In case the alias does not exist or the Arg is of another type, an
	 * exception will be thrown.
	 * 
	 * @param alias
	 * @return value
	 * @throws ArgumentsException
	 */
	public Boolean getBooleanValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getBoolean(arg);
	}

	/**
	 * Returns the boolean value of an Arg.<br>
	 * <br>
	 * In case the Arg is of another type, an exception will be thrown.
	 * 
	 * @param arg
	 * @return value
	 * @throws ArgumentsException
	 */
	private Boolean getBoolean(Arg arg) throws ArgumentsException {
		if (TypeChecker.isBoolean(arg)) {

			return (Boolean) arg.getValue();

		} else {
			throw new ArgumentsException("No such Boolean attribute: (key = " + arg.alias + ")");
		}
	}

	/**
	 * Returns the char value of an Arg.<br>
	 * <br>
	 * In case the id does not exist or the Arg is of another type, an exception
	 * will be thrown.
	 * 
	 * @param id
	 * @return value
	 * @throws ArgumentsException
	 */
	public char getCharValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getChar(arg);
	}

	/**
	 * Returns the char value of an Arg.<br>
	 * <br>
	 * In case the alias does not exist or the Arg is of another type, an
	 * exception will be thrown.
	 * 
	 * @param alias
	 * @return value
	 * @throws ArgumentsException
	 */
	public char getCharValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getChar(arg);
	}

	/**
	 * Returns the char value of an Arg.<br>
	 * <br>
	 * In case the Arg is of another type, an exception will be thrown.
	 * 
	 * @param arg
	 * @return value
	 * @throws ArgumentsException
	 */
	private char getChar(Arg arg) throws ArgumentsException {
		if (TypeChecker.isChar(arg)) {

			return (Character) arg.getValue();

		} else {
			throw new ArgumentsException("No such Boolean attribute: (key = " + arg.alias + ")");
		}
	}

	/**
	 * Returns the double value of an Arg.<br>
	 * <br>
	 * In case the id does not exist or the Arg is of another type, an exception
	 * will be thrown.
	 * 
	 * @param id
	 * @return value
	 * @throws ArgumentsException
	 */
	public Double getDoubleValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getDouble(arg);
	}

	/**
	 * Returns the double value of an Arg.<br>
	 * <br>
	 * In case the alias does not exist or the Arg is of another type, an
	 * exception will be thrown.
	 * 
	 * @param alias
	 * @return value
	 * @throws ArgumentsException
	 */
	public Double getDoubleValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getDouble(arg);
	}

	/**
	 * Returns the double value of an Arg.<br>
	 * <br>
	 * In case the Arg is of another type, an exception will be thrown.
	 * 
	 * @param arg
	 * @return value
	 * @throws ArgumentsException
	 */
	private Double getDouble(Arg arg) throws ArgumentsException {
		if (TypeChecker.isDouble(arg)) {

			return (Double) arg.getValue();

		} else {
			throw new ArgumentsException("No such Double attribute: (key = " + arg.alias + ")");
		}
	}

	/**
	 * Returns the integer value of an Arg.<br>
	 * <br>
	 * In case the id does not exist or the Arg is of another type, an exception
	 * will be thrown.
	 * 
	 * @param id
	 * @return value
	 * @throws ArgumentsException
	 */
	public Integer getIntegerValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getInteger(arg);
	}

	/**
	 * Returns the integer value of an Arg.<br>
	 * <br>
	 * In case the alias does not exist or the Arg is of another type, an
	 * exception will be thrown.
	 * 
	 * @param alias
	 * @return value
	 * @throws ArgumentsException
	 */
	public Integer getIntegerValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getInteger(arg);
	}

	/**
	 * Returns the integer value of an Arg.<br>
	 * <br>
	 * In case the Arg is of another type, an exception will be thrown.
	 * 
	 * @param arg
	 * @return value
	 * @throws ArgumentsException
	 */
	private Integer getInteger(Arg arg) throws ArgumentsException {
		if (TypeChecker.isInteger(arg)) {

			return (Integer) arg.getValue();

		} else {
			throw new ArgumentsException("No such Integer attribute: (key = " + arg.alias + ")");
		}
	}

	/**
	 * Returns the string value of an Arg.<br>
	 * <br>
	 * In case the id does not exist or the Arg is of another type, an exception
	 * will be thrown.
	 * 
	 * @param id
	 * @return value
	 * @throws ArgumentsException
	 */
	public String getStringValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getStringValue(arg);
	}

	/**
	 * Returns the string value of an Arg.<br>
	 * <br>
	 * In case the alias does not exist or the Arg is of another type, an
	 * exception will be thrown.
	 * 
	 * @param alias
	 * @return value
	 * @throws ArgumentsException
	 */
	public String getStringValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getStringValue(arg);
	}

	/**
	 * Returns the string value of an Arg.<br>
	 * <br>
	 * In case the Arg is of another type, an exception will be thrown.
	 * 
	 * @param arg
	 * @return value
	 * @throws ArgumentsException
	 */
	private String getStringValue(Arg arg) throws ArgumentsException {
		if (TypeChecker.isString(arg)) {

			return (String) arg.getValue();

		} else {
			throw new ArgumentsException("No such String attribute: (key = " + arg.alias + ")");
		}
	}

	/**
	 * Returns the string array value of an Arg.<br>
	 * <br>
	 * In case the id does not exist or the Arg is of another type, an exception
	 * will be thrown.
	 * 
	 * @param id
	 * @return value
	 * @throws ArgumentsException
	 */
	public String[] getStringArrayValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getStringArray(arg);
	}

	/**
	 * Returns the string array value of an Arg.<br>
	 * <br>
	 * In case the alias does not exist or the Arg is of another type, an
	 * exception will be thrown.
	 * 
	 * @param alias
	 * @return value
	 * @throws ArgumentsException
	 */
	public String[] getStringArrayValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getStringArray(arg);
	}

	/**
	 * Returns the string array value of an Arg.<br>
	 * <br>
	 * In case the Arg is of another type, an exception will be thrown.
	 * 
	 * @param idarg
	 * @return value
	 * @throws ArgumentsException
	 */
	private String[] getStringArray(Arg arg) throws ArgumentsException {
		if (TypeChecker.isStringArray(arg)) {

			return (String[]) arg.getValue();

		} else {
			throw new ArgumentsException("No such StringArray attribute: (key = " + arg.alias + ")");
		}
	}

	/**
	 * Returns the boolean array value of an Arg.<br>
	 * <br>
	 * In case the id does not exist or the Arg is of another type, an exception
	 * will be thrown.
	 * 
	 * @param id
	 * @return value
	 * @throws ArgumentsException
	 */
	public Boolean[] getBooleanArrayValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getBooleanArray(arg);
	}

	/**
	 * Returns the boolean array value of an Arg.<br>
	 * <br>
	 * In case the alias does not exist or the Arg is of another type, an
	 * exception will be thrown.
	 * 
	 * @param alias
	 * @return value
	 * @throws ArgumentsException
	 */
	public Boolean[] getBooleanArrayValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getBooleanArray(arg);
	}

	/**
	 * Returns the boolean array value of an Arg.<br>
	 * <br>
	 * In case the Arg is of another type, an exception will be thrown.
	 * 
	 * @param arg
	 * @return value
	 * @throws ArgumentsException
	 */
	private Boolean[] getBooleanArray(Arg arg) throws ArgumentsException {
		if (TypeChecker.isBooleanArray(arg)) {

			return (Boolean[]) arg.getValue();

		} else {
			throw new ArgumentsException("No such CharArray attribute: (key = " + arg.alias + ")");
		}
	}

	/**
	 * Returns the char array value of an Arg.<br>
	 * <br>
	 * In case the id does not exist or the Arg is of another type, an exception
	 * will be thrown.
	 * 
	 * @param id
	 * @return value
	 * @throws ArgumentsException
	 */
	public Character[] getCharArrayValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getCharArray(arg);
	}

	/**
	 * Returns the char array value of an Arg.<br>
	 * <br>
	 * In case the alias does not exist or the Arg is of another type, an
	 * exception will be thrown.
	 * 
	 * @param alias
	 * @return value
	 * @throws ArgumentsException
	 */
	public Character[] getCharArrayValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getCharArray(arg);
	}

	/**
	 * Returns the char array value of an Arg.<br>
	 * <br>
	 * In case the Arg is of another type, an exception will be thrown.
	 * 
	 * @param arg
	 * @return value
	 * @throws ArgumentsException
	 */
	private Character[] getCharArray(Arg arg) throws ArgumentsException {
		if (TypeChecker.isCharArray(arg)) {

			return (Character[]) arg.getValue();

		} else {
			throw new ArgumentsException("No such CharArray attribute: (key = " + arg.alias + ")");
		}
	}

	/**
	 * Returns the integer array value of an Arg.<br>
	 * <br>
	 * In case the id does not exist or the Arg is of another type, an exception
	 * will be thrown.
	 * 
	 * @param id
	 * @return value
	 * @throws ArgumentsException
	 */
	public Integer[] getIntegerArrayValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getIntegerArray(arg);
	}

	/**
	 * Returns the integer array value of an Arg.<br>
	 * <br>
	 * In case the alias does not exist or the Arg is of another type, an
	 * exception will be thrown.
	 * 
	 * @param alias
	 * @return value
	 * @throws ArgumentsException
	 */
	public Integer[] getIntegerArrayValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getIntegerArray(arg);
	}

	/**
	 * Returns the integer array value of an Arg.<br>
	 * <br>
	 * In case the Arg is of another type, an exception will be thrown.
	 * 
	 * @param alias
	 * @return value
	 * @throws ArgumentsException
	 */
	private Integer[] getIntegerArray(Arg arg) throws ArgumentsException {
		if (TypeChecker.isIntegerArray(arg)) {

			return (Integer[]) arg.getValue();

		} else {
			throw new ArgumentsException("No such IntegerArray attribute: (key = " + arg.alias + ")");
		}
	}

	/**
	 * Returns the double array value of an Arg.<br>
	 * <br>
	 * In case the id does not exist or the Arg is of another type, an exception
	 * will be thrown.
	 * 
	 * @param id
	 * @return value
	 * @throws ArgumentsException
	 */
	public Double[] getDoubleArrayValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getDoubleArray(arg);
	}

	/**
	 * Returns the double array value of an Arg.<br>
	 * <br>
	 * In case the alias does not exist or the Arg is of another type, an
	 * exception will be thrown.
	 * 
	 * @param alias
	 * @return value
	 * @throws ArgumentsException
	 */
	public Double[] getDoubleArrayValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getDoubleArray(arg);
	}

	/**
	 * Returns the double array value of an Arg.<br>
	 * <br>
	 * In case the Arg is of another type, an exception will be thrown.
	 * 
	 * @param arg
	 * @return value
	 * @throws ArgumentsException
	 */
	private Double[] getDoubleArray(Arg arg) throws ArgumentsException {
		if (TypeChecker.isDoubleArray(arg)) {

			return (Double[]) arg.getValue();

		} else {
			throw new ArgumentsException("No such DoubleArray attribute: (key = " + arg.alias + ")");
		}
	}

	/**
	 * Returns the true if the flag was set.<br>
	 * <br>
	 * In case the id does not exist or the Arg is of another type, an exception
	 * will be thrown.
	 * 
	 * @param id
	 * @return value
	 * @throws ArgumentsException
	 */
	public Boolean getFlagValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getFlag(arg);
	}

	/**
	 * Returns true if the flag was set.<br>
	 * <br>
	 * In case the alias does not exist or the Arg is of another type, an
	 * exception will be thrown.
	 * 
	 * @param alias
	 * @return value
	 * @throws ArgumentsException
	 */
	public Boolean getFlagValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getFlag(arg);
	}

	/**
	 * Returns true is the flag was set.<br>
	 * <br>
	 * In case the Arg is of another type, an exception will be thrown.
	 * 
	 * @param alias
	 * @return value
	 * @throws ArgumentsException
	 */
	private Boolean getFlag(Arg arg) throws ArgumentsException {
		if (arg instanceof Flag) {

			return ((Flag) arg).isSet();

		} else {
			throw new ArgumentsException("No such Flag attribute: (key = " + arg.alias + ")");
		}
	}

	/**
	 * 
	 */
	public String toString() {
		if (!usage.equals(""))
			return "Usage: " + usageToString() + "\n" + argumentsToString();
		return argumentsToString();
	}

	public String usageToString() {
		return usage;
	}

	/**
	 * Creates a String of all different Args, split to Required and Optional.
	 * 
	 * @return Args as String
	 */
	public String argumentsToString() {
		String output = requiredArgsAsString();
		output += "\n" + optionalArgsAsString();
		return output;
	}

	/**
	 * Creates a string of all required arguments.
	 * 
	 * @return required args as string
	 */
	private String requiredArgsAsString() {
		String output = "Required Arguments:";

		for (Arg arg : args) {

			if (arg instanceof RequiredArg) {
				output += "\n\t" + arg.toString();
			}

		}

		return output;
	}

	/**
	 * Creates a string of all optional arguments.
	 * 
	 * @return optional args as string
	 */
	private String optionalArgsAsString() {
		String output = "Optional Arguments:";

		for (Arg arg : args) {

			if (TypeChecker.isOptional(arg)) {
				output += "\n\t" + arg.toString();
			}

		}

		return output;
	}

	/**
	 * 
	 * @return
	 */
	public String getUsage() {
		return this.usage;
	}

	/**
	 * 
	 * @param usage
	 */
	public void setUsage(String usage) {
		this.usage = usage;
	}

}
