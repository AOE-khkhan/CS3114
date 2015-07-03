import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This class tests the popCompareClass
 *
 * @author Jack Cobb (jack3)
 * @version Feb 12, 2014
 */
public class PopCompTest
    extends TestCase
{

    // Fields
    private PopComp tester;


    /**
     * THis method sets up conditions before every test
     */
    public void setUp()
    {
        tester = new PopComp();
    }


    // ----------------------------------------------------------
    /**
     * This tests the compare method to ensure that the ensure method is working
     * correctly
     */
    public void test()
    {
        CityRecord city1 = new CityRecord("Burke", 45, 0, 0);
        CityRecord city2 = new CityRecord("Springfield", 100, 1, 1);
        CityRecord city3 = new CityRecord("Burke", 45, 2, 2);

        int diff = tester.compare(city1, city2);
        int reverseDiff = tester.compare(city2, city1);
        int equal = tester.compare(city1, city3);

        assertEquals(-55, diff);
        assertEquals(55, reverseDiff);
        assertEquals(0, equal);
    }

}
