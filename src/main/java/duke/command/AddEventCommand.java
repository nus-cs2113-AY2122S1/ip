package duke.command;

import duke.exception.DukeException;
import duke.exception.UnsavedFile;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;
import duke.task.Deadline;

import java.io.IOException;

public class AddEventCommand extends Command {
    private final String eventName;
    private final String eventTime;

    public AddEventCommand(String eventName, String eventTime) {
        this.eventName = eventName;
        this.eventTime = eventTime;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(new Deadline(eventName, eventTime));
        int currentTaskIndex = tasks.getTaskListSize() - 1;
        ui.printAdded(tasks.getTaskDetails(currentTaskIndex));
        try {
            storage.writeData(tasks.getTaskList());
        } catch (IOException e) {
            throw new UnsavedFile();
        }
    }
}