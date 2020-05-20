package cst8284.asgmt3.roomScheduler;

import java.io.Serializable;

/**
 * This class represents an Activity object, containing the description
 * and category of a given activity
 * @author Michael Rayson, 040975522
 * @version 3.0
 * 
*/
public class Activity implements Serializable{
	/**
	 * String field used to hold the description of the activity
	 */
	private String description;
	
	/**
	 * String field used to hold the category of the activity 
	 */
	private String category;
	
	/**
	 * Long field used to allow this object to be serializable
	 * Value of: {@value}
	 */
	public static final long serialVersionUID = 1L;
	
	/**
	 * 2-arg constructor for the activity class, taking in a String for the description
	 * and a String for the category
	 * @param description a description of the activity
	 * @param category a category for the activity
	 */
	public Activity(String description, String category) {
		setDescription(description);
		setCategory(category);
	}
	
	/**
	 * Getter for the description field
	 * @return the description of the activity
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Setter for the description field
	 * @param description a description for the activity
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Getter for the category field
	 * @return the category of the activity
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Setter for the category field
	 * @param category a category for the activity
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * Method used to create a string representation of the activity
	 * @return a string representation of the activity
	 */
	public String toString() {
		return "Event:" + getCategory() + "\nDescription: " + getDescription();
	}
}
