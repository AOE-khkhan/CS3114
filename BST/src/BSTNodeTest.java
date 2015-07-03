import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This is a test class for the BSTNode to ensure all methods work correctly
 *
 * @author Jack Cobb (jack3)
 * @version Feb 11, 2014
 */
public class BSTNodeTest
    extends TestCase
{
    // Fields
    private BSTNode<City> tester;
    private City          one;


    /**
     * Runs before each test
     */
    public void setUp()
    {
        one = new City(50, "Tampa", "hey");
        tester = new BSTNode<City>(one);
    }


    /**
     * Tests the node's getter methods
     */
    public void testGetters()
    {
        assertEquals(0, tester.getLeftCount());
    }


    /**
     * test the increment of the left counter
     */
    public void testIncrementLeft()
    {
        tester.incrementLeft();

        assertEquals(1, tester.getLeftCount());

        tester.decreaseLeft();

        assertEquals(0, tester.getLeftCount());
    }


    /**
     * test to check the element getter and setter
     */
    public void testElement()
    {
        City replace = new City(40, "Phoenix", "blue");

        assertEquals(one, tester.getElement());

        tester.setElement(replace);

        assertEquals(replace, tester.getElement());
    }


    /**
     * test the left and right node setters and getters
     */
    public void testChildren()
    {
        assertNull(tester.getLeft());
        assertNull(tester.getRight());

        BSTNode<City> right = new BSTNode<City>(new City(11, "Toledo", "abc"));
        BSTNode<City> left = new BSTNode<City>(new City(12, "New York", "abc"));

        tester.setLeft(left);
        tester.setRight(right);

        assertEquals(right, tester.getRight());
        assertEquals(left, tester.getLeft());
    }
}
