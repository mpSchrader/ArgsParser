package de.arguments;

import de.arguments.optional.*;
import de.arguments.required.*;

public class TypeChecker {

	public static boolean isString(Arg arg) {
		return arg instanceof RequiredString || arg instanceof OptionalString;
	}
	
	public static boolean isFlag(Arg arg) {
		return arg instanceof Flag;
	}

	public static boolean isChar(Arg arg) {
		return arg instanceof RequiredChar || arg instanceof OptionalChar;
	}

	public static boolean isDouble(Arg arg) {
		return arg instanceof RequiredDouble || arg instanceof OptionalDouble;
	}

	public static boolean isInteger(Arg arg) {
		return arg instanceof RequiredInteger || arg instanceof OptionalInteger;
	}

	public static boolean isBoolean(Arg arg) {
		return arg instanceof RequiredBoolean || arg instanceof OptionalBoolean;
	}

	public static boolean isArray(Arg arg) {
		return arg instanceof RequiredArray || arg instanceof OptionalArray;
	}

	public static boolean isStringArray(Arg arg) {
		return arg instanceof RequiredStringArray || arg instanceof OptionalStringArray;
	}

	public static boolean isIntegerArray(Arg arg) {
		return arg instanceof RequiredIntegerArray || arg instanceof OptionalIntegerArray;
	}

	public static boolean isDoubleArray(Arg arg) {
		return arg instanceof RequiredDoubleArray || arg instanceof OptionalDoubleArray;
	}
	
	public static boolean isCharArray(Arg arg) {
		return arg instanceof RequiredCharArray || arg instanceof OptionalCharArray;
	}
}
