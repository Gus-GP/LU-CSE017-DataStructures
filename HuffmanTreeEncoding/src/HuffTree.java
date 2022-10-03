package compiler;

/**
 * A Huffman coding tree
 * @author Gustavo Grinsteins (gug218)
 *
 * @version 2016.04.11
 */
class HuffTree
    implements Comparable<Object>
{
    //the root of the tree that tell us where the rest is
    private IHuffBaseNode root;


    /**
     * Constructor for the leaf node
     *
     * @param el
     *            the character of the leaf node
     * @param wt
     *            the weight of the leaf
     */
    public HuffTree(char el, int wt)
    {
        root = new HuffLeafNode(el, wt);

    }


    // ----------------------------------------------------------
    /**
     * Create a new HuffTree object for internal nodes
     *
     * @param l
     *            the left child of the internal node
     * @param r
     *            the right child of the internal node
     * @param wt
     *            the weight of the internal node
     */
    public HuffTree(IHuffBaseNode l, IHuffBaseNode r, int wt)
    {
        root = new HuffInternalNode(l, r, wt);
    }


    // ----------------------------------------------------------
    /**
     * obtain the root of the node
     *
     * @return root of the tree
     */
    public IHuffBaseNode getRoot()
    {
        return root;
    }


    // ----------------------------------------------------------
    /**
     * Obtain the weight of the root
     *
     * @return weight of the root
     */
    public int getWeight() // Weight of tree is weight of root
    {
        return root.getWeight();
    }


    /**
     * This compare to override lets you compare node between eachother
     *
     * @return weight of the root
     */
    public int compareTo(Object t)
    {
        HuffTree that = (HuffTree)t;
        if (root.getWeight() < that.getWeight())
            return -1;
        else if (root.getWeight() == that.getWeight())
            return 0;
        else
            return 1;
    }

}
