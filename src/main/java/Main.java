/*
 * ==== Main ====
 * --------------
 * Main class used to start all of the threads.
 * 
 * 
 */
public class Main {
	public static void main (String[] args) {
		// Create the intersection and specify the amount of rows and columns. (rows, columns)
		Grid intersection = new Grid(10, 20);
		// Start the thread
		intersection.start();
		// While loop to create new vehicles, will continue to do so while the grid thread is running 	
		while (intersection.isAlive()) {
			try {
				Thread.sleep(1000); // Waits 1 second after creating vehicles
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			// Creates one vehicle going South and one East every second
			new TravelEast(intersection.getGrid()).start();
			new TravelSouth(intersection.getGrid()).start();
		}
	}		
}