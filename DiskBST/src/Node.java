import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;


// -------------------------------------------------------------------------
/**
 * This is the node class that holds all of the values temporarily to be used in
 * methods
 *
 * @author Jack
 * @version Apr 30, 2014
 */
public class Node
{
    // Fields
    private float            key;
    private int              data;
    private long             left;
    private long             right;
    private long             offset;
    private RandomAccessFile raf;
    private String           filePath;


    // ----------------------------------------------------------
    /**
     * Create a new Node object for temporary use
     *
     * @param file
     *            is the file path
     * @param kv
     *            is the key vale
     * @param dv
     *            is the data value
     * @param leftLoc
     *            is the offset location of its child
     * @param rightLoc
     *            is the offset of the right child
     * @param location
     *            is the offset in the file
     * @throws FileNotFoundException
     *             if the file is not found
     */
    public Node(
        String file,
        float kv,
        int dv,
        long leftLoc,
        long rightLoc,
        long location)
        throws FileNotFoundException
    {
        key = kv;
        data = dv;
        left = leftLoc;
        right = rightLoc;
        offset = location;
        filePath = file;
    }


    // ----------------------------------------------------------
    /**
     * Create a new Node object.
     *
     * @param k
     *            is the key
     * @param dv
     *            is the value of data
     * @param location
     *            is the offset
     * @param file
     *            is the filepath
     */
    public Node(float k, int dv, long location, String file)
    {
        key = k;
        data = dv;
        offset = location;
        filePath = file;
    }


    // ----------------------------------------------------------
    /**
     * Create a new Node object.
     *
     * @param location
     *            is the offset
     */
    public Node(long location)
    {
        offset = location;
    }


    // ----------------------------------------------------------
    /**
     * This gets the key of the node
     *
     * @return is the key
     */
    public float getKey()
    {
        return key;
    }


    // ----------------------------------------------------------
    /**
     * Gets the value held by data
     *
     * @return is the data value
     */
    public int getData()
    {
        return data;
    }


    // ----------------------------------------------------------
    /**
     * Gets the right child byte offset
     *
     * @return is the right byte offset
     */
    public long getRight()
    {
        return right;
    }


    // ----------------------------------------------------------
    /**
     * Gets the left child offset
     *
     * @return is the left byte offset
     */
    public long getLeft()
    {
        return left;
    }


    // ----------------------------------------------------------
    /**
     * This changes the child in the file
     *
     * @param newChild
     *            is the new child for the node
     * @throws IOException
     *             happens when there is a problem with reading or writing from
     *             the file
     */
    public void setRight(long newChild)
        throws IOException
    {
        raf = new RandomAccessFile(filePath, "rw");
        raf.seek(offset + 16);
        raf.writeLong(newChild);
        raf.close();
    }


    // ----------------------------------------------------------
    /**
     * This changes the left child in the file
     *
     * @param newChild
     *            is the new child for the node
     * @throws IOException
     *             happens when there is a problem with reading or writing from
     *             the file
     */
    public void setLet(long newChild)
        throws IOException
    {
        raf = new RandomAccessFile(filePath, "rw");
        raf.seek(offset + 8);
        raf.writeLong(newChild);
        raf.close();
    }


    // ----------------------------------------------------------
    // ******Could combine left and right into one method?****
    /**
     * This is a method to add a right child into the file. It will attempt to
     * use the free list, and if not, then add to the end
     *
     * @param keyVal
     *            is the key to add
     * @param dataVal
     *            is the data value to add
     * @param fl
     *            is the free list head
     * @return is the byte position of the new node
     * @throws IOException
     *             if it doesn't work
     */
    public long setRight(float keyVal, int dataVal, long fl)
        throws IOException
    {
        raf = new RandomAccessFile(filePath, "rw");
        long bytePosition = -2;
        raf.seek(fl);
        long flHead = raf.readLong();
        if (flHead == 0)
        {
            long length = raf.length();
            raf.seek(length);
            useFL(length, keyVal, dataVal);
            bytePosition = length;
        }
        else
        {
            raf.seek(8);
            long firstFL = raf.readLong();
            raf.seek(firstFL);
            long newFirstFL = raf.readLong();
            raf.seek(firstFL);
            useFL(firstFL, keyVal, dataVal);
            raf.seek(8);
            raf.writeLong(newFirstFL);
            bytePosition = firstFL;
        }
        setRight(bytePosition);
        // writes the offset of the child to parent
        raf.close();
        return bytePosition;
    }


