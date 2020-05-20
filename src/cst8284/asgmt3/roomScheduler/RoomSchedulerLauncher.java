
package cst8284.asgmt3.roomScheduler;
import cst8284.asgmt3.room.*;

/**
 * The class instantiates the RoomScheduler object, launching the program
 * @author Michael Rayson, 040975522
 * @version 3.0
 *
 */
public class RoomSchedulerLauncher {
	public static void main(String[] args) {
		/**
		 * Field used to store the type of room bookings will be made for
		 */
		ComputerLab test = new ComputerLab();
		test.setRoomNumber("B119");
		
		/**
		 * Instantiation of the new RoomScheduler object
		 */
		new RoomScheduler(test).launch();
	}
}
