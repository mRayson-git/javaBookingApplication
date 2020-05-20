/* Course Name: CST8284
 * Student Name: Michael Rayson 40975522
 * Class Name: ComputerLab
 * Date: February 4th 2020
*/
package cst8284.asgmt3.room;

/**
 * This class represents a ComputerLab object, a subclass of the Room object
 * @author Michael Rayson, 040975522
 * @version 3.0
*/
public final class ComputerLab extends Room{
	/**
	 * integer field used to store the number of seats in this room
	 */
	private int seats;
	
	/**
	 * no-arg constructor used to intializes the seats to the proper integer
	 */
	public ComputerLab() {
		seats = 30;
	}
	/**
	 * Getter for the seats field
	 * @return the number of seats in this room
	 */
	protected int getSeats() {
		return seats;
	}
	/**
	 * Getter for the room type
	 * @return a string containing the room type
	 */
	protected String getRoomType() {
		return "computer lab";
	}
	/**
	 * Getter for the room details
	 * @return a string containing the room details
	 */
	protected String getDetails() {
		return "contains outlets for 30 laptops";
	}

}
