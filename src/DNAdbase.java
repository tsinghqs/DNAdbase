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
        Scanner sc = new Scanner(file);
        
        Parser parse = new Parser(sc);
        parse.parseString();
       
    }
}