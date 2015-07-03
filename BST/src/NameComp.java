import java.util.Comparator;

// -------------------------------------------------------------------------
/**
 * This class uses Comparator and allows for cities to be compared by the city
 * names
 *
 * @author Jack Cobb (jack3)
 * @version Feb 11, 2014
 */
public class NameComp
    implements Comparator<City>
{

    /**
     * Provides a method for comparing between names of the cities.
     *
     * @param num1
     *            is the first city to compare
     * @param num2
     *            is the second city to compare
     * @return is the difference between the two
     */
    public int compare(City num1, City num2)
    {
        return (num1.getCityName().compareTo(num2.getCityName()));
    }

}
