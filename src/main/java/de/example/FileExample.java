package de.example;

import de.arguments.Args;
import de.arguments.ArgsFactory;
import de.arguments.exceptions.ArgumentException;

public class FileExample {

	private static String usage = "java -jar Basic_Example.jar"
			+ " [-t, --threads] [-n, --name] [-v, --verbose] [-p]";
	
	public static void main(String[] args) {
		
		try{
			 /* Create arguments */
	        Args arguments = ArgsFactory.createArgsFromFile("./src/definition.args");
	        
	        /* Parse input */
	        arguments.parse(args);
	        
	        /* Access Values */
	        System.out.println(arguments.getIntegerValue('t'));
	        System.out.println(arguments.getBooleanValue('v'));
	        
		} catch (ArgumentException e){
			System.out.println(usage);
			System.exit(0);
		}

	}

}
