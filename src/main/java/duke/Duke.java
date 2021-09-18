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

    public static TaskList getTaskList() {
        return taskList;
    }

    public static Ui getUi() {
        return ui;
    }

    public static void startDuke() {
        storage = new StorageManager(FILE_PATH);
        taskList = new TaskList(storage.readFile());
        ui = new Ui();
        ui.printWelcomeMessage();
    }

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

    public static void exitDuke() {
        ui.printGoodbyeMessage();
    }

    public static void main(String[] args) {
        startDuke();
        runDukeOperations();
        exitDuke();
    }
}
