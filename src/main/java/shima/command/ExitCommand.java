package shima.command;

import shima.design.UserInterface;
import shima.storage.Storage;
import shima.task.TaskList;

import java.io.IOException;

public class ExitCommand extends Command {
    private static final String GOODBYE_MSG = "Bye! Hope to see you again :D";
    private final Storage storage;
    private final TaskList tasks;
    private final UserInterface ui;

    public ExitCommand(TaskList tasks, Storage storage, UserInterface ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Saves the task list to the storage file and close the program
     *
     * @throws IOException Throws this exception when there is error during saving data
     */
    public void closeProgram() throws IOException {
        storage.updateStorage(tasks);
        ui.showMessage(GOODBYE_MSG);
        System.exit(0);
    }

    public void runCommand() throws IOException {
        closeProgram();
    }
}
