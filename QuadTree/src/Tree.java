import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * This is the class for the quadtree data structure
 *
 * @author Jack Cobb jack3
 * @version Feb 28, 2014
 * @param <T>
 *            is the type of quadtree to create
 */
public class Tree<T extends CoordExtractor>
{
    // Fields
    private Node<T> flyweight;
    private Node<T> root;


    // ----------------------------------------------------------
    /**
     * Create a new Tree object.
     */
    public Tree()
    {
        flyweight = new ENode();
        root = flyweight;
    }


    /**
     * this method resets the tree
     */
    public void reset()
    {
        root = flyweight;
    }


    /**
     * This calls the root no and inserts on that
     *
     * @param city
     *            is the city to insert
     */
    public void insert(T city)
    {
        root = root.insert(city, 0, 0, 1024);

    }


    /**
     * Prints out the tree of the structure
     * @return is the treed elements
     */
    public ArrayList<String> makeTree()
    {
        ArrayList<String> l = new ArrayList<String>();
        l = root.makeTree(511, 511, 0, 512, l);
        return l;
    }


    // ----------------------------------------------------------
    /**
     * This finds the the x y coordinate and returns an array with it inside
     *
     * @param x
     *            is the x location to search for
     * @param y
     *            is the y location to search for
     * @param element
     *            is the array list that gets returned from the find
     * @return is the arraylist containing the found element
     */
    public ArrayList<T> find(int x, int y, ArrayList<T> element)
    {
        return root.find(x, y, 0, 0, element, 1024);
    }


    // ----------------------------------------------------------
    /**
     * This method deletes the x and y location from the quad tree
     *
     * @param x
     *            is where to delete
     * @param y
     *            is where to delete
     */
    public void delete(int x, int y)
    {
        root = root.delete(x, y, 0, 0, 1024);
    }


    // ----------------------------------------------------------
    /**
     * This method handles the rfind method. It calls it on the root node that
     * handles it differently based on the type of node
     *
     * @param xmin
     *            the x min of the range
     * @param xmax
     *            the x max of the range
     * @param ymin
     *            the y min of the range
     * @param ymax
     *            the y max of the range
     * @return is an arraylist of cities in the bound
     */
    public ArrayList<T> findR(int xmin, int xmax, int ymin, int ymax)
    {
        ArrayList<T> found = new ArrayList<T>();
        found = root.rFind(xmin, xmax, ymin, ymax, found, 0, 0, 1024);
        return found;
    }


    // Internalized classes-----------------------------------------------
    /**
     *  This is an abstract node class that will be implemented by the other
     *  3 types of node in the tree
     *  @param <T> is the type of node to create. Same as the tree
     *
     *  @author Jack Cobb
     *  @version Mar 3, 2014
     */
    private interface Node<T>
    {
        /**
         * This method inserts locations into the tree
         * @param data is the city record to insert
         * @param curX is where we are in the tree
         * @param curY is where we are in the tree
         * @param bound is the bound of the quadrants
         * @return is the adjusted node after inserting
         *
         */
        public abstract Node<T> insert(T data, int curX, int curY, int bound);

        /**
         * this finds a location in the tree
         * @param x is the x to look for
         * @param y is the y to look for
         * @param curx is the current x in the tree
         * @param cury is the current y in the tree
         * @param element is the array of found locations
         * @param bound is the bound of the quadrants
         * @return is the arraylist of found elements
         */
        public abstract ArrayList<T> find(
            int x,
            int y,
            int curx,
            int cury,
            ArrayList<T> element,
            int bound);

        /**
         * this method deletes a location from the tree
         * @param x is the x location to delete
         * @param y is the y location to delete
         * @param curX is the current x in the traversal
         * @param curY is the current y in the traversal
         * @param bound is the current bounds of the node
         * @return is the updated node after the delete has been taken care of
         *
         */
        public abstract Node<T> delete(
            int x,
            int y,
            int curX,
            int curY,
            int bound);

        /**
         * this method prints out a tree structure of the quadtree
         * @param curx is the current x of our position
         * @param cury is the current y of our position
         * @param depth is how far down we are
         * @param bound is the counds to pass to the next node
         * @param l is the arraylist to add
         * @return is the arraylist of found strings
         *
         */
        public abstract ArrayList<String> makeTree(int curx, int cury,
            int depth, int bound, ArrayList<String> l);

        /**
         * this method finds the cities within a given rectangle
         * @param xmin is the min x
         * @param xmax is the max x
         * @param ymin is the min y
         * @param ymax is the max y
         * @param cities is the array of found cities
         * @param curx is where we are in the tree currently
         * @param cury is where we are in the tree currently
         * @param bound is the bound limit of the nodes
         * @return the array of found cities
         */
        public abstract ArrayList<T> rFind(
            int xmin,
            int xmax,
            int ymin,
            int ymax,
            ArrayList<T> cities,
            int curx,
            int cury,
            int bound);
    }

