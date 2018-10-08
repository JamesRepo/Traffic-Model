import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/*
 * ==== GridCell ====
 * ------------------
 * Used to represents each individual cell within the grid.
 * 
 * Contains methods to occupy cells and empty them with locks and conditions used.
 *
 */
public class GridCell {
	/* Locks and conditions */
	private ReentrantLock cellLocked;
	private Condition cellOccupied;
	/* Boolean for if the cell is occupied */
	private boolean isCellOccupied;
	/* Shows what vehicle occupies that cell */
	private String cellImage;
	
	/*
	 * Constructor
	 */
	public GridCell() {
		cellLocked = new ReentrantLock();
		cellOccupied = cellLocked.newCondition();
		isCellOccupied = false;
		cellImage = "| "; // This is the default status of a cell;
	}
	
	/*
	 * Occupy Cell. Uses a lock so can only be accessed by one thread at a time.
	 * The symbol is taken as an argument. 
	 */
	public void occupyCell(String symbol) {
		// Locks it to other threads
		cellLocked.lock();
		// Try/Catch for interrupted exception
		try {
			// Waits for cell to be free
			while(isCellOccupied) {
				cellOccupied.await();
			}
			// Occupies cell
			isCellOccupied = true;
			// Sets the image of the cell to the relevant vehicle
			cellImage = "|" + symbol;
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		// This part is in a finally statement as it needs to happen for the program to continue
		finally {
			cellLocked.unlock();
		}
	}
	/*
	 * Corresponding method which empties cells and signals other threads that they can now move into that cell.
	 * Also resets the cell image to the default.
	 */
	public void emptyCell() {
		// Lock method 
		cellLocked.lock();
		try {
			isCellOccupied = false;
			cellImage = "| "; // default
			cellOccupied.signalAll(); // awakens threads waiting
		}
		finally {
			cellLocked.unlock(); // unlocks method
		}
	}
	
	/*
	 * Get methods for the cell image and status.
	 */
	public String getCellImage() {
		return cellImage;
	}
	public boolean getCellStatus() {
		return isCellOccupied;
	}
}