package duke;

import duke.commands.Command;
import duke.storage.DataStorage;
import duke.exception.InvalidCommandFormatException;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * Entry point of Duke program.
 * Initializes the program and starts interaction with the user.
 */
public class Duke {

    private final TaskList taskList;
    private final DataStorage dataStorage;

    /** Sets up the required objects needed for the program to work. */
    public Duke(String[] launchArgs) {
        this.taskList = new TaskList();

        /* launchArgs with length > 0 implies that the user has specified a file path for storage file */
        this.dataStorage = (launchArgs.length > 0) ? new DataStorage(launchArgs[0]) : new DataStorage();
    }

    public static void main(String[] launchArgs) {
        new Duke(launchArgs).run();
    }

    /** Runs the Duke program until termination. */
    private void run() {
        start();
        runLoopUntilExitCommand();
    }

    /** Loads data from storage file and prints a greeting upon entry of program. */
    private void start() {
        dataStorage.loadData(taskList);
        Ui.printHeyMessage();
    }

    /**
     *  Reads user command and executes the command until a {@code bye} command is entered.
     */
    @SuppressWarnings("InfiniteLoopStatement")
    private void runLoopUntilExitCommand() {
        String userInput;
        while(true) {
            try {
                userInput = Ui.readUserInput();
                Command command = Parser.parseCommandWord(userInput);
                command.execute(taskList, dataStorage);
            } catch (InvalidCommandFormatException | NumberFormatException fe) {
                Ui.printInvalidCommandFormatMessage();
            } catch (DateTimeParseException dtpe) {
                Ui.printInvalidDateTimeMessage();
            }
        }
    }
}
