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
 * This is the bst class and is the class that deals with receiving the input
 * and understanding the input then parses it to Database
 *
 * @author Jack Cobb (jack3)
 * @version Feb 11, 2014
 */
public class bst
{
    // Fields
    private static Database database;


    // ----------------------------------------------------------
    /**
     * This is the main method of the bst. It takes input and creates a database
     * with two different bsts that organize cities based on their population or
     * name.
     *
     * @param args
     *            is the argument, however this class functions without any
     *            arguments, so it can take null
     */
    public static void main(String[] args)
    {
        database = new Database();
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
                String payload = scan.next();
                System.out.println(command + " " + cityname + " " + popString
                    + " " + payload);
                int pop = Integer.parseInt(popString);
                database.add(cityname, pop, payload);
            }
            else if (command.equals("find"))
            {
                String field = scan.next();
                String toFind = scan.next();
                System.out.println(command + " " + field + " " + toFind);
                if (field.equals("name"))
                {
                    database.findName(toFind);
                }
                else //if (field.equals("population"))
                {
                    database.findPop(Integer.parseInt(toFind));
                }
            }
            else if (command.equals("findKth"))
            {
                String field = scan.next();
                int k = Integer.parseInt(scan.next());
                System.out.println(command + " " + field + " " + k);
                if (field.equals("name"))
                {
                    database.findK(k, 0);
                }
                else //if (field.equals("population"))
                {
                    database.findK(k, 1);
                }
            }
            else if (command.equals("findRange"))
            {
                String field = scan.next();
                String start = scan.next();
                String stop = scan.next();
                System.out.println(command + " " + field + " " + start + " "
                    + stop);
                if (field.equals("name"))
                {
                    database.findRange(0, start, stop);
                }
                else //if (field.equals("population"))
                {
                    database.findRange(1, start, stop);
                }
            }
            else if (command.equals("delete"))
            {
                String field = scan.next();
                String toDelete = scan.next();
                System.out.println(command + " " + field + " " + toDelete);
                if (field.equals("name"))
                {
                    database.delete(0, toDelete);
                }
                else //if (field.equals("population"))
                {
                    database.delete(1, toDelete);
                }
            }
            else if (command.equals("sort"))
            {
                String field = scan.next();
                System.out.println(command + " " + field);
                if (field.equals("name"))
                {
                    database.sort(0);
                }
                else //if (field.equals("population"))
                {
                    database.sort(1);
                }
            }
            else if (command.equals("tree"))
            {
                String field = scan.next();
                System.out.println(command + " " + field);
                if (field.equals("name"))
                {
                    database.tree(0);
                }
                else //if (field.equals("population"))
                {
                    database.tree(1);
                }
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
