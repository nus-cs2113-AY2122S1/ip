package duke.commands;

import duke.data.TaskList;
import duke.data.task.Event;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Adds an Event task to the task list.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_SPLITTER = "/at";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event task to the task list. "
            + "Parameters: TASK /at DAY_TIME"
            + "\n"
            + "Example: " + COMMAND_WORD
            + " CS2113T tP Meeting /at Next Thursday 2-5pm\n";

    protected String description;
    protected String at;

    /**
     * Simple constructor with raw values.
     *
     * @param description a string that contains the task description
     * @param at a string that contains the event date/time for the task
     */
    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Appends an Event task to the TaskList and storage file.
     * Shows the user a message for successful creation.
     *
     * @param tasks a task list that contains all the tasks
     * @param ui accesses format and messages to show to the user
     * @param storage accesses a text file which stores the task list
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        createEventTask(tasks);
        storage.appendEventToFile(description, at);
        ui.showSuccessfulAdd(tasks);
    }

    private void createEventTask(TaskList tasks) {
        tasks.addTask(new Event(description, at));
    }
}