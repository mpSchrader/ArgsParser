package de.arguments;

import java.util.ArrayList;
import java.util.List;

import de.arguments.exceptions.ArgumentsException;
import de.arguments.required.*;
import static de.arguments.TypeChecker.*;

class Parser {

	private List<Arg> arguments = null;
	private String[] args = null;

	protected Parser(List<Arg> arguments, String[] args) {
		this.arguments = arguments;
		this.args = args;
	}

	/**
	 * Parses the args passed in the constructor.
	 * 
	 * @throws ArgumentsException
	 */
	protected void parse() throws ArgumentsException {
		for (int i = 0; i < args.length; i++) {

			/* Checks whether the arg is identified by id or alias */
			if (args[i].startsWith("--")) {
				addValueByAlias(i, args);
			} else if (args[i].startsWith("-") && args[i].length() == 2) {
				addValueById(i, args);
			}

		}

		checkMissingArguments();
	}

	/**
	 * Adds value to argument at the given position.
	 * 
	 * @param i
	 * @param args
	 * @throws ArgumentsException
	 */
	private void addValueByAlias(int i, String[] args) throws ArgumentsException {
		String key = args[i];
		key = key.substring(2, key.length());
		Arg arg = findArg(key);
		addValueToArg(args, arg, i);
	}

	/**
	 * Adds value to argument at the given position.
	 * 
	 * @param i
	 * @param args
	 * @throws ArgumentsException
	 */
	private void addValueById(int i, String[] args) throws ArgumentsException {
		char key = args[i].charAt(1);
		Arg arg = findArg(key);
		addValueToArg(args, arg, i);
	}

