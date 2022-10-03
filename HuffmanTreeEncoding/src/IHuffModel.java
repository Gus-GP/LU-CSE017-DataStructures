package compiler;
/**
 * The interface for the model that can be attached
 * to a HuffmanView. Most of the work done in huffing
 * (and unhuffing) will be via a class that implements
 * this interface. The interface may need to be extended
 * depending on the design of the huffman program.
 * <P>
 * @author Gustavo Grinsteins (gug218)
 *
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The interface for the model that can be attached
 * to a HuffmanView. Most of the work done in huffing
 * (and unhuffing) will be via a class that implements
 * this interface. The interface may need to be extended
 * depending on the design of the huffman program.
 * <P>
 * @author Gustavo Grinsteins (gug218)
 * @version 2016.09.04
 */
public interface IHuffModel extends IHuffConstants {


    /**
     * Display all encodings (via the associated view).
     */
    public void showCodings();

    /**
     * Display all chunk/character counts (via the associated view).
     */
    public void showCounts();

    /**
     * Initialize state via an input stream. The stream most
     * likely comes from a view, it's NOT a BitInputStream
     * @param stream is an input stream for initializing state of this model
     * @throws FileNotFoundException is thrown if the bit declaration file
     * is not there
     * @throws IOException
     */
    public void initialize(InputStream stream) throws FileNotFoundException, IOException;

    /**
     * Write a compressed version of the data read
     * by the InputStream parameter, -- if the stream is
     * not the same as the stream last passed
     * to initialize, then compression won't be optimal, but will still
     * work. If force is false, compression only occurs if it saves
     * space. If force is true compression results even if no bits are saved.
     * @param stream is the input stream to be compressed
     * @param file specifies the name of the file to be written
     * @param force indicates if compression forces
     * @throws IOException if the file is corrupted somehow
     */
    public void write(InputStream stream, String file, boolean force) throws IOException;

    /**
     * Uncompress a previously compressed file.
     * @param in is the compressed file to be uncompressed
     * @param out is where the uncompressed bits will be written
     * @throws IOException
     */
    public void uncompress(InputStream in, OutputStream out) throws IOException;

}
