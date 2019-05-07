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
    private Scanner scan;


    /**
     * Constructor for parser
     * 
     * @param tree
     *            name of BST
     * @param terms
     *            string commands
     */
    public Parser(Scanner scan) {
        this.scan = scan;
    }


    /**
     * Parses input for commands.
     */
    public void parseString() {
        while (scan.hasNextLine())
        {
        String line = scan.nextLine();
        String[] commands = input.trim().split("\\s+");
        if (commands.length == 2) {
            if (commands[0].equals("insert")) {
                String seqId = commands[1];
                int seqLength = Integer.parseInt(commands[2]);
                line = scan.nextLine();
                System.out.println(line);
            }
            else if (commands[0].equals("remove")) {
                System.out.println(commands[0]);
            }
            else if (commands[0].equals("search")) {
                System.out.println(commands[0]);
            }
            else if (commands[0].equals("print"))
            {
                System.out.println(commands[0]);
            }
        }
        }

    }
}
