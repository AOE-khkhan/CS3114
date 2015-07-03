import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This tests the name compares between the city names
 *
 * @author Jack Cobb (jack3)
 * @version Feb 12, 2014
 */
public class NameCompTest
    extends TestCase
{

    // Fields
    private NameComp tester;


    /**
     * this sets up necessary conditions before each test
     */
    public void setUp()
    {
        tester = new NameComp();
    }


    // ----------------------------------------------------------
    /**
     * This test method ensures that the compare between city names functions
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

        assertEquals(-17, diff);
        assertEquals(17, reverseDiff);
        assertEquals(0, equal);
    }

}
