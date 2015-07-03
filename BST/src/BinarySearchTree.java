import java.util.ArrayList;
import java.util.Comparator;


// -------------------------------------------------------------------------
/**
 *  This class is the binary search tree and organizes the data in a binary
 *  tree that makes the data easier to be found. This implementation of a
 *  a binary search tree is based on the OpenDSA implementation.
 *
 *
 *  @author Jack Cobb (jack3)
 *  @version Feb 11, 2014
 * @param <T> Is the type of binary tree to create
 */
public class BinarySearchTree<T>
{

    private BSTNode<City> root; // Root of the BST
    private Comparator<City> comp;

    // ----------------------------------------------------------
    /**
     * Create a new BinarySearchTree object.
     * @param type is what type of comparator to use to sort the tree
     */
    public BinarySearchTree(Comparator<City> type)
    {
        root = null;
        comp = type;
    }

    /**
     * Clears the entire tree
     */
    public void makeNull()
    {
        root = null;
    }

    /**
     * Returns the size of the tree
     * @param rt is the node of count from
     * @return is the size
     */
    public int size(BSTNode<City> rt)
    {
        if (rt == null)
        {
            return 0;
        }
        return 1 + size(rt.getLeft()) + size(rt.getRight());
    }


    /**
     * This method inserts the city into the node
     *
     * @param add
     *            is the node to be added
     */
    public void insert(City add)
    {
        if (root == null)
        {
            root = new BSTNode<City>(add);
        }
        else
        {
            insertHelp(root, add);
        }
    }

    /**
     * this is a method to return the root of the entire tree
     * @return is the root of the tree
     */
    public BSTNode<City> root()
    {
        return root;
    }


    /**
     * the sort method traverses the tree in order
     *
     * @param rt
     *            is the node to start the sort from
     */
    public void sort(BSTNode<?> rt)
    {
        if (root == null)
        {
            System.out.println("Database empty");
        }
        else
        {
            if (rt == null)
            {
                return;
            }
            sort(rt.getLeft());
            sortHelp(rt.getElement());
            sort(rt.getRight());
        }

    }

    /**
     * this is the tree method. It prints out the tree in order but with
     * 4i spacing, where i is the depth of that node in the tree
     * @param rt is the node to continue the tree call from
     * @param depth is the current depth of the node
     */
    public void tree(BSTNode<?> rt, int depth)
    {
        if (root == null)
        {
            System.out.println("Database empty");
        }
        else
        {
            if (rt == null)
            {
                return;
            }
            tree(rt.getLeft(), depth + 1);
            treeHelp(rt.getElement(), depth);
            tree(rt.getRight(), depth + 1);
        }
    }

    /**
     * This is the delete method that deletes all records with the
     * input data
     * @param rt is the node to check against for deletion
     * @param gone is the city node that should be deleted
     * @return is the node to switch to
     */
    public BSTNode<City> delete(BSTNode<City> rt, BSTNode<City> gone)
    {
        if (rt == null)
        {
            return null;
        }
        else if (rt == root
            && rt.getElement().info().equals(gone.getElement().info()))
        {
            if (rt.getLeft() != null && rt.getRight() == null)
            {
                root = rt.getLeft();
            }
            else if (rt.getRight() != null && rt.getLeft() == null)
            {
                root = rt.getRight();
            }
            else
            {
                BSTNode<City> temp = getMin(root.getRight());
                root.setElement(temp.getElement());
                root.setRight(deleteHelper(root.getRight()));
            }

        }
        if (comp.compare(rt.getElement(), gone.getElement()) > 0)
        {
            rt.setLeft(delete(rt.getLeft(), gone));
        }
        else if (comp.compare(rt.getElement(), gone.getElement()) < 0)
        {
            rt.setRight(delete(rt.getRight(), gone));
        }
        else
        {

            if (rt.getLeft() != null && rt.getRight() != null)
            {
                BSTNode<City> temp = getMin(rt.getRight());
                rt.setElement(temp.getElement());
                rt.setRight(deleteHelper(rt.getRight()));
            }
            else if (rt.getRight() == null)
            {
                return rt.getLeft();
            }
            else
            {
                return rt.getRight();
            }
        }
        return rt;

    }

    /**
     * this is the find k method. It returns the kth node in the tree
     * @param k is the kth node to find
     */
    public void findKth(int k)
    {
        findKHelper(root, k);
    }

