
// -------------------------------------------------------------------------
/**
 *  This tests that the limited functionality of bst works, even though bst is
 *  just a placeholder for a functioning bst, because my implementation from the
 *  last project does not work correctly
 *
 *  @author Jack Cobb
 *  @version Mar 6, 2014
 */
public class BstTest extends student.TestCase
{

    // fields for testing
    private Bst tester;

    /**
     * this runs before every test to setup the Bst
     */
    public void setUp()
    {
        tester = new Bst();
    }

    // ----------------------------------------------------------
    /**
     * This tests the functionality of the placeholder insert
     */
    public void testInsert()
    {
        assertFalse(tester.getRoot());

        tester.insert();

        assertTrue(tester.getRoot());
    }

}
