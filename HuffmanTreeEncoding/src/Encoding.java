package compiler;

// -------------------------------------------------------------------------
/**
 *  This Class encapsulates the information that will come out of our
 *  huffman coding tree. In specific the encoding of the character and
 *  the character
 *
 *  @author Gustavo Grinsteins
 *  @version 2016.04.13
 */
public class Encoding
{
    // Data Fields
    private String coding; // string array holding the codings
    private char   letter;  // corresponding letter to the code in the array
                            // above

    //Constructor
    // ----------------------------------------------------------
    /**
     * Create a new Encoding object.
     * @param coding the encoding of the character
     * @param letter the character
     */
    public Encoding(String coding, char letter) {
        this.coding = coding;
        this.letter = letter;
    }

    //Getters and setters
    // ----------------------------------------------------------
    /**
     * returns the encoding of in the object
     * @return String representing the encoding
     */
    public String getCoding() {
        return this.coding;
    }

    // ----------------------------------------------------------
    /**
     * This will set the encoding for the character
     * @param newCoding the new encoding of the character
     */
    public void setCoding(String newCoding) {
        this.coding = newCoding;
    }

    // ----------------------------------------------------------
    /**
     * returns the character in the object
     * @return char that is in this object
     */
    public char getLetter() {
        return this.letter;
    }

    // ----------------------------------------------------------
    /**
     * Sets the character of the object
     * @param newCh is the new character of the object
     */
    public void setLetter(char newCh) {
        this.letter = newCh;
    }


}
