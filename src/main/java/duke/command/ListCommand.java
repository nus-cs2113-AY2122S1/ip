package duke.command;

import duke.task.TaskList;
import duke.task.exception.InvalidTaskIndexException;
import duke.task.exception.TaskListEmptyException;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    /**
     * Prints current tasks in the task list.
     *
     * @param taskList Current task list.
     */
    @Override
    public void execute(TaskList taskList) {
        try {
            ui.listTasks(taskList);
        } catch (TaskListEmptyException e) {
            ui.printTaskListEmptyError();
        } catch (InvalidTaskIndexException e) {
            ui.printInvalidTaskIndexError();
        }
    }
}
