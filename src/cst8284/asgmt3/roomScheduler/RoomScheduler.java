package cst8284.asgmt3.roomScheduler;
import java.util.Calendar;
import java.util.Scanner;
import cst8284.asgmt3.room.Room;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the RoomScheduler Object, creates and stores roombookings
 * @author Michael Rayson, 040975522
 * @version 3.0
 * 
 */
public class RoomScheduler {
	/**
	 * Scanner field used to record user input for later use
	 */
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * ArrayList of RoomBookings used to store all the RoomBookings
	 */
	private ArrayList<RoomBooking> roomBookings = new ArrayList<>();
	
	/**
	 * Room field used to store the information about the current room
	 */
	private Room room;
	
	/**
	 * int field used to store the value for the "display room information" option
	 * Value of: {@value}
	 */
	private static final int DISPLAY_ROOM_INFORMATION = 1;
	
	/**
	 * int field used to store the value for the "enter room booking" option
	 * Value of: {@value}
	 */
	private static final int ENTER_ROOM_BOOKING = 2;
	
	/**
	 * int field used to store the value for the "delete booking" option
	 * Value of: {@value}
	 */
	private static final int DELETE_BOOKING = 3;
	
	/**
	 * int field used to store the value for the "change booking" option
	 * Value of: {@value}
	 */
	private static final int CHANGE_BOOKING = 4;
	
	/**
	 * int field used to store the value for the "display booking" option
	 * Value of: {@value}
	 */
	private static final int DISPLAY_BOOKING = 5;
	
	/**
	 * int field used to store the value for the "display day bookings" option
	 * Value of: {@value}
	 */
	private static final int DISPLAY_DAY_BOOKINGS = 6;
	
	/**
	 * int field used to store the value for the "save bookings to file" option
	 * Value of: {@value}
	 */
	private static final int SAVE_BOOKINGS_TO_FILE = 7;
	
	/**
	 * int field used to store the value for the "load bookings from file" option
	 * Value of: {@value}
	 */
	private static final int LOAD_BOOKINGS_FROM_FILE = 8;
	
	/**
	 * int field used to store the value for the "exit condition" option
	 * Value of: {@value}
	 */
	private static final int EXIT = 0;
	
	/**
	 * 1-arg constructor for the RoomScheduler object
	 * @param room a Room object used to determine the current room RoomBookings will be scheduled for
	 */
	public RoomScheduler(Room room) {
		setRoom(room);
	}
	
	/**
	 * Method used to begin the process of asking the user for information to create and store RoomBookings
	 */
	public void launch() {
		/**
		 * integer variable used to determine what option the user has decided to chose, arbitrary initial value
		 */
		int action = -1;
		do {
			action = displayMenu();
			executeMenuItem(action);
		} while(action != EXIT);
		System.out.println("Exiting Room Booking Application");
	}
	
	/**
	 * Method used to display a menu of options to the user and return their choice
	 * @return the integer corresponding the choice of the user
	 */
	private int displayMenu() {
		return Integer.parseInt(getResponseTo("\nEnter a selection from the following menu:"
				+ "\n1. Display room information"
				+ "\n2. Enter a room booking"
				+ "\n3. Remove a room booking"
				+ "\n4. Change a room booking"
				+ "\n5. Display a booking"
				+ "\n6. Display room bookings for the whole day"
				+ "\n7. Backup current bookings to file"
				+ "\n6. Load current bookings from file"
				+ "\n0. Exit program\n"));
	}
	
	/**
	 * Method used to select the proper function from a users given input
	 * @param choice an integer that determines which option has been chosen
	 */
	private void executeMenuItem(int choice) {
		switch(choice) {
			case ENTER_ROOM_BOOKING:
				saveRoomBooking(makeBookingFromUserInput());
				break;
			case DISPLAY_BOOKING: 
				displayBooking(makeCalendarFromUserInput(null, true));
				break;
			case DISPLAY_DAY_BOOKINGS:
				displayDayBookings(makeCalendarFromUserInput(null, false));
				break;
			case EXIT:
				saveBookingsToFile();
				break;
			case DELETE_BOOKING:
				System.out.println("Enter a booking to delete");
				deleteBooking(makeCalendarFromUserInput(null, true));
				break;
			case CHANGE_BOOKING:
				System.out.println("Enter booking to change");
				changeBooking(makeCalendarFromUserInput(null, true));
				break;
			case DISPLAY_ROOM_INFORMATION:
				displayRoomInfo();
				break;
			case SAVE_BOOKINGS_TO_FILE:
				saveBookingsToFile();
				System.out.println("Current room bookings backed up to file");
				break;
			case LOAD_BOOKINGS_FROM_FILE:
				getRoomBookings().clear();
				/**
				 * ArrayList of RoomBooking objects used to store what is loaded for easier to more easily transfer them to the private ArrayList
				 */
				ArrayList<RoomBooking> bookings = new ArrayList<>(loadBookingsFromFile());
				for (RoomBooking booking: bookings) {
					getRoomBookings().add(booking);
				}
				break;
		}
	}
	
