package de.arguments;

import de.arguments.exceptions.ArgumentException;

public abstract class Arg implements Comparable<Arg> {

	protected Character id;
	protected String alias = "";

	protected Object value;

	protected String description = "";
	protected String type = "";

	protected Arg(char id) {
		this.id = id;
	}

	protected Arg(char id, String alias) {
		this(id);
		this.alias =alias.replaceAll(" ", "_");
	}

	protected Arg(char id, String alias, String description) {
		this(id, alias);
		this.description = description;
	}

	public char getId() {
		return this.id;
	}

	public String getAlias() {
		return this.alias;
	}

	abstract public <T extends Object> T getValue() throws ArgumentException;

	abstract public void setValue(Object value) throws ArgumentException;

	abstract public void setValue(String value) throws ArgumentException;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString(){
		String output = "-"+id;
		
		if(!alias.equals("")){
			output += ", --"+alias;
		}
		
		output += ": <"+type+"> ";
		
		if (!description.equals("")){
			output += description +" ";
		}
		
		return output.trim();
	}
	
	public int compareTo(Arg other) {

		int compare = id.compareTo(other.id);

		if (compare == 0) {
			compare = alias.compareTo(other.alias);
		}

		return compare;
	}

}
