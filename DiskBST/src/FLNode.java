import java.util.ArrayList;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * This class is a node for the FreeList that is read in. It only has a
 * reference to its location in the file and the filepath, so that it can get
 * the next freelist location. It also can create new freelists by zeroing out
 * the necessary data, and adding it to the lifo free list
 *
 * @author Jack Cobb
 * @version May 1, 2014
 */
public class FLNode
{
    // Fields
    private long   freeLoc;
    private String fp;


    // ----------------------------------------------------------
    /**
     * Create a new FLNode object.
     *
     * @param filepath
     *            is the file location
     * @param location
     *            is the offset in the file
     */
    public FLNode(String filepath, long location)
    {
        freeLoc = location;
        fp = filepath;
    }


    // ----------------------------------------------------------
    /**
     * Holds the free location offset. Only used if reading in a free node
     *
     * @return is the freelocation offset.
     */
    public long getFreeLoc()
    {
        return freeLoc;
    }


    // ----------------------------------------------------------
    /**
     * This empties the old node deletion and adds to the top of the stack of
     * the free list
     *
     * @param where
     *            is the location to make a new freelocation
     * @throws IOException
     *             is if there is a problem with reading in or writing to the
     *             file
     */
    public void createFreeLoc(long where)
        throws IOException
    {
        RandomAccessFile raf = new RandomAccessFile(fp, "rw");
        raf.seek(8);
        long head = raf.readLong();
        raf.seek(where);
        raf.writeLong(head);
        raf.writeLong(0);
        raf.writeLong(0);
        raf.seek(8);
        raf.writeLong(where);
        raf.close();
    }


    // ----------------------------------------------------------
    /**
     * This is the method to make the freelist from the freelist command and
     * correctly format it so that the file's freelist can be printed out
     *
     * @param depth
     *            is how far down the fl we have gone
     * @param freeList
     *            is the arraylist of strings to add the formatted fl location
     *            to
     * @throws IOException
     *             gets thrown when there is a problem with writing or reading
     *             form the file
     */
    public void freeList(int depth, ArrayList<String> freeList)
        throws IOException
    {
        if (freeLoc == 0)
        {
            return;
        }
        String format = (depth) + " at " + freeLoc;
        freeList.add(format);
        RandomAccessFile raf = new RandomAccessFile(fp, "rw");
        raf.seek(freeLoc);
        long nextFL = raf.readLong();
        FLNode next = new FLNode(fp, nextFL);
        raf.close();
        next.freeList(depth + 1, freeList);
    }
}
