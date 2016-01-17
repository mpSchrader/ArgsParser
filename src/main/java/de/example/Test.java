package de.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.arguments.Arg;
import de.arguments.Args;
import de.arguments.exceptions.ArgumentException;
import de.arguments.optional.Flag;
import de.arguments.optional.OptionalBoolean;
import de.arguments.optional.OptionalDouble;
import de.arguments.optional.OptionalInteger;
import de.arguments.optional.OptionalString;
import de.arguments.required.RequiredBoolean;
import de.arguments.required.RequiredDouble;
import de.arguments.required.RequiredInteger;
import de.arguments.required.RequiredString;

public class Test {

	public static void main(String[] args) throws ArgumentException {
		/* Setup args */
		List<Arg> arg = new ArrayList<Arg>();
		arg.add(new RequiredInteger("ir", "This is a required Integer"));
		arg.add(new OptionalBoolean("b", true, "This is a optional Boolean"));
		arg.add(new OptionalDouble("d", 1.234, "This is a optional Double"));
		arg.add(new RequiredBoolean("br", "This is a required Boolean"));
		arg.add(new RequiredDouble("dr", "This is a required Double"));
		arg.add(new OptionalInteger("i", 123, "This is a optional Integer"));
		arg.add(new OptionalInteger("n", 123));
		arg.add(new OptionalString("s", "default", "This is a optional String"));
		arg.add(new Flag("f", "This is a Flag"));
		arg.add(new RequiredString("sr", "This is a required String"));
		arg.add(new RequiredString("nr"));
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
