package duke.command;

public abstract class Command {
    /**
     * Checks if the command is meant to delete the task(s)
     *
     * @param command The input command
     * @return Returns true if the command starts with 'delete' no matter it is uppercase or lowercase or mixed, false otherwise
     */
    public static boolean isCommandDelete(String command) {
        return command.toLowerCase().startsWith("delete");
    }

    /**
     * Checks if the command is meantto delete all the tasks stored
     *
     * @param word The second word in the command
     * @return Returns true if the second word in the command is equivalent to 'all' or '*' no matter it is uppercaseor lowercase or mixed, false otherwise
     */
    public static boolean isCommandDeleteAll(String word) {
        return word.equalsIgnoreCase("all") || word.equals("*");
    }
    /**
     * Checks if the command is meant to see the help menu
     *
     * @param command The input command
     * @return Returns true if the command is equivalent to 'help' or 'view -h' no matter it is uppercase or lowercase or mixed, false otherwise
     */
    public static boolean isCommandHelp(String command) {
        return command.equalsIgnoreCase("help") || command.equalsIgnoreCase("view -h");
    }

    /**
     * Checks if the command is meant to add task to the to-do list
     *
     * @param word The array of words that compose the input command
     * @return Returns true if the command contains keywords "deadline", "event" or "todo"
     */
    public static boolean isCommandAddTask(String word) {
        return word.equalsIgnoreCase("DEADLINE") || word.equalsIgnoreCase("EVENT") || word.equalsIgnoreCase("TODO");
    }

    /**
     * Checks if the input command starts with "done"
     *
     * @param firstWord The first word in the command
     * @return Returns true if the first word is equivalent to "done" no matter it is uppercase or lowercase or mixed, false otherwise
     */
    public static boolean isCommandDone(String firstWord) {
        return firstWord.equalsIgnoreCase("DONE");
    }

    /**
     * Checks if the input command is equivalent to 'list' or 'ls'
     *
     * @param command The input command
     * @return Returns true if the command is equivalent to 'list' or 'ls' no matter it is uppercase or lowercase or mixed, false otherwise
     */
    public static boolean isCommandList(String command) {
        return command.equalsIgnoreCase("LIST") || command.equalsIgnoreCase("LS");
    }

    /**
     * Checks if the input command is equivalent to 'view -p'
     *
     * @param command The input command
     * @return Returns true if the command is equivalent to 'view -p' no matter it is uppercase or lowercase or mixed, false otherwise
     */
    public static boolean isCommandViewPersonality(String command) {
        return command.equalsIgnoreCase("VIEW -P");
    }

    /**
     * Checks if the input command is empty
     *
     * @param command The input command
     * @return Returns true if the command is empty, false otherwise
     */
    public static boolean isCommandEmpty(String command) {
        return command.isEmpty();
    }

    /**
     * Checks if the input command is equivalent to 'bye' or 'exit'
     *
     * @param command The input command
     * @return Returns true if the command is equivalent to 'bye' or 'exit' no matter it is uppercase or lowercase or mixed, false otherwise
     */
    public static boolean isCommandExit(String command) {
        return command.equalsIgnoreCase("EXIT") || command.equalsIgnoreCase("BYE");
    }
}
