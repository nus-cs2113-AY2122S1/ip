package Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.IndexTooBigException;
import exception.IndexTooSmallException;

/**
 * Represents a <code>Command</code> to delete <code>Task</code>.
 */
public class DeleteCommand extends Command {
    /**
     * Instantiates a <code>DeleteCommand</code> object.
     *
     * @param description Description of the task.
     */
    public DeleteCommand(String description) {
        super(description, IS_NOT_EXIT);
    }

    /**
     * Delete task from <code>tasks</code> list.
     *
     * @param tasks Tasks to be executed on.
     * @param ui UI to interact with user.
     * @param storage Storage to save task changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        deleteTaskWithException(tasks, ui, storage, getDescription());
    }

    private void deleteTask(TaskList tasks, Ui ui, int taskIndex)
            throws IndexTooSmallException, IndexTooBigException {
        if(taskIndex < 0) {
            throw new IndexTooSmallException();
        }
        if(taskIndex > getTasksSize(tasks.getTasks()) - 1){
            throw new IndexTooBigException();
        }

        ui.printDeleteTaskMessage(tasks, taskIndex);
        tasks.getTasks().remove(taskIndex);
    }

    private void deleteTaskWithException(TaskList tasks, Ui ui,
                                         Storage storage, String userInput) {
        int taskIndex = getTaskIndex(userInput) - 1;

        try {
            deleteTask(tasks, ui, taskIndex);
        } catch (IndexTooSmallException e) {
            ui.printTaskIndexTooSmallMessage();
        } catch (IndexTooBigException e) {
            ui.printTaskIndexTooBigMessage(tasks);
        }

        storage.saveTasksToDiskWithException(tasks.getTasks());
    }

    private int getTaskIndex(String userInput) {
        String[] inputs = userInput.split(SEPARATOR_SPACE);
        int taskNumber = Integer.parseInt(inputs[1]);

        return taskNumber;
    }
}
