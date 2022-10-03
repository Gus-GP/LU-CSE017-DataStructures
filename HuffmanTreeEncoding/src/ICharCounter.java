package compiler;

import java.io.InputStream;
import java.io.IOException;

// -------------------------------------------------------------------------
/**
 * This interface initializes and retrieves data
 *
 * @author Gustavo Grinsteins
 * @version 2016.04.06
 */
public interface ICharCounter
{
    // Methods to be implemented

    // ----------------------------------------------------------
    /**
     * Update state to record one occurrence of specified chunk/character
     *
     * @param i
     *            chunk being recorded
     */
    public void add(int i);


    // ----------------------------------------------------------
    /**
     * All the counts in the array are cleared to zero
     */
    public void clear();

    // ----------------------------------------------------------
    /**
     * Initialize state by counting bits/chunks in a stream.
     * @param stream is the source of data (text file)
     * @return count of all chunks read
     * @throws java.io.IOException
     */
    //Note: the throws statement at the front will throw the exception
    //and this will later be caught in the test
    public int countAll(InputStream stream) throws IOException;

    // ----------------------------------------------------------
    /**
     * Returns the count associated with specified character
     * @param ch is the chunk/character for which count is requested
     * @return count of the specified chunk
     * @throw IllegalArgumentException if the ch is not a valid chunk
     * or character
     */
    public int getCount(int ch);

    // ----------------------------------------------------------
    /**
     * Set the value/count associated with a specific character/chunk
     * @param i index in the array aka the decimal value for the character
     * @param value number of occurrences of the specified chunk
     */
    //check to make sure the values passed to the array are valid
    public void set(int i, int value);

}
