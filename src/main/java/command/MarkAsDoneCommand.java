package command;

import exception.AustinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

/** Marks the task as done */
public class MarkAsDoneCommand extends Command {
    public static final String COMMAND_KEYWORD = "done";
    private int taskIndex;

    public MarkAsDoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AustinException,
            IOException {
        tasks.markAsDone(taskIndex);
        storage.updateFile(tasks);
        ui.acknowledgeDone(taskIndex);
    }
}
