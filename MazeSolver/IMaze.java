package cs2114.mazesolver;

// --------------------------------------------------------------------------
/**
 * <p>
 * This interface represents a maze in your maze solver application. You must
 * write a class that implements this interface to represent the maze using a
 * square 2-dimensional array (same width and height) of {@link MazeCell} values
 * and make it support the operations in this interface.
 * </p>
 * <p>
 * Your maze class must also provide a constructor that takes a single argument,
 * which represents the size of the maze. All mazes are square, so the size
 * represents the width and the height. For example, a maze of size 5 would have
 * width 5 and height 5, for a total of 25 cells.
 * </p>
 * <p>
 * When a maze is first created, every cell should be set to
 * {@link MazeCell#UNEXPLORED}. Furthermore, its starting point should be at
 * location (0, 0) (the upper-left corner) and its goal should be at (size - 1,
 * size - 1) (the lower-right corner). These can be changed later by calling the
 * {@link #setStartLocation(ILocation)} and {@link #setGoalLocation(ILocation)}
 * methods.
 * </p>
 *
 * @author Tony Allevato
 * @version 2013.01.18
 */
public interface IMaze
{
    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Gets the size of the maze. Since mazes are square, the size of the maze
     * is the number of cells in either dimension (width or height).
     *
     * @return the size of the maze
     */
    public int size();


    // ----------------------------------------------------------
    /**
     * Gets the starting location of the maze.
     *
     * @return the starting location in the maze
     */
    public ILocation getStartLocation();


    // ----------------------------------------------------------
    /**
     * <p>
     * Sets the starting location in the maze.
     * </p>
     * <p>
     * This method must check to see if there is a wall at the desired new
     * location for the starting point. If there is, you must destroy the wall.
     * </p>
     *
     * @param location
     *            the new starting location in the maze
     */
    public void setStartLocation(ILocation location);


    // ----------------------------------------------------------
    /**
     * Gets the location of the goal in the maze.
     *
     * @return the location of the goal in the maze
     */
    public ILocation getGoalLocation();


    // ----------------------------------------------------------
    /**
     * <p>
     * Sets the location of the goal in the maze.
     * </p>
     * <p>
     * This method must check to see if there is a wall at the desired new
     * location for the goal. If there is, you must destroy the wall.
     * </p>
     *
     * @param location
     *            the new location of the goal in the maze
     */
    public void setGoalLocation(ILocation location);


    // ----------------------------------------------------------
    /**
     * Gets the type of cell at the specified location in the maze. If the
     * location is outside the bounds of the maze, then you must return
     * {@link MazeCell#INVALID_CELL}. Under no circumstances should this method
     * ever throw an exception.
     *
     * @param location
     *            the location to check
     * @return a value from the {@link MazeCell} enumerated type that indicates
     *         the type of cell at that location
     */
    public MazeCell getCell(ILocation location);


    // ----------------------------------------------------------
    /**
     * <p>
     * Sets a location in the maze to be the specified cell type.
     * </p>
     * <p>
     * There is one special condition that this method must check for: if the
     * cell type is {@link MazeCell#WALL} and the location is either the
     * starting location or the goal location, then this method must
     * <strong>ignore the request and change nothing.</strong>
     * </p>
     * <p>
     * Under no circumstances should this method throw an exception,
     * {@code IndexOutOfBounds} or otherwise. If the location given is outside
     * the bounds of the maze, then do nothing.
     * </p>
     *
     * @param location
     *            the location where the wall should be placed
     * @param cell
     *            the cell we are working with
     */
    public void setCell(ILocation location, MazeCell cell);


    // ----------------------------------------------------------
    /**
     * <p>
     * Tries to find a solution to the maze. If a solution is found, it should
     * be returned as a string that contains the coordinates of each cell in a
     * path that starts at the maze's starting point and leads to the goal,
     * formatted like this (spacing doesn't matter):
     * </p>
     *
     * <pre>
     * (0, 0) (0, 1) (1, 1) (2, 1) (2, 2)
     * </pre>
     * <p>
     * If the maze has no solution, this method should return null.
     * </p>
     * <p>
     * (If the maze has more than one possible solution, you may return any of
     * them.)
     * </p>
     *
     * @return a string representing a solution path if one exists, or null if
     *         there is no solution
     */
    public String solve();
}
