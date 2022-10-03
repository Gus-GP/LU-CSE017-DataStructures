package compiler;

// -------------------------------------------------------------------------
/**
 *  This class will be the basis of the nodes in our huffman tree.
 *  The huffman tree will have leaf and internal nodes and each of them
 *  share the following two methods
 *
 *  @author Gustavo Grinsteins
 *  @version 2016.04.11
 */
public interface IHuffBaseNode
{
    // ----------------------------------------------------------
    /**
     * This method checks if the node is a leaf or not
     * @return true or false depending on the type of tree node
     */
    boolean isLeaf();

    // ----------------------------------------------------------
    /**
     * This method returns the frequency or the weight of the node.
     * @return weight of the node
     */
    int getWeight();

}
