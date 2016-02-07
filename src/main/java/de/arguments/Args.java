package de.arguments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.arguments.exceptions.ArgumentsException;
import de.arguments.optional.*;
import de.arguments.required.*;

public class Args {

	private List<Arg> args = new ArrayList<Arg>();
	private String usage = "";

	public Args() {
	}

	public Args(String usage) {
		this.usage = usage;
	}

	protected Args(List<Arg> args) throws ArgumentsException {
		this.args = args;
		Collections.sort(this.args);
		checkArgs();
	}

	protected Args(List<Arg> args, String usage) throws ArgumentsException {
		this.args = args;
		Collections.sort(this.args);
		this.usage = usage;
		checkArgs();
	}

	private void checkArgs() throws ArgumentsException {
		checkIDs();
		checkAlias();
	}

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

	public void add(Arg arg) throws ArgumentsException {
		this.args.add(arg);
		checkArgs();
	}

	public void parse(String[] args) throws ArgumentsException {
		/* Delegating the parsing to the parser class */
		Parser parser = new Parser(this.args, args);
		parser.parse();
	}

	

	private Arg findArg(char id) throws ArgumentsException {
		for (Arg arg : args) {
			if (arg.getId() == id) {
				return arg;
			}
		}
		throw new ArgumentsException("No such argument! id: " + Character.toString(id));
	}

	private Arg findArg(String alias) throws ArgumentsException {
		
		for (Arg arg : args) {
			if (arg.getAlias().equals(alias)) {
				return arg;
			}
		}
		throw new ArgumentsException("No such argument! alias: " + alias);
	}

	public Boolean getBooleanValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getBoolean(arg);
	}

	public Boolean getBooleanValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getBoolean(arg);
	}

	private Boolean getBoolean(Arg arg) throws ArgumentsException {
		if (arg instanceof OptionalBoolean || arg instanceof RequiredBoolean) {

			return (Boolean) arg.getValue();

		} else {
			throw new ArgumentsException("No such Boolean attribute: (key = " + arg.alias + ")");
		}
	}

	public char getCharValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getChar(arg);
	}

	public char getCharValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getChar(arg);
	}

	private char getChar(Arg arg) throws ArgumentsException {
		if (arg instanceof OptionalChar || arg instanceof RequiredChar) {

			return (Character) arg.getValue();

		} else {
			throw new ArgumentsException("No such Boolean attribute: (key = " + arg.alias + ")");
		}
	}

	public Double getDoubleValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getDouble(arg);
	}

	public Double getDoubleValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getDouble(arg);
	}

	private Double getDouble(Arg arg) throws ArgumentsException {
		if (arg instanceof OptionalDouble || arg instanceof RequiredDouble) {

			return (Double) arg.getValue();

		} else {
			throw new ArgumentsException("No such Double attribute: (key = " + arg.alias + ")");
		}
	}

	public Integer getIntegerValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getInteger(arg);
	}

	public Integer getIntegerValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getInteger(arg);
	}

	private Integer getInteger(Arg arg) throws ArgumentsException {
		if (arg instanceof OptionalInteger || arg instanceof RequiredInteger) {

			return (Integer) arg.getValue();

		} else {
			throw new ArgumentsException("No such Integer attribute: (key = " + arg.alias + ")");
		}
	}

	public String getStringValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getStringValue(arg);
	}

	public String getStringValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getStringValue(arg);
	}

	private String getStringValue(Arg arg) throws ArgumentsException {
		if (arg instanceof OptionalString || arg instanceof RequiredString) {

			return (String) arg.getValue();

		} else {
			throw new ArgumentsException("No such String attribute: (key = " + arg.alias + ")");
		}
	}

	public String[] getStringArrayValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getStringArray(arg);
	}

	public String[] getStringArrayValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getStringArray(arg);
	}

	private String[] getStringArray(Arg arg) throws ArgumentsException {
		if (arg instanceof OptionalStringArray || arg instanceof RequiredStringArray) {

			return (String[]) arg.getValue();

		} else {
			throw new ArgumentsException("No such StringArray attribute: (key = " + arg.alias + ")");
		}
	}

	public Integer[] getIntegerArrayValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getIntegerArray(arg);
	}

	public Integer[] getIntegerArrayValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getIntegerArray(arg);
	}

	private Integer[] getIntegerArray(Arg arg) throws ArgumentsException {
		if (arg instanceof OptionalIntegerArray || arg instanceof RequiredIntegerArray) {

			return (Integer[]) arg.getValue();

		} else {
			throw new ArgumentsException("No such IntegerArray attribute: (key = " + arg.alias + ")");
		}
	}
	
	public Double[] getDoubleArrayValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getDoubleArray(arg);
	}

	public Double[] getDoubleArrayValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getDoubleArray(arg);
	}

	private Double[] getDoubleArray(Arg arg) throws ArgumentsException {
		if (arg instanceof OptionalDoubleArray || arg instanceof RequiredDoubleArray) {

			return (Double[]) arg.getValue();

		} else {
			throw new ArgumentsException("No such DoubleArray attribute: (key = " + arg.alias + ")");
		}
	}

	public Boolean getFlagValue(char id) throws ArgumentsException {
		Arg arg = findArg(id);
		return getFlag(arg);
	}

	public Boolean getFlagValue(String alias) throws ArgumentsException {
		Arg arg = findArg(alias);
		return getFlag(arg);
	}

	private Boolean getFlag(Arg arg) throws ArgumentsException {
		if (arg instanceof Flag) {

			return ((Flag) arg).isSet();

		} else {
			throw new ArgumentsException("No such Flag attribute: (key = " + arg.alias + ")");
		}
	}

	public String toString() {
		if (!usage.equals(""))
			return "Usage: " + usageToString() + "\n" + argumentsToString();
		return argumentsToString();
	}

	public String usageToString() {
		return usage;
	}

	public String argumentsToString() {
		String output = requiredArgsAsString();
		output += "\n" + optionalArgsAsString();
		return output;
	}

	private String requiredArgsAsString() {
		String output = "Required Arguments:";

		int maxLength = 0;
		for (Arg arg : args) {
			String firstPart = arg.toString().split(">")[0];
			int currentLength = firstPart.length();
			if (arg instanceof RequiredArg && maxLength < currentLength) {
				maxLength = currentLength;
			}
		}
		maxLength++;

		for (Arg arg : args) {

			if (arg instanceof RequiredArg) {
				output += "\n\t" + getProperString(maxLength, arg);
			}

		}

		return output;
	}

	private String optionalArgsAsString() {
		String output = "Optional Arguments:";

		int maxLength = 0;
		for (Arg arg : args) {
			String firstPart = arg.toString().split(">")[0];
			int currentLength = firstPart.length();
			boolean rightInstance = arg instanceof OptionalArg;
			if (rightInstance && maxLength < currentLength) {
				maxLength = currentLength;
			}
		}
		maxLength++;

		for (Arg arg : args) {

			boolean rightInstance = arg instanceof OptionalArg || arg instanceof Flag;
			if (rightInstance) {
				output += "\n\t" + getProperString(maxLength, arg);
			}

		}

		return output;
	}

	private String getProperString(int maxLength, Arg arg) {
		String base = arg.toString();

		// TODO change layout
		return base;
	}

}
