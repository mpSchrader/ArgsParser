package de.example;

import de.arguments.*;
import de.arguments.required.*;
import de.arguments.exceptions.ArgumentException;
import de.arguments.optional.*;

public class BasicExample {

	private static String usage = "java -jar Basic_Example.jar"
			+ " [-t, --threads] [-n, --name] [-v, --verbose] [-p]";

	public static void main(String[] agrs) {
		try {

			Args argmnts = new Args();
			/* Add all arguments you want to parse. e.g.:*/
			argmnts.addArg(new RequiredInteger('t', "threads"));
			argmnts.addArg(new RequiredString('n', "name"));
			argmnts.addArg(new Flag('v', "verbose"));
			argmnts.addArg(new OptionalDouble('p', 0.5));
			
			/* Parse Args */
			argmnts.parse(agrs);

			/* Access values */
			System.out.println("Threads: " + argmnts.getIntegerValue('t'));
			System.out.println("Name: " + argmnts.getStringValue("name"));
			System.out.println("Flag set: " + argmnts.getFlagValue('v'));
			System.out.println("Percent: " + argmnts.getDoubleValue('p'));

		} catch (ArgumentException e) {
			System.out.println(usage);
			System.exit(0);
		}

		/* Do what ever you want */

	}
}
