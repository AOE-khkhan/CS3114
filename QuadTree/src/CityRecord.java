
// -------------------------------------------------------------------------
/**
 *  This is the city record class. The payload now matters because it represents
 *  the location of the city in x and y
 *
 *  @author Jack Cobb jack3
 *  @version Feb 28, 2014
 */

public class CityRecord implements CoordExtractor
{
    // Fields
    private int    population;
    private String name;
    private int    x;
    private int    y;


    /**
     * This is the constructor that takes three variables that are inherent to
     * what constitutes a city
     *
     * @param pop
     *            is the population
     * @param cityName
     *            is the name of the city
     * @param xLoc
     *            is the x location
     * @param yLoc
     *            is the y location
     */
    public CityRecord(String cityName, int pop, int xLoc, int yLoc)
    {
        population = pop;
        name = cityName;
        x = xLoc;
        y = yLoc;
    }

    /**
     * Method to get the x location
     *
     * @return is the x location
     */
    public int getX()
    {
        return x;
    }


    /**
     * Method to get the y location
     *
     * @return is the y location
     */
    public int getY()
    {
        return y;
    }

    /**
     * To String method to return out a string detailing the city
     *
     * @return is the whole string for the city
     */
    public String toString()
    {
        return name + " " + population + " " + "(" + x + ", " + y + ")";
    }

    /**
     * getter for the name
     * @return is the cityrecord name
     */
    public String getCityName()
    {
        return name;
    }

    // ----------------------------------------------------------
    /**
     * getter for the population
     * @return is the population
     */
    public int getPopulation()
    {
        return population;
    }

}
