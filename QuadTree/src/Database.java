import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * This handles the input from the quadtree class and acts on the actual quad
 * tree implementation
 *
 * @author Jack Cobb jack3
 * @version Feb 28, 2014
 * @param <T>
 *            is the type of database to make
 */

public class Database<T extends CoordExtractor>
{
    // Fields
    private Tree<T> quad;
    //bst pop
    //bst name


    // ----------------------------------------------------------
    /**
     * This creates a new database. Its the constructor
     */
    public Database()
    {
        quad = new Tree<T>();
        //pop is a new bst using a popcomparator
        //name is a new bst using a namecomparator
    }


    // ----------------------------------------------------------
    /**
     * This parses the find command to the quad tree
     *
     * @param x
     *            is the x coordinate of the city to search for
     * @param y
     *            is the y coordinate of the city to search for
     */
    public void handleFind(int x, int y)
    {
        //Only find works for location because the bst doesn't function
        //correctly
        if (x > 1023 || x < 0 || y < 0 || y > 1023)
        {
            System.out.println("Not found");
        }
        else
        {
            ArrayList<T> found = new ArrayList<T>();
            found = quad.find(x, y, found);
            if (found.size() == 1)
            {
                System.out.println(found.get(0).toString());
            }
            else
            {
                System.out.println("Not found");
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * This searches within the max and min parameters for any cities that fall
     * withing the bounds
     *
     * @param xMin
     *            is the min x value
     * @param xMax
     *            is the max x value
     * @param yMin
     *            is the min y value
     * @param yMax
     *            is the max y value
     */
    public void handleRFind(int xMin, int xMax, int yMin, int yMax)
    {
        // handles the find resulting box of x, y (top left) and (x+w, y+h)
        if (xMin > 1023 || xMin < 0 || yMin < 0 || yMin > 1023 || xMax > 1023
            || xMax < 0 || yMax < 0 || yMax > 1023)
        {
            System.out.println("Not found");
        }
        else
        {
            ArrayList<T> found = new ArrayList<T>();
            found = quad.findR(xMin, xMax, yMin, yMax);
            if (found.size() != 0)
            {
                for (int i = 0; i < found.size(); i++)
                {
                    System.out.println(found.get(i).toString());
                }
            }
            else
            {
                System.out.println("Not found");
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * parses the delete command and correct location to delete from
     *
     * @param x
     *            is the x location to delete from
     * @param y
     *            is the y location to delete from
     */
    public void handleDeleteLoc(int x, int y)
    {
        // check they are legal coords, then would also delete the same records
        //from the name and pop bsts if the delete function worked correctly
        if (x > 1023 || x < 0 || y < 0 || y > 1023)
        {
            System.out.println("Not found");
        }
        else
        {
            quad.delete(x, y);
        }

    }


    // ----------------------------------------------------------
    /**
     * This method makes the quad tree print out the tree structure with
     * printing
     */
    public void handleTree()
    {
        //Tree would also work with the name and pop bsts if the bst inserted
        //correctly, but it doesn't so it was not included
        ArrayList<String> boss = new ArrayList<String>();
        boss = quad.makeTree();
        for (int i = 0; i < boss.size(); i++)
        {
            System.out.println(boss.get(i));
        }
    }


    // ----------------------------------------------------------
    /**
     * This method adds the city to the tree
     *
     * @param cityname
     *            is the name of the city
     * @param pop
     *            is the population of the city
     * @param x
     *            is the x coordinate of the city
     * @param y
     *            is the y coordinate of the city
     */
    @SuppressWarnings("unchecked")
    public void add(String cityname, int pop, String x, String y)
    {

        //If insert worked correctly, this would also insert the cities into
        //the name and pop bsts

        if (Integer.parseInt(x) > 1023 || Integer.parseInt(x) < 0
            || Integer.parseInt(y) < 0 || Integer.parseInt(y) > 1023)
        {
            System.out.println("Out of Bounds");
        }
        else
        {
            quad.insert((T)new CityRecord(
                cityname,
                pop,
                Integer.parseInt(x),
                Integer.parseInt(y)));
        }

    }


    // ----------------------------------------------------------
    /**
     * This method resets the quad tree
     */
    public void makeNull()
    {
        quad.reset();
        //also resets the bst and pop trees to null

    }
}