	/**
	 * Method used to print out the current Room information
	 */
	private void displayRoomInfo() {
		System.out.print(getRoom());
	}
	
	/**
	 * Method used to save a RoomBooking into the ArrayList of RoomBooking objects
	 * @param booking a RoomBooking object to be saved to the ArrayList
	 * @return true or false depending on whether or not the action was complete
	 */
	private boolean saveRoomBooking(RoomBooking booking) {
		/**
		 * Calendar field used to incrementally check if there exists a booking at a given point in the day
		 */
		Calendar index = (Calendar)booking.getTimeBlock().getStartTime().clone();
		for (int i = 0; i < booking.getTimeBlock().duration(); i++) {
			if (findBooking(index) != null) {
				System.out.println("A booking already exists at that time");
				return false;
			}
			else {
				index.set(Calendar.HOUR_OF_DAY, index.get(Calendar.HOUR_OF_DAY) + 1);
			}
		}
		getRoomBookings().add(booking);
		System.out.println("Booking time and date saved.");
		Collections.sort(getRoomBookings(), new SortRoomBookingsByCalendar());
		return true;
	}
	
	/**
	 * Method used to delete a booking from the ArrayList of RoomBookings 
	 * @param cal a calendar object used to remove the correspoinding RoomBooking from the ArrayList
	 * @return true or false depending on whether or not the action succeeded
	 */
	private boolean deleteBooking(Calendar cal) {
		if (findBooking(cal) != null) {
			displayBooking(findBooking(cal).getTimeBlock().getStartTime());
			/**
			 * String field used to store the response of the user pertaining to the deletion of the RoomBooking object
			 */
			String ans = getResponseTo("Press 'Y' to confirm deletion, any other key to abort: ");
			if (ans.equals("Y")) {
				getRoomBookings().remove(findBooking(cal));
				System.out.println("Booking deleted");
				return true;
			} else {
				return false;
			}
		}
		System.out.println("No booking was found in this timeslot");
		return false;	
	}
	
	/**
	 * Method used to change the TimeBlock of a given RoomBooking
	 * @param cal a calendar object used to find the RoomBooking object that is to be changed
	 * @return true or false depending on whether or not the action was successful
	 */
	private boolean changeBooking(Calendar cal) {
		/**
		 * RoomBooking field used to store the booking that is to be changed
		 */
		RoomBooking booking = findBooking(cal);
		/**
		 * TimeBlock field used to store a temporary TimeBlock that we can change without affecting the Bookings TimeBlock while testing different start and end times 
		 */
		TimeBlock tmp = new TimeBlock((Calendar)booking.getTimeBlock().getStartTime().clone(), (Calendar)booking.getTimeBlock().getEndTime().clone());
		displayBooking(cal);
		
		/**
		 * Boolean flag of sorts to determine whether or not a good time slot has been given
		 */
		boolean goodTimeSlot = false;
		while(!goodTimeSlot) {
			try {
				/**
				 * integer field used to store the new proposed time
				 */
				int start = processTimeString(getResponseTo("Enter new Start Time: "));
				/**
				 * integer field used to store the new proposed end time
				 */
				int end = processTimeString(getResponseTo("Enter new End Time: "));
				if (end < start) {
					throw new BadRoomBookingException("End time precedes start time", "The room booking start time must occur before the end time of the room booking.");
				} else if (end == start) {
					throw new BadRoomBookingException("Zero time room booking", "Start and end time of the room bookings are the same. The minimum time for a room booking is one hour.");
				} else {
					tmp.getStartTime().set(Calendar.HOUR_OF_DAY, start);
					tmp.getEndTime().set(Calendar.HOUR_OF_DAY, end);
					for (int i = 0; i < tmp.duration(); i++) {
						if (findBooking(tmp.getStartTime()) != null && !(findBooking(tmp.getStartTime()).getActivity().getDescription() == booking.getActivity().getDescription())) {
							System.out.println("A booking already exists at that time");
							return false;
						}
						else {
							tmp.getStartTime().set(Calendar.HOUR_OF_DAY, tmp.getStartTime().get(Calendar.HOUR_OF_DAY) + 1);
						}
					
					}
					booking.getTimeBlock().getStartTime().set(Calendar.HOUR_OF_DAY, start);
					booking.getTimeBlock().getEndTime().set(Calendar.HOUR_OF_DAY, end);
					System.out.println("Booking has been changed to: ");
					displayBooking(booking.getTimeBlock().getStartTime());
					Collections.sort(getRoomBookings(), new SortRoomBookingsByCalendar());
					goodTimeSlot = true;
				}
			} catch(BadRoomBookingException e) {
				System.out.println(e.getMessage());
			}
		}
		return true;
	}
	
