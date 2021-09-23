package duke.command;

import duke.DukeException;
import duke.Output;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DoneCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a DoneCommand to mark the task at the specified index as completed
     *
     * @param taskIndex the index of the task in taskList to be marked as completed
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the task at the specified index in taskList as completed
     *
     * @param taskList the taskList that contains the task to be marked
     * @param ui       the ui class instance that will print out the completion message
     * @throws DukeException if taskIndex < 0 or if there is no task at the specified index
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task completedTask = taskList.completeTask(taskIndex);
        String outputMessage = Output.getCompleteTaskMessage(completedTask);
        ui.printOutput(outputMessage);
        storage.saveData(taskList);
    }
}
