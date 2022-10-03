package compiler;

/**
 * Huffman tree node: Leaf class
 *
 * @author Gustavo Grinsteins (gug218)
 * @version 2016.04.11
 */
//note that this object is also a IHuffBaseNode
class HuffLeafNode
    implements IHuffBaseNode
{
    private char element; // Element for this node
    private int  weight;  // Weight for this node


    /**
     * Constructor
     *
     * @param el is the letter that is in the node
     * @param wt is the frequency of the letter i the text file
     */
    public HuffLeafNode(char el, int wt)
    {
        element = el;
        weight = wt;
    }


    /**
     * return the value of the node
     *
     * @return The element value
     */
    char element()
    {
        return element;
    }


    /**
     * Return the weight of the leaf object
     *
     * @return The weight
     */
    public int getWeight()
    {
        return weight;
    }


    /**
     * This method checks that the node is a leaf
     *
     * @return Return true because this is a leaf node
     */
    public boolean isLeaf()
    {
        return true;
    }
}
