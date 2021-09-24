package duke;

public class UI {




    public static final String WELCOME_MESSAGE =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n"
                    + "Hello I'm Duke\nWhat can I do for you?";

    public static void printInvalidIndex() {
        System.out.println("Please enter a valid task number");
    }

    public static void printWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void printInvalidDate() {
        System.out.println("Please enter a valid date");
        System.out.println("Date should be in the form DDMMYYYY or DD/MM/YYYY or DD-MM-YYYY");
    }

    public static void printInvalidCommand() {
        System.out.println("Please enter a valid command");
    }

    public static void printInvalidDescription() {
        System.out.println("Please enter a valid description");
    }

    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printNewSave() {
        System.out.println("No existing save duke.data");
        System.out.println("New save file created");
    }

//    public static void print

}
