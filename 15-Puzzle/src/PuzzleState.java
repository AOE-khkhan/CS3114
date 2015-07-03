import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * This class if used to represent a state of a 15 board. There are 16 slots
 * arranged in a 4 by 4 grid with the numbers 1-15 present and an empty slot for
 * the 16th position. A solved 15 puzzle has the numbers in order in the same
 * way as you would read (left to right, top to bottom) with the 16th slot being
 * the location of the empty slot.
 *
 * @author Jack Cobb <jack3>
 * @version Apr 4, 2014
 */
public class PuzzleState
{
    // Fields
    private byte[][]           board;
    private ArrayList<Integer> emptyLocation;
    private PuzzleState        parent;


    // ----------------------------------------------------------
    /**
     * Create a new State object. This initializes the 2d array of the puzzle
     */
    public PuzzleState()
    {
        board = new byte[4][4]; // bytes only use 8 bits and numbers only use
                                // up to 4 bits
        emptyLocation = new ArrayList<Integer>();
        parent = null;
    }


    /**
     * This is how you can get a valid move in the state
     *
     * @param move
     *            is which tile to move with
     * @return is the new state after the move
     */
    public PuzzleState move(int move)
    {
        PuzzleState nextMove = new PuzzleState();
        nextMove.copyState(this);
        ArrayList<Integer> empty = getEmpty(this);
        int xLoc = empty.get(0);
        int yLoc = empty.get(1);
        nextMove.updateEmpty(xLoc, yLoc);
        if (move == 1)
        {
            // swap with tile above
            nextMove.swapBytes(xLoc - 1, yLoc);
            nextMove.updateEmpty(xLoc - 1, yLoc);
        }
        else if (move == 2)
        {
            // swap with tile right
            nextMove.swapBytes(xLoc, yLoc + 1);
            nextMove.updateEmpty(xLoc, yLoc + 1);
        }
        else if (move == 3)
        {
            // swap with tile below
            nextMove.swapBytes(xLoc + 1, yLoc);
            nextMove.updateEmpty(xLoc + 1, yLoc);
        }
        else
        // move == 4
        {
            // swap with tile left
            nextMove.swapBytes(xLoc, yLoc - 1);
            nextMove.updateEmpty(xLoc, yLoc - 1);
        }
        return nextMove;
    }


