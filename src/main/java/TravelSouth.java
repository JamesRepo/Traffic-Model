import java.util.Random;
/*
 * ==== Travel South ====
 * ---------------------
 * Sub class of vehicle which handles vehicles going from north to south. 
 * Extends Vehicle and implements Runnable.
 * 
 */
public class TravelSouth extends Vehicle implements Runnable {
	/* Specifies what row the thread will start in. */
	private int startCell;
	/* Essentially a counter to keep track of the cells movements */
	private int movingCell;
	/* String representation for this vehicle */
	private final String cellImage = "o";
	
	/*
	 * Constructor
	 */
	public TravelSouth(GridCell[][] vg) {
		super(vg);
		startCell = 0;
		movingCell = 0;
	}
	
	/*
	 * Sets the row in which the particular thread will start.
	 */
	public void setColumnLane(int noOfColumns) {
		Random rnd = new Random();
		startCell= rnd.nextInt(noOfColumns);
	}
	
	/*
	 * Methods for setting the cells.
	 */
	public GridCell getNewCell() {
		return super.getGrid()[movingCell++][startCell];
	}
	public GridCell getOldCell() {
		return super.getGrid()[movingCell - 1][startCell];
	}
	public GridCell getFutureCell(int z) {
		return super.getGrid()[z + 1][startCell];
	}

	/*
	 * Run method for the thread.
	 */
	public void run() {
		// Method in Vehicle class to set speed
		setSpeed();
		// Set the starting row
		setColumnLane(getRows());
		// For loop for the movement of the vehicle
		for (int i = 0; i < getRows(); i++) {
			// If statement to determine in the vehicle is at the end of the road
			if (movingCell == getRows() - 1) {
				getNewCell().occupyCell(cellImage);
				try {
					Thread.sleep(getSpeed()); // Sleeps for the speed of the vehicle
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				getOldCell().emptyCell(); // Empties the cell
				return; // Breaks out of the loop here
			}
			// Move into a new cell and set it to the relevant image
			getNewCell().occupyCell(cellImage);
			try {
				Thread.sleep(getSpeed()); // Sleeps for the speed of the vehicle
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			// While loop to check if the next cell is occupied and that the current one isn't deleted. Waits if it is.
			while (getFutureCell(i).getCellStatus()) {
				try {
					Thread.sleep(10);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			getOldCell().emptyCell();
		}
	}
}