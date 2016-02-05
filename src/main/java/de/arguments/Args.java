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

	public Args(List<Arg> args) throws ArgumentsException {
		this.args = args;
		Collections.sort(this.args);
		checkArgs();
	}

	public Args(List<Arg> args, String usage) throws ArgumentsException {
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
		for (int i = 0; i < args.length; i++) {

			if (args[i].startsWith("--")) {
				addValueByAlias(i, args);
			} else if (args[i].startsWith("-") && args[i].length() == 2) {
				addValueById(i, args);
			}

		}

		checkMissingArguments();
	}

	private void addValueByAlias(int i, String[] args) throws ArgumentsException {
		String key = args[i];
		key = key.substring(2, key.length());
		Arg arg = findArg(key);
		addValueToArg(args, arg, i);
	}

	private void addValueById(int i, String[] args) throws ArgumentsException {
		char key = args[i].charAt(1);
		Arg arg = findArg(key);
		addValueToArg(args, arg, i);
	}

	private void addValueToArg(String[] args, Arg arg, int i) throws ArgumentsException {

		if (arg instanceof Flag) {
			arg.setValue(true);

		} else if (isArray(arg)) {
			addArrayValue(arg, args, i);

		} else {
			try {

				addValue(arg, args, i + 1);

			} catch (ArrayIndexOutOfBoundsException e) {
				throw new ArgumentsException("No Value for argument: " + arg.getId());
			}
		}
	}

	private void addValue(Arg arg, String[] args, int i) throws ArgumentsException {
		Object value = new Object();

		if (isString(arg)) {
			value = createStringValue(i, args);
		}

		if (isInteger(arg)) {
			value = Integer.parseInt(args[i]);
		}

		if (isDouble(arg)) {
			value = Double.parseDouble(args[i]);
		}

		if (isChar(arg)) {
			value = args[i].charAt(0);
		}

		if (isBoolean(arg)) {
			value = Boolean.parseBoolean(args[i]);
		}

		arg.setValue(value);
	}

	private boolean isString(Arg arg) {
		return arg instanceof RequiredString || arg instanceof OptionalString;
	}

	private boolean isChar(Arg arg) {
		return arg instanceof RequiredChar || arg instanceof OptionalChar;
	}

	private boolean isDouble(Arg arg) {
		return arg instanceof RequiredDouble || arg instanceof OptionalDouble;
	}

	private boolean isInteger(Arg arg) {
		return arg instanceof RequiredInteger || arg instanceof OptionalInteger;
	}

	private boolean isBoolean(Arg arg) {
		return arg instanceof RequiredBoolean || arg instanceof OptionalBoolean;
	}

	private boolean isArray(Arg arg) {
		return arg instanceof RequiredArray || arg instanceof OptionalArray;
	}

	private boolean isStringArray(Arg arg) {
		return arg instanceof RequiredStringArray || arg instanceof OptionalStringArray;
	}

	private boolean isIntegerArray(Arg arg) {
		return arg instanceof RequiredIntegerArray || arg instanceof OptionalIntegerArray;
	}

	private void addArrayValue(Arg arg, String[] args, int i) throws ArgumentsException {
		if (isStringArray(arg)) {

			String[] rawValues = createValues(i + 1, args);
			String[] value = createStringValues(rawValues);
			arg.setValue(value);

		} else if (isIntegerArray(arg)) {
			String[] rawValues = createValues(i + 1, args);
			Integer[] value = createIntegerValues(rawValues);
			arg.setValue(value);
		}

	}

	private String[] createValues(int startOfArray, String[] args) throws ArgumentsException {

		if (!args[startOfArray].startsWith("[")) {
			throw new ArgumentsException("No array start! Value of first: " + args[startOfArray]);
		}

		int endOfArray = 0;
		List<String> rawValues = new ArrayList<String>();
		for (endOfArray = startOfArray; endOfArray < args.length; endOfArray++) {

			String value = args[endOfArray];
			if (value.contains(",")) {
				addSplittedValue(rawValues, value);
			} else {
				rawValues.add(value);
			}

			if (args[endOfArray].endsWith("]")) {
				break;
			}

		}

		String[] values = rawValues.toArray(new String[rawValues.size()]);
		/* Remove braces */
		values[0] = values[0].substring(1);
		int last = values.length - 1;
		values[last] = values[last].substring(0, values[last].length() - 1);

		return values;
	}

	private String[] createStringValues(String[] inputValues) {
		List<String> rawValues = new ArrayList<String>();

		boolean isCombined = false;
		String combined = "";

		for (int i = 0; i < inputValues.length; i++) {
			String value = inputValues[i];

			if (isCombineStart(isCombined, value)) {
				combined = value;
				isCombined = true;
			} else if (isCombineEnd(isCombined, value)) {
				combined += " " + value;
				rawValues.add(combined);
				isCombined = false;
			} else if (isCombined) {
				combined += " " + value;
			} else {
				rawValues.add(value);
			}

		}

		String[] values = rawValues.toArray(new String[rawValues.size()]);
		return values;
	}

	private Integer[] createIntegerValues(String[] rawValues) {
		Integer[] values = new Integer[rawValues.length];

		for (int i = 0; i < values.length; i++) {
			values[i] = Integer.parseInt(rawValues[i]);
		}

		return values;
	}

	private boolean isCombineStart(boolean isCombined, String value) {
		return !isCombined && value.startsWith("\"") && !value.endsWith("\"");
	}

	private boolean isCombineEnd(boolean isCombined, String value) {
		return isCombined && value.endsWith("\"");
	}

	private void addSplittedValue(List<String> values, String value) {
		String[] splitted = value.split(",");
		for (String s : splitted) {
			values.add(s);
		}
	}

	private String createStringValue(int i, String[] args) {

		if (!args[i].startsWith("\"")) {
			return args[i];
		}

		String value = "";

		for (int j = i; j < args.length; j++) {

			value += " " + args[j];
			if (args[j].endsWith("\"")) {
				break;
			}

		}

		value = value.trim();
		value = value.substring(1, value.length() - 1);

		return value;
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

	private void checkMissingArguments() throws ArgumentsException {
		for (Arg arg : args) {

			if (arg instanceof RequiredArg) {
				requiredArgIsSet(arg);
			}

		}

	}

	private void requiredArgIsSet(Arg arg) throws ArgumentsException {
		RequiredArg rArg = ((RequiredArg) arg);
		if (!rArg.valueSet()) {
			throw new ArgumentsException("Required Argument " + rArg.getId() + " is not set");
		}
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
