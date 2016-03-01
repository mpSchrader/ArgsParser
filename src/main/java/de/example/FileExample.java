package de.example;

import de.arguments.Args;
import de.arguments.ArgsFactory;
import de.arguments.exceptions.ArgumentsException;

public class FileExample {

	private static String usage = "java -jar Basic_Example.jar" + " [-t, --threads] [-n, --name] [-v, --verbose] [-p]";

	public static void main(String[] args) {

		try {
			/* Create arguments */
			Args arguments = ArgsFactory.createArgsFromFile("./src/main/java/de/example/Input.json");

			/* Parse input */
			arguments.parse(args);

			/* Access Values */
			System.out.println("Threads: " + arguments.getIntegerValue('t'));
			System.out.println("Verbose: " + arguments.getBooleanValue('v'));

		} catch (ArgumentsException e) {
			System.out.println(usage);
			System.exit(1);
		}

	}

}