    /**
     * the find range method. It slightly alters the find method to find a
     * range of values
     * @param node is the node to check from
     * @param start is the starting point of the range to look within
     * @param stop is the ending point of the range to look within
     * @param found is the list of found elements to return
     * @return returns the list of elements
     */
    public ArrayList<BSTNode<City>> findRange(BSTNode<City> node, City start,
        City stop,
        ArrayList<BSTNode<City>> found)
    {
        if (node != null)
        {
            if (comp.compare(node.getElement(), start) <= 0)
            {
                findRange(node.getRight(), start, stop, found);
            }
            else if (comp.compare(node.getElement(), stop) >= 0)
            {
                findRange(node.getLeft(), start, stop, found);
            }
            else
            {
                findRange(node.getLeft(), start, stop, found);
                found.add(node);
                findRange(node.getRight(), start, stop, found);
            }
        }
        else
        {
            return null;
        }
        return found;
    }


    /**
     * Another try at a remove method
     *
     * @param current
     *            is the current node to search from
     * @param find
     *            is what we are looking for
     * @param list
     *            is a list to hold all the found cities
     * @return is an arraylist of matching cities
     */
    public ArrayList<BSTNode<City>> findCities(
        BSTNode<City> current,
        BSTNode<City> find,
        ArrayList<BSTNode<City>> list)
    {
        if (current != null)
        {
            if (comp.compare(current.getElement(), find.getElement()) == 0)
            {
                list.add(current);
                findCities(current.getRight(), find, list);
            }
            else if (comp.compare(current.getElement(), find.getElement()) < 0)
            {
                findCities(current.getRight(), find, list);
            }
            else
            // compare is > 0
            {
                findCities(current.getLeft(), find, list);
            }
        }
        return list;

    }


    //--------Private helper methods---------------
     /**
      * the sort help takes a city and returns it's string representation
     * @param start is the city to be output
     */
    private void sortHelp(Object start)
    {
        System.out.println((start.toString()));
    }

    /**
     * this method finds the minimum value in the tree
     * @param node is the node to get the minimum from
     * @return is the minimum node
     */
    public BSTNode<City> getMin(BSTNode<City> node)
    {
        if (node == null)
        {
            return null;
        }
        else if (node.getLeft() == null)
        {
            return node;
        }

        return getMin(node.getLeft());
    }


    /**
     * this is a helper method for find k.
     */
    private void findKHelper(BSTNode<City> node, int k)
    {
        if (node != null && node.getLeftCount() == k)
        {
            System.out.println(node.getElement().toString());
        }
        else if (node != null && node.getLeftCount() > k)
        {
            findKHelper(node.getLeft(), k);
        }
        else if (node != null && node.getLeftCount() < k
            && node.getRight() != null)
        {
            findKHelper(node.getRight(), k - 1 - node.getLeftCount());
        }
        else
        {
            System.out.println("Not found");
        }
    }


    /**
     * this is a helper method for tree that helps it to traverse the tree
     */
    private void treeHelp(Object start, int depth)
    {
        String spacing = "";
        for (int i = 0; i < depth; i++)
        {
            spacing = spacing + "    ";
        }
        System.out.println(spacing + (start.toString()));
    }


    /**
     * This is a helper method for the insert function
     *
     * @param node
     *            is the node to check against
     * @param toAdd
     *            is the node that needs to be added
     * @return is the node with the added city in it
     */
    private BSTNode<City> insertHelp(BSTNode<City> node, City toAdd)
    {
        if (node == null)
        {
            return new BSTNode<City>(toAdd);
        }
        if (comp.compare(node.getElement(), toAdd) > 0)
        {
            node.setLeft(insertHelp(node.getLeft(), toAdd));
            node.incrementLeft();
        }
        else
        {
            node.setRight(insertHelp(node.getRight(), toAdd));
        }
        return node;
    }


    /**
     * this is a helper method for the main delete function
     *
     * @param rt
     *            is the node
     * @return is the city
     */
    private BSTNode<City> deleteHelper(BSTNode<City> rt)
    {
        if (rt.getLeft() == null)
        {
            return rt.getRight();
        }
        rt.setLeft(deleteHelper(rt.getLeft()));
        return rt;
    }

}