    /**
     * // -----------------------------------------------------------------
    /**
     *  This is the empty node. It implements all the method of the
     *  node class to perform actions slightly different
     *
     *  @author Jack Cobb
     *  @version Mar 3, 2014
     */
    private class ENode
        implements Node<T>
    {
        /**
         * this inserts the value into the tree. This method will not do it
         * because the parent will make this a leaf node instead
         */
        public Node<T> insert(T data, int curX, int curY, int bound)
        {
            LNode leaf = new LNode();
            leaf.insert(data, curX, curY, bound);
            return leaf;
        }


        /**
         * this method finds element at x y, but will always return the array
         * because this is an empty node.
         */
        public ArrayList<T> find(
            int x,
            int y,
            int curx,
            int cury,
            ArrayList<T> element,
            int bound)
        {
            return element; // it wasn't found so return empty array
        }


        /**
         * this method removes the city at x, y. Since this is an empty node, it
         * will always return an empty array
         */
        public Node<T> delete(int x, int y, int curX, int curY, int bound)
        {
            System.out.println("Not found");
            return this;
        }


        /**
         * prints out the node structure for the quad tree
         */
        public ArrayList<String> makeTree(int curx, int cury, int depth,
            int bound,
            ArrayList<String> l)
        {
            String str = "";
            for (int i = 0; i < depth; i++)
            {
                str = str + "....";
            }
            l.add(str + "Empty");
            return l;
        }

        /**
         * this finds all the cities within a given rectangle/box
         */
        public ArrayList<T> rFind(
            int xmin,
            int xmax,
            int ymin,
            int ymax,
            ArrayList<T> cities,
            int curx,
            int cury,
            int bound)
        {
            return cities;
        }

    }


    /**
     * // -----------------------------------------------------------------
     * --- This is the internal node that can hold up to 4 children of any
     * type nodes
     *
     * @author Jack Cobb
     * @version Mar 3, 2014
     */
    private class INode
        implements Node<T>
    {
        // Fields for children
        private Node<T> nw;
        private Node<T> ne;
        private Node<T> sw;
        private Node<T> se;

        /**
         * the constructor for INode that sets all the quadrant nodes to an
         * empty node
         */
        public INode()
        {
            nw = flyweight;
            ne = flyweight;
            sw = flyweight;
            se = flyweight;
        }

        /**
         * this prints out the tree structure of this internal node
         */
        public ArrayList<String> makeTree(int curx, int cury, int depth,
            int bound, ArrayList<String> l)
        {
            ArrayList<String> tree;
            tree = l;
            int half = bound / 2;
            String nodeString = "";
            for (int i = 0; i < depth; i++)
            {
                nodeString = nodeString + "....";
            }
            nodeString =
                nodeString + "Internal (" + (curx + 1) + ", " + (cury + 1)
                    + ")";
            tree.add(nodeString);
            tree = nw.makeTree(curx - half, cury - half, depth + 1, half, l);
            tree = ne.makeTree(curx + half, cury - half, depth + 1, half, l);
            tree = sw.makeTree(curx - half, cury + half, depth + 1, half, l);
            tree = se.makeTree(curx + half, cury + half, depth + 1, half, l);
            return tree;
        }


        /**
         * this inserts the x, y value of data
         */
        public Node<T> insert(T data, int curX, int curY, int bound)
        {
            int half = bound / 2;
            if (data.getX() < curX + half && data.getY() < curY + half)
            {
                nw = nw.insert(data, curX, curY, half);
                return this;
            }
            else if (data.getX() >= curX + half && data.getY() < curY + half)
            {
                ne = ne.insert(data, half + curX, curY, half);
                return this;
            }
            else if (data.getX() < curX + half && data.getY() >= curY + half)
            {
                sw = sw.insert(data, curX, curY + half, half);
                return this;
            }
            else
            {
                se = se.insert(data, curX + half, curY + half, half);
                return this;
            }
        }


        /**
         * this finds the x, y city
         */
        public ArrayList<T> find(
            int x,
            int y,
            int curx,
            int cury,
            ArrayList<T> element,
            int bound)
        {
            int half = bound / 2;
            if (x < curx + half && y < cury + half)
            {
                return nw.find(x, y, curx, cury, element, half);
            }
            else if (x >= curx + half && y < cury + half)
            {
                return ne.find(x, y, curx + half, cury, element, half);
            }
            else if (x < curx + half && y >= cury + half)
            {
                return sw.find(x, y, curx, cury + half, element, half);
            }
            else
            {
                return se.find(x, y, curx + half, cury + half, element, half);
            }
        }


        /**
         * this deletes from the internal node
         */
        public Node<T> delete(int x, int y, int curX, int curY, int bound)
        {
            int half = bound / 2;
            if (x < curX + half && y < curY + half)
            {
                nw = nw.delete(x, y, curX, curY, half);
            }
            else if (x >= curX + half && y < curY + half)
            {
                ne = ne.delete(x, y, curX + half, curY, half);
            }
            else if (x < curX + half && y >= curY + half)
            {
                sw = sw.delete(x, y, curX, curY + half, half);
            }
            else
            {
                se = se.delete(x, y, curX + half, curY + half, half);
            }
            return transform();
        }

        /**
         * this is a helper method for the delete function
         */
        private Node<T> transform()
        {
            if (nw.toString().equals("leaf") && ne == flyweight
                && sw == flyweight && se == flyweight)
            {
                return nw;
            }
            else if (ne.toString().equals("leaf") && nw == flyweight
                && sw == flyweight && se == flyweight)
            {
                return ne;
            }
            else if (sw.toString().equals("leaf") && nw == flyweight
                && ne == flyweight && se == flyweight)
            {
                return sw;
            }
            else if (se.toString().equals("leaf") && nw == flyweight
                && ne == flyweight && sw == flyweight)
            {
                return se;
            }
            else
            {
                return this;
            }
        }

        /**
         * this is the rfind method that finds all cities within a give
         * rectangle
         */
        public ArrayList<T> rFind(
            int xmin,
            int xmax,
            int ymin,
            int ymax,
            ArrayList<T> cities,
            int curx,
            int cury,
            int bound)
        {
            ArrayList<T> foundCities = cities;
            int half = bound / 2;
            if (curx + half > xmin && cury + half > ymin)
            {
                foundCities =
                    nw.rFind(
                        xmin,
                        xmax,
                        ymin,
                        ymax,
                        foundCities,
                        curx,
                        cury,
                        half);
            }
            if (curx + half <= xmax && cury + half > ymin)
            {
                foundCities =
                    ne.rFind(
                        xmin,
                        xmax,
                        ymin,
                        ymax,
                        foundCities,
                        half,
                        cury,
                        half);
            }
            if (curx + half > xmin && cury + half <= ymax)
            {
                foundCities =
                    sw.rFind(
                        xmin,
                        xmax,
                        ymin,
                        ymax,
                        foundCities,
                        curx,
                        half,
                        half);
            }
            if (curx + half <= xmax && cury + half <= ymax)
            {
                foundCities =
                    se.rFind(
                        xmin,
                        xmax,
                        ymin,
                        ymax,
                        foundCities,
                        half,
                        half,
                        half);
            }
            return foundCities;
        }
    }

