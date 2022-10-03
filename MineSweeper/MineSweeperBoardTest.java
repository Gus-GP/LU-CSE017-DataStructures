package cs2114.minesweeper;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Testings for all the mine sweeper assignment method
 *
 * @author Gustavo Grinsteins (gug218)
 * @version 2016.02.20
 */
public class MineSweeperBoardTest
    extends TestCase
{
    // ~ Fields ................................................................
    /**
     * New MineSweeperBoard called game
     */
    MineSweeperBoard game;

    // ~ Methods ...............................................................


    // ----------------------------------------------------------
    /**
     * Sets up the test fixture. Called before every test case method.
     */
    public void setUp()
    {
        // creates default 4 by 4 board
        game = new MineSweeperBoard(4, 4, 2);
    }


    // ----------------------------------------------------------
    /**
     * This method uses the given methods to do unit tests
     *
     * @param theBoard
     *            the actual boar we manipulated
     * @param expected
     *            the board that we expect
     */
    // testing of course site
    public void assertBoard(MineSweeperBoard theBoard, String... expected)
    {
        MineSweeperBoard expectedBoard =
            new MineSweeperBoard(expected[0].length(), expected.length, 0);

        expectedBoard.loadBoardState(expected);

        assertEquals(expectedBoard, theBoard); // uses equals() from
                                               // MineSweeperBoardBase
    }

    // test case methods here.


    // ----------------------------------------------------------
    /**
     * Testing the width method for mine sweeper cell
     */
    public void testWidth()
    {

        assertEquals(4, game.width());

    }


    // ----------------------------------------------------------
    /**
     * Testing the Height method for mine sweeper cell
     */
    public void testHeight()
    {

        assertEquals(4, game.height());

    }


    // ----------------------------------------------------------
    /**
     * Testing the getcell method for mine sweeper cell
     */
    public void testGetCell()
    {

        game.loadBoardState("111 ", "1M21", "12+1", "O11F");

        assertEquals(MineSweeperCell.MINE, game.getCell(2, 2));
        assertEquals(MineSweeperCell.FLAG, game.getCell(3, 3));
        assertEquals(MineSweeperCell.INVALID_CELL, game.getCell(-1, 3));
        assertEquals(MineSweeperCell.FLAGGED_MINE, game.getCell(1, 1));

        game.loadBoardState("111 ", "1M21", "12*1", "O11F");
        assertEquals(MineSweeperCell.UNCOVERED_MINE, game.getCell(2, 2));
        assertEquals(MineSweeperCell.ADJACENT_TO_1, game.getCell(0, 1));

    }


    // ----------------------------------------------------------
    /**
     * Testing the getcell method for mine sweeper cell
     */
    public void testSetCell()
    {

        // game is declared as part of the test fixture, and
        // is initialized to be 4x4
        game.loadBoardState("    ", "OOOO", "O++O", "OOOO");

        game.setCell(1, 2, MineSweeperCell.FLAGGED_MINE);

        assertBoard(game, "    ", "OOOO", "OM+O", "OOOO");

    }


    // ----------------------------------------------------------
    /**
     * Testing the Uncover method for mine sweeper cell
     */
    public void testUncover()
    {

        game.loadBoardState("O   ", "OOOO", "O+MO", "OOOF");

        game.uncoverCell(1, 1);
        game.uncoverCell(3, 3);
        game.uncoverCell(1, 2);
        game.uncoverCell(-1, 2);
        game.uncoverCell(2, 2);
        game.uncoverCell(2, -2);
        game.uncoverCell(100, 100);
        game.uncoverCell(100, 0);
        game.uncoverCell(0, 100);
        game.uncoverCell(-100, 0);
        game.uncoverCell(-100, -2);
        game.uncoverCell(-1, 20);
        game.uncoverCell(20, -2);
        game.uncoverCell(2, 3);
        game.uncoverCell(0, 0);

        assertBoard(game, "    ", "O2OO", "O*MO", "OO2F");

        game.loadBoardState("O   ", "OOOO", "O++O", "OOOF");

        game.uncoverCell(0, 2);
        game.uncoverCell(3, 2);
        game.uncoverCell(1, 2);

        assertBoard(game, "O   ", "OOOO", "1*+1", "OOOF");

    }


    // ----------------------------------------------------------
    /**
     * testing uncover again.
     */
    public void testUncover1()
    {

        game.loadBoardState("O 12", "3456", "78+F", "M*OO");

        for (int i = 0; i < game.width(); i++)
        {

            for (int j = 0; j < game.height(); j++)
            {

                game.uncoverCell(i, j);

            }

        }

        assertEquals(false, game.isGameWon());
    }


    // ----------------------------------------------------------
    /**
     * Testing the flagCell method for mine sweeper cell
     */
    public void testFlagCell()
    {

        game.loadBoardState("    ", "OOOO", "O++O", "OOOO");

        game.flagCell(3, 3);
        game.flagCell(0, 3);
        game.flagCell(1, 2);
        game.flagCell(0, 0);
        game.flagCell(-1, 2);
        game.flagCell(1, -2);
        game.flagCell(-1, -2);
        game.flagCell(1, 20);
        game.flagCell(20, 2);
        game.flagCell(20, 20);
        game.flagCell(-1, 20);
        game.flagCell(20, -2);

        assertBoard(game, "    ", "OOOO", "OM+O", "FOOF");
    }


    // ----------------------------------------------------------
    /**
     * Testing the isgamelost method for mine sweeper cell
     */
    public void testIsGameLost()
    {

        game.loadBoardState("111 ", "1M21", "12+1", "O111");

        assertEquals(false, game.isGameLost());

        game.loadBoardState("111 ", "1M21", "12*1", " 111");

        assertEquals(true, game.isGameLost());

    }


    // ----------------------------------------------------------
    /**
     * Testing the isgamewon method for mine sweeper cell
     */

    public void testIsGameWon()
    {

        game.loadBoardState("111F", "1M21", "12+1", "O1FF");

        assertEquals(false, game.isGameWon());

        game.loadBoardState("F11F", "1+21", "12M1", "O111");

        assertEquals(false, game.isGameWon());

        game.loadBoardState("1111", "1+21", "12M1", "1111");

        assertEquals(false, game.isGameWon());

        game.loadBoardState("1111", "1M21", "12M1", "O111");

        assertEquals(false, game.isGameWon());

        game.loadBoardState("111 ", "1M21", "12M1", " 111");

        assertEquals(true, game.isGameWon());

        game.loadBoardState("111 ", "1M21", "12M1", " 111");

        assertEquals(true, game.isGameWon());

        game.loadBoardState("1M1 ", "1 21", "12M1", " 111");

        assertEquals(true, game.isGameWon());

    }


    // ----------------------------------------------------------
    /**
     * Testing the NumberOfAdjacentMines method for mine sweeper cell
     */
    public void testNumberOfAdjacentMines()
    {

        game.loadBoardState("OOOM", "OOOO", "O+*O", "OOOO");

        assertEquals(1, game.numberOfAdjacentMines(2, 0));
        assertEquals(1, game.numberOfAdjacentMines(3, 3));
        assertEquals(2, game.numberOfAdjacentMines(2, 3));
        assertEquals(0, game.numberOfAdjacentMines(1, 0));

    }


    // ----------------------------------------------------------
    /**
     * Testing the reveal board method for mine sweeper board
     */
    public void testRevealBoard()
    {

        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        game.loadBoardState("OOOO", "O+OO", "OO+O", "OOOO");

        game.revealBoard();

        assertBoard(game, "111 ", "1*21", "12*1", " 111");

    }


    /**
     * test for to string
     */
    public void testToString()
    {
        game.loadBoardState("4567", "*231", "O++O", "F8OM");
        MineSweeperBoard check = new MineSweeperBoard(4, 4, 2);
        check.loadBoardState("4567", "*231", "O++O", "F8OM");

        assertEquals(game.toString(), check.toString());
    }


    // tests provided by professor
    /**
     * test for adjacent to test
     */
    public void testAdjacentTo()
    {
        MineSweeperCell c = MineSweeperCell.ADJACENT_TO_0;
        assertNotNull(c);
        // testing for exception
        Exception thrown = null;
        try
        {
            c = MineSweeperCell.adjacentTo(10);
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try
        {
            MineSweeperCell.adjacentTo(-1);
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
        assertNotNull(MineSweeperCell.values());
        assertNotNull(
            MineSweeperCell.valueOf(MineSweeperCell.ADJACENT_TO_0.toString()));

    }


    /**
     * test for adjacent to test
     */
    public void testloadBoardState()
    {
        MineSweeperBoard a = new MineSweeperBoard(2, 2, 1);
        Exception thrown = null;
        // loadBoardState testing
        // wrong number of rows
        try
        {
            a.loadBoardState("00");
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
        thrown = null;
        // wrong number of columns
        try
        {
            a.loadBoardState("0000 ", " ");
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
        // Wrong symbol in cell
        try
        {
            a.loadBoardState("00", "$+");
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }


    /**
     * * This method test Equals.
     */
    /**
     * * This method test Equals.
     */
    public void testEqual()
    {
        MineSweeperBoard mBoard1 = new MineSweeperBoard(4, 4, 6);
        MineSweeperBoard mBoard2 = new MineSweeperBoard(4, 4, 6);
        mBoard1.loadBoardState("    ", "OOOO", "O++O", "OOOO");
        mBoard2.loadBoardState("    ", "OOOO", "O++O", "OOOO");
        // test the same board same dimensions
        assertTrue(mBoard1.equals(mBoard2));
        // same board testing same board
        assertTrue(mBoard1.equals(mBoard1));
        // testing same dimensions board with different cell
        MineSweeperBoard mBoard3 = new MineSweeperBoard(4, 4, 6);
        mBoard3.loadBoardState("    ", "O+OO", "O++O", "OOOO");
        assertFalse(mBoard1.equals(mBoard3));
        MineSweeperBoard mBoard4 = new MineSweeperBoard(15, 1, 0);
        mBoard4.loadBoardState("OFM+* 123456788");
        assertFalse(mBoard1.toString().equals(mBoard3.toString()));
        // testing two string against a board
        assertFalse(mBoard4.toString().equals(mBoard2.toString()));
        // testing against a string
        assertFalse(mBoard1.equals("abc"));
        // same width but different height
        MineSweeperBoard mBoard6 = new MineSweeperBoard(4, 5, 6);
        mBoard6.loadBoardState("    ", "O+OO", "O++O", "OOOO", "OOOO");
        assertFalse(mBoard6.equals(mBoard1));
        // different width same height
        MineSweeperBoard mBoard5 = new MineSweeperBoard(5, 4, 6);
        mBoard5.loadBoardState("     ", "O+OOO", "O++OO", "OOOOO");
        assertFalse(mBoard5.equals(mBoard1));
    }


    /**
     * Method to test checkparams method
     */
    public void testCheckParams()
    {
        // game is declared as part of the test fixture, and
        // is initialized to be 4x4
        game.loadBoardState("    ", "OOOO", "O++O", "OOOO");

        game.setCell(-1, 2, MineSweeperCell.FLAGGED_MINE);
        game.setCell(1, -2, MineSweeperCell.FLAGGED_MINE);
        game.setCell(-1, -2, MineSweeperCell.FLAGGED_MINE);
        game.setCell(100, 100, MineSweeperCell.FLAGGED_MINE);
        game.setCell(100, -2, MineSweeperCell.FLAGGED_MINE);
        game.setCell(-2, 100, MineSweeperCell.FLAGGED_MINE);
        game.setCell(100, 1, MineSweeperCell.FLAGGED_MINE);
        game.setCell(1, 100, MineSweeperCell.FLAGGED_MINE);

        game.setCell(0, 1, MineSweeperCell.FLAGGED_MINE);

        assertBoard(game, "    ", "MOOO", "O++O", "OOOO");

    }

}
