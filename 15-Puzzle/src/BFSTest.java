import java.util.ArrayList;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test class for the bfs to make sure it works
 *
 * @author Jack Cobb <jack3
 * @version Apr 9, 2014
 */

public class BFSTest
    extends TestCase
{
    // Fields
    private BFS         tester;
    private PuzzleState start;


    /**
     * sets up the test environment before each test
     */
    public void setUp()
    {
        tester = new BFS();
        ArrayList<Byte> board = new ArrayList<Byte>();
        board.add((byte)1);
        board.add((byte)2);
        board.add((byte)3);
        board.add((byte)4);
        board.add((byte)5);
        board.add((byte)6);
        board.add((byte)7);
        board.add((byte)8);
        board.add((byte)9);
        board.add((byte)10);
        board.add((byte)11);
        board.add((byte)12);
        board.add((byte)13);
        board.add((byte)16);
        board.add((byte)14);
        board.add((byte)15);
        start = new PuzzleState();
        start.setBoard(board);
    }


    /**
     * test method for fifo queue printing the solution in coordinate format
     */
    public void testFIFOSolutionCoords()
    {
        // format is 1 for grid or 0 for coordinates
        // solution is 0 for all visited or 1 for just solution path
        ArrayList<PuzzleState> visited = new ArrayList<PuzzleState>();
        tester.run(0, 0, 1, visited, start);

        String str =
            "Solution path:\n" + "1 (4,2) right 14\n" + "2 (4,3) right 15\n"
                + "3 (4,4)\n";
        assertTrue(systemOut().getHistory().contains(str));
    }


    /**
     * test method for fifo queue printing only the solution path in grid format
     */
    public void testFIFOSolutionGrid()
    {
        // format is 1 for grid or 0 for coordinates
        // solution is 0 for all visited or 1 for just solution path
        ArrayList<PuzzleState> visited = new ArrayList<PuzzleState>();
        tester.run(0, 1, 1, visited, start);

        String output =
            "Solution path:\n" + "1 (4,2) right 14\n" + "\n" + " 1  2  3  4\n"
                + " 5  6  7  8\n" + " 9 10 11 12\n" + "13    14 15\n" + "\n"
                + "2 (4,3) right 15\n" + "\n" + " 1  2  3  4\n"
                + " 5  6  7  8\n" + " 9 10 11 12\n" + "13 14    15\n" + "\n"
                + "3 (4,4)\n" + "\n" + " 1  2  3  4\n" + " 5  6  7  8\n"
                + " 9 10 11 12\n" + "13 14 15   \n";

        assertTrue(systemOut().getHistory().contains(output));
    }


    /**
     * test method for fifo queue print al visited in coordinate format
     */
    public void testFIFOAllCoords()
    {
        // format is 1 for grid or 0 for coordinates
        // solution is 0 for all visited or 1 for just solution path
        ArrayList<PuzzleState> visited = new ArrayList<PuzzleState>();
        tester.run(0, 0, 0, visited, start);

        String output =
            "Visited States:\n" + "1 (4,2)\n" + "2 (3,2)\n" + "3 (4,3)\n"
                + "4 (4,1)\n" + "5 (2,2)\n" + "6 (3,3)\n" + "7 (3,1)\n"
                + "8 (3,3)\n" + "9 (4,4)\n";

        assertTrue(systemOut().getHistory().contains(output));
    }


    /**
     * test method for fifo queue print all visited states in grid format
     */
    public void testFIFOAllGrid()
    {
        // format is 1 for grid or 0 for coordinates
        // solution is 0 for all visited or 1 for just solution path
        ArrayList<PuzzleState> visited = new ArrayList<PuzzleState>();
        tester.run(0, 1, 0, visited, start);

        String output =
            "Visited States:\n" + "1 (4,2)\n" + "\n" + " 1  2  3  4\n"
                + " 5  6  7  8\n" + " 9 10 11 12\n" + "13    14 15\n" + "\n"
                + "2 (3,2)\n" + "\n" + " 1  2  3  4\n" + " 5  6  7  8\n"
                + " 9    11 12\n" + "13 10 14 15\n" + "\n" + "3 (4,3)\n" + "\n"
                + " 1  2  3  4\n" + " 5  6  7  8\n" + " 9 10 11 12\n"
                + "13 14    15\n" + "\n" + "4 (4,1)\n" + "\n" + " 1  2  3  4\n"
                + " 5  6  7  8\n" + " 9 10 11 12\n" + "   13 14 15\n" + "\n"
                + "5 (2,2)\n" + "\n" + " 1  2  3  4\n" + " 5     7  8\n"
                + " 9  6 11 12\n" + "13 10 14 15\n" + "\n" + "6 (3,3)\n" + "\n"
                + " 1  2  3  4\n" + " 5  6  7  8\n" + " 9 11    12\n"
                + "13 10 14 15\n" + "\n" + "7 (3,1)\n" + "\n" + " 1  2  3  4\n"
                + " 5  6  7  8\n" + "    9 11 12\n" + "13 10 14 15\n" + "\n"
                + "8 (3,3)\n" + "\n" + " 1  2  3  4\n" + " 5  6  7  8\n"
                + " 9 10    12\n" + "13 14 11 15\n" + "\n" + "9 (4,4)\n" + "\n"
                + " 1  2  3  4\n" + " 5  6  7  8\n" + " 9 10 11 12\n"
                + "13 14 15   \n";

        assertTrue(systemOut().getHistory().contains(output));
    }


    /**
     * test method for priority queue solution path with coords
     */
    public void testPQSolutionCoords()
    {
        ArrayList<PuzzleState> visited = new ArrayList<PuzzleState>();
        tester.run(1, 0, 1, visited, start);

        String output =
            "Solution path:\n" + "1 (4,2) right 14\n" + "2 (4,3) right 15\n"
                + "3 (4,4)\n";

        assertTrue(systemOut().getHistory().contains(output));
    }


    /**
     * test method for priorityqueue for solution path in grid format
     */
    public void testPQSolutionGrid()
    {
        ArrayList<PuzzleState> visited = new ArrayList<PuzzleState>();
        tester.run(1, 1, 1, visited, start);

        String output =
            "Solution path:\n" + "1 (4,2) right 14\n" + "\n" + " 1  2  3  4\n"
                + " 5  6  7  8\n" + " 9 10 11 12\n" + "13    14 15\n" + "\n"
                + "2 (4,3) right 15\n" + "\n" + " 1  2  3  4\n"
                + " 5  6  7  8\n" + " 9 10 11 12\n" + "13 14    15\n" + "\n"
                + "3 (4,4)\n" + "\n" + " 1  2  3  4\n" + " 5  6  7  8\n"
                + " 9 10 11 12\n" + "13 14 15   \n";

        assertTrue(systemOut().getHistory().contains(output));
    }


    /**
     * test method for priority queue printing all visisted states as coords
     */
    public void testPQAllCoords()
    {
        ArrayList<PuzzleState> visited = new ArrayList<PuzzleState>();
        tester.run(1, 0, 0, visited, start);

        String output =
            "Visited States:\n" + "1 (4,2)\n" + "2 (3,2)\n" + "3 (4,3)\n"
                + "4 (4,1)\n" + "5 (3,3)\n" + "6 (4,4)\n";

        assertTrue(systemOut().getHistory().contains(output));
    }


    /**
     * test method for priority q printing all the visited states
     */
    public void testPQAllGrid()
    {
        ArrayList<PuzzleState> visited = new ArrayList<PuzzleState>();
        tester.run(1, 1, 0, visited, start);

        String output =
            "Visited States:\n" + "1 (4,2)\n" + "\n" + " 1  2  3  4\n"
                + " 5  6  7  8\n" + " 9 10 11 12\n" + "13    14 15\n" + "\n"
                + "2 (3,2)\n" + "\n" + " 1  2  3  4\n" + " 5  6  7  8\n"
                + " 9    11 12\n" + "13 10 14 15\n" + "\n" + "3 (4,3)\n" + "\n"
                + " 1  2  3  4\n" + " 5  6  7  8\n" + " 9 10 11 12\n"
                + "13 14    15\n" + "\n" + "4 (4,1)\n" + "\n" + " 1  2  3  4\n"
                + " 5  6  7  8\n" + " 9 10 11 12\n" + "   13 14 15\n" + "\n"
                + "5 (3,3)\n" + "\n" + " 1  2  3  4\n" + " 5  6  7  8\n"
                + " 9 10    12\n" + "13 14 11 15\n" + "\n" + "6 (4,4)\n" + "\n"
                + " 1  2  3  4\n" + " 5  6  7  8\n" + " 9 10 11 12\n"
                + "13 14 15   \n";

        assertTrue(systemOut().getHistory().contains(output));
    }


    /**
     * very long test for pq with many moves to be made
     */
    public void testPQHard()
    {
        ArrayList<Byte> board = new ArrayList<Byte>();
        board.add((byte)16);
        board.add((byte)12);
        board.add((byte)9);
        board.add((byte)13);
        board.add((byte)15);
        board.add((byte)11);
        board.add((byte)10);
        board.add((byte)14);
        board.add((byte)7);
        board.add((byte)8);
        board.add((byte)5);
        board.add((byte)6);
        board.add((byte)4);
        board.add((byte)3);
        board.add((byte)2);
        board.add((byte)1);
        start = new PuzzleState();
        start.setBoard(board);

        System.out.println("Testing Hard");

        ArrayList<PuzzleState> visited = new ArrayList<PuzzleState>();
        tester.run(1, 0, 1, visited, start);

        assertTrue(tester.equals(tester));
    }

    /**
     * tests the fifo when the initial state is the solution state
     */
    public void testFIFODone()
    {
        ArrayList<Byte> board = new ArrayList<Byte>();
        board.add((byte)1);
        board.add((byte)2);
        board.add((byte)3);
        board.add((byte)4);
        board.add((byte)5);
        board.add((byte)6);
        board.add((byte)7);
        board.add((byte)8);
        board.add((byte)9);
        board.add((byte)10);
        board.add((byte)11);
        board.add((byte)12);
        board.add((byte)13);
        board.add((byte)14);
        board.add((byte)15);
        board.add((byte)16);
        start = new PuzzleState();
        start.setBoard(board);

        ArrayList<PuzzleState> visited = new ArrayList<PuzzleState>();
        tester.run(0, 0, 1, visited, start);

        String output = "1 (4,4)\n";

        assertTrue(systemOut().getHistory().contains(output));
    }

    /**
     * tests pq when the first one is a solved solution
     */
    public void testPQDone()
    {
        ArrayList<Byte> board = new ArrayList<Byte>();
        board.add((byte)1);
        board.add((byte)2);
        board.add((byte)3);
        board.add((byte)4);
        board.add((byte)5);
        board.add((byte)6);
        board.add((byte)7);
        board.add((byte)8);
        board.add((byte)9);
        board.add((byte)10);
        board.add((byte)11);
        board.add((byte)12);
        board.add((byte)13);
        board.add((byte)14);
        board.add((byte)15);
        board.add((byte)16);
        start = new PuzzleState();
        start.setBoard(board);

        ArrayList<PuzzleState> visited = new ArrayList<PuzzleState>();
        tester.run(1, 0, 1, visited, start);

        String output = "1 (4,4)\n";

        assertTrue(systemOut().getHistory().contains(output));
    }

}
