/* Course Name: CST8284
 * Student Name: Michael Rayson 40975522
 * Class Name: Boardroom
 * Date: February 4th 2020
*/
package cst8284.asgmt3.room;

/**
 * This class represents a BoardRoom object, a subclass of the Room object
 * @author Michael Rayson, 040975522
 * @version 3.0
*/
final class Boardroom extends Room{
	/**
	 * integer field used to store the amount of seats in a given room type
	 */
	private int seats;
	
	/**
	 * Constructor for the boardroom, initializes the amount of seats available.
	 */
	public Boardroom() {
		seats = 16;
	}
	
	/**
	 * Getter for the seats field
	 * @return the number of seats
	 */
	protected int getSeats() {
		return seats;
	}
	
	/**
	 * Getter for the room type
	 * @return a string representing the type of room
	 */
	protected String getRoomType() {
		return "board room";
	}
	
	/**
	 * Getter for the details of the room
	 * @return a string containing the details of the room
	 */
	protected String getDetails() {
		return "conference call enabled";
	}
}
