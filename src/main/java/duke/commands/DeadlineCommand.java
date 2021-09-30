package duke.commands;

import duke.data.TaskList;
import duke.data.task.Deadline;
import duke.storage.Storage;
import duke.ui.TextUi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Adds a Deadline task to the task list.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    /** (?i) Toggles case insensitivity for the splitter "/by" */
    public static final String COMMAND_SPLITTER = "(?i)/by";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline task to the task list. "
            + "Parameters: DESCRIPTION /by DEADLINE\n"
            + "Example: " + COMMAND_WORD
            + " Do CS2113T iP /by 2021-10-01\n";

    protected String description;
    protected String by;

    /**
     * Simple constructor using raw values.
     *
     * @param description a string that contains the task description
     * @param by          a string that contains the deadline for the task
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Appends a Deadline task to the TaskList and storage file depending on the type of the deadline provided.
     * Shows the user a message for successful creation.
     *
     * @param tasks   a task list that contains all the tasks
     * @param ui      accesses format and messages to show to the user
     * @param storage accesses a text file which stores the task list
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            createDeadlineWithLocalDateTask(tasks, storage);
        } catch (DateTimeParseException e) {
            createDeadlineWithStringTask(tasks, storage);
        }
        ui.showSuccessfulAdd(tasks);
    }

    /**
     * Creates a Deadline task with the deadline as a LocalDate class.
     * Adds the task to the task list and saves the data to the storage file.
     *
     * @param tasks the task list
     * @param storage accesses the storage file
     * @throws DateTimeParseException if the program is unable to parse the deadline into a LocalDate class.
     */
    private void createDeadlineWithLocalDateTask(TaskList tasks, Storage storage) {
        LocalDate d1 = LocalDate.parse(by, DateTimeFormatter.ISO_DATE);
        String date = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        tasks.addTask(new Deadline(description, d1));
        storage.appendDeadlineToFile(description, date);
    }

    /**
     * Creates a Deadline task with the deadline as a String.
     * Adds the task to the task list and saves the data to the storage file.
     *
     * @param tasks the task list
     * @param storage accesses the storage file
     */
    private void createDeadlineWithStringTask(TaskList tasks, Storage storage) {
        tasks.addTask(new Deadline(description, by));
        storage.appendDeadlineToFile(description, by);
    }

}