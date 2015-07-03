
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.ArrayDeque;;

// -------------------------------------------------------------------------
/**
 *  This is the bfs class. It handles both the different queues and hashing
 *  pf the puzzle states
 *
 *  @author Jack Cobb <jack3>
 *  @version Apr 7, 2014
 */
public class BFS
{
    //Fields
    private PriorityQueue<PuzzleState> pq;
    private ArrayDeque<PuzzleState> dq;
    private HashTable<PuzzleState> table;

    // ----------------------------------------------------------
    /**
     * Create a new BFS object.
     */
    public BFS()
    {
        pq = new PriorityQueue<PuzzleState>(2000, new StateComp());
        dq = new ArrayDeque<PuzzleState>();
        table = new HashTable<PuzzleState>(1024);
    }

    // ----------------------------------------------------------
    /**
     * Uses FIFO to find the solution of the puzzle
     * @param add is the state to be inserted
     * @param visited is the array of visited states to add to
     * @param format is the type of format to output
     * @param solution is the type of solution to print
     * @return is the found goal path
     */
    public PuzzleState fifo(PuzzleState add,
        ArrayList<PuzzleState> visited, int format, int solution)
    {
        dq.add(add);
        table.insert(add);
        visited.add(add);
        PuzzleState first = new PuzzleState();
        if (add.manhattan() == 0)
        {
            first = add;
            dq.clear();
        }
        while (!dq.isEmpty())
        {
            first = dq.remove();
            ArrayList<Integer> simple = first.moves();
            //int size = simple.size();
            for (Integer i : simple)
            {
                PuzzleState move = first.move(i);
                boolean enqueued = table.insert(first.move(i));
                if (enqueued)
                {
                    dq.add(move);
                    move.setParent(first);
                    visited.add(move);
                    if (move.manhattan() == 0)
                    {
                        return move;
                    }
                }
            }
        }
        return first;

    }

    /**
     * helper method used to only print the solution path for the solved
     * state
     */
    private void solutionPath(PuzzleState start, int format)
    {
        boolean atStart = false;
        PuzzleState place = start;
        ArrayList<PuzzleState> order = new ArrayList<PuzzleState>();
        while (!atStart)
        {
            if (place.getParent() == null)
            {
                atStart = true;
                order.add(place);
            }
            else
            {
                order.add(place);
                place = place.getParent();
            }
        }
        //reverse the order
        //done getting solution path; begin printing
        ArrayList<PuzzleState> correctOrder = new ArrayList<PuzzleState>();
        for (int k = 0; k < order.size(); k++)
        {
            correctOrder.add(order.get(order.size() - k - 1));
        }
        System.out.println("Solution path:");
        for (int i = 0; i < order.size(); i++)
        {
            PuzzleState cur = correctOrder.get(i);
            String firstLine = (i + 1) + " " + cur.printEmpty();
            if (i < order.size() - 1)
            {
                //adds the next move it makes to get there
                firstLine = firstLine + " "
                    + cur.difference(correctOrder.get(i + 1));
            }
            System.out.println(firstLine);
            if (format == 1)
            {
                System.out.println();
                cur.printGrid();
                if (i != order.size())
                {
                    System.out.println();   //adds extra line before next grid
                }
            }

        }
    }
    // ----------------------------------------------------------
    /**
     * This utilizes a priority queue to find a quick solution path to the goal
     * state
     * @param add is the puzzle to start with
     * @param visited is the array of visited states
     * @param format is they type of format: 0 for coords, 1 for grid
     * @param solution is the type of state output: 0 for all, 1 for only
     * the solution path
     * @return is the found goal state
     */
    public PuzzleState priority(PuzzleState add, ArrayList<PuzzleState> visited,
        int format, int solution)
    {
        pq.add(add);
        visited.add(add);
        table.insert(add);
        PuzzleState first = new PuzzleState();
        if (add.manhattan() == 0)
        {
            first = add;
            pq.clear();
        }
        while (!pq.isEmpty())
        {
            first = pq.remove();
            ArrayList<Integer> moves = first.moves();
            for (int i = 0; i < moves.size(); i++)
            {
                int direction = moves.get(i);
                PuzzleState move = first.move(direction);
                boolean enqueued = table.insert(first.move(direction));
                if (enqueued)
                {
                    pq.add(move);
                    move.setParent(first);
                    visited.add(move);
                    if (move.manhattan() == 0)
                    {
                        return move;
                    }
                }
            }
        }
        return first;
    }

    // ----------------------------------------------------------
    /**
     * The run method calls different queues.
     * @param queue is the type of queue to use it
     * @param format is what type of format to do it
     * @param solution is what solution to do
     * @param visited is the array of visited states
     * @param begin is the state to begin with
     */
    public void run(int queue, int format, int solution,
        ArrayList<PuzzleState> visited, PuzzleState begin)
    {
        PuzzleState stop;
        if (queue == 1)
        {
            stop = priority(begin, visited, format, solution);
        }
        else
        {
            stop = fifo(begin, visited, format, solution);
        }
        if (solution == 1)
        {
            //print only solution path with differing type (helper method)
            solutionPath(stop, format);
        }
        else    //wants all nodes visited
        {
            System.out.println("Visited States:");
            for (int j = 0; j < visited.size(); j++)
            {
                //helper method that prints grid or regular
                //but for all nodes visited
                System.out.println((j + 1) + " " + visited.get(j).printEmpty());
                if (format == 1)
                {
                    System.out.println();
                    visited.get(j).printGrid();
                    if (j != visited.size() - 1)
                    {
                        System.out.println();
                    }
                }
            }
        }
    }
}
