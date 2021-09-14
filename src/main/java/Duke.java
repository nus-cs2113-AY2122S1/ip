import java.io.IOException;
import java.util.NoSuchElementException;

public class Duke {

    //TODO initialize as such or just use as static class...
    public static final TaskManager taskManager = new TaskManager();

    //TODO Abstract repeated functions

    public static void main(String[] args) {
        welcomeMessage();
        try {
            FileManager.initializeFile();

        } catch (IOException e) {
            printlnTab(e.getMessage());

        }
        try {
            CommandManager.executeCommand();
        } catch (NoSuchElementException e) {
            printlnTab("Bye. Hope to see you again soon!");
            printDivider();
        }
    }

    public static void printlnTab(String str) {
        System.out.println("\t" + str);
    }

    public static void printDivider() {
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static void welcomeMessage() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        printDivider();
        System.out.println(logo);
        printlnTab("Hello! I'm Duke\n\tWhat can I do for you?");
        printDivider();
    }


}
