import duke.Error.DukeException;
import duke.TaskList.command.*;
import duke.Ui.DisplayManager;
import duke.Storage.FileManager;
import duke.TaskList.TaskManager;
import duke.Ui.Parser;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private FileManager fileManager;
    private DisplayManager displayManager;
    private TaskManager taskManager;
    private Parser parser;
    private Scanner in;

    private static final String COMMAND_EXIT = "bye";

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

    public void run() {
        displayManager.printStartGreet();
        String fullCommand;
        fullCommand = in.nextLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = parser.parse(taskManager, parser, fullCommand);
                if (command != null) {
                    command.execute();
                }
                if (command instanceof ExitCommand) {
                    isExit = true;
                    break;
                }
            } catch (DukeException e) {
                DisplayManager.printHorizontalSeparator();
                System.out.println(e);
                DisplayManager.printHorizontalSeparator();
            }
            fullCommand = in.nextLine();
        }
        displayManager.printEndGreet();
    }

    public static void main(String[] args) {
        new Duke("data/savedTasks.txt").run();
    }
}
