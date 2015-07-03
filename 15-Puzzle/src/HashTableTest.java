import java.util.ArrayList;
import student.TestCase;


// -------------------------------------------------------------------------
/**
 *  This is a test class for the hashtable to ensure that it works
 *
 *  @author Jack Cobb <jack3>
 *  @version Apr 10, 2014
 */
public class HashTableTest extends TestCase
{
    //Fields
    private HashTable<PuzzleState> table;

    /**
     * this runs to set up the testing environment
     */
    public void setUp()
    {
        table = new HashTable<PuzzleState>(10);
    }

    /**
     * this tests that the table inserts elements and dynamically
     * allocated correctly
     */
    public void testInsert()
    {
        PuzzleState tester = new PuzzleState();
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
        tester.setBoard(board);

        //board is setup. Add to hashtable and a bunch of moves as well.
        table.insert(tester);

        assertEquals(1, table.getElements());

        //Make another 10 states and add to hashtable
        PuzzleState var1 = tester.move(1);
        PuzzleState var2 = tester.move(2);
        PuzzleState var3 = tester.move(3);
        PuzzleState var4 = tester.move(4);
        PuzzleState var5 = var1.move(2);
        PuzzleState var6 = var1.move(4);
        PuzzleState var7 = var2.move(2);
        PuzzleState var8 = var2.move(3);
        PuzzleState var9 = var2.move(1);
        PuzzleState var2Down = var2.move(3);
        PuzzleState varBackto2 = var2Down.move(1);
        //add all to hashtable


        table.insert(var1);
        table.insert(var2);
        table.insert(var3);
        table.insert(var4);
        table.insert(var5);
        boolean worked = table.insert(var2);
        boolean workedLater = table.insert(varBackto2);
        table.insert(var6);
        table.insert(var7);
        table.insert(var8);
        table.insert(var9);

        assertEquals(10, table.getElements());
        assertEquals(20, table.getTable().length);
        assertFalse(worked);
        assertFalse(workedLater);
    }


}
