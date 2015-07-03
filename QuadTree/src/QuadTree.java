import java.util.Scanner;
// -------------------------------------------------------------------------
// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than the instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * This is the quadtree class that takes input from the command line and relays
 * it to the database class. This quadtree is based on a node centric approach
 * to a quadtree that utilizes generics and a flyweight.
 *
 * @author Jack Cobb jack3
 * @version Mar 1, 2014
 */

public class QuadTree
{

    // ----------------------------------------------------------
    // Fields
    private static Database<CityRecord> database;


    // ----------------------------------------------------------
    /**
     * This is the main method that handles the input from the command line and
     * parses it to the database class
     *
     * @param args
     *            is the argument, however this class functions without any
     *            arguments, so it can take null
     */
    public static void main(String[] args)
    {
        database = new Database<CityRecord>();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext())
        {
            String command = scan.next();
            if (command.equals("#"))
            {
                scan.nextLine();
            }
            else if (command.equals("insert"))
            {
                String cityname = scan.next();
                String popString = scan.next();
                String x = scan.next();
                String y = scan.next();
                System.out.println(command + " " + cityname + " " + popString
                    + " " + x + " " + y);
                int pop = Integer.parseInt(popString);
                database.add(cityname, pop, x, y);
            }
            else if (command.equals("find"))
            {
                String field = scan.next();
                String x = scan.next();
                String y = scan.next();
                System.out.println(command + " " + field + " " + x + " " + y);
                database
                    .handleFind(Integer.parseInt(x), Integer.parseInt(y));
            }
            else if (command.equals("rfind"))
            {
                String x = scan.next();
                String y = scan.next();
                String w = scan.next();
                String h = scan.next();
                int xmin = Integer.parseInt(x);
                int xmax = xmin + Integer.parseInt(w);
                int ymin = Integer.parseInt(y);
                int ymax = ymin + Integer.parseInt(h);
                System.out.println("rfind " + x + " " + y + " " + w + " " + h);
                database.handleRFind(xmin, xmax, ymin, ymax);
            }
            //findRange would also be implemented if my bst functioned
            //correctly
            else if (command.equals("delete"))
            {
                String field = scan.next();
                String deleteX = scan.next();
                String deleteY = scan.next();
                System.out.println(command + " " + field + " " + deleteX + " "
                    + deleteY);
                // call remove on db
                database.handleDeleteLoc(
                    Integer.parseInt(deleteX),
                    Integer.parseInt(deleteY));
            }
            else if (command.equals("tree"))
            {
                String field = scan.next();
                System.out.println(command + " " + field);
                database.handleTree();
            }
            else
            {
                System.out.println(command);
                database.makeNull();
            }
        }
        scan.close();

    }
}
