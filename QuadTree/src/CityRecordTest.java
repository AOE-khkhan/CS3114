
// -------------------------------------------------------------------------
/**
 *  This is a test class for city record to ensure it works correctly
 *
 *  @author Jack
 *  @version Mar 3, 2014
 */
public class CityRecordTest
    extends student.TestCase
{
    private CityRecord tester;

    /**
     * this is run before every test
     */
    public void setUp()
    {
        tester = new CityRecord("Blacksburg", 1000, 45, 178);
    }

    /**
     * Testing the get x and get y works for city records
     */
    public void testGetters()
    {
        int testerX = tester.getX();
        int testerY = tester.getY();

        assertEquals(45, testerX);
        assertEquals(178, testerY);
    }

    /**
     * Tester of the to string method for city records
     */
    public void testToString()
    {
        String testerString = tester.toString();
        assertEquals("Blacksburg 1000 (45, 178)", testerString);
    }

}
