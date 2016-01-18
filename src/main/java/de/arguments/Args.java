package de.arguments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.arguments.exceptions.ArgumentException;
import de.arguments.optional.*;
import de.arguments.required.*;

public class Args {

	private List<Arg> args = new ArrayList<Arg>();
	private String usage = "";

	public Args(List<Arg> args) throws ArgumentException {
		this.args = args;
		Collections.sort(this.args);
		checkArgs();
	}

	public Args(List<Arg> args, String usage) throws ArgumentException {
		this.args = args;
		Collections.sort(this.args);
		this.usage = usage;
		checkArgs();
	}

	private void checkArgs() throws ArgumentException {
		String keys = "";

		for (Arg arg : args) {

			String key = " " + arg.getId() + " ";
			System.out.println("keys: "+keys+" key: "+key);
			if (keys.contains(key)) {
				throw new ArgumentException("Duplicate Key: " + key);
			}
			keys += key;

		}

	}

	public void parseArgs(String[] args) throws ArgumentException {
		for (int i = 0; i < args.length; i++) {

			if (args[i].startsWith("--")) {
				addValueByAlias(i,args);
			} else if (args[i].startsWith("-")) {
				addValueById(i, args);
			}

		}

		checkMissingArguments();
	}

	private void addValueByAlias(int i, String[]args) throws ArgumentException{
		String key = args[i];
		key = key.substring(2, key.length());
		Arg arg = findArg(key);
		addValueToArg(arg,i,args);
	}
	
	private void addValueById(int i, String[]args) throws ArgumentException{
		char key = args[i].charAt(1);
		Arg arg = findArg(key);
		addValueToArg(arg,i,args);
	}
	
	private void addValueToArg(Arg arg,int i ,String[]args) throws ArgumentException {
		

		if (arg instanceof Flag) {

			arg.setValue(true);

		} else if (arg instanceof RequiredString
				|| arg instanceof OptionalString) {

			String value = getStringValue(i, args);
			arg.setValue(value);

		} else {
			try {

				arg.setValue(args[i + 1]);

			} catch (ArrayIndexOutOfBoundsException e) {
				throw new ArgumentException("No Value for argument: " + arg.getId());
			}
		}
	}

	private String getStringValue(int i, String[] args) {

		if (!args[i + 1].startsWith("\"")) {
			return args[i + 1];
		}

		String value = "";

		for (int j = i + 1; j < args.length; j++) {

			value += " " + args[j];
			if (args[j].endsWith("\"")) {
				break;
			}

		}

		value = value.trim();
		value = value.replaceFirst("\"", "");
		value = value.substring(0, value.length() - 1);

		return value;
	}

	private Arg findArg(char id) throws ArgumentException {
		for (Arg arg : args) {
			if (arg.getId() == id) {
				return arg;
			}
		}
		throw new ArgumentException("No such argument!");
	}
	
	private Arg findArg(String alias) throws ArgumentException {
		for (Arg arg : args) {
			if (arg.getAlias().equals(alias)) {
				return arg;
			}
		}
		throw new ArgumentException("No such argument!");
	}

	private void checkMissingArguments() throws ArgumentException {
		for (Arg arg : args) {

			if (arg instanceof RequiredArg) {
				requiredArgIsSet(arg);
			}

		}

	}

	private void requiredArgIsSet(Arg arg) throws ArgumentException {
		RequiredArg rArg = ((RequiredArg) arg);
		if (!rArg.checkIfValueIsSet()) {
			throw new ArgumentException("Required Argument " + rArg.getId()
					+ " is not set");
		}
	}

	public Boolean getBooleanValue(char id) throws ArgumentException {
		Arg arg = findArg(id);
		return getBoolean(arg);
	}
	
	public Boolean getBooleanValue(String alias) throws ArgumentException {
		Arg arg = findArg(alias);
		return getBoolean(arg);
	}
	
	private Boolean getBoolean(Arg arg) throws ArgumentException{
		if (arg instanceof OptionalBoolean || arg instanceof RequiredBoolean) {

			return (Boolean) arg.getValue();

		} else {
			throw new ArgumentException("No such Boolean attribute: (key = "
					+ arg.alias + ")");
		}
	}

	public Double getDoubleValue(char id) throws ArgumentException {
		Arg arg = findArg(id);
		return getDouble(arg);		
	}
	
	public Double getDoubleValue(String alias) throws ArgumentException {
		Arg arg = findArg(alias);
		return getDouble(arg);		
	}
	
	private Double getDouble(Arg arg) throws ArgumentException{
		if (arg instanceof OptionalDouble || arg instanceof RequiredDouble) {

			return (Double) arg.getValue();

		} else {
			throw new ArgumentException("No such Double attribute: (key = "
					+ arg.alias + ")");
		}
	}

	public Integer getIntegerValue(char id) throws ArgumentException {
		Arg arg = findArg(id);
		return getInteger(arg);
	}
	
	public Integer getIntegerValue(String alias) throws ArgumentException {
		Arg arg = findArg(alias);
		return getInteger(arg);
	}

	private Integer getInteger(Arg arg) throws ArgumentException {
		if (arg instanceof OptionalInteger || arg instanceof RequiredInteger) {

			return (Integer) arg.getValue();

		} else {
			throw new ArgumentException("No such Integer attribute: (key = "
					+ arg.alias + ")");
		}
	}

	public String getStringValue(char id) throws ArgumentException {
		Arg arg = findArg(id);
		return getString(arg);
	}
	
	public String getStringValue(String alias) throws ArgumentException {
		Arg arg = findArg(alias);
		return getString(arg);
	}

	private String getString(Arg arg) throws ArgumentException {
		if (arg instanceof OptionalString || arg instanceof RequiredString) {

			return (String) arg.getValue();

		} else {
			throw new ArgumentException("No such String attribute: (key = "
					+ arg.alias + ")");
		}
	}

	public Boolean getFlagValue(char id) throws ArgumentException {
		Arg arg = findArg(id);
		return getFlag(arg);
	}
	
	public Boolean getFlagValue(String alias) throws ArgumentException {
		Arg arg = findArg(alias);
		return getFlag(arg);
	}

	private Boolean getFlag(Arg arg) throws ArgumentException {
		if (arg instanceof Flag) {

			return ((Flag) arg).isSet();

		} else {
			throw new ArgumentException("No such Flag attribute: (key = " + arg.alias
					+ ")");
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
			String firstPart = arg.toString().split(":")[0];
			int currentLength = firstPart.length();
			if (arg instanceof RequiredArg && maxLength < currentLength) {
				maxLength = currentLength;
			}
		}

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
			String firstPart = arg.toString().split(":")[0];
			int currentLength = firstPart.length();
			boolean rightInstance = arg instanceof OptionalArg;
			if (rightInstance && maxLength < currentLength) {
				maxLength = currentLength;
			}
		}

		for (Arg arg : args) {

			boolean rightInstance = arg instanceof OptionalArg
					|| arg instanceof Flag;
			if (rightInstance) {
				output += "\n\t" + getProperString(maxLength, arg);
			}

		}

		return output;
	}

	private String lengthenString(int maxLength, String string) {
		while (string.length() < maxLength) {
			string += " ";
		}
		return string;
	}

	private String getProperString(int maxLength, Arg arg) {
		String base = arg.toString();

		if (base.contains(":")) {
			String[] split = base.split(":");
			String firstPart = split[0];
//			firstPart = lengthenString(maxLength, firstPart);
			String secondPart = "";
			for (int i = 1; i < split.length; i++) {
				secondPart += split[i];
			}
			base = firstPart + ":" + secondPart;
		}
		return base;
	}

}