    /**
     * This method checks if two states are equal to each other by checking
     * every byte in the 2d array
     *
     * @param compare
     *            is the state to compare against
     * @return is if they are equal
     */
    public boolean equals(Object compare)
    {
        PuzzleState used = (PuzzleState)compare;
        boolean equal = true;
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if (this.get(i, j) != used.get(i, j))
                {
                    equal = false;
                }
            }
        }
        return equal;
    }


    /**
     * This returns the current value at that position on the board
     *
     * @param i
     *            is the column
     * @param j
     *            is the row
     * @return is the value
     */
    public byte get(int i, int j)
    {
        return board[i][j];
    }


    /**
     * This sets the board. Called for testing and for initially creating a
     * solved board.
     *
     * @param order
     *            is the array list of the order to put the bytes into the board
     */
    public void setBoard(ArrayList<Byte> order)
    {
        int count = 0;
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                board[i][j] = order.get(count);
                count++;
                if (board[i][j] == 16)
                {
                    updateEmpty(i, j);
                }
            }
        }
    }


    /**
     * This determines what moves are available based upon where the empty slot
     * it
     *
     * @return the moves available
     */
    public ArrayList<Integer> moves()
    {
        ArrayList<Integer> availableMoves = new ArrayList<Integer>();
        ArrayList<Integer> empty = getEmpty(this);
        int xLoc = empty.get(0);
        int yLoc = empty.get(1);
        if (xLoc == 0)
        {
            if (yLoc == 0)
            {
                // top left corner
                availableMoves.add(2);
                availableMoves.add(3);
            }
            else if (yLoc == 3)
            {
                // top right corner
                availableMoves.add(3);
                availableMoves.add(4);
            }
            else
            {
                // top row
                availableMoves.add(2);
                availableMoves.add(3);
                availableMoves.add(4);
            }
        }
        else if (xLoc == 3)
        {
            if (yLoc == 0)
            {
                // bottom left
                availableMoves.add(1);
                availableMoves.add(2);
            }
            else if (yLoc == 3)
            {
                // bottom right
                availableMoves.add(1);
                availableMoves.add(4);
            }
            else
            {
                // bottom row
                availableMoves.add(1);
                availableMoves.add(2);
                availableMoves.add(4);
            }
        }
        else if (yLoc == 0)
        {
            // left column
            availableMoves.add(1);
            availableMoves.add(2);
            availableMoves.add(3);
        }
        else if (yLoc == 3)
        {
            // right column
            availableMoves.add(1);
            availableMoves.add(3);
            availableMoves.add(4);
        }
        else
        {
            // every move available
            availableMoves.add(1);
            availableMoves.add(2);
            availableMoves.add(3);
            availableMoves.add(4);
        }

        return availableMoves;
    }


    /**
     * this return a hashcode value for the puzzlestate
     * @return is the hashcode for the puzzle
     */
    public int hashCode()
    {
        int code = 0;
        int count = 1;
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                code += board[i][j] * count * 1381;
                count++;
            }
        }
        return code;
    }


    /**
     * This computes the manhattan distance
     *
     * @return is the number of moves until the slots are in the correct
     *         configuration
     */
    public int manhattan()
    {
        int manDist = 0;
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                byte value = board[i][j];
                manDist += distance(i, j, value);
            }
        }
        return manDist;
    }


    /**
     * This method changes the parent
     *
     * @param parental
     *            is the new parent
     */
    public void setParent(PuzzleState parental)
    {
        parent = parental;
    }


    /**
     * Returns the parent of the state
     *
     * @return is the parent
     */
    public PuzzleState getParent()
    {
        return parent;
    }


    /**
     * Prints out the x,y location of the empty location
     *
     * @return is the string format of that
     */
    public String printEmpty()
    {
        int x = emptyLocation.get(0);
        int y = emptyLocation.get(1);
        String format = "(" + (x + 1) + "," + (y + 1) + ")";
        return format;
    }


    // ----------------------------------------------------------
    /**
     * Prints out the board in grid format for easy checking of the board
     */
    public void printGrid()
    {
        for (int i = 0; i < 4; i++)
        {
            String line = "";
            for (int j = 0; j < 4; j++)
            {
                if (board[i][j] < 10)
                {
                    line = line + " " + board[i][j];
                }
                else if (board[i][j] == 16)
                {
                    line = line + "  ";
                }
                else
                {
                    line = line + board[i][j];
                }
                if (j < 3)
                {
                    line = line + " ";
                }
            }
            System.out.println(line);
        }
    }


    /** --------------------------Private Methods---------------------------- **/

    /**
     * This copies over the same exact board state.
     *
     * @param toCopy
     *            is the state to copy
     */
    private void copyState(PuzzleState toCopy)
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                board[i][j] = toCopy.get(i, j);
            }
        }
    }


    /**
     * returns an arraylist of the x and y location of the empty location
     *
     * @return is the arraylist containing location
     * @param current
     *            is the puzzleboard to get the empty slot from
     */
    public ArrayList<Integer> getEmpty(PuzzleState current)
    {
        return emptyLocation;
    }

    /**
     * updates the location of the empty location array
     */
    private void updateEmpty(int xLoc, int yLoc)
    {
        emptyLocation.clear();
        emptyLocation.add(xLoc);
        emptyLocation.add(yLoc);
    }

    /**
     * swaps the empty slot with the x and y location of the board
     */
    private void swapBytes(int x, int y)
    {
        ArrayList<Integer> temp = getEmpty(this);
        board[temp.get(0)][temp.get(1)] = board[x][y];
        board[x][y] = 16;
    }


    /**
     * figures out the distance for the manhattan function. It takes a byte
     * value and a slot location and computes the distance
     */
    private int distance(int x, int y, byte value)
    {
        int distance = 0;

        if (value == 1)
        {
            distance += Math.abs(x) + Math.abs(y);
        }
        else if (value == 2)
        {
            distance += Math.abs(x) + Math.abs(y - 1);
        }
        else if (value == 3)
        {
            distance += Math.abs(x) + Math.abs(y - 2);
        }
        else if (value == 4)
        {
            distance += Math.abs(x) + Math.abs(y - 3);
        }
        else if (value == 5)
        {
            distance += Math.abs(x - 1) + Math.abs(y);
        }
        else if (value == 6)
        {
            distance += Math.abs(x - 1) + Math.abs(y - 1);
        }
        else if (value == 7)
        {
            distance += Math.abs(x - 1) + Math.abs(y - 2);
        }
        else if (value == 8)
        {
            distance += Math.abs(x - 1) + Math.abs(y - 3);
        }
        else if (value == 9)
        {
            distance += Math.abs(x - 2) + Math.abs(y);
        }
        else if (value == 10)
        {
            distance += Math.abs(x - 2) + Math.abs(y - 1);
        }
        else if (value == 11)
        {
            distance += Math.abs(x - 2) + Math.abs(y - 2);
        }
        else if (value == 12)
        {
            distance += Math.abs(x - 2) + Math.abs(y - 3);
        }
        else if (value == 13)
        {
            distance += Math.abs(x - 3) + Math.abs(y);
        }
        else if (value == 14)
        {
            distance += Math.abs(x - 3) + Math.abs(y - 1);
        }
        else if (value == 15)
        {
            distance += Math.abs(x - 3) + Math.abs(y - 2);
        }
        else
        // value is 16
        {
            distance = distance + 0;
        }
        return distance;
    }


    // ----------------------------------------------------------
    /**
     * Method to get the string format difference of the states
     *
     * @param dif
     *            is the state to compare against
     * @return is the string format of the difference
     */
    public String difference(PuzzleState dif)
    {
        String move = "";
        boolean up = false, right = false, left = false, down = false;
        for (int j = 0; j < moves().size(); j++)
        {
            int direction = moves().get(j);
            if (dif.equals(move(direction)))
            {
                if (direction == 1)
                {
                    up = true;
                }
                if (direction == 2)
                {
                    right = true;
                }
                if (direction == 3)
                {
                    down = true;
                }
                if (direction == 4)
                {
                    left = true;
                }
            }

        }
        // found which move equals the dif
        if (up)
        {
            move = "up";
        }
        if (right)
        {
            move = "right";
        }
        if (down)
        {
            move = "down";
        }
        if (left)
        {
            move = "left";
        }
        byte value = dif.get(emptyLocation.get(0), emptyLocation.get(1));
        move = move + " " + value;
        return move;
    }
}
