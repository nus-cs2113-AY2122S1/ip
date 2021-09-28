package Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.IndexTooBigException;
import exception.IndexTooSmallException;

/**
 * Represents a <code>Command</code> to mark <code>Task</code> as done.
 */
public class DoneCommand extends Command {
    /**
     * Instantiates a <code>DoneCommand</code> object.
     *
     * @param description Description of the task.
     */
    public DoneCommand(String description) {
        super(description, IS_NOT_EXIT);
    }

    /**
     * Set task done in <code>tasks</code> list.
     *
     * @param tasks Tasks to be executed on.
     * @param ui UI to interact with user.
     * @param storage Storage to save task changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        setTaskDoneWithException(tasks, ui, storage, getDescription());
    }

    private void setTaskDone(TaskList tasks, Ui ui, Storage storage, int taskIndex)
            throws IndexTooSmallException, IndexTooBigException {
        if (taskIndex < 0) {
            throw new IndexTooSmallException();
        }
        if (taskIndex > getTasksSize(tasks.getTasks()) - 1){
            throw new IndexTooBigException();
        }
        tasks.getTasks().get(taskIndex).markAsDone();
        storage.saveTasksToDiskWithException(tasks.getTasks());
        ui.printMarkAsDoneMessage(tasks, taskIndex);
    }

    private void setTaskDoneWithException(TaskList tasks, Ui ui,
                                          Storage storage, String userInput) {
        int taskIndex = getTaskIndex(userInput) - 1;

        try {
            setTaskDone(tasks, ui, storage, taskIndex);
        } catch (IndexTooSmallException e) {
            ui.printTaskIndexTooSmallMessage();
        } catch (IndexTooBigException e) {
            ui.printTaskIndexTooBigMessage(tasks);
        }
    }

    private int getTaskIndex(String userInput) {
        String[] inputs = userInput.split(SEPARATOR_SPACE);
        int taskNumber = Integer.parseInt(inputs[1]);

        return taskNumber;
    }

}
