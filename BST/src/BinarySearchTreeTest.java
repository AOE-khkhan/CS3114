import java.util.ArrayList;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This class is used for testing the binary search tree class to ensure that
 * the functions work correctly
 *
 * @author Jack Cobb(jack3)
 * @version Feb 11, 2014
 */
public class BinarySearchTreeTest
    extends TestCase
{
    // Fields'
    private BinarySearchTree<City> tester;


    /**
     * sets up conditions before each test
     */
    public void setUp()
    {
        tester = new BinarySearchTree<City>(new PopComp());
        tester.insert(new City(56, "Burke", "a"));
        tester.insert(new City(24, "Fairfax", "b"));
        tester.insert(new City(78, "Raleigh", "c"));
    }


    // ----------------------------------------------------------
    /**
     * This tests the make null method ot ensure it runs correctly
     */
    public void testMakeNull()
    {
        tester.makeNull();

        assertEquals(0, tester.size(tester.root()));
    }


    /**
     * This method tests that the insert function works properly when inserting
     * a city that has a value already present in the tree
     */
    public void testInsertEqual()
    {
        tester.insert(new City(56, "Tampa", "d"));

        tester.insert(new City(56, "Tamp", "d"));

        tester.insert(new City(56, "Idaho", "waddup"));

        assertEquals(tester.size(tester.root()), 6);
    }


    /**
     * this test method tests the find method to ensure it works correctly
     */
    public void testFind()
    {
        City a = new City(10, "Idaho", "waddup");
        City b = new City(13, "Reno", "waddup");
        City c = new City(63, "Idaho", "waddup");
        City d = new City(66, "NYC", "help");
        tester.insert(a);
        tester.insert(b);
        tester.insert(c);
        tester.insert(d);

        assertEquals(7, tester.size(tester.root()));
    }


    /**
     * tests the find method for finding multiple elements
     */
    public void testFindMultiple()
    {
        City a = new City(10, "Idaho", "waddup");
        City b = new City(13, "Reno", "waddup");
        City c = new City(63, "Idaho", "waddup");
        City d = new City(56, "NYC", "help");
        tester.insert(a);
        tester.insert(b);
        tester.insert(c);
        tester.insert(d);

        ArrayList<BSTNode<City>> found = new ArrayList<BSTNode<City>>();

        found =
            tester.findCities(tester.root(), new BSTNode<City>(new City(
                56,
                "Las Vegas",
                "")), found);

        assertEquals(2, found.size());
    }


    /**
     * tests for the delete function and ensure that it works correctly
     */
    public void testDeleteRoot()
    {
        City a = new City(10, "Idaho", "waddup");
        City b = new City(13, "Reno", "waddup");
        City c = new City(63, "Idaho", "waddup");
        City d = new City(66, "NYC", "help");
        tester.insert(a);
        tester.insert(b);
        tester.insert(c);
        tester.insert(d);

        City dd = new City(57, "NYC", "help");
        tester.insert(dd);

        tester.delete(tester.root(), new BSTNode<City>(new City(
            56,
            "Burke",
            "payload")));

        assertEquals(dd, tester.root().getElement());

        tester.delete(tester.root(), new BSTNode<City>(new City(
            57,
            "NYC",
            "payload")));

        assertEquals(c, tester.root().getElement());
    }


    /**
     * this tests removing an entry not present
     */
    public void testNotPresentDelete()
    {
        tester.delete(tester.root(), new BSTNode<City>(new City(
            64,
            "Richmond",
            "payload")));

        assertEquals(3, tester.size(tester.root()));
    }


    /**
     * testing the delete method when there are multiple nodes with the same
     * value
     */
    public void testDeleteMultiple()
    {
        tester.insert(new City(56, "Tampa", "d"));
        tester.insert(new City(56, "Tampa", "d"));
        tester.insert(new City(56, "Tampa", "d"));
        ArrayList<BSTNode<City>> found = new ArrayList<BSTNode<City>>();

        found =
            tester.findCities(tester.root(), new BSTNode<City>(new City(
                56,
                "Tampa",
                "payload")), found);
        for (int i = 0; i < found.size(); i++)
        {
            tester.delete(tester.root(), found.get(i));
        }

        assertEquals(2, tester.size(tester.root()));
        assertEquals(new City(78, "Raleigh", "c").getPopulation(), tester
            .root().getElement().getPopulation());
    }


    /**
     * tests the sort method
     */
    public void testSort()
    {
        tester.sort(tester.root());
        String outputSort = systemOut().getHistory();
        String sort = "Fairfax 24 b\nBurke 56 a\nRaleigh 78 c\n";
        assertEquals(sort, outputSort);

        tester.makeNull();
        systemOut().clearHistory();
        tester.sort(tester.root());
        assertEquals("Database empty\n", systemOut().getHistory());
    }


    /**
     * this method tests the find kth
     */
    public void testFindK()
    {
        City a = new City(10, "Idaho", "waddup");
        City b = new City(13, "Reno", "waddup");
        City c = new City(63, "Idaho", "waddup");
        City d = new City(66, "NYC", "help");
        tester.insert(a);
        tester.insert(b);
        tester.insert(c);
        tester.insert(d);

        tester.findKth(0);
        String output0 = systemOut().getHistory();
        assertEquals("Idaho 10 waddup\n", output0);
        systemOut().clearHistory();

        tester.findKth(1);
        String output1 = systemOut().getHistory();
        assertEquals("Reno 13 waddup\n", output1);
        systemOut().clearHistory();

        tester.findKth(2);
        String output2 = systemOut().getHistory();
        assertEquals("Fairfax 24 b\n", output2);
        systemOut().clearHistory();

        tester.findKth(3);
        String output3 = systemOut().getHistory();
        assertEquals("Burke 56 a\n", output3);
        systemOut().clearHistory();

        tester.findKth(4);
        String output4 = systemOut().getHistory();
        assertEquals("Idaho 63 waddup\n", output4);
        systemOut().clearHistory();

        tester.findKth(5);
        String output5 = systemOut().getHistory();
        assertEquals("NYC 66 help\n", output5);
        systemOut().clearHistory();

        tester.findKth(6);
        String output6 = systemOut().getHistory();
        assertEquals("Raleigh 78 c\n", output6);
        systemOut().clearHistory();

        tester.findKth(7);
        String output7 = systemOut().getHistory();
        assertEquals("Not found\n", output7);
    }


    /**
     * test method for tree to ensure it works correctly
     */
    public void testTree()
    {
        City a = new City(10, "Idaho", "waddup");
        City b = new City(13, "Reno", "waddup");
        City c = new City(63, "Idaho", "waddup");
        City d = new City(66, "NYC", "help");
        tester.insert(a);
        tester.insert(b);
        tester.insert(c);
        tester.insert(d);

        tester.tree(tester.root(), 0);
        String outputTree = systemOut().getHistory();
        String tree =
            "        Idaho 10 waddup\n            Reno 13 waddup\n"
                + "    Fairfax 24 b\nBurke 56 a\n        Idaho 63 waddup\n"
                + "            NYC 66 help\n    Raleigh 78 c\n";
        assertEquals(tree, outputTree);

        tester.makeNull();
        systemOut().clearHistory();
        tester.tree(tester.root(), 0);
        assertEquals("Database empty\n", systemOut().getHistory());
    }


    /**
     * test the correct return of children
     */
    public void testPromote()
    {
        tester.makeNull();

        City a = new City(30, "Idaho", "waddup");
        City aa = new City(33, "", "");
        City b = new City(50, "Reno", "waddup");
        City c = new City(35, "Idaho", "waddup");
        City d = new City(66, "NYC", "help");
        tester.insert(a);
        tester.insert(aa);
        tester.insert(b);
        tester.insert(c);
        tester.insert(d);

        tester.delete(tester.root(), new BSTNode<City>(new City(
            30,
            "Idaho",
            "please")));
        tester.tree(tester.root(), 0);

        assertEquals(aa, tester.root().getElement());
    }


    /**
     * test the new find method that returns an array
     */
    public void testNewFind()
    {
        City a = new City(39, "Idaho", "waddup");
        City aa = new City(33, "", "");
        City b = new City(50, "Reno", "waddup");
        City c = new City(33, "Idaho", "waddup");
        City d = new City(33, "NYC", "help");
        tester.insert(a);
        tester.insert(aa);
        tester.insert(b);
        tester.insert(c);
        tester.insert(d);

        ArrayList<BSTNode<City>> list1 = new ArrayList<BSTNode<City>>();
        ArrayList<BSTNode<City>> list2 = new ArrayList<BSTNode<City>>();
        ArrayList<BSTNode<City>> list3 = new ArrayList<BSTNode<City>>();

        assertEquals(
            3,
            tester.findCities(tester.root(), new BSTNode<City>(aa), list1)
                .size());
        assertEquals(
            1,
            tester.findCities(tester.root(), new BSTNode<City>(b), list2)
                .size());
        assertEquals(
            1,
            tester.findCities(tester.root(), new BSTNode<City>(a), list3)
                .size());

        tester.makeNull();

        ArrayList<BSTNode<City>> list4 = new ArrayList<BSTNode<City>>();
        assertEquals(
            0,
            tester.findCities(tester.root(), new BSTNode<City>(aa), list4)
                .size());
    }


    /**
     * test delete again
     */
    public void testNewRemove()
    {
        City a = new City(10, "Idaho", "waddup");
        City b = new City(30, "Reno", "waddup");
        City c = new City(63, "Idaho", "waddup");
        City d = new City(60, "NYC", "help");
        tester.insert(a);
        tester.insert(b);
        tester.insert(c);
        tester.insert(d);

        City dd = new City(80, "NYC", "help");
        tester.insert(dd);
        City aa = new City(5, "NYC", "help");
        tester.insert(aa);

        tester.delete(tester.root(), new BSTNode<City>(new City(
            56,
            "Burke",
            "a")));

        assertEquals(d, tester.root().getElement());

        tester.delete(tester.root(), new BSTNode<City>(new City(
            60,
            "NYC",
            "help")));

        assertEquals(c, tester.root().getElement());

        tester.delete(tester.root(), new BSTNode<City>(new City(
            24,
            "Fairfax",
            "b")));

        assertEquals(c, tester.root().getElement());

    }


    /**
     * test method of get min when its null
     */
    public void testMin()
    {
        assertNull(tester.getMin(null));
    }


    /**
     * this tests the delete method when deleting the root when it has left
     * children, but no right children
     */
    public void testDeleteRootLeftChild()
    {
        tester.makeNull();
        City a = new City(10, "Idaho", "waddup");
        City b = new City(30, "Reno", "waddup");
        City c = new City(63, "Idaho", "waddup");
        City d = new City(60, "NYC", "help");
        tester.insert(c);
        tester.insert(d);
        tester.insert(a);
        tester.insert(b);

        tester.delete(tester.root(), new BSTNode<City>(new City(
            63,
            "Idaho",
            "a")));

        assertEquals(d, tester.root().getElement());
    }


    /**
     * this tests the delete method when deleting the root when it has right
     * children, but no left children
     */
    public void testDeleteRootRightChild()
    {
        tester.makeNull();
        City a = new City(10, "Idaho", "waddup");
        City b = new City(30, "Reno", "waddup");
        City c = new City(63, "Idaho", "waddup");
        City d = new City(60, "NYC", "help");
        tester.insert(a);
        tester.insert(b);
        tester.insert(d);
        tester.insert(c);

        tester.delete(tester.root(), new BSTNode<City>(new City(
            10,
            "Idaho",
            "a")));

        assertEquals(b, tester.root().getElement());
    }


    /**
     * this method tests the find range
     */
    public void testFindRange()
    {
        City a = new City(10, "Idaho", "waddup");
        City b = new City(30, "Reno", "waddup");
        City c = new City(63, "Idaho", "waddup");
        City d = new City(60, "NYC", "help");
        tester.insert(c);
        tester.insert(d);
        tester.insert(a);
        tester.insert(b);

        City start1 = new City(20, "Idaho", "waddup");
        City stop1 = new City(65, "Reno", "waddup");

        ArrayList<BSTNode<City>> range = new ArrayList<BSTNode<City>>();
        range = tester.findRange(tester.root(), start1, stop1, range);

        assertEquals(5, range.size());

        City start2 = new City(70, "Idaho", "waddup");
        City stop2 = new City(90, "Reno", "waddup");

        range.clear();
        range = tester.findRange(tester.root(), start2, stop2, range);

        assertEquals(1, range.size());
    }
}