    /**
     * // ----------------------------------------------------
    /**
     *  This is the leaf node of a quad tree
     *
     *  @author Jack
     *  @version Mar 3, 2014
     */
    private class LNode
        implements Node<T>
    {
        // Fields
        private T value;

        /**
         * this is how the insert gets performed for a leaf node
         */
        public Node<T> insert(T data, int curX, int curY, int bound)
        {
            if (value == null)
            {
                value = data;
                return this;
            }
            else if (value.getX() == data.getX() && value.getY() == data.getY())
            {
                System.out.println("Duplicate");
                return this;
            }
            else
            {
                Node<T> internal = new INode();
                internal = internal.insert(value, curX, curY, bound);
                internal = internal.insert(data, curX, curY, bound);
                return internal;
            }
        }

        /**
         * it returns the array with the found element
         */
        public ArrayList<T> find(
            int x,
            int y,
            int curx,
            int cury,
            ArrayList<T> element,
            int bound)
        {
            if (value.getX() == x && value.getY() == y)
            {
                element.add(value);
            }
            return element;
        }

        /**
         * this is the leafs implementation of delete.
         */
        public Node<T> delete(int x, int y, int curX, int curY, int bound)
        {
            if (value.getX() == x && value.getY() == y)
            {
                return flyweight;
            }
            else
            {
                System.out.println("Not found");
                return this;
            }
        }

        /**
         * prints out a tree of the leaf
         * @return is the arraylist of tree'd elements
         */
        public ArrayList<String> makeTree(int curx, int cury, int depth,
            int bound, ArrayList<String> l)
        {
            String nodeString = "";
            for (int i = 0; i < depth; i++)
            {
                nodeString = nodeString + "....";
            }
            nodeString = nodeString + value.toString();
            l.add(nodeString);
            return l;
        }

        /**
         * makes a to string of the node type
         */
        public String toString()
        {
            return "leaf";
        }


        /**
         * this method is the leafs implementation of rfind
         */
        public ArrayList<T> rFind(
            int xmin,
            int xmax,
            int ymin,
            int ymax,
            ArrayList<T> cities,
            int curx,
            int cury,
            int bound)
        {
            if (value.getX() >= xmin && value.getX() < xmax
                && value.getY() >= ymin && value.getY() < ymax)
            {
                cities.add(value);
            }
            return cities;
        }
    }
}
