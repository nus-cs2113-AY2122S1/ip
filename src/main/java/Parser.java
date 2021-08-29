
public class Parser {

    private String command;
    private String arguments;

    /**
     * Parse input and separates the command from the arguments
     *
     * @param command the command line input to be parsed
     */
    public Parser(String command) {
        command = command.strip();
        if (command.isEmpty()) {
            this.command = null;
            arguments = null;
        } else if (!command.contains(" ")) {
            this.command = command;
            arguments = null;
        } else {
            int commandIndex = command.indexOf(" ");
            this.command = command.substring(0, commandIndex).strip();
            this.arguments = command.substring(commandIndex).strip();
        }
    }

    /**
     * Further splits the arguments based on a delimiter
     * gives the first half the split string
     *
     * @param delimiter the delimiter to split the arguments by
     * @return first half the string split delimiters
     * @throws ArrayIndexOutOfBoundsException if the delimiter is not found in the string
     */
    public String getArgsBeforeString(String delimiter) throws ArrayIndexOutOfBoundsException {
        int locationOfArgument = arguments.indexOf(delimiter);
        if (locationOfArgument == -1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arguments.substring(0, locationOfArgument).trim();
    }

    /**
     * Further splits the arguments based on a delimiter
     * gives the second half the split string
     *
     * @param delimiter the delimiter to split the arguments by
     * @return second half the string split delimiters
     * @throws ArrayIndexOutOfBoundsException if the delimiter is not found in the string
     */
    public String getArgsAfterString(String delimiter) throws ArrayIndexOutOfBoundsException {
        int locationOfArgument = arguments.indexOf(delimiter);
        if (locationOfArgument == -1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arguments.substring(locationOfArgument + delimiter.length()).trim();
    }

    /**
     * Attempts to parse the arguments as an index for TaskManager
     *
     * @return the index to item in TaskManager
     * @throws NumberFormatException if the input is not a number
     */
    public int getArgsAsIndex() throws NumberFormatException {
        return Integer.parseInt(arguments) - 1;
    }

    public String getCommand() {
        return command;
    }

    public String getArguments() {
        return arguments;
    }
}
