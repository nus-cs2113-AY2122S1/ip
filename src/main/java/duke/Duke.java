package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.storage.StorageManager;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private static final String FILE_PATH = "./data/task.txt";

    private static StorageManager storage;
    private static TaskList taskList;
    private static Ui ui;

    /**
     * Return the task list stored in this object.
     * 
     * @return task list.
     */
    public static TaskList getTaskList() {
        return taskList;
    }

    /**
     * Return the ui object of this object.
     * 
     * @return the ui object.
     */
    public static Ui getUi() {
        return ui;
    }

    /**
     * Initialize the program by assigning new values to the variables in this
     * object, then show welcome message to the user to indicate that the program
     * has been started.
     */
    public static void startDuke() {
        storage = new StorageManager(FILE_PATH);
        taskList = new TaskList(storage.readFile());
        ui = new Ui();
        ui.printWelcomeMessage();
    }

    /**
     * Run Duke until user enter the command "bye"
     */
    public static void runDukeOperations() {
        Command userCommand;
        CommandResult result;
        do {
            userCommand = ui.getCommand();
            result = taskList.executeCommand(userCommand);
            storage.updateStorage(taskList.getTasks());
            ui.printCommandResult(result);
        } while (!userCommand.isExitCommand());
    }

    /**
     * Show message to user to indicated that the program has been terminated.
     */
    public static void exitDuke() {
        ui.printGoodbyeMessage();
    }

    public static void main(String[] args) {
        startDuke();
        runDukeOperations();
        exitDuke();
    }
}
