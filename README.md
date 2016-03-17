# ArgsParser
### A parser for command-line arguments in Java
This is a first draft and will be improved. The ArgsParser allows you to easily parse and access the arguments passed to your main method. The parser can either be defined in your code or you can load a JSON-File, which defines all possible arguments. There are two basic kinds of arguments which can be created. First required arguments, which need to be passed in the args. Second optional arguments, which are always created with a default value. This default value will be replaced, if a new value is passed.

Available argument types: 
* Boolean
* Character
* Double
* Integer
* String
* BooleanArray
* CharacterArray
* DoubleArray
* IntegerArray
* StringArray

### Why this one?
The main difference to other parsers is that you can define required and optional argument, so you do not have to care if a special arguments has been set. In case a required argument not has been set, an exception will be thrown. And if a optional argument was not set, it will just use the default value you defined.

In addition the possibility exists to use a configuration file for the arguments. This allows you to keep your code as short as possible.

### Example
Examples can also be found in the package **de.example**.
#### 1. Basic
````java
public static void main(String[] args){
    try{
        /* Create arguments */
        Args arguments = new Args();
        arguments.add(new RequiredInteger('t', "time"));
        arguments.add(new OptionalBoolean('v', "verbose", true));
        
        /* Parse input */
        arguments.parse(args)
        
        /* Access Values */
        System.out.println(arguments.getIntegerValue('t');
        System.out.println(arguments.getBooleanValue('v');
        
    } catch (ArgumentsException e){
    	/* Handle Error your way. E.g.: */
		System.exit(1);
    }
}
````

#### 2. From config file
**Definition JSON File:**
````JSON
{
	"usage" : "Some description e.g. java -jar my.jar",
	"required" : [{
		"identifier" : "s",
		"alias" : "my_string",
		"description" : "needed for what ever",
		"type": "String"
	}],
	"optional" : [{
		"identifier" : "o",
		"description" : "This is an optional boolean",
		"type" : "Boolean",
		"default" : "true"
	}, {
		"identifier" : "a",
		"alias" : "my_array",
		"description" : "description",
		"type" : "StringArray",
		"default" : ["My Array","is","a","StringArray"]
		}] 
}
````
**Java Code:**
````java
public static void main(String[] args){
    try{
        /* Create arguments */
        Args arguments = ArgsFactory.createArgsFromFile("./src/definition.args");
        
        /* Parse input */
        arguments.parse(args)
        
        /* Access Values */
        System.out.println(arguments.getStringValue("my_string"));
        System.out.println(arguments.getBooleanValue('o'));
        System.out.println(arguments.getStringArrayValue('a'));
        
    } catch (ArgumentsException e){
    	/* Handle Error your way. E.g.: */
		System.exit(1);
    }
}
````


### Version
0.0.1

Please give me feedback, what I can improve or what you are missing?
