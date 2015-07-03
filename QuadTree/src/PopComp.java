import java.util.Comparator;

// -------------------------------------------------------------------------
/**
 * This popCompare class offers a way to compare two cities against each other
 * by population
 *
 * @author Jack Cobb (jack3)
 * @version Feb 11, 2014
 */
public class PopComp
    implements Comparator<CityRecord>
{

    /**
     * This method compares the city names
     *
     * @param num1
     *            is the first city to compare
     * @param num2
     *            is the second city to compare against
     * @return is the result of the difference
     */
    public int compare(CityRecord num1, CityRecord num2)
    {
        int result = num1.getPopulation() - num2.getPopulation();
        return result;
    }

}
