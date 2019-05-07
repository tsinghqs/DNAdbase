import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Parser executes each command input.
 * 
 * @author tsingh
 * @version 3.17.2019
 *
 */
public class Parser {
    /**
     * command read from input file.
     */
    private String input;
    /**
     * parseBoy executes each command.
     */
    Scanner scan;
    HashTable hashboy;
    MemoryManager memboy;


    /**
     * Constructor for parser
     * 
     * @param tree
     *            name of BST
     * @param terms
     *            string commands
     * @throws FileNotFoundException if file not found
     */
    public Parser(File fp, HashTable hashing, MemoryManager meming) throws FileNotFoundException {
        scan = new Scanner(fp);
        hashboy = hashing;
        memboy = meming;
    }


    /**
     * Parses input for commands.
     * @throws IOException if there is IO
     */
    public void parseString() throws IOException {
        while (this.scan.hasNextLine())
        {
            String line = this.scan.nextLine();
            CommandExecution commando = new CommandExecution(hashboy, memboy);
            String[] commands = line.trim().split("\\s+");
                if (commands[0].equals("insert")) {
                    String seqId = commands[1];
                    int seqLength = Integer.parseInt(commands[2]);
                    line = this.scan.nextLine();
                    commando.insert(seqId, line);
                    System.out.println("Sequence ID: " + seqId);
                    System.out.println("Sequence" + line);
                    
                }/**
                else if (commands[0].equals("remove")) {
                    System.out.println(commands[0]);
                }
                else if (commands[0].equals("search")) {
                    System.out.println(commands[0]);
                }
                else if (commands[0].equals("print")) {
                    System.out.println(commands[0]);
                }**/
            
        }

    }
}
