package cs2114.mazesolver;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This class is in charge to test the maze.
 *
 * @author Gustavo Grinsteins (gug218)
 * @version 2016.03.14
 */
public class MazeTest
    extends TestCase
{
    // ~ Fields ................................................................
    /**
     * New location objects
     */
    private Maze     p;
    private Maze     s;
    private Maze     big;
    private Location n;


    // ~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Create a new test class
     */
    public MazeTest()
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
        // create two maze objects
        p = new Maze(4);
        s = new Maze(3);
        big = new Maze(8);
        n = new Location();
    }


    /**
     * Test the size method
     */
    public void testSize()
    {
        assertEquals(4, p.size());
    }


    /**
     * Test the getStartLocation method
     */
    public void testGetStartLocation()
    {
        // Location ILocation confusion
        Location z = new Location();
        z = (Location)p.getStartLocation();
        assertEquals("(0, 0)", z.toString());

        n = new Location(0, 0);
        assertEquals(true, n.equals(p.getStartLocation()));
    }


    /**
     * Test the setStartLocation method
     */
    public void testSetStartLocation()
    {
        p.setStartLocation(new Location(0, 1));
        n = new Location(0, 1);
        assertEquals(true, n.equals(p.getStartLocation()));
        p.setCell(new Location(1, 1), MazeCell.WALL);
        n = new Location(1, 1);
        p.setStartLocation(n);
        assertEquals(true, n.equals(p.getStartLocation()));
    }


    /**
     * Test the getGoalLocation method
     */
    public void testGetGoalLocation()
    {
        n = new Location(p.size() - 1, p.size() - 1);
        assertEquals(true, n.equals(p.getGoalLocation()));
    }


    /**
     * Test the setGoalLocation method
     */
    public void testSetGoalLocation()
    {
        p.setGoalLocation(new Location(0, 1));
        n = new Location(0, 1);
        assertEquals(true, n.equals(p.getGoalLocation()));
        p.setCell(new Location(0, 2), MazeCell.WALL);
        n = new Location(0, 2);
        p.setGoalLocation(n);
        assertEquals(true, n.equals(p.getGoalLocation()));
    }


    /**
     * Test the getCell method
     */
    public void testGetCell()
    {
        // get something from the board
        assertEquals(MazeCell.UNEXPLORED, p.getCell(new Location(0, 1)));
        // get something you cant get
        assertEquals(MazeCell.INVALID_CELL, p.getCell(new Location(0, -1)));

    }


    /**
     * Test the setCell method
     */
    public void testSetCell()
    {
        // test it with a good input set a wall where the location is
        // not a start or a goal
        p.setGoalLocation(new Location(0, 0));
        p.setStartLocation(new Location(2, 2));
        n = new Location(3, 3);
        p.setCell(n, MazeCell.WALL);
        assertEquals(MazeCell.WALL, p.getCell(n));

        // set something besides a wall
        n = new Location(0, 3);
        p.setCell(n, MazeCell.CURRENT_PATH);
        assertEquals(MazeCell.CURRENT_PATH, p.getCell(n));

        // with an invalid cell location
        p.setCell(new Location(-1, 1), MazeCell.WALL);
        assertEquals(MazeCell.INVALID_CELL, p.getCell(new Location(-1, 1)));

        // test trying to set a cell as a wall where there is a goal
        n = new Location(1, 1);
        p.setGoalLocation(n);

        // this does not change anything
        p.setCell(n, MazeCell.WALL);
        assertEquals(MazeCell.UNEXPLORED, p.getCell(n));

        // test trying to set a cell as a wall where there is a start
        n = new Location(1, 2);
        p.setStartLocation(n);

        // this does not change anything
        p.setCell(n, MazeCell.WALL);
        assertEquals(MazeCell.UNEXPLORED, p.getCell(n));

    }


    /**
     * test the check location method
     */
    public void testCheckLocation()
    {
        n = new Location(1, 1);
        assertEquals(true, p.checkLocation(n));
        n = new Location(1, -1);
        assertEquals(false, p.checkLocation(n));
        n = new Location(-1, -1);
        assertEquals(false, p.checkLocation(n));
        n = new Location(-1, 1);
        assertEquals(false, p.checkLocation(n));
        n = new Location(100, 1);
        assertEquals(false, p.checkLocation(n));
        n = new Location(1, 100);
        assertEquals(false, p.checkLocation(n));
        n = new Location(100, 100);
        assertEquals(false, p.checkLocation(n));
        n = new Location(100, -1);
        assertEquals(false, p.checkLocation(n));
        n = new Location(-1, 100);
        assertEquals(false, p.checkLocation(n));
    }


    /**
     * Test the enumerated type
     */
    public void testEnum()
    {
        MazeCell l = MazeCell.valueOf("WALL");
        assertEquals(true, l.equals(MazeCell.WALL));
    }


    /**
     * Test the solve method
     */

    public void testSolve()
    {
        // simple test
        n = new Location(0, 0);
        p.setGoalLocation(n);
        assertEquals("(0,0)", p.solve());

        // create a simple size 3 maze
        s.setGoalLocation(new Location(1, 0));
        s.setCell(new Location(0, 1), MazeCell.WALL);
        s.setCell(new Location(1, 1), MazeCell.WALL);

        // create a test with turns
        s.setGoalLocation(new Location(2, 2));
        // solve the maze
        assertEquals("(0,0)(1,0)(2,0)(2,1)(2,2)", s.solve());

        // create a test with no solutions
        s.setCell(new Location(2, 1), MazeCell.WALL);
        assertEquals(null, s.solve());
    }


    /**
     * Test the solve method "another one"
     */
    public void testSolve2()
    {
        // start and goal at different location
        p.setGoalLocation(new Location(3, 0));
        p.setStartLocation(new Location(1, 1));
        // set the walls
        p.setCell(new Location(1, 0), MazeCell.WALL);
        p.setCell(new Location(2, 0), MazeCell.WALL);
        p.setCell(new Location(2, 1), MazeCell.WALL);
        p.setCell(new Location(2, 2), MazeCell.WALL);
        // solve the maze
        assertEquals("(1,1)(1,2)(1,3)(2,3)(3,3)(3,2)(3,1)(3,0)", p.solve());
        p.setCell(new Location(3, 3), MazeCell.WALL);
        assertEquals(null, p.solve());
    }


    /**
     * Test the solve method "another one"
     */
    public void testSolve3()
    {
        // set goals with no walls
        big.setGoalLocation(new Location(7, 0));
        big.setStartLocation(new Location(0, 7));
        // place walls
        big.setCell(new Location(1, 7), MazeCell.WALL);
        big.setCell(new Location(1, 6), MazeCell.WALL);
        big.setCell(new Location(1, 5), MazeCell.WALL);
        big.setCell(new Location(1, 4), MazeCell.WALL);
        big.setCell(new Location(1, 3), MazeCell.WALL);
        big.setCell(new Location(1, 2), MazeCell.WALL);
        big.setCell(new Location(1, 1), MazeCell.WALL);
        big.setCell(new Location(3, 0), MazeCell.WALL);
        big.setCell(new Location(3, 1), MazeCell.WALL);
        big.setCell(new Location(3, 2), MazeCell.WALL);
        big.setCell(new Location(3, 3), MazeCell.WALL);
        big.setCell(new Location(3, 4), MazeCell.WALL);
        big.setCell(new Location(3, 5), MazeCell.WALL);
        big.setCell(new Location(3, 6), MazeCell.WALL);
        big.setCell(new Location(4, 6), MazeCell.WALL);
        big.setCell(new Location(5, 6), MazeCell.WALL);
        big.setCell(new Location(6, 6), MazeCell.WALL);
        big.setCell(new Location(7, 4), MazeCell.WALL);
        big.setCell(new Location(6, 4), MazeCell.WALL);
        big.setCell(new Location(4, 4), MazeCell.WALL);
        big.setCell(new Location(4, 3), MazeCell.WALL);
        // solve the maze
        assertEquals(
            "(0,7)(0,6)(0,5)(0,4)(0,3)(0,2)(0,1)(0,0)(1,0)(2,0)"
                + "(2,1)(2,2)(2,3)(2,4)(2,5)(2,6)(2,7)(3,7)(4,7)(5,7)"
                + "(6,7)(7,7)(7,6)(7,5)(6,5)(5,5)(5,4)(5,3)(5,2)(5,1)"
                + "(5,0)(6,0)(6,1)(6,2)(6,3)(7,3)(7,2)(7,1)(7,0)",
            big.solve());

    }

}
