import java.io.IOException;

/**
 * // -------------------------------------------------------------------------
 * /** This class is used to test the flnode class
 *
 * @author Jack
 * @version May 3, 2014
 */
public class FLNodeTest
    extends student.TestCase
{

    // Fields
    private FLNode tester;


    /**
     * this method sets up the testing environment
     */
    public void setUp()
    {
        tester = new FLNode("C:\\Users\\Jack\\Desktop\\test.bin", 8);
    }


    // ----------------------------------------------------------
    /**
     * Tests the get freeloc of the flnode
     *
     * @throws IOException
     *             gets thrown if there is a problem reading or writing to file
     */
    public void test()
        throws IOException
    {
        assertEquals(8, tester.getFreeLoc());
    }

}
