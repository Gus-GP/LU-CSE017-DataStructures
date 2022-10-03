package cs2114.mazesolver;

// -------------------------------------------------------------------------
/**
 * This class will be an abstraction that represents a maze using a 2D array
 *
 * @author Gustavo Grinsteins
 * @version 2016.03.15
 */
public class Maze
    implements IMaze
{
    // DATA FIELDS
    private int          mazeSize;
    private MazeCell[][] mazeBoard;
    private Location     start;
    private Location     goal;
    // this stack will hold locations that will help navigate through
    // the maze
    // private ArrayListStack<Location> stack; // stack initialization


    // CONSTRUCTOR
    /**
     * constructor that takes a single argument, which represents the size of
     * the maze.
     *
     * @param size
     *            this is the size of the square matrix
     */
    public Maze(int size)
    {
        // declare the size of the maze
        this.mazeSize = size;
        // create 2d square Maze
        this.mazeBoard = new MazeCell[this.mazeSize][this.mazeSize];

        for (int i = 0; i < this.mazeSize; i++)
        {
            for (int j = 0; j < this.mazeSize; j++)
            {

                // when a new maze is creates every cell must start as
                // MazeCell.UNEXPLORED
                this.setCell(new Location(i, j), MazeCell.UNEXPLORED);

            }

        }

        // For a new maze
        // set start starting point
        // Location p = new Location(0,0);
        this.setStartLocation(new Location(0, 0));
        // set goal location
        // p = new Location(this.mazeSize - 1, this.mazeSize - 1);
        this.setGoalLocation(
            new Location(this.mazeSize - 1, this.mazeSize - 1));
    }


    // ~ Public methods ........................................................
    // ----------------------------------------------------------
    /**
     * Gets the size of the maze. Since mazes are square, the size of the maze
     * is the number of cells in either dimension (width or height).
     *
     * @return the size of the maze
     */
    public int size()
    {
        return this.mazeSize;
    }


    // ----------------------------------------------------------
    /**
     * Gets the starting location of the maze.
     *
     * @return the starting location in the maze
     */
    public ILocation getStartLocation()
    {
        return this.start;
    }


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
    public void setStartLocation(ILocation location)
    {
        if (this.getCell(location).equals(MazeCell.WALL))
        {
            // destroying the wall
            this.setCell(location, MazeCell.UNEXPLORED);
        }
        // make this location a start
        this.start = (Location)location;
    }


    // ----------------------------------------------------------
    /**
     * Gets the location of the goal in the maze.
     *
     * @return the location of the goal in the maze
     */
    public ILocation getGoalLocation()
    {
        return this.goal;
    }


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
    public void setGoalLocation(ILocation location)
    {
        if (this.getCell(location).equals(MazeCell.WALL))
        {
            // destroying the wall
            this.setCell(location, MazeCell.UNEXPLORED);
        }

        // set this location as a goal
        this.goal = (Location)location;
    }


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
    public MazeCell getCell(ILocation location)
    {

        if (this.checkLocation(location))
        {
            return this.mazeBoard[location.x()][location.y()];
        }

        // if invalid input return invalid cell
        return MazeCell.INVALID_CELL;

    }


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
     *            the cell type
     */
    public void setCell(ILocation location, MazeCell cell)
    {

        Location temp = (Location)this.getGoalLocation();
        Location temp2 = (Location)location;

        boolean check = (cell.equals(MazeCell.WALL)
            && (location.equals(this.getStartLocation()))
            || location.equals(this.getGoalLocation()));

        if (this.checkLocation(location) && !(check))
        {
            mazeBoard[location.x()][location.y()] = cell;
        }

        //This does not set a wall in the goal location
        else if (temp.equals(temp2) && !(cell.equals(MazeCell.WALL)))
        {

            mazeBoard[location.x()][location.y()] = cell;

        }

    }


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
    public String solve()
    {
        // declare the stack
        ArrayListStack<Location> stack;
        // the algorithm here should implement stacks
        // I will use the stack from class
        // initialize the stack
        stack = new ArrayListStack<Location>();
        // push the start location into the stack
        stack.push((Location)this.getStartLocation());

        // set the while loop that will navigate to the solution
        // while the stack is not empty
        while (!(stack.isEmpty()))
        {
            // set the current location to a current path
            this.setCell(stack.top(), MazeCell.CURRENT_PATH);

            // do one of the following
            if (stack.top().equals(this.getGoalLocation()))
            {
                // we reached our location
                // initialize the string that you will
                // return
                String solution = "";
                // this for loop will obtain
                // all the locations from the path
                // we create a primitive variable since the size of the stack
                // will change as we pop things out of it
                int initialsize = stack.size();
                for (int i = 0; i < initialsize; i += 1)
                {
                    // note that the way i am printing this solution
                    // will help me print the desire output by just reversing
                    // the values that I am printing
                    solution +=
                        ")" + stack.top().y() + "," + stack.top().x() + "(";

                    // this will change currents reference to look at the next
                    stack.pop();

                }
                // the wanted result will be the reverse of solution
                return this.reverseString(solution);
            }

            // explore the unexplored neighboors
            else if (this.getCell(stack.top().north())
                .equals(MazeCell.UNEXPLORED))
            {

                // travel north
                stack.push(stack.top().north());

            }

            else if (this.getCell(stack.top().south())
                .equals(MazeCell.UNEXPLORED))
            {

                // travel south
                // chose the location a push it into the stack
                stack.push(stack.top().south());

            }

            else if (this.getCell(stack.top().east())
                .equals(MazeCell.UNEXPLORED))
            {

                // travel east
                stack.push(stack.top().east());

            }

            else if (this.getCell(stack.top().west())
                .equals(MazeCell.UNEXPLORED))
            {

                // travel west
                stack.push(stack.top().west());

            }

            // if there is no unexplored neighboors
            else
            {
                // mark the location as a failed attempt
                this.setCell(stack.top(), MazeCell.FAILED_PATH);
                // pop it of the stack
                // Note: this line of code will be responsible of
                // getting outside the while loop if no path takes us
                // to were we want to go
                stack.pop();
            }

        }

        // if we manage to get outside the while loop then there is no
        // solution
        return null;
    }


    /**
     * This method will check the Location object to make sure it has valid
     * coordinates that are in the maze
     *
     * @param location
     *            the location object we are bringing
     * @return boolean statement that lets us carry the job
     */
    public boolean checkLocation(ILocation location)
    {
        // this boolean will check that both x and y are within the
        // maze boundaries
        return (location.x() >= 0 && location.x() < this.mazeSize
            && location.y() >= 0 && location.y() < this.mazeSize);

    }


    // ----------------------------------------------------------
    /**
     * This method is an easy way to reverse the answer we got to get the right
     * answer
     *
     * @param s
     *            the string we want to reverse
     * @return the reversed string
     */
    public String reverseString(String s)
    {
        if (s.length() == 0)
        {
            return s;
        }

        return reverseString(s.substring(1)) + s.charAt(0);
    }

// // ----------------------------------------------------------
// public String toString()
// {
// StringBuffer buffer = new StringBuffer((this.mazeSize + 2) * (this.mazeSize +
// 3));
// for (int x = 0; x < this.mazeSize; x++)
// {
// buffer.append('-');
// }
// buffer.append("--\n");
// for (int y = 0; y < this.mazeSize; y++)
// {
// buffer.append('|');
// for (int x = 0; x < this.mazeSize; x++)
// {
// switch (this.getCell(new Location(x, y)))
// {
// case UNEXPLORED:
// buffer.append('U');
// break;
// case WALL:
// buffer.append('W');
// break;
// case CURRENT_PATH:
// buffer.append('C');
// break;
// case FAILED_PATH:
// buffer.append('F');
// break;
// default:
// break;
// }
// }
// buffer.append("|\n");
// }
// for (int x = 0; x < this.mazeSize; x++)
// {
// buffer.append('-');
// }
// buffer.append("--\n");
// return buffer.toString();
// }

}
