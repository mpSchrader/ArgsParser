package de.arguments;

import de.arguments.exceptions.ArgumentException;

public abstract class Arg implements Comparable<Arg>{

	protected String identifier;
	protected Object value;

	private String description = "";

	protected Arg(String identifier) {
		
		if (!identifier.startsWith("-")){
			this.identifier = "-"+identifier;
		} else {
			this.identifier = identifier;
		}
		
	}

	protected Arg(String identifier, String description) {
		this(identifier);
		this.description = description;
	}

	public String getKey() {
		return this.identifier;
	}

	abstract public <T extends Object> T getValue() throws ArgumentException;
	abstract public void setValue(Object value) throws ArgumentException;
	abstract public void setValue(String value) throws ArgumentException;
	
	public String getUsage() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int compareTo(Arg other){
		return identifier.compareTo(other.identifier);	
	}

}