	/**
	 * Adds value to argument at the given position.
	 * 
	 * @param i
	 * @param arg
	 * @param args
	 * @throws ArgumentsException
	 */
	private void addValueToArg(String[] args, Arg arg, int i) throws ArgumentsException {

		/* Handle arg depending on its type. */
		if (isFlag(arg)) {
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

	/**
	 * Simply add the value to a normal arg. <br>
	 * <br>
	 * No Flag or Array.
	 * 
	 * @param arg
	 * @param args
	 * @param i
	 * @throws ArgumentsException
	 */
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

	/**
	 * Add the all values of the array to arg.
	 * 
	 * @param arg
	 * @param args
	 * @param i
	 * @throws ArgumentsException
	 */
	private void addArrayValue(Arg arg, String[] args, int i) throws ArgumentsException {
		if (isStringArray(arg)) {

			String[] rawValues = createValues(i + 1, args);
			String[] value = createStringValues(rawValues);
			arg.setValue(value);

		} else if (isIntegerArray(arg)) {
			String[] rawValues = createValues(i + 1, args);
			Integer[] value = createIntegerValues(rawValues);
			arg.setValue(value);
		} else if (isDoubleArray(arg)) {
			String[] rawValues = createValues(i + 1, args);
			Double[] value = createDoubleValues(rawValues);
			arg.setValue(value);
		} else if (isCharArray(arg)) {
			String[] rawValues = createValues(i + 1, args);
			Character[] value = createCharValues(rawValues);
			arg.setValue(value);
		} else if (isBooleanArray(arg)) {
			String[] rawValues = createValues(i + 1, args);
			Boolean[] value = createBooleanValues(rawValues);
			arg.setValue(value);
		}

	}

	/**
	 * Creates a raw string array of all elements in the list. <br>
	 * <br>
	 * Start and end of the list are defined by "[" and "]" and splits by ","..
	 * 
	 * @param startOfArray
	 * @param args
	 * @return raw values array
	 * @throws ArgumentsException
	 */
	private String[] createValues(int startOfArray, String[] args) throws ArgumentsException {

		if (!args[startOfArray].startsWith("[")) {
			throw new ArgumentsException("No array start! Value of first: " + args[startOfArray]);
		}

		List<String> rawValues = new ArrayList<String>();
		for (int i = startOfArray; i < args.length; i++) {

			String value = args[i];
			if (value.contains(",")) {
				addSplittedValue(rawValues, value);
			} else {
				rawValues.add(value);
			}

			if (args[i].endsWith("]")) {
				break;
			}

		}

		String[] values = rawValues.toArray(new String[rawValues.size()]);
		/* Remove braces [] */
		values[0] = values[0].substring(1);
		int last = values.length - 1;
		values[last] = values[last].substring(0, values[last].length() - 1);

		return values;
	}

	/**
	 * Creates a String array from the raw array.<br>
	 * <br>
	 * This allows to use " as start and end indicator to have a String
	 * containing blanks.
	 * 
	 * @param inputValues
	 * @return string array
	 */
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

	/**
	 * Creates integer array from raw array.
	 * 
	 * @param rawValues
	 * @return array of integer values
	 */
	private Integer[] createIntegerValues(String[] rawValues) {
		Integer[] values = new Integer[rawValues.length];

		for (int i = 0; i < values.length; i++) {
			values[i] = Integer.parseInt(rawValues[i]);
		}

		return values;
	}

	/**
	 * Creates boolean array from raw array.
	 * 
	 * @param rawValues
	 * @return array of boolean values
	 */
	private Boolean[] createBooleanValues(String[] rawValues) {
		Boolean[] values = new Boolean[rawValues.length];

		for (int i = 0; i < values.length; i++) {
			String currentValue = rawValues[i].replace(",", "");
			values[i] = Boolean.parseBoolean(currentValue);
		}

		return values;
	}

	/**
	 * Creates char array from raw array.
	 * 
	 * @param rawValues
	 * @return array of char values
	 */
	private Character[] createCharValues(String[] rawValues) throws ArgumentsException {
		Character[] values = new Character[rawValues.length];

		for (int i = 0; i < values.length; i++) {
			rawValues[i] = rawValues[i].trim();
			if (rawValues[i].length() != 1) {
				throw new ArgumentsException("No Character for CharArray: " + rawValues[i]);
			}
			values[i] = rawValues[i].charAt(0);
		}

		return values;
	}

	/**
	 * Creates double array from raw array.
	 * 
	 * @param rawValues
	 * @return array of double values
	 */
	private Double[] createDoubleValues(String[] rawValues) {
		Double[] values = new Double[rawValues.length];

		for (int i = 0; i < values.length; i++) {
			values[i] = Double.parseDouble(rawValues[i]);
		}

		return values;
	}

	/**
	 * Returns true if the string starts with " and not ends with "
	 * 
	 * @param isCombined
	 * @param value
	 * @return
	 */
	private boolean isCombineStart(boolean isCombined, String value) {
		return !isCombined && value.startsWith("\"") && !value.endsWith("\"");
	}

	/**
	 * Returns true if the string ends with " and isComined is set true.
	 * 
	 * @param isCombined
	 * @param value
	 * @return
	 */
	private boolean isCombineEnd(boolean isCombined, String value) {
		return isCombined && value.endsWith("\"");
	}

	/**
	 * Splits the value string by "," and adds the single parts to the values
	 * list.
	 * 
	 * @param values
	 * @param value
	 */
	private void addSplittedValue(List<String> values, String value) {
		String[] splitted = value.split(",");
		for (String s : splitted) {
			values.add(s);
		}
	}

	/**
	 * Creates one String form the args starting at position i.<br>
	 * <br>
	 * This allows to have String containing blanks.
	 * 
	 * @param i
	 * @param args
	 * @return
	 */
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

	/**
	 * Checks whether a required arg is not set. <br>
	 * <br>
	 * In case at least one required arg is not set, an exception will be
	 * thrown.
	 * 
	 * @throws ArgumentsException
	 */
	private void checkMissingArguments() throws ArgumentsException {
		for (Arg arg : arguments) {

			if (arg instanceof RequiredArg) {
				requiredArgIsSet(arg);
			}

		}

	}

	/**
	 * Checks if the value of the passed arg is set. If this is not the case an
	 * exception will be thrown.
	 * 
	 * @param arg
	 * @throws ArgumentsException
	 */
	private void requiredArgIsSet(Arg arg) throws ArgumentsException {
		RequiredArg rArg = ((RequiredArg) arg);
		if (rArg.valueNotSet()) {
			throw new ArgumentsException("Required Argument " + rArg.getId() + " is not set");
		}
	}

	/**
	 * Searches for arg by id.<br>
	 * <br>
	 * In case the arg does not exist an exception will be thrown.
	 * 
	 * @param id
	 * @return arg
	 * @throws ArgumentsException
	 */
	private Arg findArg(char id) throws ArgumentsException {
		for (Arg arg : arguments) {
			if (arg.getId() == id) {
				return arg;
			}
		}
		throw new ArgumentsException("No such argument! id: " + Character.toString(id));
	}

	/**
	 * Searches for arg by alias.<br>
	 * <br>
	 * In case the arg does not exist an exception will be thrown.
	 * 
	 * @param alias
	 * @return arg
	 * @throws ArgumentsException
	 */
	private Arg findArg(String alias) throws ArgumentsException {

		for (Arg arg : arguments) {
			if (arg.getAlias().equals(alias)) {
				return arg;
			}
		}
		throw new ArgumentsException("No such argument! alias: " + alias);
	}

}
