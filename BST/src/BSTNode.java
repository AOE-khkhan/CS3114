
// -------------------------------------------------------------------------
/**
 * This is the BSTNode class. It takes a city, and deals with the city's
 * children
 *
 * @author Jack Cobb (jack3)
 * @version Feb 11, 2014
 * @param <T>
 *            The data type that the node will be of
 */
public class BSTNode<T>
{
    // Fields
    private City          element;
    private BSTNode<City> left;
    private BSTNode<City> right;
    private int        leftCount;


    // ~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a node with no children.
     *
     * @param theElement
     *            the element to store in this node.
     */
    public BSTNode(City theElement)
    {
        element = theElement;
        left = null;
        right = null;
        leftCount = 0;
    }


    // ~ Public methods ........................................................

    /**
     * Get the current data value stored in this node.
     *
     * @return the element
     */
    public City getElement()
    {
        return element;
    }


    /**
     * Set the data value stored in this node.
     *
     * @param value
     *            the new data value to set
     */
    public void setElement(City value)
    {
        element = value;
    }


    /**
     * Get the left child of this node.
     *
     * @return a reference to the left child.
     */
    public BSTNode<City> getLeft()
    {
        return left;
    }


    /**
     * Set this node's left child.
     *
     * @param value
     *            the node to point to as the left child.
     */
    public void setLeft(BSTNode<City> value)
    {
        left = value;
    }


    /**
     * Get the right child of this node.
     *
     * @return a reference to the right child.
     */
    public BSTNode<City> getRight()
    {
        return right;
    }


    /**
     * Set this node's right child.
     *
     * @param value
     *            the node to point to as the right child.
     */
    public void setRight(BSTNode<City> value)
    {
        right = value;
    }


    /**
     * A method to return the left node count
     *
     * @return is the left node count
     */
    public int getLeftCount()
    {
        return leftCount;
    }


    /**
     * increment the left node count
     */
    public void incrementLeft()
    {
        leftCount++;
    }

    /**
     * decrease the left node count
     */
    public void decreaseLeft()
    {
        leftCount--;
    }
}