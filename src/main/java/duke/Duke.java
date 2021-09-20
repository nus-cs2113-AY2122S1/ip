package duke;

import duke.command.CommandExecutor;
import duke.datasaver.DataManager;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Entry point of Duke program.
 * Initializes the program and starts interaction with the user.
 */
public class Duke {

    private final CommandExecutor commandExecutor;
    private final TaskList taskList;
    private final DataManager dataManager;

    /** Sets up the required objects needed for the program to work. */
    public Duke() {
        this.commandExecutor = new CommandExecutor();
        this.taskList = new TaskList();
        this.dataManager = new DataManager();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /** Runs the Duke program until termination. */
    private void run() {
        start();
        runLoopUntilExitCommand(commandExecutor);
    }

    /** Loads data from storage file and prints a greeting upon entry of program. */
    private void start() {
        dataManager.loadData(taskList.getTaskList());
        Ui.printHeyMessage();
    }

    /** Reads user command and executes the command until a {@code bye} command is entered. */
    private void runLoopUntilExitCommand(CommandExecutor commandExecutor) {
        String userInput;
        boolean isExit;

        do {
            userInput = Ui.readUserInput();
            commandExecutor.execute(userInput, taskList, dataManager);
            isExit = commandExecutor.isExit(userInput);
        } while (!isExit);
    }
}
