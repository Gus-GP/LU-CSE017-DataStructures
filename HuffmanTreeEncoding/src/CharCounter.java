package compiler;

import java.io.InputStream;
import java.io.IOException;

// -------------------------------------------------------------------------
/**
 * This class will deal with establishing a matrix that stores the occurrences
 * of the words
 *
 * @author Gustavo Grinsteins
 * @version 2016.04.06
 */
public class CharCounter
    implements ICharCounter, IHuffConstants
{
    // data fields (create getters and setters)

    private int[] occurr; // this will store the occurrence of the
    // characters in the text file


    // ~Constructor
    // ----------------------------------------------------------
    /**
     * Create a new CharCounter object.
     */
    public CharCounter()
    {

        // create an array of size 256 (# of characters)
        occurr = new int[256];

    }


    // ~Implementation of the methods
    // ----------------------------------------------------------
    /**
     * All the counts in the array are cleared to zero
     */
    public void clear()
    {

        // set all the values of the array to zero
        for (int i = 0; i < occurr.length; i++)
        {
            occurr[i] = 0;
        }

    }


    // ----------------------------------------------------------
    /**
     * Update state to record one occurrence of specified chunk/character
     *
     * @param i
     *            chunk being recorded
     */
    public void add(int i)
    {

        // check if the index is valid
        boolean check = (0 <= i && i < occurr.length);
        // add one to the specified cell index
        if (check)
        {
            occurr[i] += 1;
        }
        else
        {
            // do nothing because the letter is not within our
            // range
        }

    }


    // ~Getters and Setters
    // ----------------------------------------------------------
    /**
     * Set the value/count associated with a specific character/chunk
     *
     * @param i
     *            index in the array aka the decimal value for the character
     * @param value
     *            number of occurrences of the specified chunk
     */
    // check to make sure the values passed to the array are valid
    public void set(int i, int value)
    {
        // check if the index is valid
        boolean check = (0 <= i && i < occurr.length);
        // check if the value is valid
        boolean check2 = (value >= 0);
        if (check && check2)
        {
            occurr[i] = value;
        }
        else
        {
            // do nothing
        }
    }


    // ----------------------------------------------------------
    /**
     * Returns the count associated with specified character
     *
     * @param ch
     *            is the chunk/character for which count is requested
     * @return count of the specified chunk
     * @throw IllegalArgumentException if the ch is not a valid chunk or
     *        character
     */
    public int getCount(int ch)
    {
        // Check to see if the character matches any of them
        // initialize a boolean for the check
        boolean check;
        for (int i = 0; i < occurr.length; i++)
        {

            check = (ch == i);

            // if it matches one of the characters
            if (check)
            {
                // break if the number matches
                break;
            }

            // if we reach the end of the for loop without a break
            else if (i == (occurr.length - 1))
            {
                // if the loop gets out without returning anything throw an
                // exception
                throw new IllegalArgumentException(
                    "The character is not valid");
            }
        }

        return occurr[ch];

    }

    // ~Methods


    // ----------------------------------------------------------
    /**
     * Initialize state by counting bits/chunks in a stream.
     *
     * @param stream
     *            is the source of data (text file)
     * @return count of all chunks read
     * @throws java.io.IOException
     */
    public int countAll(InputStream stream)
        throws IOException
    {
        int inbits;
        // how many letters we read
        int count = 0;

        while ((inbits = ((BitInputStream)stream).read(BITS_PER_WORD)) != -1)
        {
            // update the matrix use one of the methods
            this.add(inbits); // this will add one to the specified
            // index whenever it reads it from the text file

            // add one to the count of letters read
            ++count;
        }

        return count;
    }


    // ----------------------------------------------------------
    /**
     * This method returns the original file size by getting the total number
     * of occurrences in the occurr array
     *
     * @return an int value with the size of the original file in byte units
     */
    public int getOriginalFileSize()
    {

        int totalFrequencies = 0;
        //get the total number of frequencies
        for (int i = 0; i < occurr.length; i++)
        {
            if (this.getCount(i) > 0) {
                totalFrequencies += this.getCount(i);
            }
        }
        return totalFrequencies;
    }
}
