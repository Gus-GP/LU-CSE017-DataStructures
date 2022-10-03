package compiler;

import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * This class is in charge of storing information from the huffman tree. This
 * information will be used latter on in the code.
 *
 * @author Gustavo Grinsteins
 * @version 2016.04.13
 */
// do I need to extend or import something?
public class HuffTreeCodes
{
    // data fields
    private ArrayList<Encoding> codes; // this will store the encoding
                                       // objects that result from the tree
    private ArrayList<String>   stack; // this stack will store the "0" or
                                       // "1"
    private Encoding            enc;   // encoding object


    // constructor
    // ----------------------------------------------------------
    /**
     * Create a new HuffTreeCodes object.
     *
     * @param cSize
     *            the size of the arrayList
     */
    public HuffTreeCodes(int cSize)
    {
        codes = new ArrayList<Encoding>(cSize); // create an arrayList of
                                                // encoding objects with an
                                                // specific size since we have
                                                // this number
        stack = new ArrayList<String>(); // create the stack with no
                                         // default size
    }


    // ----------------------------------------------------------
    /**
     * Return the size of the codes array
     *
     * @return size of the arraylist
     */
    public int getCodesSize()
    {
        return codes.size();
    }


    // getter method for codes
    // ----------------------------------------------------------
    /**
     * Method to obtain values from our encoding arrayList
     *
     * @param i
     *            index of the array
     * @return encoding object
     */
    public Encoding getEncoding(int i)
    {
        return codes.get(i);
    }


    /**
     * This method will get a value from 0 to 255. This value will be casted
     * into a char and looked for in the codes array
     *
     * @param num
     *            is the decimal of 8 bit char
     * @return a string with the corresponding codes
     */
    public String getLetterCode(int num)
    {
        // for loop to traverse the codes array
        for (int i = 0; i < this.codes.size(); i++)
        {
            if (this.codes.get(i).getLetter() == num)
            {
                return this.codes.get(i).getCoding();
            }
        }

        return "";
    }


    // Traversing code
    // ----------------------------------------------------------
    /**
     * This method will traverse our created Huffman tree and store in the codes
     * arrayList an encoding object that holds a character with its
     * corresponding encoding
     *
     * @param root
     *            of our Huffman tree
     */
    public void traverse(IHuffBaseNode root)
    {
        if (root.isLeaf())
        {
            // turning the stack into a string
            String solution = "";

            // this for loop creates a string that has the coding
            for (int i = 0; i < stack.size(); i++)
            {
                // add the numbers to the the solution string
                solution += stack.get(i);

            }

            // create a new encoding object that has the character with its
            // encoding
            enc = new Encoding(solution, ((HuffLeafNode)root).element());

            // removes the last object on the array list
            stack.remove(stack.size() - 1);

            // add the created encoding object to codes ArrayList
            codes.add(enc);

            return;
        }

        // TRAVERSE
        // push a 0 into the stack because we are going left
        stack.add("0");
        // cast root into internal node
        traverse(((HuffInternalNode)root).getLeft());

        // push a 1 into the stack because we are going right
        stack.add("1");
        // cast root into internal node
        traverse(((HuffInternalNode)root).getRight());

        if (!(stack.isEmpty()))
        {
            stack.remove(stack.size() - 1);
        }

        return;

    }
}
