import java.util.ArrayList;

//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than the instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.

// -------------------------------------------------------------------------
/**
 *  This class is the solver class. It Randomizes a game state, then tries to
 *  solver it.
 *
 *  @author Jack Cobb <jack3>
 *  @version Apr 5, 2014
 */
public class Solver
{
    //Fields
    private static BFS bfs;

    // ----------------------------------------------------------
    /**
     * This is the main class
     * @param args is the array of arguments that the function uses
     */
    public static void main(String[] args)
    {
        bfs = new BFS();
        int format = 0;
        int solution = 0;
        if (args[0].equals("S"))
        {   //we want only the solution path
            solution = 1;
        }
        if (args[2].equals("V"))
        {   //we want grids not just coords
            format = 1;
        }
        ArrayList<Byte> initialState = new ArrayList<Byte>();
        for (int i = 0; i < 16; i++)
        {
            String number = args[i + 3];
            int value = Integer.parseInt(number);
            if (value == 0)
            {
                value = 16;
            }
            initialState.add((byte)value);
        }
        PuzzleState startingBoard = new PuzzleState();
        startingBoard.setBoard(initialState);
        ArrayList<PuzzleState> visited = new ArrayList<PuzzleState>();
        if (args[1].equals("F"))
        {
            //use FIFO
            bfs.run(0, format, solution, visited, startingBoard);
        }
        else
        {
            //use prority queue
            bfs.run(1, format, solution, visited, startingBoard);
        }
    }
}
