package duke.command;

import duke.exception.DukeException;
import duke.exception.UnsavedFile;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;
import duke.task.Event;

import java.io.IOException;
import java.time.LocalDate;

public class AddEventCommand extends Command {
    private final String eventName;
    private final LocalDate eventStartDate;
    private final LocalDate eventEndDate;
    private final String eventStartTime;
    private final String eventEndTime;

    public AddEventCommand(String eventName, LocalDate eventStartDate, String eventStartTime,
                 LocalDate eventEndDate, String eventEndTime) {
        this.eventName = eventName;
        this.eventStartDate = eventStartDate;
        this.eventStartTime = eventStartTime;
        this.eventEndDate = eventEndDate;
        this.eventEndTime = eventEndTime;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(new Event(eventName, eventStartDate, eventStartTime,
                eventEndDate, eventEndTime));
        int currentTaskIndex = tasks.getTaskListSize() - 1;
        ui.printAdded(tasks.getTaskDetails(currentTaskIndex));
        try {
            storage.writeData(tasks.getTaskList());
        } catch (IOException e) {
            throw new UnsavedFile();
        }
    }
}