# ArgsParser
### A parser for command-line arguments in Java
This is a first draft and will be improved.

Available argument types: 
* Boolean
* Double
* Integer
* String

````java
public static void main(String[] args){
    try{
        /* Create Information Strucutre */
        Args arguments = new Args();
        arguments.add(new RequiredInteger('t', "time"));
        arguments.add(new OptionalBoolean('v', "verbose", true));
        
        /* Parse input */
        arguments.parse(args)
        
        /* Access Values */
        System.out.println(arguments.getIntegerValue('t');
        System.out.println(arguments.getBooleanValue('v');
        
    } catch (ArgumentsException e){
    
    }
}
````

### Version
0.0.1
