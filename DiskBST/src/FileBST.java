import java.io.RandomAccessFile;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


// -------------------------------------------------------------------------
/**
 * This is the FileBST class. It takes a single argument at first. It is the
 * file path and then reads input from system in so that is will begin making a
 * bst on disk.
 *
 * @author Jack
 * @version May 1, 2014
 */
public class FileBST
{
    // Fields
    private static Database db;


    // ----------------------------------------------------------
    /**
     * This is the main method that takes care of reading and writing
     * to the disk
     *
     * @param args
     *            is the arguments to run the program with
     * @throws IOException
     *             is there is a problem reading or writing to file
     */
    public static void main(String[] args)
        throws IOException
    {
        String filePath = args[0];
        boolean isFile = new File(filePath).isFile();
        if (isFile)
        {
            System.out.println("database existing " + filePath);
        }
        else
        {
            // format the first 5 longs to 0
            RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
            raf.writeLong(0);
            raf.writeLong(0);
            raf.close();
            System.out.println("database new " + filePath);
        }
        db = new Database(filePath);
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext())
        {
            String command = scan.next();
            if (command.equals("insert"))
            {
                float key = scan.nextFloat();
                int data = scan.nextInt();
                db.runInsert(key, data);
            }
            else if (command.equals("find"))
            {
                float key = scan.nextFloat();
                db.runFind(key);
            }
            else if (command.equals("delete"))
            {
                float key = scan.nextFloat();
                db.runDelete(key);
            }
            else if (command.equals("tree"))
            {
                db.runTree();
            }
            else if (command.equals("freelist"))
            {
                db.runFreeList();
            }
            else
            {
                scan.nextLine();
            }
        }
        scan.close();
    }

}
