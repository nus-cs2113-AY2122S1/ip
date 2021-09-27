package duke.command;

import duke.exception.UnsavedFile;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;
import duke.task.Event;

import java.io.IOException;
import java.time.LocalDate;


/**
 * Represents a command to add an event.
 */
public class AddEventCommand extends Command {
    private final String eventName;
    private final LocalDate eventStartDate;
    private final LocalDate eventEndDate;
    private final String eventStartTime;
    private final String eventEndTime;

    /**
     * AddEventCommand constructor.
     * @param eventName the name/description of the event task
     * @param eventStartDate the starting date of the event task
     * @param eventStartTime the starting time of the event task
     * @param eventEndDate the ending date of the event task
     * @param eventEndTime the ending time of the event task
     */
    public AddEventCommand(String eventName, LocalDate eventStartDate, String eventStartTime,
                 LocalDate eventEndDate, String eventEndTime) {
        this.eventName = eventName;
        this.eventStartDate = eventStartDate;
        this.eventStartTime = eventStartTime;
        this.eventEndDate = eventEndDate;
        this.eventEndTime = eventEndTime;
    }

    /**
     * Add the event task to the list
     * @param tasks   the TaskList object that takes in the new deadline task
     * @param ui      the Ui object responsible for printing messages
     * @param storage the Storage object responsible for saving data in a local txt file
     * @throws UnsavedFile if fail to save data to the local file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UnsavedFile {
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