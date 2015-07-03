import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;

// -------------------------------------------------------------------------
/**
 * This is the three class that handles the implementation of the binary tree on
 * a disk
 *
 * @author Jack Cobb
 * @version Apr 25, 2014
 */
public class Tree
{
    // Fields
    private long   freeList;
    private String fp;
    private FLNode fl;


    /**
     * the constructor for a new tree. This class does not need to be generic
     *
     * @param file
     *            is the file location
     * @throws FileNotFoundException
     *             when the file location is not found
     */
    public Tree(String file)
        throws FileNotFoundException
    {
        fp = file;
        freeList = 8;
        fl = new FLNode(fp, 8);
    }


    // find
    // find key value at bytePosition
    // or
    // find key not found
    // ----------------------------------------------------------
    /**
     * This method reads through the file until it finds the key or doesn't find
     * it at all
     *
     * @param current
     *            is the current node from the file
     * @param key
     *            is the key to search for
     * @return is the byte position the key is found at. -1 if not found
     * @throws IOException
     *             is it can't read from file
     */
    public long find(Node current, float key)
        throws IOException
    {
        long bytePosition = 0;
        float curK = current.getKey();
        if (curK == key)
        {
            bytePosition = current.getOffset();
        }
        else if (current.getOffset() == 0)
        {
            bytePosition = -1;
        }
        else if (curK < key)
        {
            Node next = current.getRightNode();
            bytePosition = find(next, key);
        }
        else
        // curK > key
        {
            Node next = current.getLeftNode();
            bytePosition = find(next, key);
        }
        return bytePosition;

    }


    // insert
    // insert key value at bytePosition
    // or
    // insert key value duplicate

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @param current
     *            is the current node where we are in the file
     * @param key
     *            is the key to insert
     * @param data
     *            is the data value to insert
     * @return the byte position it was inserted at. -1 if duplicate key is
     *         found
     * @throws IOException
     *             is there is a problem reading or writing to file
     */
    public long insert(Node current, float key, int data)
        throws IOException
    {
        // nothing about when the tree is empty. Handle this in the database for
        // inserting a single node first
        long bytePosition = 0;
        float curK = current.getKey();
        if (curK == key) // found same key; duplicate
        {
            bytePosition = -1;
        }
        else
        {
            if (curK > key) // go left
            {
                Node left = current.getLeftNode();
                if (left.getOffset() == 0)
                {
                    bytePosition = current.setLeft(key, data, freeList);
                }
                else
                {
                    bytePosition = insert(left, key, data);
                }
            }
            else if (curK < key) // go right
            {
                Node right = current.getRightNode();
                if (right.getOffset() == 0)
                {
                    bytePosition = current.setRight(key, data, freeList);
                }
                else
                {
                    bytePosition = insert(right, key, data);
                }
            }
        }
        // if inserted bytepostion is where, if dupe found its -1
        return bytePosition;
    }


    // ----------------------------------------------------------
    /**
     * This method attempts to delete the desired key from the file. If it is
     * not found then it returns not found
     *
     * @param current
     *            is the current node
     * @param key
     *            is the key to delete
     * @param removedOffset
     *            is an array to hold the removed offset (location)
     * @return is the correct child here
     * @throws IOException
     *             if there is a problem writing or reading from file
     */
    public long delete(Node current, float key, Node[] removedOffset)
        throws IOException
    {
        long newOffset = current.getOffset();
        if (current.getKey() == key)
        {
            long right = current.getRight();
            long left = current.getLeft();
            removedOffset[0] = current; // adds removed
            if (right != 0 && left != 0)
            {
                long[] minOff = new long[1];
                Node rightN = current.getRightNode();
                // get min of right for new offset to record (should handle
// fixing its parent pointers)
                current.setRight(rightN.getMin(rightN, minOff));
                // with min of right offset, change pointer to current.left and
// current.right
                Node found = new Node(minOff[0]);
                found.setLet(current.getLeft());
                found.setRight(current.getRight());
                // return the min of right subtree
                newOffset = minOff[0];
            }
            else if (left != 0)
            {
                // return left child
                newOffset = left;
            }
            else if (right != 0)
            {
                // return right offset
                newOffset = right;
            }
            else
            { // return 0 because no children\
                newOffset = 0;
            }
            fl.createFreeLoc(current.getOffset());
        }
        else if (newOffset == 0)
        {
            removedOffset[0] = new Node(key, current.getData(), -1, fp);
            // -1 represents not in file
        }
        else if (current.getKey() > key)
        {
            current.setLet(delete(current.getLeftNode(), key, removedOffset));
        }
        else
        {
            current
                .setRight(delete(current.getRightNode(), key, removedOffset));
        }
        return newOffset;
    }


    // ----------------------------------------------------------
    /**
     * This runs down the file making a tree for easier human viewing of the
     * tree. Not how it looks in the file
     *
     * @param current
     *            is the current node we are at
     * @param depth
     *            is the depth down the tree. Used for output formatting
     * @param treeList
     *            is an arralist of strings in the correct order used for
     *            printing
     * @throws IOException
     *             gets thrown if there is a problem reading or writing from the
     *             file
     */
    public void tree(Node current, int depth, ArrayList<String> treeList)
        throws IOException
    {
        if (current.getOffset() == 0)
        {
            return;
        }
        // tree on left node
        tree(current.getLeftNode(), depth + 1, treeList);

        // format the string for the key and value at this byte position
        String level = "";
        for (int i = 0; i < depth; i++)
        {
            level = level + ">";
        }
        String key = Float.toString(current.getKey());
        String value = Integer.toString(current.getData());
        level = level + key + " " + value + " at " + current.getOffset();
        treeList.add(level);

        // tree on right node
        tree(current.getRightNode(), depth + 1, treeList);
    }


    // ----------------------------------------------------------
    /**
     * This attempts to run through the freelist to print out the stack of the
     * freelist
     *
     * @param start
     *            is the head of the free list
     * @param list
     *            is the freelist in order for printing
     * @throws IOException
     *             gets thrown if there is a problem reading or writing from the
     *             file
     */
    public void makeFreeList(FLNode start, ArrayList<String> list)
        throws IOException
    {
        start.freeList(1, list);
    }

}
