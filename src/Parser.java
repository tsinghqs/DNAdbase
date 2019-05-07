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
    private MemoryManager parseBoy;


    /**
     * Constructor for parser
     * 
     * @param tree
     *            name of BST
     * @param terms
     *            string commands
     */
    public Parser(MemoryManager tree, String terms) {
        parseBoy = tree;
        input = terms;
    }


    /**
     * Parses input for commands.
     */
    public void parseString() {
        String[] commands = input.trim().split("\\s+");
        if (commands.length == 2) {
            if (commands[0].equals("insert")) {
                parseBoy.insert(commands[1]);
            }
            else if (commands[0].equals("remove")) {
                parseBoy.remove(commands[1]);
            }
            else if (commands[0].equals("search")) {
                parseBoy.search(commands[1]);
            }
            else if (commands[0].equals("print") && 
                commands[1].equals("lengths")) {
                parseBoy.printLengths();
            }
            else if (commands[0].equals("print") && 
                commands[1].equals("stats")) {
                parseBoy.printStats();
            }
        }
        else if (commands.length == 1 
            && commands[0].equals("print")) {
            parseBoy.print();
        }

    }
}
