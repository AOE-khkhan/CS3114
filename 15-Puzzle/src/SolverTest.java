


// -------------------------------------------------------------------------
/**
 *  Test class for the solver class to ensure it works correctly.
 *
 *  @author Jack Cobb <jack3>
 *  @version Apr 10, 2014
 */
public class SolverTest extends student.TestCase
{
    // Fields
    private Solver tester;
    private String[] args;


    /**
     * runs before each test to setup the environment
     */
    public void setUp()
    {
        tester = new Solver();
        args = new String[19];
        args[0] = "S";
        args[1] = "F";
        args[2] = "Q";
        args[3] = "1";
        args[4] = "2";
        args[5] = "3";
        args[6] = "4";
        args[7] = "5";
        args[8] = "6";
        args[9] = "7";
        args[10] = "8";
        args[11] = "9";
        args[12] = "10";
        args[13] = "11";
        args[14] = "12";
        args[15] = "13";
        args[16] = "0";
        args[17] = "14";
        args[18] = "15";

    }

    /**
     * this tests all the method for the fifo
     */
    public void testFIFO()
    {
        //Fifo solution path with coords
        Solver.main(args);

        String str = "Solution path:\n" +
            "1 (4,2) right 14\n" +
            "2 (4,3) right 15\n" +
             "3 (4,4)\n";

        assertTrue(systemOut().getHistory().contains(str));
        assertNotNull(tester);
    }

    /**
     * this tests all the methods for the priority queue
     */
    public void testPQ()
    {
        //PQ all with grid
        args[0] = "V";
        args[1] = "P";
        args[2] = "V";

        Solver.main(args);

        String output = "Visited States:\n"
            + "1 (4,2)\n"
            + "\n"
            + " 1  2  3  4\n"
            + " 5  6  7  8\n"
            + " 9 10 11 12\n"
            + "13    14 15\n"
            + "\n"
            + "2 (3,2)\n"
            + "\n"
            + " 1  2  3  4\n"
            + " 5  6  7  8\n"
            + " 9    11 12\n"
            + "13 10 14 15\n"
            + "\n"
            + "3 (4,3)\n"
            + "\n"
            + " 1  2  3  4\n"
            + " 5  6  7  8\n"
            + " 9 10 11 12\n"
            + "13 14    15\n"
            + "\n"
            + "4 (4,1)\n"
            + "\n"
            + " 1  2  3  4\n"
            + " 5  6  7  8\n"
            + " 9 10 11 12\n"
            + "   13 14 15\n"
            + "\n"
            + "5 (3,3)\n"
            + "\n"
            + " 1  2  3  4\n"
            + " 5  6  7  8\n"
            + " 9 10    12\n"
            + "13 14 11 15\n"
            + "\n"
            + "6 (4,4)\n"
            + "\n"
            + " 1  2  3  4\n"
            + " 5  6  7  8\n"
            + " 9 10 11 12\n"
            + "13 14 15   \n";

        assertTrue(systemOut().getHistory().contains(output));

    }

    /**
     * this is a test to see how long it takes. Trying to make sure it doesn't
     * time out
     */
    public void test40kSolution()
    {
        args[0] = "V";
        args[9] = "15";
        args[14] = "7";
        args[16] = "14";
        args[17] = "12";
        args[18] = "0";

        Solver.main(args);
    }


}
