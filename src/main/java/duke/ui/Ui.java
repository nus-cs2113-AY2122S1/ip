package duke.ui;

public class Ui {
    private static final String SEPARATOR_LINE = "-----------------------------------------------";

    /**
     * Prints a horizontal separator line.
     */
    public void printLine() {
        System.out.println(SEPARATOR_LINE);
    }

    /**
     * Prints a welcome message to greet the user.
     */
    public void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Prints a goodbye message.
     */
    public void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints an error message to inform the user that the command entered was invalid.
     * Shows a list of available commands.
     */
    public void printErrorInvalidCommand() {
        printLine();
        System.out.println("OOPS! I'm sorry, but I don't know what that means! :(");
        System.out.println("Available commands: deadline, todo, event, done, list, delete, find, bye");
        printLine();
    }

    /**
     * Prints an error message to inform the user that the arguments of the command were empty.
     * Shows the proper format of the commands.
     */
    public void printErrorEmptyCommandArg() {
        printLine();
        System.out.println("OOPS! The description of the command word cannot be empty! " +
                "Please follow this format:");
        System.out.println("deadline <your task here> /by <your deadline time>");
        System.out.println("event <your task here> /at <your event time period>");
        System.out.println("todo <your task here>");
        System.out.println("delete <task number>");
        System.out.println("find <keyword>");
        printLine();
    }

    /**
     * Prints an error message to inform the user that the command has an invalid or missing separator.
     * Shows the proper format of the commands.
     */
    public void printErrorInvalidSeparator() {
        printLine();
        System.out.println("OOPS! The deadline/event description must be separated from " +
                "the time using '/by' or '/at'. Please follow this format:");
        System.out.println("deadline <your task here> /by <your deadline time>");
        System.out.println("event <your task here> /at <your event time period>");
        printLine();
    }

    /**
     * Prints an error message to inform the user that the entered task index is out-of-bounds.
     */
    public void printErrorInvalidTaskIndex() {
        printLine();
        System.out.println("OOPS! That task does not exist!");
        printLine();
    }
}
