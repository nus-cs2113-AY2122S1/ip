package duke;

/**
 * Prints the interface and error messages
 */
public class Ui {
    public static void startup(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("To see what I can do, type 'help'.");
    }

    public static void end() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void emptyListError() {
        System.out.println("☹ OOPS!!! There are no items in the list.");
    }

    public static void noItemDoneError() {
        System.out.println("☹ OOPS!!! There is no such item to be done.");
    }

    public static void noIndexError() {
        System.out.println("☹ OOPS!!! Please input the index of the item.");
    }

    public static void noItemError() {
        System.out.println("☹ OOPS!!! There is no such item.");
    }

    public static void unknownCommandError() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void emptyDescriptionError() {
        System.out.println("☹ OOPS!!! The description cannot be empty.");
    }

    public static void emptyTimeError() {
        System.out.println("☹ OOPS!!! The time cannot be empty.");
    }

    public static void unknownError() {
        System.out.println("☹ OOPS!!! There is an unknown error.");
    }

    public static void dateFormatError() {
        System.out.println("☹ OOPS!!! I don't recognise the date format.");
    }

    public static void printHelp() {
        System.out.println( "Here are the list of things I can do for you:\n" +
                "1. Adding todos: todo NAME\n" +
                "2. Adding events: event NAME /at DATE\n" +
                "3. Adding deadlines: deadline /by DATE\n" +
                "4. Listing tasks: list\n" +
                "5. Marking tasks: done INDEX\n" +
                "6. Searching tasks: find KEYWORD\n" +
                "7. Deleting tasks: delete INDEX\n" +
                "8. Command summary: help\n" +
                "9. Exiting the program : bye");
    }
}
