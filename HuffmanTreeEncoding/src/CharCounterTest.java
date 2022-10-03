package compiler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests for the CharCounter class
 *
 * @author Gustavo Grinsteins (gug218)
 * @version 2016.04.001
 */
public class CharCounterTest
    extends TestCase implements IHuffConstants
{
    // ~ Instance/static variables .............................................
    private CharCounter c;
    private BitInputStream bits;
    // ~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Create a new test class
     */
    public CharCounterTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }

    // ~ Public methods ........................................................


    // ----------------------------------------------------------
    /**
     * Creates a escenario for the testing
     */
    public void setUp()
    {
        //create a new CharCounter object
        c = new CharCounter();
    }


    // ----------------------------------------------------------
    /**
     * Test the countAll Method when no error occurs
     */
    public void testCountAll()
    {
        int inbits;
        Exception occurred = null;
        try
        {
            //FileInputStream throws a FileNotFoundException if the file
            //is not found
            bits = new BitInputStream(new FileInputStream("Hello.txt"));
            //countAll throws an IOException if something is wrong with
            //the input file
            assertEquals(11,c.countAll(bits));
        }
        catch (Exception exception)
        {
            occurred = exception;
        }
        assertNull(occurred);

        //test to see if the CharCounter object was updated
        assertEquals(1,c.getCount('H')); //implicit casting to int
        assertEquals(3,c.getCount('l')); //implicit casting to int

    }

 // ----------------------------------------------------------
    /**
     * Test the countAll Method when error occurs
     */
    public void testCountAll2()
    {
        int inbits;
        Exception occurred = null;
        try
        {
            //FileInputStream throws a FileNotFoundException if the file
            //is not found
            bits = new BitInputStream(new FileInputStream("He.txt"));
            //countAll throws an IOException if something is wrong with
            //the input file
            c.countAll(bits);

        }
        catch (Exception exception)
        {
            occurred = exception;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof FileNotFoundException);

    }

    /**
     * test methods of charcounter
     */
    public void testGeneral() {
        //test add
        for(int i = 0; i < 256; i++) {
            c.add(i);
            assertEquals(1, c.getCount((char)i));
        }

        //clear the charcounter object
        //and get count

        c.clear();
        for(int i = 0; i < 256; i++) {
            assertEquals(0, c.getCount((char)i));
        }

        //test set
        c.set(200, 3);
        assertEquals(3,c.getCount((char)200));
    }

    /**
     * Test the get count exception
     */
    public void testGetCount() {

        Exception occurred = null;
        try
        {
            //this should throw an error
            c.getCount(256);

        }
        catch (Exception exception)
        {
            occurred = exception;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof IllegalArgumentException);

        assertEquals(
            "The character is not valid",
            occurred.getMessage());
    }

}
