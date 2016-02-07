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

	public void parse() throws ArgumentsException {
		for (int i = 0; i < args.length; i++) {

			if (args[i].startsWith("--")) {
				addValueByAlias(i, args);
				System.out.println("By Alias: " + args[i]);
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

	private Double[] createDoubleValues(String[] rawValues) {
		Double[] values = new Double[rawValues.length];

		for (int i = 0; i < values.length; i++) {
			values[i] = Double.parseDouble(rawValues[i]);
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

	private void checkMissingArguments() throws ArgumentsException {
		for (Arg arg : arguments) {

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

	private Arg findArg(char id) throws ArgumentsException {
		for (Arg arg : arguments) {
			if (arg.getId() == id) {
				return arg;
			}
		}
		throw new ArgumentsException("No such argument! id: " + Character.toString(id));
	}

	private Arg findArg(String alias) throws ArgumentsException {

		for (Arg arg : arguments) {
			if (arg.getAlias().equals(alias)) {
				return arg;
			}
		}
		throw new ArgumentsException("No such argument! alias: " + alias);
	}

}
