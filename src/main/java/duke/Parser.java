package duke;


/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses user input into the respective important fields of the command for execution.
     *
     * @param fullCommand The entire string command entered in by the user.
     * @return The parsed command that can be fed into the TaskList functions.
     * @throws ArrayIndexOutOfBoundsException If the command is in the wrong format.
     */
    public static String[] parse(String fullCommand) throws ArrayIndexOutOfBoundsException {
        String[] words = fullCommand.split(" ", 2);
        String commandWord = words[0].toLowerCase();

        switch (commandWord) {
        case "list":
        case "bye":
        case "help":
            return words;
        case "done":
        case "delete":
            String[] parsedFullCommand = fullCommand.split(" ");
            return parsedFullCommand;
        case "todo":
            String description = words[1];
            return new String[]{commandWord, description};
        case "deadline":
            description = words[1].split(" /by ")[0];
            String by = words[1].split(" /by ")[1];
            return new String[]{commandWord, description, by};
        case "event":
            description = words[1].split(" /at ")[0];
            String at = words[1].split(" /at ")[1];
            return new String[]{commandWord, description, at};
        case "find":
            String keyword = words[1];
            return new String[]{commandWord, keyword};
        default:
            return new String[]{"wrong", "input"};
        }
    }
}
