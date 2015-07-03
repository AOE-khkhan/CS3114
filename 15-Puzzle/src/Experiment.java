import java.util.Random;
import java.util.ArrayList;


// -------------------------------------------------------------------------
/**
 *  This is the experiment class that is used for comparing
 *
 *  @author Jack
 *  @version Apr 10, 2014
 */
public class Experiment
{
    //Fields
    private PuzzleState initial;

    /**
     * this constructor creates a solved board, so that it can generate a
     * random, yet solvable state
     */
    public Experiment()
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
        initial = new PuzzleState();
        initial.setBoard(board);
    }

    /**
     * this is used to run the fifo bfs search with the solution path and
     * coordinates used for printing and experiments
     */
    public void testFIFO()
    {
        ArrayList<PuzzleState> visited = new ArrayList<PuzzleState>();
        BFS tester = new BFS();
        tester.run(0, 0, 1, visited, initial);
    }

    /**
     * this is used to run the pq bfs search within the solution path and
     * coordinates used for printing and experiments
     */
    public void testPQ()
    {
        ArrayList<PuzzleState> visited = new ArrayList<PuzzleState>();
        BFS tester = new BFS();
        tester.run(1, 0, 1, visited, initial);
    }
    /**
     * takes an int for how many random moves to generate
     * @param n is how many random moves to do
     */
    public void moveN(int n)
    {
        int previousMove = 0;
        for (int i = 0; i < n; i++)
        {
            ArrayList<Integer> directions = initial.moves();
            directions = removeDirection(previousMove, directions);
            Random generator = new Random();
            int randomMove = generator.nextInt(directions.size());
            initial = initial.move(randomMove);
            previousMove = randomMove;
        }
    }

    /**
     * removes the previous move from the list
     * @param direction is which was the previous direction
     * @param moves is the arraylist of possible moves
     * @return is the updated arraylist of mvoes
     */
    public ArrayList<Integer> removeDirection(int direction,
        ArrayList<Integer> moves)
    {
        ArrayList<Integer> updated = moves;
        int remove = 0;
        if (direction == 0)
        {
            return updated;
        }
        else if (direction == 2)
        {
            remove = 4;
        }
        else
        {
            remove = (direction + 2) % 4;
        }
        for (int i = 0; i < updated.size(); i++)
        {
            if (updated.get(i) == remove)
            {
                updated.remove(i);
            }
        }
        return updated;
    }
}
