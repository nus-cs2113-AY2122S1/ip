package duke.commands;

import duke.data.TaskList;
import duke.data.task.Deadline;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Adds a Deadline task to the task list.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline task to the task list. "
            + "Parameters: TASK /by DEADLINE"
            + "\n|| "
            + "Example: " + COMMAND_WORD
            + " Do CS2113T iP /by Tomorrow 6pm\n||";

    protected String description;
    protected String by;

    /**
     * Simple constructor using raw values.
     *
     * @param description a string that contains the task description
     * @param by a string that contains the deadline for the task
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Appends a Deadline task to the TaskList and storage file.
     * Shows the user a message for successful creation.
     *
     * @param tasks a task list that contains all the tasks
     * @param ui accesses format and messages to show to the user
     * @param storage accesses a text file which stores the task list
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        createDeadlineTask(tasks);
        storage.appendDeadlineToFile(description, by);
        ui.showSuccessfulAdd(tasks);
    }

    private void createDeadlineTask(TaskList tasks) {
        tasks.addTask(new Deadline(description, by));
    }

}