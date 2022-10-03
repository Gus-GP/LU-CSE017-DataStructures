package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * This method will be used for an easy way to decompress files without using
 * unit testing
 *
 * @author Gustavo Grinsteins
 * @version 2016.05.02
 */
public class UnHuff
{

    // ----------------------------------------------------------
    /**
     * This main method will be in charge of running the decompression portion
     * of the code
     *
     * @param args
     *            the name of the file to Decompress
     * @throws IOException
     */
    public static void main(String[] args)
        throws IOException
    {
        try {

            File compressedFile = new File(args[0]);

            if(!(compressedFile.exists())) {
                throw new FileNotFoundException("The file was not found");
            }

            else if(compressedFile.length() == 0) {
                System.out.println("Your file is empty, please input a non-empty file");
            }

            else {

            BitInputStream compress = new BitInputStream(compressedFile);
            
            //ask the user how they want to name the file
            Scanner reader = new Scanner(System.in);
            System.out.println("How would you like to name the decompressed file ? ");
    			String nameFile = reader.next();
    			reader.close();

            BitOutputStream out = new BitOutputStream(nameFile + ".txt");

            HuffModel model = new HuffModel();

            model.uncompress(compress, out);

            System.out.println("SUCCESS!!!!, your file has been decompressed.");
            System.out.println();
            System.out.println("To get your decompressed file, ");
            System.out.println("look in your folder for a text file named " + nameFile);

            }

        }

        catch(FileNotFoundException e) {
            //print out a message
            System.out.println("ERROR please check file spelling or your file does not exist");
            e.printStackTrace();
        }

        catch(Exception e) {

            //print out a message
            System.out.println("ERROR please check your file input, make sure the file is UTF-8");
            e.printStackTrace();

        }

        //oma219

    }

}
