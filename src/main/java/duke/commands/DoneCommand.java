package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private static final String MESSAGE_TASK_MARKED_AS_DONE = "Nice! I've marked this task as done:\n" + "  %1$s";
    private static final String MESSAGE_NONEXISTENT_TASK_NUMBER = "That task number does not exist!";

    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            final Task task = tasks.getTask(index);
            task.setAsDone();
            return String.format(MESSAGE_TASK_MARKED_AS_DONE, task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_NONEXISTENT_TASK_NUMBER);
        }
    }
}
