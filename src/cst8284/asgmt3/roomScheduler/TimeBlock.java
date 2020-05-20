package cst8284.asgmt3.roomScheduler;
import java.io.Serializable;
import java.util.Calendar;

/**
 * The class represents the TimeBlock object, used to create a timeframe for the RoomBooking object
 * @author Michael Rayson, 040975522
 * @version 3.0
 *
 */
public class TimeBlock implements Serializable{
	/**
	 * Calendar field used to store the startTime of the time block
	 */
	private Calendar startTime;
	
	/**
	 * Calendar field used to store the endTime of the time block
	 */
	private Calendar endTime;
	
	/**
	 * Long field used to allow this object to be serializable
	 * Value of: {@value}
	 */
	public static final long serialVersionUID = 1L;
	
	/**
	 * No arg constructor for the TimeBlock class that chains with the 2 arg constructor to make a default timeBlock spanning from 8 am to 10 pm
	 */
	public TimeBlock() {
		this(new Calendar.Builder().set(Calendar.HOUR_OF_DAY, 8).build(), new Calendar.Builder().set(Calendar.HOUR_OF_DAY, 22).build());
	}
	
	/**
	 * 2-arg constructor used to create a TimeBlock object given a specific start and end time.
	 * @param start a Calendar object used to store the start time of the TimeBlock
	 * @param end a Calendar object used to store the end time of the TimeBlock
	 */
	public TimeBlock(Calendar start, Calendar end){
		try {
			setStartTime(start);
			setEndTime(end);
		} catch(IllegalArgumentException e) {
			throw new BadRoomBookingException("Bad Calendar format","Bad calendar date was entered. The correct format is DDMMYYYY.");
		}
		
	}
	
	/**
	 * Getter for the startTime field
	 * @return the startTime field
	 */
	public Calendar getStartTime() {
		return startTime;
	}
	
	/**
	 * Setter for the startTime field
	 * @param startTime a Calendar object used to store the start time of the TimeBlock
	 */
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * Getter for the endTime field
	 * @return the endTime field
	 */
	public Calendar getEndTime() {
		return endTime;
	}
	
	/**
	 * Setter for the endTime field. Throws BadRoomBooking exception when endTime precedes startTime or if it is the same time as the startTime.
	 * @param endTime a Calendar object used to store the end time of the TimeBlock
	 * @throws BadRoomBookingException thrown when the end time precedes the start time, or if a timeblock of zero time is proposed
	 */
	public void setEndTime(Calendar endTime) throws BadRoomBookingException{
		if (endTime.get(Calendar.HOUR_OF_DAY) < getStartTime().get(Calendar.HOUR_OF_DAY)) {
			throw new BadRoomBookingException("End time precedes start time", "The room booking start time must occur before the end time of the room booking.");
		} else if (endTime.get(Calendar.HOUR) == getStartTime().get(Calendar.HOUR_OF_DAY)) {
			throw new BadRoomBookingException("Zero time room booking", "Start and ent time of the room booking are the same. The minimum time for a room booking is one hour.");
		} else {
			this.endTime = endTime;
		}
	}
	
	/**
	 * Method used to determine if two TimeBlocks overlap.
	 * @param newBlock a TimeBlock object given to check if they overlap
	 * @return true if they do, false if they do not
	 */
	public boolean overlaps(TimeBlock newBlock) {
		return !(newBlock.getStartTime().get(Calendar.DATE) != this.getStartTime().get(Calendar.DATE) || newBlock.getStartTime().get(Calendar.MONTH) != this.getStartTime().get(Calendar.MONTH) || newBlock.getStartTime().get(Calendar.YEAR) != this.getStartTime().get(Calendar.YEAR))
			&& !(newBlock.getEndTime().get(Calendar.HOUR_OF_DAY) <= this.getStartTime().get(Calendar.HOUR_OF_DAY) || newBlock.getStartTime().get(Calendar.HOUR_OF_DAY) >= this.getEndTime().get(Calendar.HOUR_OF_DAY));
	}
	
	/**
	 * Method used to determine the length of the TimeBlock
	 * @return an integer representing the length of time between the start and end of the TimeBlock
	 */
	public int duration() {
		return endTime.get(Calendar.HOUR_OF_DAY) - startTime.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * Method used to create a string representation of the TimeBlock
	 * @return a string representation of the TimeBlock
	 */
	public String toString() {
		return getStartTime().get(Calendar.HOUR_OF_DAY) + ":00 - " + getEndTime().get(Calendar.HOUR_OF_DAY) + ":00";
	}
}
