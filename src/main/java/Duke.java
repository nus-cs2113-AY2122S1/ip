import duke.error.DukeException;
import duke.tasklist.command.Command;
import duke.tasklist.command.ExitCommand;
import duke.ui.DisplayManager;
import duke.storage.FileManager;
import duke.tasklist.TaskManager;
import duke.ui.Parser;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private final FileManager fileManager;
    private final DisplayManager displayManager;
    private final TaskManager taskManager;
    private final Parser parser;
    private final Scanner in;

    /**
     * Initialise essential components for the program to function.
     * @param filePath String containing the file path to save task data.
     */
    public Duke(String filePath) {
        displayManager = new DisplayManager();
        fileManager = new FileManager(filePath);
        taskManager = new TaskManager();
        parser = new Parser();
        in = new Scanner(System.in);

        try {
            fileManager.load(taskManager, parser);
        } catch (IOException e) {
            displayManager.printErrorLoadingData();
        }
    }

    /**
     * Runs the program until stated by user otherwise.
     * Waits for a user input and processes it.
     */
    public void run() {
        displayManager.printStartGreet();
        String fullCommand;
        fullCommand = in.nextLine().toLowerCase();
        while (true) {
            try {
                Command command = parser.parse(taskManager, parser, fullCommand);
                if (command != null) {
                    command.execute();
                }
                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (DukeException e) {
                DisplayManager.printHorizontalSeparator();
                System.out.println(e);
                DisplayManager.printHorizontalSeparator();
            }
            fullCommand = in.nextLine().toLowerCase();
        }
        displayManager.printEndGreet();
    }

    public static void main(String[] args) {
        new Duke("data/savedTasks.txt").run();
    }
}
