
package cst8284.asgmt3.roomScheduler;

import java.io.Serializable;

/**
 * The class represents the RoomBooking object
 * @author Michael Rayson, 040975522
 * @version 3.0
 *
 */
public class RoomBooking implements Serializable{
	/**
	 * ContactInfo field used to store the contact info of the RoomBooking object
	 */
	private ContactInfo contactInfo;
	
	/**
	 * Activity field used to store the activity information of the RoomBooking object
	 */
	private Activity activity;
	
	/**
	 * TimeBlock field used to store the start and end times of the RoomBooking object
	 */
	private TimeBlock timeBlock;
	
	/**
	 * Long field used to allow this object to be serializable
	 * Value of: {@value}
	 */
	public static final long serialVersionUID = 1L;
	
	/**
	 * 3-arg constructor for the RoomBooking Object
	 * @param timeBlock an object storing the start and end time of the RoomBooking
	 * @param contactInfo an object storing the contact information of the RoomBooking
	 * @param activity an object storing the activity information of the RoomBooking
	 */
	public RoomBooking(TimeBlock timeBlock, ContactInfo contactInfo, Activity activity) {
		setTimeBlock(timeBlock);
		setContactInfo(contactInfo);
		setActivity(activity);
	}
	
	/**
	 * Getter for the ContactInfo field of the RoomBooking object
	 * @return the contactInfo of the current RoomBooking
	 */
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	
	/**
	 * Setter for the ContactInfo field of the RoomBooking object
	 * @param contactInfo an object storing the contact information of the RoomBooking
	 */
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	/**
	 * Getter for the activity field of the RoomBooking object
	 * @return the current Activity object
	 */
	public Activity getActivity() {
		return activity;
	}
	
	/**
	 * Setter for the activity field of the current RoomBooking object
	 * @param activity an object storing the activity information of the RoomBooking
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	/**
	 * Getter for the TimeBlock field of the current RoomBooking object
	 * @return the timeBlock object
	 */
	public TimeBlock getTimeBlock() {
		return timeBlock;
	}
	
	/**
	 * Setter for the TimeBlock field of the current RoomBooking object
	 * @param timeBlock an objects storing the start and end time of the RoomBooking
	 */
	public void setTimeBlock(TimeBlock timeBlock) {
		this.timeBlock = timeBlock;
	}
	
	/**
	 * Method used to create a string representation of the RoomBooking object
	 * @return a string representation of the RoomBooking object
	 */
	public String toString() {
		return "---------------\n" + getTimeBlock().toString() + "\n" + getActivity().toString() + "\n" + getContactInfo().toString() + "\n---------------";
	}
}


