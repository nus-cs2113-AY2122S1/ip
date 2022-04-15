package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.file.FileManager;
import duke.parser.Parser;
import duke.task.TaskManager;
import duke.ui.Ui;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private static final String ROOT = System.getProperty("user.dir");
    private static final Path FILE_PATH = Paths.get(ROOT, "data", "duke.txt");
    private static final Path DIRECTORY_PATH = Paths.get(ROOT, "data");

    private final TaskManager taskManager;

    private final FileManager fileManager;

    private final Parser parser;

    private final Ui ui;

    /**
     * Constructor. Instantiates a TaskManager, FileManager, Parser, and Ui.
     */
    public Duke() {
        taskManager = new TaskManager();
        fileManager = new FileManager(FILE_PATH.toString(), DIRECTORY_PATH.toString());
        fileManager.initialiseDuke(taskManager);
        parser = new Parser();
        ui = new Ui();
    }

    /**
     * Runs the duke program.
     * Executes various commands based on user inputs.
     */
    public void run() {
        ui.printGreeting();
        boolean isExit = false;
        do {
            String line = ui.readCommand();
            ui.printHorizontalLine();
            try {
                Command c = parser.execute(line);
                c.execute(taskManager, ui);
                isExit = c.getIsBye();
            } catch (DukeException e) {
                ui.println("  " + e.getMessage());
            }
            fileManager.saveDukeWithoutMessage(taskManager);
            ui.printHorizontalLine();
            ui.printNewLine();
        } while (!isExit);
        fileManager.saveDuke(taskManager);
    }

    /**
     * Instantiates an instance of Duke and runs it.
     *
     * @param args Not utilised for this program.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
