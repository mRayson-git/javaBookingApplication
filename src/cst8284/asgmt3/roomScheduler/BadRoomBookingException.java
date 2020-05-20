package cst8284.asgmt3.roomScheduler;

/**
 * The class represents the custom exception used for RoomBooking errors
 * @author Michael Rayson, 040975522
 * @version 3.0
 *
 */
class BadRoomBookingException extends RuntimeException{
	
	/**
	 * Long field used to allow this object to be serializable
	 * Value of: {@value}
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * String field used to store the header for this error
	 */
	private String header;
	
	/**
	 * No-arg constructor for the BadRoomBookingException object
	 */
	protected BadRoomBookingException() {
		this("Bad room booking entered", "Please try again");
	}
	
	/**
	 * 2-arg constructor for the BadRoomBookingException object
	 * @param header a string containing general summary of the error
	 * @param message a detailed description of what caused the error
	 */
	protected BadRoomBookingException(String header, String message) {
		super(message);
		setHeader(header);
	}
	
	/**
	 * Setter for the header field
	 * @param header a string containing general summary of the error
	 */
	public void setHeader(String header) {
		this.header = header;
	}
	
	/**
	 * Getter for the header field
	 * @return header of current BadRoomBookingException object
	 */
	public String getHeader() {
		return header;
	}
	
}
