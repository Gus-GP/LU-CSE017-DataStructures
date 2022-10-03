package compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This class tests the HuffModel Class
 *
 * @author Gustavo Grinsteins (gug218)
 * @version 2016.
 */
public class HuffModelTest
    extends TestCase
    implements IHuffConstants
{
    // ~ Instance/static variables .............................................
    private HuffModel      model;
    private HuffModel      model2;
    private BitInputStream bits;
    // ~ Constructor ...........................................................


    // ----------------------------------------------------------
    /**
     * Create a new test class
     */
    public HuffModelTest()
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
        // create a new HuffModel object
        model = new HuffModel();
        model2 = new HuffModel();
    }


// //TODO --> write a test case for when the file is empty
// // ----------------------------------------------------------
// /**
// * Test when the file is empty
// */
// public void testFileEmpty()
// {
// Exception occurred = null;
// try
// {
// //FileInputStream throws a FileNotFoundException if the file
// //is empty
// bits = new BitInputStream(new FileInputStream("emptyfile"));
// //countAll throws an IOException if something is wrong with
// //the input file
// model.initialize(bits);
//
// }
// catch (Exception exception)
// {
// occurred = exception;
// }
// assertNull(occurred);
// assertTrue(occurred instanceof FileNotFoundException);
//
// }

//    /**
//     * Test the minHeap in show codings
//     *
//     * @throws IOException
//     */
//    public void testMedium()
//        throws IOException
//    {
//        bits = new BitInputStream(new FileInputStream("prueba.txt"));
//        model2.initialize(bits);
//        model2.showCounts();
//        model2.showCodings();
//        BitInputStream bits2 =
//            new BitInputStream(new FileInputStream("prueba.txt"));
//        model2.write(bits2, "pruebahuff.txt", false); // write into file
//
//        // test the decompression portion
//        BitInputStream compress =
//            new BitInputStream(new FileInputStream("pruebahuff.txt"));
//        BitOutputStream out = new BitOutputStream("pruebadecomp.txt");
//        model2.uncompress(compress, out);
//
//    }


    /**
     * Test the model class
     *
     * @throws IOException
     */
    public void testBig()
        throws IOException
    {
        bits = new BitInputStream(new FileInputStream("test.txt"));
        File file = new File("test.txt");
        System.out.println("File size: " + file.length() + "bytes"); // TODO - create a
                                                           // method inside the
        // HuffModel that checks the
        // compressed file actal file
        // relationship
        model2.initialize(bits);
        model2.showCounts();
        model2.showCodings();
        BitInputStream bits2 =
            new BitInputStream(new FileInputStream("test.txt"));
        //new method -->
        model2.write(bits2, "testhuff.txt", false); // write into file

        // test the decompression portion
        BitInputStream compress =
            new BitInputStream(new FileInputStream("testhuff.txt"));
        BitOutputStream out = new BitOutputStream("testdecomp.txt");
        model2.uncompress(compress, out);

    }


    /**
     * Test the writing part of the code
     *
     * @throws IOException
     */
    public void testMississippi()
        throws IOException
    {
        bits = new BitInputStream(new FileInputStream("Mississippi.txt"));
        model.initialize(bits);
        model.showCounts();
        model.showCodings();
        BitInputStream bits2 =
            new BitInputStream(new FileInputStream("Mississippi.txt"));
        //__>
        model.write(bits2, "Mississippihuff.txt", false); // write into file

        // test the decompression portion
        BitInputStream compress =
            new BitInputStream(new FileInputStream("Mississippihuff.txt"));
        BitOutputStream out = new BitOutputStream("Mississippidecomp.txt");
        model.uncompress(compress, out);
    }

}
