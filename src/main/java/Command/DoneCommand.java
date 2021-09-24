package Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.IndexTooBigException;
import exception.IndexTooSmallException;

public class DoneCommand extends Command {
    public DoneCommand(String description) {
        super(description, IS_NOT_EXIT);
    }

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
