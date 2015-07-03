import java.util.ArrayList;


// -------------------------------------------------------------------------
/**
 *  This is a hashtable that can hold elements and dynmically allocates itself
 *  as it gets to be about 70 percent full.
 *
 *  @param <T> is the type of hashtable
 *
 *  @author Jack Cobb <jack3>
 *  @version Apr 5, 2014
 */
public class HashTable<T>
{

    //Fields
    private Object[] table;
    private int elements;
    private ArrayList<T> hashed;

    /**
     * Create a new HashTable object.
     * @param size is how big to start the table size at
     */
    public HashTable(int size)
    {
        table = new Object[size];
        elements = 0;
        hashed = new ArrayList<T>();
    }

    /**
     * This method is used to insert elements based on their hash code
     * @param element is what to insert
     * @return is whether or not it worked; true if inserts, false if already
     * hashed
     */
    public boolean insert(T element)
    {
        int hash = element.hashCode();
        int place = hash % table.length;
        boolean worked = place(place, element, table);
        float percent = (elements / table.length) * 100;
        if (worked && percent >= 65)
        {
            upgrade();
        }
        return worked;

    }

    /**
     * This returns the hashed table with elements in it
     * @return is the table
     */
    public Object[] getTable()
    {
        return table;
    }

    /**
     * This method returns the number of elements that have been hashed.
     * Mainly used for testing
     * @return is the number of elements
     */
    public int getElements()
    {
        return elements;
    }

    /**---------------------------Private Methods-------------------------**/
    /**
     * helper method to double the size of the array used for hashing then
     * rehash all elements present in the queue
     */
    private void upgrade()
    {
        int size = 2 * table.length;
        Object[] doubled = new Object[size];
        table = doubled;
        elements = 0;
        for (int j = 0; j < hashed.size(); j++)
        {
            T element = hashed.get(j);
            int hash = element.hashCode();
            int place = hash % table.length;
            place(place, element, doubled);
        }
    }

    /**
     * helper method used to actually insert into the arrray based on its hash
     * code
     */
    @SuppressWarnings("unchecked")
    private boolean place(int location, Object element, Object[] tableNew)
    {
        int offset = 0;
        boolean done = false;
        boolean found = false;
        while (!done && !found)
        {
            int newHome = (location + offset) % tableNew.length;
            if (tableNew[newHome] != null)
            {
                if (tableNew[newHome].equals(element))
                {
                    found = true;
                }
                else
                {
                    offset++;
                }
            }
            else    //empty slot
            {
                tableNew[newHome] = element;
                elements++;
                hashed.add((T)element);
                done = true;
            }
        }
        return done;
    }


}
