package duke.command;

import duke.exception.DukeException;
import duke.exception.NoDataFile;
import duke.exception.UnsavedFile;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;
import duke.task.Deadline;

import java.io.IOException;

public class AddDeadlineCommand extends Command {
    private final String deadlineName;
    private final String deadlineTime;

    public AddDeadlineCommand(String deadlineName, String deadlineTime) {
        this.deadlineName = deadlineName;
        this.deadlineTime = deadlineTime;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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
