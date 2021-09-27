package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public abstract class AddTaskCommand extends Command {
    protected static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:\n" + "  %1$s\n"
            + "Now you have %2$s task(s) in the list";
    protected Task toAdd;

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(toAdd);
        return String.format(MESSAGE_TASK_ADDED, toAdd, tasks.getSize());
    }
}