	/**
	 * Method used to display a single RoomBooking object that occurs at the given time
	 * @param cal a calendar object used to find the RoomBooking that is to be displayed
	 * @return the RoomBooking object
	 */
	private RoomBooking displayBooking(Calendar cal) {
		if (findBooking(cal) == null) {
			System.out.println("No booking scheduled between " + cal.get(Calendar.HOUR_OF_DAY) + ":00 and " + (cal.get(Calendar.HOUR_OF_DAY)+1) + ":00");
			return null;
		}
		else {
			System.out.println(findBooking(cal).toString());
			return findBooking(cal);
		}
	}
	
	/**
	 * Method used to display the timeslots throughout a given day along with the RoomBooking objects that are booked within that day
	 * @param cal a calendar object used to determine which day the bookings will be displayed for
	 */
	private void displayDayBookings(Calendar cal) {
		
		/**
		 * integer field used to hold the current hour of the day.
		 */
		int currentTimeSlot = 8;
		cal.set(Calendar.HOUR_OF_DAY, 8);
		/**
		 * Calendar field used to store the endtime of a TimeBlock for the given start time
		 */
		Calendar calEnd = (Calendar)cal.clone();
		calEnd.set(Calendar.HOUR_OF_DAY, calEnd.get(Calendar.HOUR_OF_DAY)+1);
		
		try {
			 while (currentTimeSlot < 22) {
				int exists = Collections.binarySearch(getRoomBookings(), new RoomBooking(new TimeBlock(cal, calEnd), null, null), new SortRoomBookingsByCalendar());
				if (exists != -1) {
					System.out.println(getRoomBookings().get(exists));
					currentTimeSlot = getRoomBookings().get(exists).getTimeBlock().getEndTime().get(Calendar.HOUR_OF_DAY);
					while (getRoomBookings().get(exists+1).getTimeBlock().getStartTime().get(Calendar.DATE) == getRoomBookings().get(exists).getTimeBlock().getEndTime().get(Calendar.DATE) 
							&& getRoomBookings().get(exists+1).getTimeBlock().getStartTime().get(Calendar.MONTH) == getRoomBookings().get(exists).getTimeBlock().getEndTime().get(Calendar.MONTH) 
							&& getRoomBookings().get(exists+1).getTimeBlock().getStartTime().get(Calendar.YEAR) == getRoomBookings().get(exists).getTimeBlock().getEndTime().get(Calendar.YEAR)) {
						exists++;
						while (currentTimeSlot < getRoomBookings().get(exists).getTimeBlock().getStartTime().get(Calendar.HOUR_OF_DAY)) {
							System.out.println("No booking scheduled between " + currentTimeSlot + ":00 and " + (currentTimeSlot + 1) + ":00");
							currentTimeSlot++;
						}
						System.out.println(getRoomBookings().get(exists));
						currentTimeSlot = getRoomBookings().get(exists).getTimeBlock().getEndTime().get(Calendar.HOUR_OF_DAY);
						exists++;
					}
				} else {
					System.out.println("No booking scheduled between " + currentTimeSlot + ":00 and " + (currentTimeSlot + 1) + ":00");
					currentTimeSlot++;
					cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 1);
					calEnd.set(Calendar.HOUR_OF_DAY, calEnd.get(Calendar.HOUR_OF_DAY) + 1);
				}
			 }
		} catch (IndexOutOfBoundsException e) {
		} finally {
			while (currentTimeSlot < 22) {
				System.out.println("No booking scheduled between " + currentTimeSlot + ":00 and " + (currentTimeSlot + 1) + ":00");
				currentTimeSlot++;
			}
		}
	}
	
	/**
	 * Method used to save the current ArrayList of RoomBooking objects to a file stored in the project directory
	 * @return true or false depending on whether or not the action was successful
	 */
	private boolean saveBookingsToFile(){
		Collections.sort(getRoomBookings(), new SortRoomBookingsByCalendar());
		try (FileOutputStream ofs = new FileOutputStream("CurrentRoomBookings.book"); ObjectOutputStream oos = new ObjectOutputStream(ofs);){
			for (RoomBooking booking: getRoomBookings()) {
				oos.writeObject(booking);
			}
			return true;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}
	
	/**
	 * Method used to load and populate the ArrayList of RoomBooking objects with an ObjectInputStream of RoomBooking objects
	 * @return an ArrayList populated with RoomBooking Objects taken from a file in the project directory
	 */
	private ArrayList<RoomBooking> loadBookingsFromFile(){
		/**
		 * RoomBooking object used to hold the value read from the file
		 */
		RoomBooking booking;
		/**
		 * ArrayList of RoomBooking objects used to store all the RoomBooking objects that are read from the file
		 */
		ArrayList<RoomBooking> bookings = new ArrayList<>();
		try(FileInputStream fis = new FileInputStream("CurrentRoomBookings.book"); ObjectInputStream ois = new ObjectInputStream(fis);){
			while(true) {
				booking = (RoomBooking) ois.readObject();
				bookings.add(booking);
			}
		} catch (EOFException e) {
			System.out.println("Current room bookings loaded from file");
			return bookings;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Method used to retrieve user input.
	 * @param s a string storing the question for the user
	 * @return the users inputted value
	 */
	private String getResponseTo(String s) {
		System.out.print(s);
		/**
		 * Boolean field used to determine whether the user has entered a field that is acceptable (i.e. not empty or null)
		 */
		boolean goodInput = false;
		while (!goodInput) {
			try {
				/**
				 * String value used to store the users input
				 */
				String a = scan.nextLine();
				if (a.isEmpty()) {
					throw new BadRoomBookingException("Missing value", "Missing an input value");
				} else if (a == null) {
					throw new BadRoomBookingException("Null value entered", "An attempt was made to pass a null value to a variable.");
				}
				return a;
			} catch (BadRoomBookingException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	/**
	 * Method used to create RoomBooking objects from the user's input. 
	 * @return a RoomBooking object
	 */
	private RoomBooking makeBookingFromUserInput() {
		/**
		 * ContactInfo field used to store the information retrieved about the Contact later on
		 */
		ContactInfo a = null;
		/**
		 * Activity field used to store the information retrieved about the Activity later on
		 */
		Activity b = null;
		/**
		 * TimeBlock field used to store the information retrieved about the TimeBlock later on
		 */
		TimeBlock c = null;
		
		/**
		 * Boolean field used to determine whether or not the user has given good contact info (i.e. no exceptions were thrown)
		 */
		boolean goodContactInfo = false;
		while (!goodContactInfo) {
			try {
				/**
				 * Array of Strings used to hold the client's name
				 */
				String[] clientName = getResponseTo("Enter Client Name (as FirstName LastName): ").split(" ");
				/**
				 * String used to hold the clients phone number
				 */
				String phoneNumber = getResponseTo("Phone Number (e.g. 613-555-1212): ");
				/**
				 * String used to store the organization
				 */
				System.out.print("Organization (optional): "); String organization = scan.nextLine();
				if (organization.equals("")) {
					a = new ContactInfo(clientName[0], clientName[1], phoneNumber);
				}
				else {
					a = new ContactInfo(clientName[0], clientName[1], phoneNumber, organization);
				}
				goodContactInfo = true;
			} catch(BadRoomBookingException e) {
				System.out.println(e.getMessage());
			}
		}
		
		/**
		 * String used to store the category of the event
		 */
		String category = getResponseTo("Enter event category: ");
		/**
		 * String used to store the description of the event
		 */
		String description = getResponseTo("Enter detailed description of event: ");
		b = new Activity(description, category);
		/**
		 * Boolean value used to determine whether or not the user entered good room booking information (i.e. Calendar was created properly)
		 */
		boolean goodRoomBooking = false;
		while (!goodRoomBooking) {
			try {
				/*
				 * Calendar field used to store the start time of the TimeBlock
				 */
				Calendar startTime = makeCalendarFromUserInput(null, true);
				/**
				 * Calendar field used to store the end time of the TimeBlock
				 */
				Calendar endTime = (Calendar)startTime.clone();
				endTime = makeCalendarFromUserInput(endTime, true);
				c = new TimeBlock(startTime, endTime);
				goodRoomBooking = true;
			} catch (BadRoomBookingException e) {
				System.out.println(e.getMessage());
				goodRoomBooking = false;
			}
		}
		
		return new RoomBooking(c, a, b);
		
	}
	
	/**
	 * Method used to create a Calendar object from the user input
	 * @param cal a calendar object that determines whether a new calendar is made (when it itself is null) or whether an end time is going to be created
	 * @param requestHour a boolean value used to determine whether the user is asked to enter an HOUR_OF_DAY value for the calendar
	 * @return a Calendar Object ready for use.
	 */
	private Calendar makeCalendarFromUserInput(Calendar cal, boolean requestHour){
		if (cal == null) {
			/**
			 * Boolean field used to determine whether or not the user entered good calendar information, (end time not before start, proper format, etc...)
			 */
			boolean goodCalendarInput = false;
			while (!goodCalendarInput) {
				cal = new Calendar.Builder().build();
				cal.setLenient(false);
				try {
					/**
					 * String field used to store the date of the event
					 */
					String date = getResponseTo("Event Date (Entered as DDMMYYYY): ");
					if (date.length() > 8) {
						throw new BadRoomBookingException("Bad Calendar format","Bad calendar date was entered. The correct format is DDMMYYYY.");
					}
					cal.clear();
					cal.set(Integer.parseInt(date.substring(4)), Integer.parseInt(date.substring(2,4)), Integer.parseInt(date.substring(0,2)));
					goodCalendarInput = true;
				} catch (IllegalArgumentException f) {
					throw new BadRoomBookingException("Bad Calendar format","Bad calendar date was entered. The correct format is DDMMYYYY.");
				}
			}
			
			if (requestHour) {
				cal.clear(Calendar.HOUR_OF_DAY);
				cal.set(Calendar.HOUR_OF_DAY,processTimeString(getResponseTo("Start Time: ")));
			}
		}
		else {
			if (requestHour) {
				cal.clear(Calendar.HOUR_OF_DAY);
				cal.set(Calendar.HOUR_OF_DAY,processTimeString(getResponseTo("End Time: ")));
			}
		}
		return cal;
	}
	
	/**
	 * Method used to process and format a given time string into a unified form.
	 * @param t a string that is storing the time to be formatted
	 * @return an integer value between 1-24
	 */
	private int processTimeString(String t) {
		t = t.trim();
		if (t.contains(":")) {
			t=t.split(":")[0];
			return Integer.parseInt(t);
		}
		else if (t.contains("a") || t.contains("12")){
			t=t.split(" ")[0];
			return Integer.parseInt(t);
		}
		else if (t.contains("p")) {
			t=t.split(" ")[0];
			return Integer.parseInt(t) + 12;
		}
		else {
			return -1;
		}
	}
	
	/**
	 * Method used to find and retrieve RoomBooking objects from the ArrayList of RoomBooking objects by the Calendar date passed.
	 * @param cal a Calendar object used to find if a RoomBooking exists
	 * @return a RoomBooking object if one is found, otherwise null
	 */
	private RoomBooking findBooking(Calendar cal) {
		/**
		 * Calendar field used to create an endtime 1 hour after the given calendar to create a "TimeBlock" that we can test against
		 */
		Calendar calEnd = (Calendar)cal.clone();
		calEnd.set(Calendar.HOUR_OF_DAY, calEnd.get(Calendar.HOUR_OF_DAY)+1);
		int exists = Collections.binarySearch(getRoomBookings(), new RoomBooking(new TimeBlock(cal, calEnd), null, null), new SortRoomBookingsByCalendar());
		try {
			return getRoomBookings().get(exists);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * Getter for the ArrayList of RoomBooking Objects
	 * @return the ArrayList of RoomBooking Objects
	 */
	private ArrayList<RoomBooking> getRoomBookings() {
		return roomBookings;
	}
	
	/**
	 * Setter for the room field of the RoomScheduler object
	 * @param room a Room object used to set the current room field
	 */
	private void setRoom(Room room) {
		this.room = room;
	}
	
	/**
	 * Getter for the room field of the RoomScheduler object
	 * @return the room field of the RoomShceduler object
	 */
	private Room getRoom() {
		return room;
	}
}
