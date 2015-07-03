import java.util.ArrayList;
import student.TestCase;


// -------------------------------------------------------------------------
/**
 *  This is a tester class for the state class that ensures it works correctly
 *
 *  @author Jack Cobb <jack3>
 *  @version Apr 4, 2014
 */
public class StateTest extends TestCase
{
    //Fields
    private PuzzleState tester;
    private ArrayList<Byte> board;

    /**
     * gets run before each test and sets up a test environment
     */
    public void setUp()
    {
        tester = new PuzzleState();
        board = new ArrayList<Byte>();
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
        tester.setBoard(board);
    }

    /**
     * This tests the equals method
     */
    public void testEqual()
    {
        PuzzleState test2 = new PuzzleState();
        test2.setBoard(board);

        assertTrue(tester.equals(test2));
    }

    /**
     * This tests the get index method
     */
    public void testGetIndex()
    {
        assertEquals(tester.get(2, 2), 15);
    }

    /**
     * test that move works right
     */
    public void testMove()
    {
        PuzzleState var1 = tester.move(1);

        assertEquals(16, var1.get(0, 1));
        assertEquals(4, var1.get(1, 1));

        PuzzleState var2 = tester.move(2);

        assertEquals(16, var2.get(1, 2));
        assertEquals(7, var2.get(1, 1));

        PuzzleState var3 = tester.move(3);

        assertEquals(16, var3.get(2, 1));
        assertEquals(5, var3.get(1, 1));

        PuzzleState var4 = tester.move(4);

        assertEquals(16, var4.get(1, 0));
        assertEquals(13, var4.get(1, 1));

        assertFalse(tester.equals(var4));
    }

    /**
     * tests the number of moves when the empty slot is inside
     */
    public void testMovesNormal()
    {
        //test for the moves when inside
        //should be 1 2 3 4
        assertEquals(4, tester.moves().size());
    }

    /**
     * this tests the number of available moves when the empty is on the top
     * row, including corners
     */
    public void testMovesTop()
    {
        //0 3, 0 0, top row
        PuzzleState topRow = tester.move(1);
        PuzzleState topLeft = topRow.move(4);
        PuzzleState topRow2 = topRow.move(2);
        PuzzleState topRight = topRow2.move(2);

        //test top left
        assertEquals(topLeft.moves().get(0).intValue(), 2);
        assertEquals(topLeft.moves().get(1).intValue(), 3);

        //test top right
        assertEquals(topRight.moves().get(0).intValue(), 3);
        assertEquals(topRight.moves().get(1).intValue(), 4);

      //test top row
        assertEquals(topRow.moves().get(0).intValue(), 2);
        assertEquals(topRow.moves().get(1).intValue(), 3);
        assertEquals(topRow.moves().get(2).intValue(), 4);
    }

    /**
     * This tests the available moves of a state when its on the bottom,
     * corners included
     */
    public void testMovesBottom()
    {
        //3 0, 3 3, bottom row
        PuzzleState thirdRow = tester.move(3);
        PuzzleState bottomRow = thirdRow.move(3);
        PuzzleState bottomLeft = bottomRow.move(4);
        PuzzleState bottomRow2 = bottomRow.move(2);
        PuzzleState bottomRight = bottomRow2.move(2);

        //test bottom left
        assertEquals(bottomLeft.moves().get(0).intValue(), 1);
        assertEquals(bottomLeft.moves().get(1).intValue(), 2);

        //test bottom right
        assertEquals(bottomRight.moves().get(0).intValue(), 1);
        assertEquals(bottomRight.moves().get(1).intValue(), 4);

        //test bottom row
        assertEquals(bottomRow.moves().get(0).intValue(), 1);
        assertEquals(bottomRow.moves().get(1).intValue(), 2);
        assertEquals(bottomRow.moves().get(2).intValue(), 4);
    }

    /**
     * This tests the number of moves that the state has when its on the left
     * or right
     */
    public void testSides()
    {
        //left column and right column
        PuzzleState leftColumn = tester.move(4);
        PuzzleState internal = tester.move(2);
        PuzzleState rightColumn = internal.move(2);

        //test left column
        assertEquals(leftColumn.moves().get(0).intValue(), 1);
        assertEquals(leftColumn.moves().get(1).intValue(), 2);
        assertEquals(leftColumn.moves().get(2).intValue(), 3);

        //test right column
        assertEquals(rightColumn.moves().get(0).intValue(), 1);
        assertEquals(rightColumn.moves().get(1).intValue(), 3);
        assertEquals(rightColumn.moves().get(2).intValue(), 4);
    }

