import duke.Error.DukeException;
import duke.TaskList.command.Command;
import duke.TaskList.command.ExitCommand;
import duke.Ui.DisplayManager;
import duke.Storage.FileManager;
import duke.TaskList.TaskManager;
import duke.Ui.Parser;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private final FileManager fileManager;
    private final DisplayManager displayManager;
    private final TaskManager taskManager;
    private final Parser parser;
    private final Scanner in;

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
            fullCommand = in.nextLine();
        }
        displayManager.printEndGreet();
    }

    public static void main(String[] args) {
        new Duke("data/savedTasks.txt").run();
    }
}
