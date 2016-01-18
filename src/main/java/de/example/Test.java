package de.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.arguments.Arg;
import de.arguments.Args;
import de.arguments.exceptions.ArgumentException;

import de.arguments.required.*;

public class Test {

	public static void main(String[] args) throws ArgumentException {
		/* Setup args */
		List<Arg> arg = new ArrayList<Arg>();
		arg.add(new RequiredInteger('i', "This is a required Integer"));
		Args arguments = new Args(arg,"java -jar run.jar -s this");

		String actual = arguments.toString();
		
		System.out.println(actual);
		
//		RequiredArray<Integer> requiredArray = new RequiredArray<Integer>("ra","How to use me");
		String[] value = {"1.0 test","2.3","4.0"};
		Character b = "a".charAt(0);
		System.out.println(Arrays.toString(value));
		System.out.println(b);
//		requiredArray.setValue(value);
//		System.out.println(requiredArray);
//		System.out.println(requiredArray.getClass().getSimpleName());
//		System.out.println(value.getClass().getSimpleName());
//		@SuppressWarnings("unchecked")
//		Class<Float> clazz = (Class<Float>) value[0].getClass();
//		System.out.println(clazz.getComponentType());
	}

}
