import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * This is class responsible for running the project properly. 
 * @author tsinghqs
 * @version 2018.02.19
 *
 */
public class DNAdbase 
{
    /**
     * This is the main class and function that runs the project
     * 
     * @param args a string value to run the project
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        //read file as an argument
        String fileName = args[0];
        File file = new File(fileName);   
        File memFile = new File(args[2]);
        HashTable hash = new HashTable(Integer.parseInt(args[1]));
        MemoryManager mem = new MemoryManager(memFile);
        
        Parser parse = new Parser(file, hash, mem);
        parse.parseString();
        for (int i = 0; i < 512; i++)
        {
            if (hash.getRecords()[i] != null)
            {
                System.out.println("Not Null");
            }
        }
       
    }
}