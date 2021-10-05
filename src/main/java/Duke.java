import todo.Deadline;
import todo.Event;
import todo.Task;
import todo.ToDo;

import java.util.ArrayList;


/**
 * The Duke class implements an application that runs the Duke program
 * which takes in simple commands to add, remove and save a set of tasks.
 */
public class Duke {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static ArrayList<String> commands = new ArrayList<>();
    protected static String filePath;
    protected static String[] commandsToAdd = new String[]{"done", "todo", "event",
            "deadline", "delete", "list", "save", "bye", "find"};

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
        for (String s : commandsToAdd) {
            commands.add(s);
        }
    }

    protected static void printDivider() {
        System.out.println("____________________________________________________________\n");
    }

}

