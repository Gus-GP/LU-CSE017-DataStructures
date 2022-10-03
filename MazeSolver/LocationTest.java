package cs2114.mazesolver;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This class is in charge to test location.
 *
 * @author Gustavo Grinsteins (gug218)
 * @version 2016.03.14
 */
public class LocationTest
    extends TestCase
{
    // ~ Fields ................................................................
    /**
     * New location objects
     */
    private Location s;
    private Location n;
    private Object   o;


    // ~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Create a new test class
     */
    public LocationTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    // ~ Public methods ........................................................
    // ----------------------------------------------------------
    /**
     * Set up the different object that we are going to test
     */
    public void setUp()
    {
        s = new Location(1, 3);
        n = new Location();
        o = new Object();
    }


    /**
     * Test the equals method
     */
    public void testEquals()
    {
        // test with null location
        assertEquals(false, s.equals(n));
        // test with not a location object
        assertEquals(false, s.equals(o));
        // test with a location object with different coordinates
        n = new Location(2, 3);
        assertEquals(false, s.equals(n));
        // test with a location that has the same coordinates
        n = new Location(1, 3);
        assertEquals(true, s.equals(n));
    }


    /**
     * Test the x method
     */
    public void testX()
    {
        // what if null or not a location?
        // test getting an x from Location object
        assertEquals(1, s.x());
        n = new Location(100, 3);
        assertEquals(100, n.x());
    }


    /**
     * Test the y method
     */
    public void testY()
    {
        // what if null or not a location?
        // test getting an x from Location object
        assertEquals(3, s.y());
        n = new Location(100, 3);
        assertEquals(3, s.y());
    }


    /**
     * Test the North method
     */
    public void testNorth()
    {
        n = new Location(1, 2);
        assertEquals(n, s.north());
    }


    /**
     * Test the South method
     */
    public void testSouth()
    {
        n = new Location(1, 4);
        assertEquals(n, s.south());

    }


    /**
     * Test the West method
     */
    public void testWest()
    {
        n = new Location(0, 3);
        assertEquals(n, s.west());
    }


    /**
     * Test the East method
     */
    public void testEast()
    {
        n = new Location(2, 3);
        assertEquals(n, s.east());
    }


    /**
     * Test the toString method
     */
    public void testToString()
    {
        n = new Location(2, 5);
        assertEquals("(2, 5)", n.toString());

        assertEquals("(1, 3)", s.toString());
    }

}
