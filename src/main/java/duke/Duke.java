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

    public static void run() {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        String userInput = "";
        start();
        runCommandLoopUntilExitCommand(in, taskList, userInput);
        end();
    }

    public static void start() {
        Ui.printIntro();
    }

    private static void runCommandLoopUntilExitCommand(Scanner in, ArrayList<Task> taskList, String userInput) {
        DataManager.printPreviousFileContents(FILE_PATH, taskList);
        Parser.parseCommand(in, taskList, userInput);
        DataManager.storeCurrentList(FILE_PATH, taskList);
    }

    public static void end() {
        Ui.printOutro();
    }
}
