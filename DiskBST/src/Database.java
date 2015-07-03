import java.util.ArrayList;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;

// -------------------------------------------------------------------------
/**
 * This is the database that handles input from main then tells the tree what to
 * do
 *
 * @author Jack Cobb
 * @version May 1, 2014
 */
public class Database
{
    // Fields
    private Tree   tree;
    private String fp;


    // ----------------------------------------------------------
    /**
     * Create a new Database object.
     *
     * @param filePath
     *            is the filepath to pass to the tree
     * @throws FileNotFoundException
     *             if the file does not work
     */
    public Database(String filePath)
        throws FileNotFoundException
    {
        fp = filePath;
        tree = new Tree(filePath);
    }


    // tree insert does not handle when the three is empty. If it is, insert the
    // root here, else, run tree.insert

    // ----------------------------------------------------------
    /**
     * This tells the tree to run insert. It tries to insert the key and data
     * value to the file. It creates a node from root and tries to run
     *
     * @param key
     *            is the key to insert
     * @param data
     *            is the data value to insert
     * @throws IOException
     *             gets thrown when there is a problem writing or reading from
     *             the file
     */
    public void runInsert(float key, int data)
        throws IOException
    {
        String position = "";
        // if root == 0, insert is the
        RandomAccessFile raf = new RandomAccessFile(fp, "rw");
        long rootOffset = raf.readLong();
        long insertOffSet;
        if (rootOffset == 0)
        {
            Node root = new Node(key, data, rootOffset, fp);
            insertOffSet = root.makeRoot();
        }
        else
        {
            raf.seek(rootOffset);
            Node root =
                new Node(
                    fp,
                    raf.readFloat(),
                    raf.readInt(),
                    raf.readLong(),
                    raf.readLong(),
                    rootOffset);
            insertOffSet = tree.insert(root, key, data);
        }
        // format the string
        position = "insert " + key + " " + data + " ";
        if (insertOffSet == -1)
        {
            position = position + "duplicate";
        }
        else
        {
            position = position + "at " + insertOffSet;
        }
        raf.close();
        System.out.println(position);
    }


    // ----------------------------------------------------------
    /**
     * This method creates a node from the root of the file and runs through the
     * file searching for the key
     *
     * @param key
     *            is the key to search for
     * @throws IOException
     *             gets thrown when there is a problem reading or writing from
     *             the file
     */
    public void runFind(float key)
        throws IOException
    {
        RandomAccessFile raf = new RandomAccessFile(fp, "rw");
        raf.seek(0);
        long rootNode = raf.readLong();
        raf.seek(rootNode);
        float keyVa = raf.readFloat();
        int data = raf.readInt();
        long leftOff = raf.readLong();
        long rightOff = raf.readLong();
        Node current = new Node(fp, keyVa, data, leftOff, rightOff, rootNode);
        long location = tree.find(current, key);
        String output = "find ";
        if (location == -1)
        {
            // not found
            output = output + key + " not found";
        }
        else
        {
            raf.seek(location);
            float foundKey = raf.readFloat();
            int foundData = raf.readInt();
            output = output + foundKey + " " + foundData + " at " + location;
        }
        raf.close();
        System.out.println(output);
    }


    // ----------------------------------------------------------
    /**
     * This runs through the file and models the tree of the bst
     *
     * @throws IOException
     *             when there is a problem with reading or writing to the file
     */
    public void runTree()
        throws IOException
    {
        ArrayList<String> treeOut = new ArrayList<String>();
        RandomAccessFile raf = new RandomAccessFile(fp, "rw");
        raf.seek(0);
        long rootNode = raf.readLong();
        if (rootNode == 0)
        {
            System.out.println("tree empty");
            raf.close();
            return;
        }
        raf.seek(rootNode);
        float keyVa = raf.readFloat();
        int data = raf.readInt();
        long leftOff = raf.readLong();
        long rightOff = raf.readLong();
        raf.close();
        Node current = new Node(fp, keyVa, data, leftOff, rightOff, rootNode);
        tree.tree(current, 0, treeOut);
        // need to account for empty tree and format the correct output based on
// command
        System.out.println("tree");
        for (int i = 0; i < treeOut.size(); i++)
        {
            System.out.println(treeOut.get(i));
        }
    }


    // ----------------------------------------------------------
    /**
     * This creates a flnode from where the fl says it starts at the beginning
     * of the file. Then prints out the freelist structure
     *
     * @throws IOException
     *             when there is a problem reading or writing to the file
     */
    public void runFreeList()
        throws IOException
    {
        ArrayList<String> freeOut = new ArrayList<String>();
        RandomAccessFile raf = new RandomAccessFile(fp, "rw");
        raf.seek(8);
        long headFl = raf.readLong();
        raf.close();
        FLNode current = new FLNode(fp, headFl);
        tree.makeFreeList(current, freeOut);
        if (freeOut.size() == 0)
        {
            System.out.println("freelist empty");
            return;
        }
        System.out.println("freelist");
        for (int i = 0; i < freeOut.size(); i++)
        {
            System.out.println(freeOut.get(i));
        }
    }


    // delete starts at the root after reading in the location
    // ----------------------------------------------------------
    /**
     * This runs the tree delete method after creating a root node found from
     * beginning of the file then prints the correct output
     *
     * @param key
     *            is the key to try and delete
     * @throws IOException
     *             happens when there is a problem reading or writing to the
     *             file
     */
    public void runDelete(float key)
        throws IOException
    {
        Node[] deleted = new Node[1];
        RandomAccessFile raf = new RandomAccessFile(fp, "rw");
        raf.seek(0);
        long rootNode = raf.readLong();
        raf.seek(rootNode);
        float keyVa = raf.readFloat();
        int data = raf.readInt();
        long leftOff = raf.readLong();
        long rightOff = raf.readLong();
        raf.close();
        Node current = new Node(fp, keyVa, data, leftOff, rightOff, rootNode);
        long newRoot = tree.delete(current, key, deleted);
        changeRoot(newRoot);
        if (deleted[0].getOffset() == -1)
        {
            System.out.println("delete " + key + " not found");
        }
        else
        {
            System.out.println("delete " + key + " " + deleted[0].getData()
                + " at " + deleted[0].getOffset());
        }
    }


    // ----------------------------------------------------------
    /**
     * Changes the root listed in the tree
     *
     * @param newRoot
     *            is the new offset that becomes the root
     * @throws IOException
     *             happens if there is a problem reading or writing to file
     */
    public void changeRoot(long newRoot)
        throws IOException
    {
        RandomAccessFile raf = new RandomAccessFile(fp, "rw");
        raf.seek(0);
        raf.writeLong(newRoot);
        raf.close();
    }
}
