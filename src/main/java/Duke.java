
import todo.*;

import java.util.ArrayList;


/**
 * The Duke class implements an application that runs the Duke program
 * which takes in simple commands to add, remove and save a set of tasks.
 */
public class Duke {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static ArrayList<String> commands = new ArrayList<>();
    protected static String filePath;

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    /**
     * Starts running the whole program by showing the welcome screen,
     * adding commands available and reading any file inputs.
     */
    protected static void run() {
        Ui.showWelcomeScreen();
        addCommands();
        Storage.readFile();
        Parser.readInput();
    }

    /**
     * Constructs an instance of the Duke class
     *
     * @param filePath path to save to
     */
    public Duke(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Adds all valid commands for the program.
     */
    protected static void addCommands() {
        String[] commandsToAdd = new String[]{"done", "todo", "event",
                    "deadline", "delete", "list", "save", "bye", "find"};
        for (String s : commandsToAdd) {
            commands.add(s);
        }
    }

    public static void printMessage(String text) {
        System.out.println(text);
        printDivider();
    }

    protected static void printDivider() {
        System.out.println("____________________________________________________________\n");
    }

}

