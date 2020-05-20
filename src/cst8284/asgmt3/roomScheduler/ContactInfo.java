package cst8284.asgmt3.roomScheduler;

import java.io.Serializable;

/**
 * The class represents the contact info object
 * @author Michael Rayson, 040975522
 * @version 3.0
 *
 */
public class ContactInfo implements Serializable{
	
	/**
	 * String field used to hold the firstName of the ContactInfo
	 */
	private String firstName;
	
	/**
	 * String field used to hold the lastName of the ContactInfo
	 */
	private String lastName;
	
	/**
	 * String field used to hold the phoneNumber of the ContactInfo
	 */
	private String phoneNumber;
	
	/**
	 * String field used to hold the organization of the ContactInfo
	 */
	private String organization;
	
	/**
	 * Long field used to allow this object to be serializable
	 * Value of: {@value}
	 */
	public static final long serialVersionUID = 1L;
	
	/**
	 * 3-arg constructor used to create the ContactInfo object by chaining with the 4-arg constructor
	 * @param firstName a string containing the first name of the contact
	 * @param lastName a string containing the last name of the contact
	 * @param phoneNumber a string containing the phone number of the contact
	 */
	public ContactInfo(String firstName, String lastName, String phoneNumber) {
		this(firstName, lastName, phoneNumber, "Algonquin College");
	}
	
	/**
	 * 4-arg constructor for the ContactInfo object
	 * @param firstName a string containing the first name of the contact
	 * @param lastName a string containing the last name of the contact
	 * @param phoneNumber a string containing the phone number of the contact
	 * @param organization a string containing the organization of the contact
	 */
	public ContactInfo(String firstName, String lastName, String phoneNumber, String organization){
		setFirstName(firstName);
		setLastName(lastName);
		setPhoneNumber(phoneNumber);
		setOrganization(organization);
	}
	
	/**
	 * Getter for the firstName field
	 * @return the first name of the contact
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Setter for the firstName field of the ContactInfo object. Can throw a BadRoomBookingException if invalid String is provided
	 * @param firstName a string containing the first name of the contact
	 * @throws BadRoomBookingException thrown when the name contains illegal characters (any none letter not in (-,.,')
	 */
	public void setFirstName(String firstName) throws BadRoomBookingException{
		/**
		 * Array of chars used to store the first name so that we may iterate through it
		 */
		char[] fna = firstName.toCharArray();
		for (char test: fna) {
			if (!Character.isLetter(test) && (test != '-' || test != '.' || test != '\'')) {
				throw new BadRoomBookingException("Name contains illegal characters", "A name cannot include characters other than alphabetic characters, the dash (-), the period (.), and the apostrophe (').");
			} else if (firstName.length() > 30) {
				throw new BadRoomBookingException("Name length exceeded", "The first or last name exceeds the 30 character maximum allowed.");
			} else {
				this.firstName = firstName;
			}
		}
	}
	
	/**
	 * Getter for the lastName field of the ContactInfo object
	 * @return the last name of the contact
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Setter for the lastName field of the ContactInfo object. Throws BadRoomBookingException if invalid String is passed.
	 * @param lastName a string containing the last name of the contact
	 * @throws BadRoomBookingException thrown when the name contains illegal characters (any none letter not in (-,.,')
	 */
	public void setLastName(String lastName) throws BadRoomBookingException{
		char[] fna = lastName.toCharArray();
		for (char test: fna) {
			if (!Character.isLetter(test) && (test != '-' || test != '.' || test != '\'')) {
				throw new BadRoomBookingException("Name contains illegal characters", "A name cannot include characters other than alphabetic characters, the dash (-), the period (.), and the apostrophe (').");
			} else if (lastName.length() > 30) {
				throw new BadRoomBookingException("Name length exceeded", "The first or last name exceeds the 30 character maximum allowed.");
			} else {
				this.lastName = lastName;
			}
		}
		
	}
	
	/**
	 * Getter for the phoneNumber field of the ContactInfo object
	 * @return the phone number of the Contact
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * Setter for the phoneNumber field of the ContactInfo object. Will throw a BadRoomBookingException is invalid String is passed.
	 * @param phoneNumber a string containing the phone number of the contact
	 * @throws BadRoomBookingException thrown when the phone number is not in the proper format (no "-", improper length, etc...)
	 */
	public void setPhoneNumber(String phoneNumber) throws BadRoomBookingException{
		try {
			if (phoneNumber.contains("-") && phoneNumber.length() == 12) {
				/**
				 * String array for the phone number splitting each section at the "-"
				 */
				String[] test = phoneNumber.split("-");
				for (String i: test) {
					Integer.decode(i);
				}
			}else {
				throw new BadRoomBookingException("Bad telephone number", "The telephone number must be a 10-digit number, separated by \"-\" in the form, e.g. 613-555-1212");
			}
			this.phoneNumber = phoneNumber;
		} catch (NumberFormatException e) {
			throw new BadRoomBookingException("Bad telephone number", "The telephone number must be a 10-digit number, separated by \"-\" in the form, e.g. 613-555-1212");
		}
	}
	
	/**
	 * Getter for the organization field of the ContactInfo object
	 * @return the organization of the contact
	 */
	public String getOrganization() {
		return organization;
	}
	
	/**
	 * Setter for the organization field of the ContactInfo object
	 * @param organization a string containing the organization for the contact
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	/**
	 * Method used to create a string representation of the ContactInfo object
	 * @return a string representation of the ContactInfo object
	 */
	public String toString() {
		return "Contact Information: " + getFirstName() + " " + getLastName() + "\nPhone: " + getPhoneNumber() + "\n" + getOrganization();
	}

}
