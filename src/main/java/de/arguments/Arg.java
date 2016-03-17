package de.arguments;

import de.arguments.exceptions.ArgumentsException;

/**
 * 
 * @author Max-Philipp Schrader
 *
 */
public abstract class Arg implements Comparable<Arg> {

	protected Character id;
	protected String alias = "";

	protected Object value;

	protected String description = "";
	protected String type = "";

	/**
	 * Creates an Arg, which can only be identified by the id.
	 * 
	 * @param id
	 */
	protected Arg(char id) {
		this.id = id;
	}

	/**
	 * Creates an Arg, which can be identified by the id as well as the alias.
	 * 
	 * @param id
	 * @param alias
	 */
	protected Arg(char id, String alias) {
		this(id);
		this.alias = alias.replaceAll(" ", "_");
	}

	/**
	 * 
	 * @return
	 */
	public char getId() {
		return this.id;
	}

	/**
	 * 
	 * @return
	 */
	public String getAlias() {
		return this.alias;
	}

	/**
	 * 
	 * @return
	 * @throws ArgumentsException
	 */
	abstract public <T extends Object> T getValue() throws ArgumentsException;

	/**
	 * 
	 * @param value
	 * @throws ArgumentsException
	 */
	abstract public void setValue(Object value) throws ArgumentsException;

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	@Override
	public String toString() {
		String output = "-" + id;

		if (!alias.equals("")) {
			output += ", --" + alias;
		}

		output += ": <" + type + "> ";

		if (!description.equals("")) {
			output += description + " ";
		}

		return output.trim();
	}

	/**
	 * Checks this compares two arguments with each other in regards to there id
	 * and alias.
	 * 
	 * @param Arg
	 * @return
	 */
	public int compareTo(Arg other) {

		int compare = id.compareTo(other.id);

		if (compare == 0) {
			compare = alias.compareTo(other.alias);
		}

		return compare;
	}

	/**
	 * 
	 * @return
	 */
	public boolean valueSet() {
		return this.value != null;
	}

	/**
	 * 
	 * @return
	 */
	public boolean valueNotSet() {
		return this.value == null;
	}

}
