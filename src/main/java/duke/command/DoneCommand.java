package duke.command;

import duke.Storage;
import duke.DukeException;
import duke.TaskList;

/**
 * Class that defines and represents the behaviour of the Done command
 */
public class DoneCommand extends Command{
    protected int taskIndex;

    /**
     * Constructor initialises the Done command with the index of the task to be marked as done in the task list
     * @param taskIndex
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes Command and marks task as done in the task list
     * @param taskList Task Manager that executes task based on the given command
     * @param storage Stores User data in a csv file
     * @throws DukeException Throws any error messages from markTaskAsDone or saveData functions
     */
    @Override
    public void executeCommand(TaskList taskList, Storage storage) throws DukeException {
        taskList.markTaskAsDone(taskIndex);
        storage.saveData(taskList);
    }
}
