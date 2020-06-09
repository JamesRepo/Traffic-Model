import java.util.Random;

/*
 * ==== Vehicle ====
 * -----------------
 * The base class for all vehicles. 
 * Defines common variables and methods.
 * 
 */
public class Vehicle extends Thread {
	/* The grid and its rows and columns*/
	private GridCell[][] vehicleGrid;
	/* Stores the speed of the vehicle */
	private int vehicleSpeed;
	
	/*
	 * Constructor
	 */
	public Vehicle(GridCell[][] vg) {
		vehicleGrid = vg;
		vehicleSpeed = 0;
	}
	
	/*
	 * Sets the vehicle's speed.
	 * Sets it to a random integer within a range. 
	 * The speed is essentially the time it takes for a vehicle to move from one cell to the next.
	 */
	public void setSpeed() {
		// Variables for the range of speed. Can be changed here if needs to be amended
		int maxSpeed = 2500;
		int minSpeed = 100;
		// Randomly select a value within the range
		Random rnd = new Random();
		// Set the speed
		vehicleSpeed = rnd.nextInt((maxSpeed - minSpeed) + 1) + minSpeed;
	}
	
	/*
	 * Get the speed.
	 */
	public int getSpeed() {
		return vehicleSpeed;
	}
	
	/*
	 * Return the grid so can be used in subclasses.
	 */
	public GridCell[][] getGrid() {
		return vehicleGrid;
	}
	
	/*
	 * Get the rows and columns of the grid.
	 * Need these to give a range in which the vehicles can start.
	 */
	public int getRows() {
		return vehicleGrid.length;
	}
	public int getColumns() {
		return vehicleGrid[0].length;
	}	
}