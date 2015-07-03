// -------------------------------------------------------------------------
/**
 * This is a class for a city. It includes all the information that is relevant
 * to a city, like populations, city name, and payload
 *
 * @author Jack Cobb (jack3)
 * @version Feb 11, 2014
 */
public class City
{

    // Fields
    private int    population;
    private String name;
    private String payload;


    /**
     * This is the constructor that takes three variables that are inherent to
     * what constitutes a city
     *
     * @param pop
     *            is the population
     * @param cityName
     *            is the name of the city
     * @param payLoad
     *            is the payload the record carries
     */
    public City(int pop, String cityName, String payLoad)
    {
        population = pop;
        name = cityName;
        payload = payLoad;
    }


    /**
     * A method to get the City Name
     *
     * @return is the city's name
     */
    public String getCityName()
    {
        return name;
    }


    /**
     * Method to get the payload
     *
     * @return is the payload
     */
    public String getPayLoad()
    {
        return payload;
    }


    /**
     * A method to get the city's population
     *
     * @return the city population
     */
    public int getPopulation()
    {
        return population;
    }


    /**
     * To String method to return out a string detailing the city
     *
     * @return is the whole string for the city
     */
    public String toString()
    {

        return name + " " + population + " " + payload;
    }

    /**
     * This returns just the important info
     * @return just the important info in a string
     */
    public String info()
    {
        if (name == null)
        {
            return Integer.toString(population);
        }
        else if (population == 0)
        {
            return name;
        }
        else
        {
            String info = name + " " + Integer.toString(population);
            return info;
        }
    }

    /**
     * setter for population
     * @param newPop is the new city population
     */
    public void setPop(int newPop)
    {
        population = newPop;
    }

    /**
     * setter for name
     * @param newName is the new city name
     */
    public void setName(String newName)
    {
        name = newName;
    }

    /**
     * setter for the payload
     * @param newPayload is the new payload
     */
    public void setPayload(String newPayload)
    {
        payload = newPayload;
    }
}
