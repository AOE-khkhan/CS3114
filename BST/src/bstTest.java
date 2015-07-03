// -------------------------------------------------------------------------
/**
 * The test class for my bst class
 *
 * @author Jack Cobb
 * @version Feb 15, 2014
 */
public class bstTest
    extends student.TestCase
{

    /**
     * this sets up the conditions before every test
     */
    public void setUp()
    {
        // insert in all my methods first
    }


    // ----------------------------------------------------------
    /**
     * This is a main test method that checks if the scanner as a next line
     */
    public void testInsert()
    {
        String output =
            "insert Balwin 30404 stuff\n" + "insert Alexandria 139966 stuff\n"
                + "insert Danville 43055 stuff\n"
                + "insert Lynchburg 75568 stuff\n"
                + "insert Portsmouth 95535 stuff\n"
                + "insert Suffolk 84585 stuff\n"
                + "insert Radford 16408 stuff\n"
                + "insert Williamsburg 14068 stuff\n"
                + "insert Fredericksburg 24286 stuff\n"
                + "insert Norfolk 242803 stuff\n"
                + "insert Baltimore 620961 stuff\n";

        setSystemIn("insert Balwin 30404 stuff\n"
            + "insert Alexandria 139966 stuff\n"
            + "insert Danville 43055  stuff\n"
            + "insert Lynchburg 75568 stuff\n"
            + "insert Portsmouth 95535 stuff\n"
            + "insert Suffolk 84585 stuff\n" + "insert Radford 16408 stuff\n"
            + "insert Williamsburg 14068 stuff\n"
            + "insert Fredericksburg 24286 stuff\n"
            + "insert Norfolk 242803 stuff\n"
            + "insert Baltimore 620961 stuff\n");
        bst.main(null);

        assertEquals(output, systemOut().getHistory());
    }


    /**
     * tests the find method, findkth, and find range methods
     */
    public void testAllFinds()
    {
        setSystemIn("insert Balwin 30404 stuff\n"
            + "insert Alexandria 139966 stuff\n"
            + "insert Danville 43055  stuff\n"
            + "insert Lynchburg 75568 stuff\n"
            + "insert Portsmouth 95535 stuff\n"
            + "insert Suffolk 84585 stuff\n" + "insert Radford 16408 stuff\n"
            + "insert Williamsburg 14068 stuff\n"
            + "insert Fredericksburg 24286 stuff\n"
            + "insert Norfolk 242803 stuff\n"
            + "insert Baltimore 620961 stuff\n" + "find name Danville\n"
            + "find name Blacksburg\n"
            + "find population 24286\nfind population 1000000\n"
            + "findKth name 9\nfindKth population 5\nfindKth name 20\n"
            + "findRange name B E\n");

        bst.main(null);

        String output =
            "find name Danville\nDanville 43055 stuff\n"
                + "find name Blacksburg\nNot found\nfind population 24286\n"
                + "Fredericksburg 24286 stuff\nfind population 1000000\n"
                + "Not found\nfindKth name 9\nSuffolk 84585 stuff\n"
                + "findKth population 5\nLynchburg 75568 stuff\n"
                + "findKth name 20\n"
                + "Not found\nfindRange name B E\nBaltimore 620961 stuff\n"
                + "Balwin 30404 stuff\nDanville 43055 stuff\n";

        assertTrue(systemOut().getHistory().contains(output));
    }


    /**
     * tests the delete method
     */
    public void testDelete()
    {
        setSystemIn("insert Balwin 30404 stuff\n"
            + "insert Alexandria 139966 stuff\n"
            + "insert Danville 43055  stuff\n"
            + "insert Lynchburg 75568 stuff\n"
            + "insert Portsmouth 95535 stuff\n"
            + "insert Suffolk 84585 stuff\n" + "insert Radford 16408 stuff\n"
            + "insert Williamsburg 14068 stuff\n"
            + "insert Fredericksburg 24286 stuff\n"
            + "insert Norfolk 242803 stuff\n"
            + "insert Baltimore 620961 stuff\n" + "find name Danville\n"
            + "delete  name Alexandria\n" + "delete population 9553500\n");

        bst.main(null);

        String output =
            "delete name Alexandria\ndelete population 9553500\n"
                + "Not found\n";

        assertTrue(systemOut().getHistory().contains(output));
    }

    /**
     * tests the delete method when multiple cities for population and
     * when deleting by name, there aren't any present
     */
    public void testMoreDelete()
    {
        setSystemIn("insert Balwin 30404 stuff\n"
            + "insert Alexandria 139966 stuff\n"
            + "insert Danville 43055  stuff\n"
            + "insert Lynchburg 75568 stuff\n"
            + "insert Portsmouth 95535 stuff\n"
            + "insert Suffolk 84585 stuff\n" + "insert Radford 16408 stuff\n"
            + "insert Williamsburg 14068 stuff\n"
            + "insert Fredericksburg 24286 stuff\n"
            + "insert Norfolk 242803 stuff\n"
            + "insert Baltimore 620961 stuff\n" + "delete population 16408\n"
            + "delete name Burke\n");

        bst.main(null);

        String output =
            "delete population 16408\ndelete name Burke\n"
                + "Not found\n";

        assertTrue(systemOut().getHistory().contains(output));

    }

    /**
     * tests the sort method
     */
    public void testSort()
    {
        setSystemIn("insert Balwin 30404 stuff\n"
            + "insert Alexandria 139966 stuff\n"
            + "insert Danville 43055  stuff\n"
            + "insert Lynchburg 75568 stuff\n"
            + "insert Portsmouth 95535 stuff\n"
            + "insert Suffolk 84585 stuff\n" + "insert Radford 16408 stuff\n"
            + "insert Williamsburg 14068 stuff\n"
            + "insert Fredericksburg 24286 stuff\n"
            + "insert Norfolk 242803 stuff\n"
            + "insert Baltimore 620961 stuff\n" + "sort population\n"
            + "sort name\n");

        String nameSort =
            "Alexandria 139966 stuff\nBaltimore 620961 stuff\n"
                + "Balwin 30404 stuff\n"
                + "Danville 43055 stuff\nFredericksburg 24286 stuff\n"
                + "Lynchburg 75568 stuff\nNorfolk 242803 stuff\n"
                + "Portsmouth 95535 stuff\nRadford 16408 stuff\n"
                + "Suffolk 84585 stuff\nWilliamsburg 14068 stuff\n";
        String popSort =
            "Williamsburg 14068 stuff\n"
                + "Radford 16408 stuff\nFredericksburg 24286 stuff\n"
                + "Balwin 30404 stuff\nDanville 43055 stuff\n"
                + "Lynchburg 75568 stuff\nSuffolk 84585 stuff\n"
                + "Portsmouth 95535 stuff\nAlexandria 139966 stuff\n"
                + "Norfolk 242803 stuff\nBaltimore 620961 stuff\n";
        bst.main(null);

        assertTrue(systemOut().getHistory().contains(nameSort));

        assertTrue(systemOut().getHistory().contains(popSort));
    }


    /**
     * tests the tree method
     */
    public void testTree()
    {
        String treeName =
            "    Alexandria 139966 stuff\n"
                + "        Baltimore 620961 stuff\n" + "Balwin 30404 stuff\n"
                + "    Danville 43055 stuff\n"
                + "            Fredericksburg 24286 stuff\n"
                + "        Lynchburg 75568 stuff\n"
                + "                Norfolk 242803 stuff\n"
                + "            Portsmouth 95535 stuff\n"
                + "                    Radford 16408 stuff\n"
                + "                Suffolk 84585 stuff\n"
                + "                    Williamsburg 14068 stuff\n";
        String treePop =
            "        Williamsburg 14068 stuff\n" + "    Radford 16408 stuff\n"
                + "        Fredericksburg 24286 stuff\n"
                + "Balwin 30404 stuff\n" + "        Danville 43055 stuff\n"
                + "            Lynchburg 75568 stuff\n"
                + "                    Suffolk 84585 stuff\n"
                + "                Portsmouth 95535 stuff\n"
                + "    Alexandria 139966 stuff\n"
                + "        Norfolk 242803 stuff\n"
                + "            Baltimore 620961 stuff\n";

        setSystemIn("insert Balwin 30404 stuff\n"
            + "insert Alexandria 139966 stuff\n"
            + "insert Danville 43055  stuff\n"
            + "insert Lynchburg 75568 stuff\n"
            + "insert Portsmouth 95535 stuff\n"
            + "insert Suffolk 84585 stuff\n" + "insert Radford 16408 stuff\n"
            + "insert Williamsburg 14068 stuff\n"
            + "insert Fredericksburg 24286 stuff\n"
            + "insert Norfolk 242803 stuff\n"
            + "insert Baltimore 620961 stuff\n" + "tree\n" + "name\n"
            + "tree population\n");

        bst.main(null);

        assertTrue(systemOut().getHistory().contains(treeName));

        assertTrue(systemOut().getHistory().contains(treePop));
    }


    /**
     * test make null with a preceding comment
     */
    public void testCommentNull()
    {
        setSystemIn("insert Balwin 30404 stuff\n"
            + "insert Alexandria 139966 stuff\n"
            + "insert Danville 43055  stuff\n"
            + "insert Lynchburg 75568 stuff\n"
            + "insert Portsmouth 95535 stuff\n"
            + "insert Suffolk 84585 stuff\n" + "insert Radford 16408 stuff\n"
            + "insert Williamsburg 14068 stuff\n"
            + "insert Fredericksburg 24286 stuff\n"
            + "insert Norfolk 242803 stuff\n"
            + "insert Baltimore 620961 stuff\n" + "tree\n" + "name\n"
            + "tree population\n" + "# ignopre this\nmakenull\n");

        bst.main(null);

        assertTrue(systemOut().getHistory().contains("makenull\n"));
    }

    /**
     * test method to further test findrange
     */
    public void testFindRange()
    {
        setSystemIn("insert Balwin 30404 stuff\n"
            + "insert Alexandria 139966 stuff\n"
            + "insert Danville 43055  stuff\n"
            + "insert Lynchburg 75568 stuff\n"
            + "insert Portsmouth 95535 stuff\n"
            + "insert Suffolk 84585 stuff\n" + "insert Radford 16408 stuff\n"
            + "insert Williamsburg 14068 stuff\n"
            + "insert Fredericksburg 24286 stuff\n"
            + "insert Norfolk 242803 stuff\n"
            + "insert Baltimore 620961 stuff\n"
            + "findRange population 10000 20000\n"
            + "findRange population 100 200\n");

        bst.main(null);

        String output =
            "insert Balwin 30404 stuff\n"
                + "insert Alexandria 139966 stuff\n"
                + "insert Danville 43055 stuff\n"
                + "insert Lynchburg 75568 stuff\n"
                + "insert Portsmouth 95535 stuff\n"
                + "insert Suffolk 84585 stuff\n"
                + "insert Radford 16408 stuff\n"
                + "insert Williamsburg 14068 stuff\n"
                + "insert Fredericksburg 24286 stuff\n"
                + "insert Norfolk 242803 stuff\n"
                + "insert Baltimore 620961 stuff\n"
                + "findRange population 10000 20000\nWilliamsburg 14068 stuff\n"
                + "Radford 16408 stuff\nfindRange population 100 200\n" +
                "Not found\n";
        assertEquals(output, systemOut().getHistory());
    }
}
