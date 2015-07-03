import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * // -------------------------------------------------------------------------
 * /** This is a test class used to help test the node class
 *
 * @author Jack
 * @version May 3, 2014
 */

public class NodeTest
    extends student.TestCase
{

    // Fields
    private Node tester;


    /**
     * this method runs before each test to setup the testing environment
     */
    public void setUp()
        throws FileNotFoundException
    {
        tester =
            new Node("C:\\Users\\Jack\\Desktop\\test.bin", 10, 1, 40, 64, 16);
    }


    // ----------------------------------------------------------
    /**
     * Tests a few of the node class methods
     *
     * @throws IOException
     *             gets thrown if there is an issue reading or writing to the
     *             file
     */
    public void test()
        throws IOException
    {
        boolean key = tester.getKey() == 10.0;
        boolean data = tester.getData() == 1;

        assertTrue(key);
        assertTrue(data);

    }

}
