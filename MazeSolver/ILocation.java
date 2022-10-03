package cs2114.mazesolver;

//--------------------------------------------------------------------------
/**
 * <p>
 * This interface represents an (x, y) coordinate pair, used to indicate
 * cell locations in the maze. Locations should be <strong>immutable</strong>.
 * Immutability means that once you create one you cannot change its
 * coordinates; you can only create new ones with different coordinates.
 * </p><p>
 * Locations do not have references back to a maze; they represent the (x, y)
 * values only.
 * </p><p>
 * You must write a class that implements this interface in your program. In
 * addition to the methods required by this interface, you must also provide:
 * </p>
 * <ul>
 * <li><p>
 * A two-argument constructor that takes the integer x- and y-coordinates of
 * the location, in that order.
 * </p></li>
 * <li><p>
 * An overridden {@code equals(Object)} method that returns true if the object
 * passed in is also a {@code Location} and has the same x- and y-coordinates
 * as the receiver.
 * </p></li>
 * <li><p>
 * An overridden {@code toString()} method that returns a string representation
 * of the location, formatted like "(x, y)".
 * </p></li>
 * </ul>
 *
 * @author  Tony Allevato
 * @version 2013.01.18
 */
public interface ILocation
{
    //~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Gets the x-coordinate of the location.
     *
     * @return the x-coordinate of the location
     */
    public int x();


    // ----------------------------------------------------------
    /**
     * Gets the y-coordinate of the location.
     *
     * @return the y-coordinate of the location
     */
    public int y();


    // ----------------------------------------------------------
    /**
     * <p>
     * Gets a new location that represents the (x, y) coordinates one cell
     * north of this location.
     * </p><p>
     * This method should not perform any bounds checking, because locations
     * are simply (x, y) pairs. If {@code north()} is called on a location
     * representing (0, 0), then the result should be (0, -1).
     * </p>
     *
     * @return a new location representing the (x, y) coordinates one cell
     *     north of this location
     */
    public ILocation north();


    // ----------------------------------------------------------
    /**
     * <p>
     * Gets a new location that represents the (x, y) coordinates one cell
     * south of this location.
     * </p><p>
     * This method should not perform any bounds checking, because locations
     * are simply (x, y) pairs.
     * </p>
     *
     * @return a new location representing the (x, y) coordinates one cell
     *     south of this location
     */
    public ILocation south();


    // ----------------------------------------------------------
    /**
     * <p>
     * Gets a new location that represents the (x, y) coordinates one cell
     * west of this location.
     * </p><p>
     * This method should not perform any bounds checking, because locations
     * are simply (x, y) pairs. If {@code west()} is called on a location
     * representing (0, 0), then the result should be (-1, 0).
     * </p>
     *
     * @return a new location representing the (x, y) coordinates one cell
     *     west of this location
     */
    public ILocation west();


    // ----------------------------------------------------------
    /**
     * <p>
     * Gets a new location that represents the (x, y) coordinates one cell
     * east of this location.
     * </p><p>
     * This method should not perform any bounds checking, because locations
     * are simply (x, y) pairs.
     * </p>
     *
     * @return a new location representing the (x, y) coordinates one cell
     *     east of this location
     */
    public ILocation east();
}
