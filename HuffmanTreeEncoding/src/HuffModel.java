package compiler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * This class is the meat of this project. It is in charge of running all the
 * crucial methods that we need to run in order to get the actions we want for
 * the compression.
 *
 * @author Gustavo Grinsteins
 * @version 2016.04.09
 */
public class HuffModel
    implements IHuffModel
{
    // Data fields
    private CharCounter   frequencies; // the frequencies of letters in the text
                                       // file
    private MinHeap       Hheap;       // heap created from textfile
    private HuffTree      huffManTree; // Huffman tree created from text file
    private HuffTreeCodes treeCodes;   // class managing the tree information


    // constructor
    // ----------------------------------------------------------
    /**
     * Create a new HuffModel object.
     */
    public HuffModel()
    {
        // make a CharCounter instance
        frequencies = new CharCounter();

    }


    // public methods
    // ----------------------------------------------------------
    /**
     * Obtain the information about the frequencies.
     *
     * @return frequencies the CharCounter holding all the frequencies
     */
    public CharCounter getFrequencies()
    {
        return this.frequencies;
    }


    // ----------------------------------------------------------
    /**
     * Initialize state via an input stream. The stream most likely comes from a
     * view, it's NOT a BitInputStream.
     *
     * @param stream
     *            is an input stream for initializing state of this model
     * @throws IOException
     */
    public void initialize(InputStream stream)
        throws IOException,
        FileNotFoundException
    {
        // get all the frequencies from all the letters in
        // the text file
        frequencies.countAll(stream);

    }


    // ----------------------------------------------------------
    /**
     * Display all chunk/character counts (via the associated view)
     */
    public void showCounts()
    {
        // print a table with the indexes and their frequencies
        for (int i = 0; i < 256; i++)
        {
            if (frequencies.getCount(i) > 0)
                System.out.println(
                    i + " : " + (char)i + " : " + frequencies.getCount(i));
        }
    }


    @Override
    public void showCodings()
    {
        // create all the nodes
        // initialize the variable that will hold the tree node we need
        HuffTree m;
        // this array list will store the leaf nodes we create below.
        // note that this is an array list because we do not know
        // how many words we will have in the text file
        ArrayList<HuffTree> c = new ArrayList<HuffTree>();
        for (int i = 0; i < 256; i++)
        {
            // we only care about things that are in the
            // text file
            if (frequencies.getCount(i) > 0)
            {
                // make a tree out of that char we found with
                // the corresponding weight
                m = new HuffTree((char)i, frequencies.getCount(i));
                // put them in array
                c.add(m);
            }
        }

        // we need to pass a comparable array to the minheap class
        // in order to create our huff tree

        HuffTree[] f = new HuffTree[c.size() + 1]; // note the capacity,
        // it is the size of the previously made array list plus one to add
        // the end of file mark

        // initialize our comparable array for the mean hip
        for (int i = 0; i < c.size() + 1; i++)
        {
            // if we are at the end of the iteration, we will add the end of
            // file tree
            if (i == ((c.size() + 1) - 1))
            {
                f[i] = new HuffTree((char)PSEUDO_EOF, 1);
            }
            else
            {
                f[i] = c.get(i);
            }

        }

        // create our heap
        Hheap = new MinHeap(f, f.length, f.length);

        // call the build tree method that is inside this class
        huffManTree = buildTree();

        // store string of coding
        // start by creating a hufftreetraversal object
        treeCodes = new HuffTreeCodes(f.length); // it could also be c.size()
        // this initializes an array list that has ENCONDING objects that
        // stores a letter and its corresponding encoding

        // call method that initializes everything in treeCodes
        // this initializes the fields inside the HuffTreeCodes
        // class
        treeCodes.traverse(huffManTree.getRoot());

        // Print the values into the console

        System.out.println();

        for (int i = 0; i < f.length; i++)
        {

            System.out.println(
                "letter: " + treeCodes.getEncoding(i).getLetter() + " Coding: "
                    + treeCodes.getEncoding(i).getCoding());

        }

    }

    // TODO - Your program should not crash if another user is using another
    // file that is not is a text file. Try doing this with a random file


    /**
     * This method will be in charge of writing the compressed information
     * obtained from the file we read to another file following certain
     * parameters.
     *
     * @param stream
     *            this will be the text file we are compressing
     * @param file
     *            this is a file object that contains the name of our output
     *            file
     * @param force
     *            this will define if the file that is being compressed is
     *            larger than the initial file or not.
     * @throws IOException
     */
    @Override

    public void write(InputStream stream, String file, boolean force)
        throws IOException
    {

        // create an output stream object
        BitOutputStream out = new BitOutputStream(file);

        // if the force is true then we are going to compress anyway
        if (force)
        {
            // FIRST: write the magic number into a text file
            // write the magic number in 32 bits
            out.write(BITS_PER_INT, MAGIC_NUMBER);

            // SECOND: write the huffman tree
            // THIRD: Write the pseudo_eof char to signify the end of the
            // Huffman
            // NOTE: since we physically added the PSEUDO_EOF in our tree the
            // following method does part three too
            writeTree(huffManTree.getRoot(), out);

            // FOURTH: Read the original file BITS_PER_WORD at a time, for each
            // char
            // get the corresponding code (computed in Part II), and write
            // each char of the code using 1 bit

            // this value will have a number from 0 to 255
            // is the 8 bit ascii decimal

            int inbits;

            while ((inbits =
                ((BitInputStream)stream).read(BITS_PER_WORD)) != -1)
            {
                String codes = "";
                // get the code computed in part II
                codes = this.treeCodes.getLetterCode(inbits);
                // convert that code into an array of chars using .toCharArray()
                // method
                char[] charArray = codes.toCharArray();
                // go through the array of char and write each char (cast as
                // int)
                // using 1 bit
                for (int i = 0; i < charArray.length; i++)
                {
                    // implicit casting from char to int
                    out.write(1, charArray[i]);
                }

            }

            // close the stream
            stream.close();

            // FIVE: Write the PSEUDO_EOF character
            // You need to include pseudo_eof in your Huffman tree. Increase the
            // the
            // size of your array of Hufftrees used to initialized the MinHeap
            // by 1.
            // Then insert a Hufftree created using PSEUDO_EOF as the
            // element/"el"
            // argument and 1 for the weight/" wt" argument. To write PSEUDO_EOF
            // character, you will use the protocol described in (4).

            // get the code for the PSEUDO_EOF char in our tree
            String pSeudo = this.treeCodes.getLetterCode(PSEUDO_EOF);

            // create another array of chars for pseudo
            char[] pSeudoCharArray = pSeudo.toCharArray();

            // go through the array of char and write each char (cast as int)
            // using 1 bit
            for (int i = 0; i < pSeudoCharArray.length; i++)
            {
                // implicit casting from char to int
                out.write(1, pSeudoCharArray[i]);
            }

            // close the stream
            out.close();
        }

        // if force false
        else
        {
            // get the size of original and compressed files
            int[] orig_CompSize = isCompFileBigger();

            int originalByteSize = orig_CompSize[0];

            int compressedByteSize = orig_CompSize[1];

            // check if the file is bigger
            // if the compressed file is bigger
            if (originalByteSize < compressedByteSize)
            {

                // print the size of both compressed and uncompressed file
                System.out.println(
                    "Size of Original File (SOF): " + originalByteSize
                        + " Bytes");

                System.out.println(
                    "Size of Compressed File (SCF): " + compressedByteSize
                        + " Bytes");

                System.out.println(
                    "Because SOF < SCF and force = false, the compression was NOT performed");

            }

            // if the compressed file is not bigger
            else
            {
                // print the size of both compressed and uncompressed file
                System.out.println(
                    "Size of Original File (SOF): " + originalByteSize
                        + " Bytes");

                System.out.println(
                    "Size of Compressed File (SCF): " + compressedByteSize
                        + " Bytes");

                System.out.println(
                    "Because SOF > SCF and force = false, the compression happened");

                // perform compression
                out.write(BITS_PER_INT, MAGIC_NUMBER);

                writeTree(huffManTree.getRoot(), out);

                int inbits;

                while ((inbits =
                    ((BitInputStream)stream).read(BITS_PER_WORD)) != -1)
                {
                    String codes = "";
                    // get the code computed in part II
                    codes = this.treeCodes.getLetterCode(inbits);
                    // convert that code into an array of chars using
                    // .toCharArray()
                    // method
                    char[] charArray = codes.toCharArray();
                    // go through the array of char and write each char (cast as
                    // int)
                    // using 1 bit
                    for (int i = 0; i < charArray.length; i++)
                    {
                        // implicit casting from char to int
                        out.write(1, charArray[i]);
                    }

                }

                stream.close();

                String pSeudo = this.treeCodes.getLetterCode(PSEUDO_EOF);

                // create another array of chars for pseudo
                char[] pSeudoCharArray = pSeudo.toCharArray();

                // go through the array of char and write each char (cast as
                // int)
                // using 1 bit
                for (int i = 0; i < pSeudoCharArray.length; i++)
                {
                    // implicit casting from char to int
                    out.write(1, pSeudoCharArray[i]);
                }

                // close the stream
                out.close();

            }

        }

    }


    // ----------------------------------------------------------
    /**
     * This method will obtain the compressed file size to the original file
     * size.
     *
     * @param original
     *            original file that we are going to compress
     * @return an array of int where [0] is the Original size and [1] is the
     *         compressed file size
     */
    private int[] isCompFileBigger()

    {

        // size of the original file
        int originalByteSize = this.frequencies.getOriginalFileSize();

        // calculating the size of the original file
        originalByteSize = this.frequencies.getOriginalFileSize();

        // store the size of the compressed file somewhere
        int compBitSize = 0;
        // STEP1: calculate the size of compress file

        // store the 32 vits read for the Magic Number
        compBitSize += 32;

        // Get the number of bits stored in the first part of our compression
        // writing
        compBitSize += bitsCount(this.huffManTree.getRoot());

        // add the number of numbers in coding multiplied by frequency
        int[] total_EofCodeLength = compressedContent();

        compBitSize += total_EofCodeLength[0];

        // add the number of numbers of the PSEUDO_EOF file
        compBitSize += total_EofCodeLength[1];

        // divide total number of bits by 8 (8 bits in one byte)

        int compByteSize = (compBitSize / 8);

        // store the values in an array
        int[] sizes = new int[2];

        sizes[0] = originalByteSize;
        sizes[1] = compByteSize;
        return sizes;

    }


    /**
     * Uncompress a previously compressed file.
     *
     * @param in
     *            is the COMPRESSED file to be uncompressed
     * @param out
     *            is where the uncompressed bits will be written
     * @throws IOException
     */
    @Override
    public void uncompress(InputStream in, OutputStream out)
        throws IOException
    {
        // Make sure the Magic Number is written correctly
        // store magic number in a variable
        int magic = ((BitInputStream)in).read(BITS_PER_INT);

        // compare magic number
        if (magic != MAGIC_NUMBER)
        {
            throw new IOException("magic number not right");
        }

        // SECOND: load the huffman tree

        // Create the root of the decompressed tree
        IHuffBaseNode root = decompressTree(in);

        // THIRD: Use tree to write out the original text file
        writeToOriginal(in, (BitOutputStream)out, root);

        // close the out stream
        out.close();

    }


    // PRIVATE METHODS
    // ----------------------------------------------------------
    /**
     * Makes a tree out of nodes, This is private since only this class uses the
     * method
     *
     * @return HuffTree object
     */
    private HuffTree buildTree()
    {
        HuffTree tmp1, tmp2, tmp3 = null;

        while (Hheap.heapsize() > 1)
        { // While two items left
            tmp1 = (HuffTree)Hheap.removemin();
            tmp2 = (HuffTree)Hheap.removemin();
            tmp3 = new HuffTree(
                tmp1.getRoot(),
                tmp2.getRoot(),
                tmp1.getWeight() + tmp2.getWeight());
            Hheap.insert(tmp3); // Return new tree to heap
        }
        return tmp3; // Return the tree
    }


    /**
     * pre-order traversal method that will take the root of the Huff tree and
     * the BitOutputStream object as parameters
     *
     * @param root
     *            is the root of the huffman tree we are traversing
     * @param out
     *            is the output stream we are writing to
     */
    private void writeTree(IHuffBaseNode root, BitOutputStream out)
    {

        // NOTE: we do not need a node == null base case since the isLeaf method
        // serves as the base case

        // IMPLEMENT PRE ORDER TRAVERSAL
        // VISIT
        if (root.isLeaf())
        {
            out.write(1, 1); // write a 1 using one bit
            // write the element of the node (aka the char value) in 9 bits
            out.write(9, ((HuffLeafNode)root).element());

            return;
        }

        else
        {
            // if it is not a leaf it is an internal node so...
            out.write(1, 0); // write a 0 using one bit
            // TRAVERSE LEFT
            writeTree(((HuffInternalNode)root).getLeft(), out);

            // TRAVERSE RIGHT
            writeTree(((HuffInternalNode)root).getRight(), out);

            return;

        }

    }


    /**
     * This method will take the compress file created and create a huffman tree
     * from the encoded values in the compress file
     *
     * @param in
     *            compress file
     * @return the huffmantree created from the comrpess file
     * @throws IOException
     */

    private IHuffBaseNode decompressTree(InputStream in)
        throws IOException
    {

        // implement a Preorder traversal reading
        // SECOND: load the huffman tree
        // HOW DO YOU KNOW IF STORE LEFT OR RIGHT

        // store the read section in a variable
        int value = ((BitInputStream)in).read(1);
        if (value == 1)
        {
            // store the next section in another variable
            int nodeValue = ((BitInputStream)in).read(9);

            if (nodeValue == PSEUDO_EOF)
            {
                // stop recursion somehow
                return new HuffLeafNode((char)nodeValue, 0);
            }

            else
            {
                // create a leaf node
                // insert char of the element and the wt does not matter
                // char of the element

                // IHuffBaseNode root = new(l, r, wt);
                // return a tree
                // return a leaf node
                return new HuffLeafNode((char)nodeValue, 0);

            }
        }

        // the value is equal to 0
        else
        {
            // create an internal node and recursive call
            return new HuffInternalNode(
                decompressTree(in),
                decompressTree(in),
                0);

        }

    }


    /**
     * This method will be in charge of traversing the decompressed tree using
     * the compressed file
     *
     * @param in
     *            is the compressed file we are reading
     * @param out
     *            will be the output test file that will be the same as the
     *            original fileOutputStream out
     * @param root
     *            is the root of the huffman tree
     * @throws IOException
     */
    private void writeToOriginal(
        InputStream in,
        BitOutputStream out,
        IHuffBaseNode root)
            throws IOException
    {

        // Store the root in another variable
        IHuffBaseNode temp = root;
        int bits;
        while (true)
        {

            if (temp.isLeaf())
            {
                if (((HuffLeafNode)temp).element() == PSEUDO_EOF)
                {
                    // loop = 1;
                    break;
                }
                else
                {
                    // write character stored in leaf-node
                    out.write(((HuffLeafNode)temp).element());

                    // make root the original root
                    temp = root;
                }

            }

            bits = ((BitInputStream)in).read(1);

            if (bits == -1)
            {
                throw new IOException("unexpected end of input file");
            }

            else
            {

                // read a 0, go left in tree
                if (bits == 0)
                {
                    // go left
                    // check if leaf node and pseudo stuff
                    temp = ((HuffInternalNode)temp).getLeft();

                }

                // read a 1, go right in tree
                else
                {
                    // go right
                    temp = ((HuffInternalNode)temp).getRight();

                }

            }

        }

    }


    private int bitsCount(IHuffBaseNode root)
    {
        if (root == null)
        {
            return 0;
        }

        else if (root.isLeaf())
        {
            return 10;
        }

        else
        {

            return bitsCount(((HuffInternalNode)root).getLeft())
                + bitsCount(((HuffInternalNode)root).getRight()) + 1;

        }

    }


    /**
     * This method will get the total c
     *
     * @return total bit count
     */
    private int[] compressedContent()
    {
        // for loop to go through the array of encoding objects
        int total = 0;
        String eofCode = "";
        for (int i = 0; i < this.treeCodes.getCodesSize(); i++)
        {
            // for each iteration we are looking to multiply the frequency by
            // the length of the encoding of the character

            // get the coding of that portion of the array
            String coding = this.treeCodes.getEncoding(i).getCoding();

            // get the char of that portion of the array
            char letter = this.treeCodes.getEncoding(i).getLetter();

            // get the length of the string that represents the number of bits
            // it takes to represent
            int codLength = coding.length();

            // get the frequency of that letter
            int freqOfLetter = 0;
            if (letter < 256)
            {
                freqOfLetter = this.frequencies.getCount(letter);
            }
            // we have the end of file character
            else
            {

                // add nothing
                freqOfLetter = 0;
                // save the coding of the EOF_Character
                eofCode = coding;

            }

            // get the total number of bits stored in the compressed file for
            // that letter
            int numberAdded = freqOfLetter * codLength;
            total += numberAdded;

        }

        int[] results = new int[2];

        results[0] = total;

        results[1] = eofCode.length();

        return results;
    }

}
