import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This test class tests all the methods in the city class to make sure it is
 * working correctly.
 *
 * @author Jack Cobb (jack3)
 * @version Feb 11, 2014
 */
public class CityTest
    extends TestCase
{

    private City toTest;


    // ----------------------------------------------------------
    /**
     * This sets up the test case
     */
    public void setUp()
    {
        toTest = new City(45, "Burke", "wow");
    }


    // ----------------------------------------------------------
    /**
     * This tests the getter methods and the toString method in city
     */
    public void test()
    {
        assertEquals(45, toTest.getPopulation());
        assertEquals("Burke", toTest.getCityName());
        assertEquals("wow", toTest.getPayLoad());
        assertEquals("Burke 45 wow", toTest.toString());
    }

    /**
     * tests the setters
     */
    public void testSetters()
    {
        toTest.setName("Miami");
        toTest.setPayload("So cold");
        toTest.setPop(12345);

        assertEquals(12345, toTest.getPopulation());
        assertEquals("Miami", toTest.getCityName());
        assertEquals("So cold", toTest.getPayLoad());
    }

    /**
     * test the getters when they are null
     */
    public void testGetters()
    {
        String name = toTest.getCityName();
        String payload = toTest.getPayLoad();

        assertEquals("Burke", name);
        assertEquals("wow", payload);
    }

    /**
     * this is a testing method for the info method when a value is missing
     */
    public void testInfo()
    {
        String infoString = toTest.info();

        assertEquals("Burke 45", infoString);
        toTest.setName(null);

        assertEquals("45", toTest.info());

        toTest.setName("Burke");
        toTest.setPop(0);

        assertEquals("Burke", toTest.info());
    }

}
