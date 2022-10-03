package cs2114.minesweeper;

// -------------------------------------------------------------------------
/**
 * This code will simulate a mine Sweeper board
 *
 * @author Gustavo Grinsteins (gug218)
 * @version 2016.02.20
 */
public class MineSweeperBoard
    extends MineSweeperBoardBase
{
    // data fields
    private int width;
    private int height;
    private int numMines;

    private MineSweeperCell[][] board; // try making it an object array


    // ----------------------------------------------------------
    /**
     * Create a new MineSweeperBoard object.
     *
     * @param width
     *            number of columns for board
     * @param height
     *            number of rows for board
     * @param numMines
     *            number of mines for board
     */
    // Argumented constructor for MineSweeper
    public MineSweeperBoard(int width, int height, int numMines)
    {
        // Set the data fields
        this.width = width; // number of columns
        this.height = height; // number of rows
        this.numMines = numMines;
        // create 2d board
        this.board = new MineSweeperCell[this.width][this.height];

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {

                this.setCell(i, j, MineSweeperCell.COVERED_CELL);

            }

        }
        // randomly place specified number of mines in the board
        // Place the mines a random locations on the board
        int m = 0;
        while (m < this.numMines)
        {

            int x = (int)(Math.random() * (width)); // column

            int y = (int)(Math.random() * (height)); // row

            // Check to make sure there is no mine at that spot
            // if there is a mine the loop will run again until
            // a mine-less spot is found
            if ((this.getCell(x, y) != MineSweeperCell.MINE))
            {

                this.setCell(x, y, MineSweeperCell.MINE);

                m += 1;

            }

        }

    }


    // implement the methods
    // ----------------------------------------------------------
    /**
     * Get the number of columns in this MineSweeperBoard.
     *
     * @return the number of columns in this MineSweeperBoard.
     */
    // getter for width
    public int width()
    {
        return this.width;
    }


    // ----------------------------------------------------------
    /**
     * Get the number of rows in this MineSweeperBoard.
     *
     * @return the number of rows in this MineSweeperBoard
     */
    // getter for height
    public int height()
    {
        return this.height;
    }


    // ----------------------------------------------------------
    /**
     * Get the contents of the specified cell on this MineSweeperBoard. The
     * value returned from this method must be one of the values from the
     * {@link MineSweeperCell} enumerated type.
     *
     * @param x
     *            the column containing the cell.
     * @param y
     *            the row containing the cell.
     * @return the value contained in the cell specified by x and y, or
     *         INVALID_CELL if the specified cell does not exist.
     */
    public MineSweeperCell getCell(int x, int y)
    {
        if (this.chekParams(x, y))
        {
            return this.board[x][y];
        }

        return MineSweeperCell.INVALID_CELL;

    }


    // ----------------------------------------------------------
    /**
     * Uncover the specified cell. If the cell already contains a flag it should
     * not be uncovered. If there is not a mine under the specified cell then
     * the value in that cell is changed to the number of mines that appear in
     * adjacent cells. If there is a mine under the specified cell the game is
     * over and the player has lost. If the specified cell is already uncovered
     * or is invalid, no change is made to the board.
     *
     * @param x
     *            the column of the cell to be uncovered.
     * @param y
     *            the row of the cell to be uncovered.
     */
    public void uncoverCell(int x, int y)
    {
        MineSweeperCell c = this.getCell(x, y);
        // switch statement implementation
        switch (c)
        {
            case COVERED_CELL:
                // this should define the cell as the number of adjacent
                // mines that are close to it
                this.setCell(
                    x,
                    y,
                    MineSweeperCell
                        .adjacentTo(this.numberOfAdjacentMines(x, y)));

                break;

            case MINE:
                this.setCell(x, y, MineSweeperCell.UNCOVERED_MINE);
                break;

            default:
                // do nothing
                break;
        }

    }


    // ----------------------------------------------------------
    /**
     * flag the cell you want to flag
     *
     * @param x
     *            column reference
     * @param y
     *            row reference
     */
    public void flagCell(int x, int y)
    {
        // for this method check to see if the cell has a mine
        // if it does call it a flagged mine enum
        switch (this.getCell(x, y))
        {
            case COVERED_CELL:
                // this should define the cell as the number of adjacent
                // mines that are close to it
                this.setCell(x, y, MineSweeperCell.FLAG);

                break;

            case MINE:

                this.setCell(x, y, MineSweeperCell.FLAGGED_MINE);

                break;

            default:
                // do nothing
                break;

        }

    }


    // ----------------------------------------------------------
    /**
     * Determine if the player has lost the current game. The game is lost if
     * the player has uncovered a mine.
     *
     * @return true if the current game has been lost and false otherwise
     */
    public boolean isGameLost()
    {

        for (int i = 0; i < height; i++)
        {

            for (int j = 0; j < width; j++)
            {

                if ((this.getCell(i, j)).equals(MineSweeperCell.UNCOVERED_MINE))
                {
                    return true;
                }

            }

        }

        return false;

    }


    // ----------------------------------------------------------
    /**
     * Determine if the player has won the current game. The game is won when
     * three conditions are met:
     * <ol>
     * <li>Flags have been placed on all of the mines.</li>
     * <li>No flags have been placed incorrectly.</li>
     * <li>All non-flagged cells have been uncovered.</li>
     * </ol>
     *
     * @return true if the current game has been won and false otherwise.
     */
    public boolean isGameWon()
    {
        // meet the conditions
        // implement foor loop that examines the board
        // use numMines for mine examination
        // only mines can be flagged?
        // this values will be used for testing
        int m = 0;
        // EXAMINING
        for (int i = 0; i < width; i++)
        {

            for (int j = 0; j < height; j++)
            {

                if ((this.getCell(i, j)).equals(MineSweeperCell.FLAGGED_MINE))
                {
                    m += 1;
                }
                // assuming the only flags are the ones on the mines
                else if ((this.getCell(i, j)).equals(MineSweeperCell.FLAG))
                {
                    return false;
                }
                else if ((this.getCell(i, j))
                    .equals(MineSweeperCell.COVERED_CELL))
                {
                    return false;
                }

            }

        }

        // EVALUATING
        return (m == this.numMines);

    }


    // ----------------------------------------------------------
    /**
     * Count the number of mines that appear in cells that are adjacent to the
     * specified cell.
     *
     * @param x
     *            the column of the cell.
     * @param y
     *            the row of the cell.
     * @return the number of mines adjacent to the specified cell.
     */
    public int numberOfAdjacentMines(int x, int y)
    {
        // start counter to find the number of mines adjacent
        int counter = 0;
        // this for loop is incharge of exploring the adjacent
        // cells relative to the cell we want to find the number
        // of mines adjacent to
        // column loop
        for (int a = (x - 1); a <= (x + 1); a++)
        {

            // row loop loop
            for (int b = (y - 1); b <= (y + 1); b++)
            {

                // check to make sure the index is not out
                // of bounds Then change the number
                if (a >= 0 && a < this.width && b >= 0 && b < this.height)
                {

                    // if there is a mine around add that to the counter
                    if (this.getCell(a, b).equals(MineSweeperCell.MINE)
                        || this.getCell(a, b)
                            .equals(MineSweeperCell.FLAGGED_MINE)
                        || this.getCell(a, b)
                            .equals(MineSweeperCell.UNCOVERED_MINE))
                    {

                        counter += 1;

                    }

                    // bracket for if statement
                }
                // bracket for row for loop
            }
            // bracket for column for loop
        }

        // return what we have counted
        return counter;
    }


    // ----------------------------------------------------------
    /**
     * Uncover all of the cells on the board.
     */
    public void revealBoard()
    {
        // this for loop will run through the board
        // and uncover everything
        for (int i = 0; i < this.width; i++)
        {

            for (int j = 0; j < this.height; j++)
            {

                this.uncoverCell(i, j);

            }

        }
    }


    // ----------------------------------------------------------
    /**
     * Set the contents of the specified cell on this MineSweeperBoard. The
     * value passed in should be one of the defined constants in the
     * {@link MineSweeperCell} enumerated type.
     *
     * @param x
     *            the column containing the cell
     * @param y
     *            the row containing the cell
     * @param value
     *            the value to place in the cell
     */
    protected void setCell(int x, int y, MineSweeperCell value)
    {
        if (this.chekParams(x, y))
        {
            board[x][y] = value;
        }

    }


    // ----------------------------------------------------------
    /**
     * Check if the values that are being inputed are valid
     *
     * @param x
     *            the column containing the cell
     * @param y
     *            the row containing the cell
     * @return boolean that checks if the values are valid
     */
    private boolean chekParams(int x, int y)
    {

        boolean b =
            !(x < 0 || x > (this.width - 1) || y < 0 || y > (this.height - 1));

        return b;

    }

}
