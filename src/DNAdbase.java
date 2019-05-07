import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException
    {
        //read file as an argument
        String fileName = args[0];
        File file = new File(fileName);   
        RandomAccessFile memFile = new RandomAccessFile(args[2], "rw");
        MemoryManager mem = new MemoryManager(memFile);
        HashTable hash = new HashTable(Integer.parseInt(args[1]), mem);

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