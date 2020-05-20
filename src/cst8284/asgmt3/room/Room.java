/* Course Name: CST8284
 * Student Name: Michael Rayson 40975522
 * Class Name: Room
 * Date: February 4th 2020
*/
package cst8284.asgmt3.room;

public abstract class Room {
	
	private static final String DEFAULT_ROOM_NUMBER = "unknown room number";
	private String roomNumber;
	
	protected Room() {
		this(DEFAULT_ROOM_NUMBER);
	}
	protected Room(String roomNum) { 
		setRoomNumber(roomNum); 
	}
	
	public void setRoomNumber(String roomNum) {
		roomNumber = roomNum;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	
    protected abstract String getRoomType() ;
	protected abstract int getSeats();
	protected abstract String getDetails();
	
	public String toString( ) { 
		return getRoomNumber() + " is a " + getRoomType() + " with " + getSeats() + " seats; " + getDetails() +"\n" ;
	}
}
