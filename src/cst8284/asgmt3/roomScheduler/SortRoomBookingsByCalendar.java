package cst8284.asgmt3.roomScheduler;
import java.util.Comparator;

/**
 * The class represents the custom Comparator used for for sorting the RoomBooking objects
 * @author Michael Rayson, 040975522
 * @version 3.0
 *
 */
class SortRoomBookingsByCalendar implements Comparator<RoomBooking>{
	
	/**
	 * Overwridden method for the Comparator interface used to compare two RoomBooking objects based on their Calendars
	 * @return integer value representing whether or not the second booking is earlier, later or at the same time
	 */
	public int compare(RoomBooking b1, RoomBooking b2) {
		return b1.getTimeBlock().getStartTime().compareTo(b2.getTimeBlock().getStartTime());
	}
}
