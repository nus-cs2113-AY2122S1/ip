package duke.command;

import duke.exception.UnsavedFile;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;
import duke.task.Deadline;

import java.io.IOException;


/**
 * Represents a command to add a deadline.
 */
public class AddDeadlineCommand extends Command {
    private final String deadlineName;
    private final String deadlineTime;

    /**
     * The AddDeadlineCommand constructor.
     *
     * @param deadlineName name/description of this deadline task
     * @param deadlineTime by what time this deadline task should be finished
     */
    public AddDeadlineCommand(String deadlineName, String deadlineTime) {
        this.deadlineName = deadlineName;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Add the deadline task into the list.
     * @param tasks the TaskList object that takes in the new deadline task
     * @param ui the Ui object responsible for printing messages
     * @param storage the Storage object responsible for saving data in a local txt file
     * @throws UnsavedFile if fail to save data in the local file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UnsavedFile {
        tasks.addTask(new Deadline(deadlineName, deadlineTime));
        int currentTaskIndex = tasks.getTaskListSize() - 1;
        ui.printAdded(tasks.getTaskDetails(currentTaskIndex));
        try {
            storage.writeData(tasks.getTaskList());
        } catch (IOException e) {
            throw new UnsavedFile();
        }
    }
}
