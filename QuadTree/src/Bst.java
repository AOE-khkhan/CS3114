
// -------------------------------------------------------------------------
/**
 *  This is a stub for the bsts. It was not included due to non functionality of
 *  some of the necessary methods
 *
 *  @author Jack Cobb <jack3>
 *  @version Mar 6, 2014
 */
public class Bst
{

    //Fields
    private boolean root;

    // ----------------------------------------------------------
    /**
     * Create a new Bst object. with nothing "inserted" yet
     */
    public Bst()
    {
        root = false; //nothing has been "inserted" yet
    }

    //This class would be used to form a binary tree either based on
    //the city names or the city populations. Since my bst was lacking in
    //functionality, I am implementing a placeholder for the old bsts.

    //It would implement one of the comparators that is how it would organize
    //the cities either based on name or population. Those comparators
    //are central to the methods that need to be implemented. It is used in
    //almost every method except the tree method


    // ----------------------------------------------------------
    /**
     * This method "inserts" into the bst. This is just a place holder that
     * performs an action so that the bst is not empty
     */
    public void insert()
    {
        root = true;
    }

    /**
     * method for checking the status of root
     * @return is the status
     */
    public boolean getRoot()
    {
        return root;
    }
}