    /**
     * This is a method to add a left child into the file. It will attempt to
     * use the free list, and if not, then add to the end
     *
     * @param keyVal
     *            is the key to add
     * @param dataVal
     *            is the data value to add
     * @param fl
     *            is the free list head
     * @return is the byte position of the new node
     * @throws IOException
     *             if it doesn't work
     */
    public long setLeft(float keyVal, int dataVal, long fl)
        throws IOException
    {
        raf = new RandomAccessFile(filePath, "rw");
        long bytePosition = -2;
        raf.seek(fl);
        long flHead = raf.readLong();
        if (flHead == 0)
        {
            long length = raf.length();
            raf.seek(length);
            useFL(length, keyVal, dataVal);
            bytePosition = length;
        }
        else
        {
            raf.seek(8);
            long firstFL = raf.readLong();
            raf.seek(firstFL);
            long newFirstFL = raf.readLong();
            raf.seek(firstFL);
            useFL(firstFL, keyVal, dataVal);
            raf.seek(8);
            raf.writeLong(newFirstFL);
            bytePosition = firstFL;
        }
        setLet(bytePosition);
        // writes the offset of the child to parent
        raf.close();
        return bytePosition;
    }


    /**
     * This is a helper method to create the new node in the file
     *
     * @param offsetLoc
     *            is the offset in the file to being writing
     * @param keyVal
     *            is the key to add
     * @param value
     *            is the data value to put there
     */
    private void useFL(long offsetLoc, float keyVal, int value)
        throws IOException
    {
        raf = new RandomAccessFile(filePath, "rw");
        raf.seek(offsetLoc);
        raf.writeFloat(keyVal);
        raf.writeInt(value);
        raf.writeLong(0);
        raf.writeLong(0);
    }


    /**
     * Returns the left node as a Node
     *
     * @return is the left child from file in Node form
     * @throws IOException
     *             if it doesnt work
     */
    public Node getLeftNode()
        throws IOException
    {
        raf = new RandomAccessFile(filePath, "rw");
        if (left == 0)
        {
            raf.close();
            return new Node(filePath, 0, 0, 0, 0, 0);
        }
        raf.seek(left);
        float leftKey = raf.readFloat();
        int leftData = raf.readInt();
        long leftLocation = raf.readLong();
        long rightLocation = raf.readLong();
        return new Node(
            filePath,
            leftKey,
            leftData,
            leftLocation,
            rightLocation,
            left);
    }


    // ----------------------------------------------------------
    /**
     * Returns the left child from the file in Node form
     *
     * @return is the child
     * @throws IOException
     *             if it doesn't work
     */
    public Node getRightNode()
        throws IOException
    {
        raf = new RandomAccessFile(filePath, "rw");
        raf.seek(right);
        float leftKey = raf.readFloat();
        int leftData = raf.readInt();
        long leftLocation = raf.readLong();
        long rightLocation = raf.readLong();
        return new Node(
            filePath,
            leftKey,
            leftData,
            leftLocation,
            rightLocation,
            right);
    }


    /**
     * returns the offset of this node
     *
     * @return is the offset in the file
     */
    public long getOffset()
    {
        return offset;
    }


    // ----------------------------------------------------------
    /**
     * This method makes this node the root. Only used when root == 0 (empty
     * tree)
     *
     * @return is the byte position of the new root
     * @throws IOException
     *             is there is a problem writing or reading from fil
     */
    public long makeRoot()
        throws IOException
    {
        long bytePos = 0;
        raf = new RandomAccessFile(filePath, "rw");
        raf.seek(8);
        long node = raf.readLong();
        if (node == 0) // nothing in freelist; completely empty
        {
            raf.seek(16);
            raf.writeFloat(key);
            raf.writeInt(data);
            raf.writeLong(0);
            raf.writeLong(0);
            raf.seek(0);
            raf.writeLong(16);
            bytePos = 16;
        }
        else
        {
            raf.seek(node);
            long nextFl = raf.readLong();
            raf.seek(node);
            raf.writeFloat(key);
            raf.writeInt(data);
            raf.writeLong(0);
            raf.writeLong(0);
            raf.seek(8);
            raf.writeLong(nextFl);
            bytePos = node;
            raf.seek(0);
            raf.writeLong(bytePos);
        }
        raf.close();
        return bytePos;
    }


    // ----------------------------------------------------------
    /**
     * A method to get the filepath for other use
     *
     * @return is the filepath
     */
    public String getFP()
    {
        return filePath;
    }


    // ----------------------------------------------------------
    /**
     * This method returns the min of the tree
     *
     * @param cur
     *            is the current node to start the search from
     * @param minOff
     *            is an array to hold the offset of the found min
     * @return is the offset of the next node as it goes down
     * @throws IOException
     *             if there is a problem reading or writing from the file
     */
    public long getMin(Node cur, long[] minOff)
        throws IOException
    {
        long min = 0;
        // search for min (left most node)
        if (left != 0)
        {
            min = getMin(cur.getLeftNode(), minOff);
        }
        else if (right != 0)
        {
            minOff[0] = cur.getOffset();
            // promote right child to current spot
            min = cur.getRight();
        }
        else
        {
            minOff[0] = cur.getOffset();
        }
        return min;
    }


    // ----------------------------------------------------------
    /**
     * Creates a new freelist location based on the offset given
     *
     * @throws IOException
     *             if there is a problem
     */
    public void makeFL()
        throws IOException
    {
        FLNode gone = new FLNode(filePath, offset);
        gone.createFreeLoc(offset);
    }

}
