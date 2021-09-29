package duke.ui;

public class Ui {
    private static final String SEPARATOR_LINE = "-----------------------------------------------";

    public void printLine() {
        System.out.println(SEPARATOR_LINE);
    }

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

    public void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public void printErrorInvalidCommand() {
        printLine();
        System.out.println("OOPS! I'm sorry, but I don't know what that means! :(");
        System.out.println("Available commands: deadline, todo, event, done, list, delete, find, bye");
        printLine();
    }

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

    public void printErrorInvalidSeparator() {
        printLine();
        System.out.println("OOPS! The deadline/event description must be separated from " +
                "the time using '/by' or '/at'. Please follow this format:");
        System.out.println("deadline <your task here> /by <your deadline time>");
        System.out.println("event <your task here> /at <your event time period>");
        printLine();
    }

    public void printErrorInvalidTaskIndex() {
        printLine();
        System.out.println("OOPS! That task does not exist!");
        printLine();
    }
}
