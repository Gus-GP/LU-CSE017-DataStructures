package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * The purpose of this class is to be able to run the decompression easily
 *
 * @author Gustavo Grinsteins
 * @version 2016.05.02
 */
public class Huff
{

    // ----------------------------------------------------------
    /**
     * This method will print out the frequencies and codes that result from the
     * text file
     *
     * @param args
     *            the boolean of force and the name of the file to be compressed
     * @throws IOException
     */
    public static void main(String[] args)
        throws IOException
    {

        // TODO create a big try catch exception with a general exception in the
        // catching and then print the e.stcaktrace

        try {

            // put the original txt file into a File object
            File originalFile = new File(args[1]);
        	    

            if(!(originalFile.exists())) {
                throw new FileNotFoundException("The file was not found");
            }

            else if(originalFile.length() == 0) {
                System.out.println("Your file is empty, please input a non-empty file");
            }

            else {

            BitInputStream bits = new BitInputStream(originalFile);

            HuffModel model = new HuffModel();

            model.initialize(bits);

            model.showCounts();

            model.showCodings();

            boolean force;

            if (args[0].equalsIgnoreCase("true"))
            {
                force = true;
            }
            else
            {
                force = false;
            }

            bits.reset();
            
            //ask the user how they want to name the file
            Scanner reader = new Scanner(System.in);
            System.out.println("How would you like to name the compressed file ? ");
    			String nameFile = reader.next();
    			reader.close();

            model.write(bits, nameFile + ".huff", force);

            }

        }

        catch(FileNotFoundException e) {
            //print out a message
            System.out.println("ERROR please check file spelling or your file does not exist");
            e.printStackTrace();
        }

        catch(Exception e) {
            //print out a message
            System.out.println("ERROR please check your file inputs, make sure the file is UTF-8");
            e.printStackTrace();
        }

    }

}