    /**
     * this tests the has code output when the empty slot is inside. Not on
     * the sides or in the corner
     */
    public void testHashCodeInternal()
    {
        PuzzleState dif = tester.move(3);
        assertNotSame(dif.hashCode(), tester.hashCode());
    }

    /**
     * this tests the hash code output when the empty slot is on the sides
     */
    public void testHasCodeSides()
    {
        PuzzleState top = tester.move(1);
        PuzzleState left = tester.move(4);
        PuzzleState var1 = tester.move(2);
        PuzzleState right = var1.move(2);
        PuzzleState var2 = var1.move(3);
        PuzzleState bottom = var2.move(3);

        assertNotSame(left.hashCode(), top.hashCode());
        assertNotSame(top.hashCode(), left.hashCode());
        assertNotSame(bottom.hashCode(), right.hashCode());
        assertNotSame(right.hashCode(), bottom.hashCode());
    }

    /**
     * this is a test for the hash code for when the corners are empty
     */
    public void testHashCodeCorners()
    {
        PuzzleState left1 = tester.move(4);
        PuzzleState topLeft = left1.move(1);
        PuzzleState left2 = left1.move(3);
        PuzzleState bottomLeft = left2.move(3);
        PuzzleState right1 = tester.move(2);
        PuzzleState right2 = right1.move(2);
        PuzzleState topRight = right2.move(1);
        PuzzleState right3 = right2.move(3);
        PuzzleState bottomRight = right3.move(3);

        assertNotSame(tester.hashCode(), topLeft.hashCode());
        assertNotSame(topLeft.hashCode(), topRight.hashCode());
        assertNotSame(tester.hashCode(), bottomLeft.hashCode());
        assertNotSame(right3.hashCode(), bottomRight.hashCode());
    }

    /**
     * This method tests the manhattan distance of the board to the
     * solved state
     */
    public void testManHattanDistance()
    {
        PuzzleState solved = new PuzzleState();

        ArrayList<Byte> inOrder = new ArrayList<Byte>();
        inOrder.add((byte)1);
        inOrder.add((byte)2);
        inOrder.add((byte)3);
        inOrder.add((byte)4);
        inOrder.add((byte)5);
        inOrder.add((byte)6);
        inOrder.add((byte)15);
        inOrder.add((byte)8);
        inOrder.add((byte)9);
        inOrder.add((byte)10);
        inOrder.add((byte)11);
        inOrder.add((byte)7);
        inOrder.add((byte)13);
        inOrder.add((byte)14);
        inOrder.add((byte)12);
        inOrder.add((byte)16);

        solved.setBoard(inOrder);

        assertEquals(6, solved.manhattan());
    }

    /**
     * tests the difference method that returns the string of what direction
     * and what the value of the moved slot was
     */
    public void testDifference()
    {
        PuzzleState up = tester.move(1);
        PuzzleState right = tester.move(2);
        PuzzleState down = tester.move(3);
        PuzzleState left = tester.move(4);

        assertEquals("up 4", tester.difference(up));
        assertEquals("right 7", tester.difference(right));
        assertEquals("down 5", tester.difference(down));
        assertEquals("left 13", tester.difference(left));
    }

    /**
     * test method for the grid output of the board
     */
    public void testGrid()
    {
        String grid = " 2  4  3 11\n" + "13     7  1\n" + "10  5 15 14\n" +
                        " 9  6 12  8\n";

        tester.printGrid();

        assertTrue(systemOut().getHistory().contains(grid));
    }

    /**
     * test method for the getters and setters of the parent methods
     */
    public void testParenMethods()
    {
        PuzzleState parent = tester.move(2);

        assertNull(tester.getParent());

        tester.setParent(parent);

        assertEquals(parent, tester.getParent());
    }

    /**
     * test method for printing of the empty slot in the correct format
     */
    public void testPrintEmpty()
    {
        assertEquals("(2,2)", tester.printEmpty());
    }

    /**
     * Test method for the has code after moving to make sure that move
     * works correctly and generates a new hash code
     */
    public void testHashCodeAfterMove()
    {
        PuzzleState left = tester.move(2);
        PuzzleState original = left.move(4);

        assertEquals(tester.hashCode(), original.hashCode());
    }
}

