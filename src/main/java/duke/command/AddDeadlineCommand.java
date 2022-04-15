package duke.command;

import duke.exception.UnsavedFile;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;
import duke.task.Deadline;

import java.io.IOException;
import java.time.LocalDate;


/**
 * Represents a command to add a deadline.
 */
public class AddDeadlineCommand extends Command {
    private final String deadlineName;
    private final LocalDate deadlineDate;
    private final String deadlineTime;

    /**
     * AddDeadlineCommand constructor.
     * @param deadlineName the name/description of the deadline task
     * @param deadlineDate the date by which should finish the deadline task
     * @param deadlineTime the date by which should finish the deadline task
     */
    public AddDeadlineCommand(String deadlineName, LocalDate deadlineDate, String deadlineTime) {
        this.deadlineName = deadlineName;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Add the deadline task to the list.
     * @param tasks   the TaskList object that takes in the new deadline task
     * @param ui      the Ui object responsible for printing messages
     * @param storage the Storage object responsible for saving data in a local txt file
     * @throws UnsavedFile when fail to save the data to local file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UnsavedFile {
        tasks.addTask(new Deadline(deadlineName, deadlineDate, deadlineTime));
        int currentTaskIndex = tasks.getTaskListSize() - 1;
        ui.printAdded(tasks.getTaskDetails(currentTaskIndex));
        try {
            storage.writeData(tasks.getTaskList());
        } catch (IOException e) {
            throw new UnsavedFile();
        }
    }
}
