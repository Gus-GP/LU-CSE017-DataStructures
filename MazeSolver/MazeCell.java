package cs2114.mazesolver;

// -------------------------------------------------------------------------
/**
 * An enumerated type that represents the possible values of a cell in the
 * maze.
 *
 * @author  Tony Allevato
 * @version 2013.01.18
 */
public enum MazeCell
{
    //~ Constants .............................................................

    /**
     * Represents a path in the maze that the explorer has yet to move to.
     */
    UNEXPLORED,

    /**
     * Represents a wall in the maze that blocks the explorer's movement.
     */
    WALL,

    /**
     * Represents a cell along the explorer's current path as she tries to
     * solve the maze.
     */
    CURRENT_PATH,

    /**
     * Represents a cell along a path that the explorer tried but then had to
     * backtrack on because she reached a dead end.
     */
    FAILED_PATH,

    /**
     * Represents an invalid cell.
     */
    INVALID_CELL
}
