import java.util.ArrayList;


// -------------------------------------------------------------------------
/**
 * This class is the database that setups up the binary trees and deals with the
 * parsed data and calls functions on the trees
 *
 * @author Jack Cobb (jack3)
 * @version Feb 12, 2014
 */
public class Database
{
    // Fields
    private BinarySearchTree<City> pop;
    private BinarySearchTree<City> name;


    /**
     * this is a constructor for the database class
     */
    public Database()
    {
        pop = new BinarySearchTree<City>(new PopComp());
        name = new BinarySearchTree<City>(new NameComp());
    }


    // this class parses the input from the main class and tells the bst's
    // what to do

    /**
     * this is the find k method
     *
     * @param k
     *            is the kth node to search for in the tree
     * @param tree
     *            is which tree to look in
     */
    public void findK(int k, int tree)
    {
        if (tree == 1)
        {
            pop.findKth(k);
        }
        else
        {
            name.findKth(k);
        }
    }


    /**
     * this is the find range. It utilizes a modified find, but for the entire
     * range
     *
     * @param tree
     *            is which tree to do
     * @param start
     *            is the starting value
     * @param stop
     *            is the stopping value
     */
    public void findRange(int tree, String start, String stop)
    {
        ArrayList<BSTNode<City>> range = new ArrayList<BSTNode<City>>();
        if (tree == 1)
        {
            int startVal = Integer.parseInt(start);
            int stopVal = Integer.parseInt(stop);
            range = pop.findRange(
                pop.root(),
                new City(startVal, "", "empty"),
                new City(stopVal, "", "empty"), range);
        }
        else
        {
            range = name.findRange(name.root(),
                new City(0, start, "empty"),
                    new City(0, stop, "empty"), range);
        }
        if (range != null && range.size() > 0)
        {
            for (int i = 0; i < range.size(); i++)
            {
                System.out.println(range.get(i).getElement().toString());
            }
        }
        else
        {
            System.out.println("Not found");
        }
    }


    // delete deletes everything in that tree with the value, then finds them
    // from the other tree also (make sure they are the same cities)
    /**
     * This methods sets both trees to be empty
     */
    public void makeNull()
    {
        pop.makeNull();
        name.makeNull();
    }


    /**
     * this is a method that finds elements from a database
     *
     * @param a
     *            is he city population to find
     */
    public void findPop(int a)
    {
        ArrayList<BSTNode<City>> list = new ArrayList<BSTNode<City>>();
        BSTNode<City> find = new BSTNode<City>(new City(a, "", ""));
        list = pop.findCities(pop.root(), find, list);
        if (list.size() == 0)
        {
            System.out.println("Not found");
        }
        else
        {
            for (int i = 0; i < list.size(); i++)
            {
                String output = list.get(i).getElement().toString();
                System.out.println(output);
            }
        }


    }


    /**
     * This searches for the specific names in the name tree
     *
     * @param cityName
     *            is the city name to find
     */
    public void findName(String cityName)
    {

        ArrayList<BSTNode<City>> list = new ArrayList<BSTNode<City>>();
        BSTNode<City> find = new BSTNode<City>(new City(0, cityName, ""));
        list = name.findCities(name.root(), find, list);
        if (list.size() == 0)
        {
            System.out.println("Not found");
        }
        else
        {
            for (int i = 0; i < list.size(); i++)
            {
                String output = list.get(i).getElement().toString();
                System.out.println(output);
            }
        }
    }


    /**
     * the tree method that prints out the tree formated in strings
     *
     * @param a
     *            is what tree to print. Tree pop is 1, everything else is name
     *            tree
     */
    public void tree(int a)
    {
        if (a == 1)
        {
            pop.tree(pop.root(), 0);
        }
        else
        {
            name.tree(name.root(), 0);
        }
    }


    /**
     * the sort method that prints out the tree formated in strings
     *
     * @param a
     *            is what tree to print. Tree pop is 1, everything else is name
     *            tree
     */
    public void sort(int a)
    {
        if (a == 1)
        {
            pop.sort(pop.root());
        }
        else
        {
            name.sort(name.root());
        }
    }


    /**
     * this is the add command for the database. It will insert the new city
     * into both trees;
     *
     * @param nameCity
     *            is the name of the new city
     * @param popCity
     *            is the population of the new city
     * @param payload
     *            is the payload of the city
     */
    public void add(String nameCity, int popCity, String payload)
    {
        City toAdd = new City(popCity, nameCity, payload);
        pop.insert(toAdd);
        name.insert(toAdd);
    }


    /**
     * this is the delete command that calls remove from the binary tree and
     * removes all the specific nodes
     *
     * @param tree
     *            is what type of tree to delete from
     * @param find
     *            is the term to look for in that tree
     */
    public void delete(int tree, String find)
    {
        if (tree == 1)
        {
            int toFind = Integer.parseInt(find);
            ArrayList<BSTNode<City>> list = new ArrayList<BSTNode<City>>();
            BSTNode<City> lookFor = new BSTNode<City>(new City(toFind, "", ""));
            list = pop.findCities(pop.root(), lookFor, list);
            if (list.size() == 0)
            {
                System.out.println("Not found");
            }
            else
            {
                for (int i = 0; i < list.size(); i++)
                {
                    pop.delete(pop.root(), list.get(i));
                    name.delete(name.root(), list.get(i));
                }
            }
        }
        else
        {
            ArrayList<BSTNode<City>> list = new ArrayList<BSTNode<City>>();
            BSTNode<City> lookFor = new BSTNode<City>(new City(0, find, ""));
            list = name.findCities(name.root(), lookFor, list);
            if (list.size() == 0)
            {
                System.out.println("Not found");
            }
            else
            {
                for (int i = 0; i < list.size(); i++)
                {
                    name.delete(name.root(), list.get(i));
                    pop.delete(pop.root(), list.get(i));
                }
            }
        }
    }

}
