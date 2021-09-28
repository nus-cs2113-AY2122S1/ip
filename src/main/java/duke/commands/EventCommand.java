package duke.commands;

import duke.data.TaskList;
import duke.data.task.Event;
import duke.storage.Storage;
import duke.ui.TextUi;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event task to the task list. "
            + "Parameters: TASK /at DAY_TIME"
            + "\n|| "
            + "Example: " + COMMAND_WORD
            + " CS2113T tP Meeting /at Next Thursday 2-5pm\n||";

    protected String description;
    protected String at;

    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        createEventTask(tasks);
        storage.appendEventToFile(description, at);
        ui.showSuccessfulAdd(tasks);
    }

    private void createEventTask(TaskList tasks) {
        tasks.addTask(new Event(description, at));
    }
}