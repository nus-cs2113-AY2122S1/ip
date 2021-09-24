package duke;

import duke.command.Command;
import duke.datasaver.DataManager;
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
    private final DataManager dataManager;

    /** Sets up the required objects needed for the program to work. */
    public Duke() {
        this.taskList = new TaskList();
        this.dataManager = new DataManager();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /** Runs the Duke program until termination. */
    private void run() {
        start();
        runLoopUntilExitCommand();
    }

    /** Loads data from storage file and prints a greeting upon entry of program. */
    private void start() {
        dataManager.loadData(taskList.getTaskList());
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
                command.execute(taskList, dataManager);
            } catch (InvalidCommandFormatException | NumberFormatException fe) {
                Ui.printInvalidCommandFormatMessage();
            } catch (DateTimeParseException dtpe) {
                Ui.printInvalidDateTimeMessage();
            }
        }
    }
}
