import java.util.Comparator;


// -------------------------------------------------------------------------
/**
 *  This is a comparator for use in the priority queue that compares based on
 *  the manhattan distance
 *
 *  @author Jack Cobb <jack3>
 *  @version Apr 7, 2014
 */
public class StateComp implements Comparator<PuzzleState>
{

    /**
     * this is used to compare to states
     * @param o1 is the first state
     * @param o2 is the second state
     * @return is the direction to go
     */
    public int compare(PuzzleState o1, PuzzleState o2)
    {
        if (o1.manhattan() < o2.manhattan())
        {
            return -1;
        }
        else if (o1.manhattan() > o2.manhattan())
        {

            return 1;
        }
        else
        {
            return 0;
        }

    }

}
