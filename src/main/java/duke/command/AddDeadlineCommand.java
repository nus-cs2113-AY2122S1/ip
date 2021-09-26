package duke.command;

import duke.exception.DukeException;
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

    public AddDeadlineCommand(String deadlineName, LocalDate deadlineDate,String deadlineTime) {
        this.deadlineName = deadlineName;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

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
