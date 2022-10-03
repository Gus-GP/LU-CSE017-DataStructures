package cs2114.mazesolver;

// -------------------------------------------------------------------------
/**
 * This class is an abstraction that represents an (x, y) coordinate pair. it
 * will be used to indicate the position of things in the maze.
 *
 * @author Gustavo Grinsteins
 * @version 2016.03.15
 */
public class Location
    implements ILocation
{
    // DATA FIELDS
    private int x;
    private int y;


    // CONSTRUCTORS
    // ----------------------------------------------------------
    /**
     * Create a new null Location object for testing.
     */
    public Location()
    {
        // This no arg constructor will be used for testing
    }


    // ----------------------------------------------------------
    /**
     * Create a new Location object.
     *
     * @param x
     *            location
     * @param y
     *            location
     */
    public Location(int x, int y)
    {
        // probably you should write a code
        // that acts when two invalid numbers are inputed
        // are there invalid numbers?
        // Give the values
        this.x = x;
        this.y = y;
    }


    // METHODS
    // Overridden Methods
    /**
     * Returns true id the object passed to it i another Location that has the
     * same x and y coordinates.
     *
     * @return a boolean that tells if the two objects have the same
     *         coordinates.
     * @param o
     *            is the object we are comparing for the code
     */
    @Override
    public boolean equals(Object o)
    {
        // takes care of null and arguments that are not the same type
        // check if they are the same type & check if the object is null
        if ((!(o instanceof Location)) || o == null)
        {
            return false;
        }
        // check if they have the same coordinates
        return (this.x == ((Location)o).x && this.y == ((Location)o).y);

    }


    /**
     * Returns the coordinate formated like "(x, y)" (without the quotes)
     *
     * @return a string with the coordinates
     */
    @Override
    public String toString()
    {
        return "(" + this.x + "," + " " + this.y + ")";
    }


    // regular methods
    // ----------------------------------------------------------
    /**
     * Gets the x-coordinate of the location.
     *
     * @return the x-coordinate of the location
     */
    public int x()
    {
        // check if there is a catch for this method
        return this.x;
    }


    // ----------------------------------------------------------
    /**
     * Gets the y-coordinate of the location.
     *
     * @return the y-coordinate of the location
     */
    public int y()
    {
        return this.y;
    }


    // ----------------------------------------------------------
    /**
     * <p>
     * Gets a new location that represents the (x, y) coordinates one cell north
     * of this location.
     * </p>
     * <p>
     * This method should not perform any bounds checking, because locations are
     * simply (x, y) pairs. If {@code north()} is called on a location
     * representing (0, 0), then the result should be (0, -1).
     * </p>
     *
     * @return a new location representing the (x, y) coordinates one cell north
     *         of this location
     */
    public Location north()
    {

        // implement this method

        return new Location(this.x, (this.y) - 1);
    }


    // ----------------------------------------------------------
    /**
     * <p>
     * Gets a new location that represents the (x, y) coordinates one cell south
     * of this location.
     * </p>
     * <p>
     * This method should not perform any bounds checking, because locations are
     * simply (x, y) pairs.
     * </p>
     *
     * @return a new location representing the (x, y) coordinates one cell south
     *         of this location
     */
    public Location south()
    {
        return new Location(this.x, (this.y) + 1);
    }


    // ----------------------------------------------------------
    /**
     * <p>
     * Gets a new location that represents the (x, y) coordinates one cell west
     * of this location.
     * </p>
     * <p>
     * This method should not perform any bounds checking, because locations are
     * simply (x, y) pairs. If {@code west()} is called on a location
     * representing (0, 0), then the result should be (-1, 0).
     * </p>
     *
     * @return a new location representing the (x, y) coordinates one cell west
     *         of this location
     */
    public Location west()
    {

        return new Location((this.x) - 1, this.y);

    }


    // ----------------------------------------------------------
    /**
     * <p>
     * Gets a new location that represents the (x, y) coordinates one cell east
     * of this location.
     * </p>
     * <p>
     * This method should not perform any bounds checking, because locations are
     * simply (x, y) pairs.
     * </p>
     *
     * @return a new location representing the (x, y) coordinates one cell east
     *         of this location
     */
    public Location east()
    {

        return new Location((this.x) + 1, this.y);

    }

}
