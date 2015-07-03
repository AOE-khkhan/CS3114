import java.util.ArrayList;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  This is a test class for the puzzle state comparator that compares the
 *  states based on their manhattan values
 *
 *  @author Jack Cobb <jack3>
 *  @version Apr 8, 2014
 */
public class StateCompTest extends TestCase
{
    //Fields
    private StateComp tester;

    /**
     * runs before every test and and creates a new test environment
     */
    public void setUp()
    {
        tester = new StateComp();
    }

    /**
     * tests that the comparator functions correctly
     */
    public void testCompare()
    {
        PuzzleState board1 = new PuzzleState();
        ArrayList<Byte> board = new ArrayList<Byte>();
        board.add((byte)2);
        board.add((byte)4);
        board.add((byte)3);
        board.add((byte)11);
        board.add((byte)13);
        board.add((byte)16);
        board.add((byte)7);
        board.add((byte)1);
        board.add((byte)10);
        board.add((byte)5);
        board.add((byte)15);
        board.add((byte)14);
        board.add((byte)9);
        board.add((byte)6);
        board.add((byte)12);
        board.add((byte)8);
        board1.setBoard(board);

        PuzzleState down = board1.move(3);
        PuzzleState right = board1.move(2);

        assertEquals(1, tester.compare(board1, down));
        assertEquals(-1, tester.compare(board1, right));
    }

}
