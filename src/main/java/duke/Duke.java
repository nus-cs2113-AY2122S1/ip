package duke;

import duke.tasklist.Task;
import duke.parser.Parser;
import duke.storage.DataManager;
import duke.ui.Ui;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String FILE_PATH = "duke.txt";

    public static void main(String[] args) {
        Duke.run();
    }

    /** Runs the program until termination */
    public static void run() {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        String userInput = "";
        start();
        parseCommandsToDuke(in, taskList, userInput);
        end();
    }

    /** Prints the welcome message. */
    public static void start() {
        Ui.printIntro();
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command
     *
     * @param in object of scanner
     * @param taskList Task type arraylist to store all the tasks entered by the user
     * @param userInput takes input from the keyboard
     */
    private static void parseCommandsToDuke(Scanner in, ArrayList<Task> taskList, String userInput) {
        DataManager.printPreviousFileContents(FILE_PATH, taskList);
        Parser.parseCommand(in, taskList, userInput);
        DataManager.storeCurrentList(FILE_PATH, taskList);
    }

    /** Prints the exit message. */
    public static void end() {
        Ui.printOutro();
    }
}
