/*
 *  ==== Grid ====
 *  --------------
 *  Class used to build the grid and refresh it at a certain rate. 
 *  Builds a grid of x rows and columns, specified by the user. 
 *  Extends Thread for use of concurrency. 
 * 
 */
public class Grid extends Thread {
	/* Variables for Dimensions */
	private int rows;
	private int columns;
	/* 2D matrix data structure for the grid */
	private GridCell [][] vehicleGrid;
	/* Constants for draws and refresh rate */
	private final int DRAW = 2000; // Amount of times the grid will be drawn
	private final int REFRESH = 20; // Amount of milliseconds between grid refreshes
	
	/*
	 * Constructor
	 */
	public Grid (int r, int c) {
		// Assign rows and columns
		rows = r;
		columns = c;
		// initialise grid
		vehicleGrid = new GridCell[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int a = 0; a < columns; a++) {
				vehicleGrid[i][a] = new GridCell();
			}
		}
	}
	
	/*
	 * Run method to draw the grid
	 */
	public void run() {
		// loop will draw the grid the amount of times the DRAW constant specifies
		for (int i = 0; i < DRAW; i++) {
			// Try/catch to allow the thread to sleep
			try {
				// String builder to draw the grid, created in the loop so a new grid is created every time and not just appended onto an old one
				StringBuilder gridBuild = new StringBuilder();
				// Creates a border at the top of the grid
				for (int a = 0; a < columns; a++) {
					gridBuild.append("--");
				}
				// New line to begin the traffic modelling
				gridBuild.append("-\n");
				// Loop to get individual cell images
				for (int b = 0; b < rows; b++) {
					for (int c = 0; c < columns; c++) {
						gridBuild.append(vehicleGrid[b][c].getCellImage());
					}
					// Completes final column and starts a new line 
					gridBuild.append("|\n");
				}
				// Finishes border at the bottom of the grid
				for (int d = 0; d < columns; d++) {
					gridBuild.append("--");
				}
				gridBuild.append("-");
				// Labels the drawing of the grid with a number (testing)
				System.out.println("Intersection " + (i + 1));
				// Print grid to console
				System.out.println(gridBuild);
				// Puts the thread to sleep for the amount the refresh rate specifies
				Thread.sleep(REFRESH);
			}
			// Catches interruption exception
			catch (InterruptedException e) {
			}
		}
	}
	
	/*
	 * Get methods for the grid, rows, and columns.
	 */
	public GridCell[][] getGrid() {
		return vehicleGrid;
	}
	public int getRows() {
		return rows;
	}
	public int getColumns() {
		return columns;
	}

}
