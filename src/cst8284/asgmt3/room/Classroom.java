/* Course Name: CST8284
 * Student Name: Michael Rayson 40975522
 * Class Name: ClassRoom
 * Date: February 4th 2020
*/
package cst8284.asgmt3.room;

/**
 * This class represents a Classroom object, a subclass of the Room object
 * @author Michael Rayson, 040975522
 * @version 3.0
*/
final class Classroom extends Room{
	/**
	 * integer field containing the number of seats in this room
	 */
	private int seats;
	
	/**
	 * no-arg constructor for this room type, initializes the seats field to the proper integer
	 */
	public Classroom() {
		seats = 30;
	}
	/**
	 * Getter for the seats field
	 * @return the number of seats available
	 */
	protected int getSeats() {
		return seats;
	}
	/**
	 * Getter for the room type
	 * @return a string containing the room type
	 */
	protected String getRoomType() {
		return "class room";
	}
	
	/**
	 * Getter for the room details
	 * @return a string containing the room details
	 */
	protected String getDetails() {
		return "contains overhead projector";
	}
}
