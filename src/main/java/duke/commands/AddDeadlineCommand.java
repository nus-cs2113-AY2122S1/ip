package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasks.Deadline;
import duke.tasks.TaskList;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Adds a deadline to the TaskList
 */
public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final String description;
    private final String by;

    /**
     * Constructs AddDeadLineCommand
     *
     * @param description the description of the deadline to be added
     * @param by          the date and time by when the deadline has to be completed
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the AddDeadLineCommand by adding it to the TaskList and storage file.
     *
     * @param tasks   the TaskList that contains all current tasks
     * @param storage the storage object responsible for writing to the data file
     * @param ui      the ui object responsible for acknowledging to user that the command has been executed
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        Deadline toAdd = getDeadline();
        tasks.addTask(toAdd);
        storage.writeToData(toAdd, tasks.getNumberOfTasks());
        ui.acknowledgeAddCommand(toAdd, tasks.getNumberOfTasks());
    }

    /**
     * Returns a Deadline with the description and time-limit of this.description and this.by. The Deadline's
     * by is a LocalDateTime if it is parsable in the format of "d/M/yyyy HHmm" and a String if it is not.
     *
     * @return Deadline object with description and by the same as this AddDeadLineCommand object
     */
    private Deadline getDeadline() {
        try {
            LocalDateTime byDT = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return new Deadline(description, byDT, false);
        } catch (DateTimeParseException dtpe) {
            return new Deadline(description, by, false);
        }
    }
}
