package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasks.Event;
import duke.tasks.TaskList;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Adds an event to the TaskList
 */
public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private final String description;
    private final String at;

    /**
     * Constructs AddEventCommand
     *
     * @param description description of the event
     * @param at          time that the event is scheduled at
     */
    public AddEventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Executes the AddEventCommand by adding the event to the TaskList and storage file.
     *
     * @param tasks   the TaskList that contains all current tasks
     * @param storage the storage object responsible for writing to the data file
     * @param ui      the ui object responsible for acknowledging to user that the command has been executed
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        Event toAdd = getEvent();
        tasks.addTask(toAdd);
        storage.writeToData(toAdd, tasks.getNumberOfTasks());
        ui.acknowledgeAddCommand(toAdd, tasks.getNumberOfTasks());
    }

    /**
     * Returns an Event with the description and time-limit of this.description and this.at. The Event's
     * at is a LocalDateTime if it is parsable in the format of "d/M/yyyy HHmm" and a String if it is not.
     *
     * @return Event object with description and by the same as this AddEventCommand object
     */
    private Event getEvent() {
        try {
            LocalDateTime atDT = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return new Event(description, atDT, false);
        } catch (DateTimeParseException dtpe) {
            return new Event(description, at, false);
        }
    }

}
