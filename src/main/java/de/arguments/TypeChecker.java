package de.arguments;

import de.arguments.optional.*;
import de.arguments.required.*;

public class TypeChecker {

	/**
	 * Returns true if the arg is either a required or an optional string arg.
	 * 
	 * @param arg
	 * @return 
	 */
	public static boolean isString(Arg arg) {
		return arg instanceof RequiredString || arg instanceof OptionalString;
	}

	/**
	 * Returns true if the arg is a flag.
	 * 
	 * @param arg
	 * @return 
	 */
	public static boolean isFlag(Arg arg) {
		return arg instanceof Flag;
	}

	/**
	 * Returns true if the arg is either a required or an optional char arg.
	 * 
	 * @param arg
	 * @return 
	 */
	public static boolean isChar(Arg arg) {
		return arg instanceof RequiredChar || arg instanceof OptionalChar;
	}

	/**
	 * Returns true if the arg is either a required or an optional double arg.
	 * 
	 * @param arg
	 * @return 
	 */
	public static boolean isDouble(Arg arg) {
		return arg instanceof RequiredDouble || arg instanceof OptionalDouble;
	}

	/**
	 * Returns true if the arg is either a required or an optional integer arg.
	 * 
	 * @param arg
	 * @return 
	 */
	public static boolean isInteger(Arg arg) {
		return arg instanceof RequiredInteger || arg instanceof OptionalInteger;
	}

	/**
	 * Returns true if the arg is either a required or an optional boolean arg.
	 * 
	 * @param arg
	 * @return 
	 */
	public static boolean isBoolean(Arg arg) {
		return arg instanceof RequiredBoolean || arg instanceof OptionalBoolean;
	}

	/**
	 * Returns true if the arg is either a required or an optional array arg.
	 * 
	 * @param arg
	 * @return 
	 */
	public static boolean isArray(Arg arg) {
		return arg instanceof RequiredArray || arg instanceof OptionalArray;
	}

	/**
	 * Returns true if the arg is either a required or an optional string array arg.
	 * 
	 * @param arg
	 * @return 
	 */
	public static boolean isStringArray(Arg arg) {
		return arg instanceof RequiredStringArray || arg instanceof OptionalStringArray;
	}

	/**
	 * Returns true if the arg is either a required or an optional integer array arg.
	 * 
	 * @param arg
	 * @return 
	 */
	public static boolean isIntegerArray(Arg arg) {
		return arg instanceof RequiredIntegerArray || arg instanceof OptionalIntegerArray;
	}

	/**
	 * Returns true if the arg is either a required or an optional double array arg.
	 * 
	 * @param arg
	 * @return 
	 */
	public static boolean isDoubleArray(Arg arg) {
		return arg instanceof RequiredDoubleArray || arg instanceof OptionalDoubleArray;
	}

	/**
	 * Returns true if the arg is either a required or an optional boolean array arg.
	 * 
	 * @param arg
	 * @return 
	 */
	public static boolean isBooleanArray(Arg arg) {
		return arg instanceof RequiredBooleanArray || arg instanceof OptionalBooleanArray;
	}

	/**
	 * Returns true if the arg is either a required or an optional char array arg.
	 * 
	 * @param arg
	 * @return 
	 */
	public static boolean isCharArray(Arg arg) {
		return arg instanceof RequiredCharArray || arg instanceof OptionalCharArray;
	}

	/**
	 * Returns true if the arg is either an optional arg or flag.
	 * 
	 * @param arg
	 * @return 
	 */
	public static boolean isOptional(Arg arg) {
		return arg instanceof OptionalArg || arg instanceof Flag;
	}
}
