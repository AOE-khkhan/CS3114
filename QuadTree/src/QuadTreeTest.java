// -------------------------------------------------------------------------
/**
 * This is a test class for quad tree that checks the output versus the input
 *
 * @author Jack Cobb <jack3>
 * @version Mar 1, 2014
 */
public class QuadTreeTest
    extends student.TestCase
{
    // ----------------------------------------------------------
    /**
     * This is a test method that tests the insert method and that it works
     * correctly
     */
    public void testInsert()
    {
        setSystemIn("insert home 10 0 0\n" + "insert away 5 64 64\n");
        QuadTree.main(null);

        assertTrue(systemOut().getHistory().contains(
            "insert home 10 0 0\n" + "insert away 5 64 64\n"));
    }


    // ----------------------------------------------------------
    /**
     * This is a test to ensure find works
     */
    public void testFind()
    {
        setSystemIn("insert home 10 0 0\n" + "insert away 5 64 64\n"
            + "find location 64 64\n" + "find location 34 34\n"
            + "find location 65 35\nfind location 37 65\n");
        QuadTree.main(null);

        String str = "away 5 (64, 64)";
        String notfound =
            "find location 34 34\nNot found\n"
                + "find location 65 35\nNot found\nfind location 37 65\n"
                + "Not found\n";

        assertTrue(systemOut().getHistory().contains(str));
        assertTrue(systemOut().getHistory().contains(notfound));
    }


    /**
     * test that tree works correctly
     */
    public void testTree()
    {
        setSystemIn("insert home 5 0 0\n" + "insert away 10 64 64\n"
            + "tree location\n");
        QuadTree.main(null);

        String str =
            "tree location\nInternal (512, 512)\n"
                + "....Internal (256, 256)\n" + "........Internal (128, 128)\n"
                + "............Internal (64, 64)\n"
                + "................home 5 (0, 0)\n"
                + "................Empty\n................Empty\n"
                + "................away 10 (64, 64)\n............Empty\n"
                + "............Empty\n............Empty\n........Empty\n"
                + "........Empty\n........Empty\n....Empty\n....Empty\n"
                + "....Empty\n";

        assertTrue(systemOut().getHistory().contains(str));
    }


    /**
     * tests the makenull command
     */
    public void testMakeNull()
    {
        setSystemIn("insert home 5 0 0\n" + "insert away 10 64 64\n"
            + "makenull\n");
        QuadTree.main(null);

        assertTrue(systemOut().getHistory().contains("makenull\n"));
    }


    /**
     * test the delete function. Regular delete for all nodes and correct node
     * promotion when an internal node is left with only a single leaf node
     */
    public void testDelete()
    {
        setSystemIn("insert city1 1 0 0\ninsert city2 2 64 64\n"
            + "insert city3 3 123 40\ninsert city4 4 40 123\n"
            + "insert city5 5 1 1\ninsert city6 6 71 73\ninsert city7 7 69 32\n"
            + "insert city8 8 35 70\ndelete location 71 73\n");
        QuadTree.main(null);
        assertTrue(systemOut()
            .getHistory()
            .contains(
                "insert city1 1 0 0\ninsert city2 2 64 64\n"
                    + "insert city3 3 123 40\ninsert city4 4 40 123\n"
                    + "insert city5 5 1 1\ninsert city6 6 71 73\n" +
                    "insert city7 7 69 32\n"
                    + "insert city8 8 35 70\ndelete location 71 73\n"));
    }


    /**
     * test for the rfind method
     */
    public void testRFind()
    {
        setSystemIn("#i am nothing\ninsert city1 1 0 0\ninsert city2 2 64 64\n"
            + "insert city3 3 123 40\ninsert city4 4 40 123\n"
            + "insert city5 5 1 1\ninsert city6 6 71 73\ninsert city7 7 69 32\n"
            + "insert city8 8 35 70\nrfind 0 0 512 512\n");
        QuadTree.main(null);
        String output =
            "rfind 0 0 512 512\ncity1 1 (0, 0)\ncity5 5 (1, 1)\n"
                + "city7 7 (69, 32)\ncity3 3 (123, 40)\ncity8 8 (35, 70)\n"
                + "city4 4 (40, 123)\ncity2 2 (64, 64)\ncity6 6 (71, 73)\n";

        assertTrue(systemOut().getHistory().contains(output));
    }


    /**
     * test for delete that transforms correctly
     */
    public void testTransform1()
    {
        setSystemIn("insert city1 1 1 1\ninsert city2 2 550 34\n"
            + "delete location 1 1\n");

        QuadTree.main(null);

        assertEquals("insert city1 1 1 1\ninsert city2 2 550 34\n"
            + "delete location 1 1\n", systemOut().getHistory());
    }


    /**
     * test for delete that transforms correctly
     */
    public void testTransform2()
    {
        setSystemIn("insert city1 1 1 1\ninsert city2 2 30 550\n"
            + "delete location 1 1\n");

        QuadTree.main(null);

        assertEquals("insert city1 1 1 1\ninsert city2 2 30 550\n"
            + "delete location 1 1\n", systemOut().getHistory());
    }


    /**
     * test for delete transform correctly
     */
    public void testTransform3()
    {
        setSystemIn("insert city1 1 1 1\ninsert city2 2 550 550\n"
            + "delete location 1 1\n");

        QuadTree.main(null);

        assertEquals("insert city1 1 1 1\ninsert city2 2 550 550\n"
            + "delete location 1 1\n", systemOut().getHistory());
    }


    /**
     * test the delete when not found on a leaf and not found on an empty
     */
    public void testDeleteMIA()
    {
        setSystemIn("insert city1 1 1 1\n\n#\ndelete location 1 0\n"
            + "delete location 760 4\n\n");
        QuadTree.main(null);
        String output =
            "delete location 1 0\nNot found\n"
                + "delete location 760 4\nNot found\n";

        assertTrue(systemOut().getHistory().contains(output));
    }


    /**
     * test for inserting duplicates
     */
    public void testDupe()
    {
        setSystemIn("insert city1 1 1 1\ninsert city2 2 1 1\n");
        QuadTree.main(null);

        assertEquals(
            "insert city1 1 1 1\ninsert city2 2 1 1\nDuplicate\n",
            systemOut().getHistory());
    }


    /**
     * tests for out of bounds measures; add, rfind, find, and delete out of
     * bounds
     */
    public void testBounds()
    {
        setSystemIn("insert city1 1 -1 1100\ninsert city2 2 1100 -1\n"
            + "delete location -12 -12\ndelete location 1200 1200\n"
            + "find location 1200 1200\nfind location -1 -1\n"
            + "rfind -1 1100 1500 -1\nrfind 1100 -1 -1 1500\n");
        QuadTree.main(null);

        String output =
            "insert city1 1 -1 1100\nOut of Bounds\n"
                + "insert city2 2 1100 -1\nOut of Bounds\n"
                + "delete location -12 -12\nNot found\n"
                + "delete location 1200 1200\nNot found\n"
                + "find location 1200 1200\nNot found\n" +
                "find location -1 -1\n"
                + "Not found\nrfind -1 1100 1500 -1\nNot found\n"
                + "rfind 1100 -1 -1 1500\nNot found\n";

        assertTrue(systemOut().getHistory().contains(output));
    }

    // ----------------------------------------------------------
    /**
     * Testing of the r find when nothing is found
     */
    public void testRFindNothing()
    {
        setSystemIn("rfind 1 7 170 400\n");
        QuadTree.main(null);

        assertTrue(systemOut().getHistory().contains("Not found\n"));
    }

    /**
     * test for delete on an empty node and delete a northwest child
     */
    public void testDeleteNW()
    {
        setSystemIn("insert citya 0 520 30\ninsert cityb 0 900 40\n"
            + "delete location 900 900\ndelete location 900 40\n");
        QuadTree.main(null);

        assertTrue(systemOut().getHistory().contains("Not found\n"));
    }

}
