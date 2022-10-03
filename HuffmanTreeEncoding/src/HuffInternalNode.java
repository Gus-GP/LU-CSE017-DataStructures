package compiler;

/**
 * Huffman tree node: Internal class
 *
 * @author Gustavo Grinsteins (gug218)
 * @version 2016.04.11
 */
//note that this node does not have a character in it just a number
//for the weight
class HuffInternalNode
    implements IHuffBaseNode
{
    private int           weight;
    private IHuffBaseNode left;
    private IHuffBaseNode right;


    /**
     * Constructor for the internal node
     *
     *@param l is the left child of this node
     *@param r is the right child of this node
     *@param wt is the weight of this node
     */
    public HuffInternalNode(IHuffBaseNode l, IHuffBaseNode r, int wt)
    {
        left = l;
        right = r;
        weight = wt;
    }


    /**
     * getter method for huffman tree
     * @return The left child
     */
    public IHuffBaseNode getLeft()
    {
        return left;
    }


    /**
     * getter method for the Huffman tree
     * @return The right child
     */
    public IHuffBaseNode getRight()
    {
        return right;
    }


    /**
     * This method gives us the chance to access the weight of the object
     * @return The weight of the object
     */
    public int getWeight()
    {
        return weight;
    }


    /**
     * this method gives us a chance to make sure this is not a leaf
     * @return false because this is not a leaf
     */
    public boolean isLeaf()
    {
        return false;
    }
}
