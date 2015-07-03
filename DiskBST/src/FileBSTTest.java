import java.io.IOException;
import java.io.File;


/**
 * \// -------------------------------------------------------------------------
 * /** This is a test class for the file bst to make sure that its working
 *
 * @author Jack Cobb
 * @version May 2, 2014
 */

public class FileBSTTest
    extends student.TestCase
{
    // fields
    private String fp = "C:\\Users\\Jack\\Desktop\\testFile.bin";


    // ----------------------------------------------------------
    /**
     * Runs before every test to setup the file
     *
     * @throws IOException
     *             is there is a problem reading or writing to file
     */
    public void setUp()
        throws IOException
    {
        setSystemIn("tree\ninsert 10 1\ninsert 5 1\ninsert 6 1\ninsert 12 1\n"
            + "insert 11 1\ntree\nfreelist\nfind 5\nfind 11\ntree\n"
            + "delete 11\ninsert 11 1\n");
        String[] args = { fp };
        FileBST.main(args);
    }


    // ----------------------------------------------------------
    /**
     * Test method that checks against the output produced by running main in
     * setup
     */
    public void testAll()
    {
        String output = "tree\n";
        String output2 = "freelist";

        assertTrue(systemOut().getHistory().contains(output));
        assertTrue(systemOut().getHistory().contains(output2));
        File gone = new File(fp);
        gone.delete();
    }


    // ----------------------------------------------------------
    /**
     * Tests a non existant file
     *
     * @throws IOException
     *             when there is a problem reading or writing to file
     */
    public void testNonExisting()
        throws IOException
    {
        String[] args = { "C:\\Users\\Jack\\Desktop\\test.bin" };
        FileBST.main(args);

        assertTrue(systemOut().getHistory().contains("new"));
    }


    // ----------------------------------------------------------
    /**
     * Test for ignoring comments and deleting from the file
     *
     * @throws IOException
     *             if there is a problem reading and writing from the file
     */
    public void testCommentAndDelete()
        throws IOException
    {
        setSystemIn("145jlb\ndelete 6\ninadus 5\nfreelist\n");
        String[] args = { fp };
        FileBST.main(args);

        boolean there = systemOut().getHistory().contains("delete 6.0");

        assertTrue(there);
    }
}
