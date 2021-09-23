package shima.command;

import shima.design.Default;
import shima.storage.Storage;
import shima.task.TaskList;

import java.io.IOException;

public class ExitCommand extends Command {
    private static final String GOODBYE_MSG = "Bye! Hope to see you again :D";
    private final Storage storage;
    private final TaskList tasks;

    public ExitCommand(TaskList tasks, Storage storage){
        this.tasks = tasks;
        this.storage = storage;
    }

    public void closeProgram() throws IOException {
        storage.updateStorage(tasks);
        Default.showMessage(GOODBYE_MSG);
        System.exit(0);
    }

    public void runCommand() throws IOException {
        closeProgram();
    }
}
