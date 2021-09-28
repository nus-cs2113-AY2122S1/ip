package duke.commands;

import duke.data.TaskList;
import duke.data.task.Deadline;
import duke.storage.Storage;
import duke.ui.TextUi;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline task to the task list. "
            + "Parameters: TASK /by DEADLINE"
            + "\n|| "
            + "Example: " + COMMAND_WORD
            + " Do CS2113T iP /by Tomorrow 6pm\n||";

    protected String description;
    protected String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

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