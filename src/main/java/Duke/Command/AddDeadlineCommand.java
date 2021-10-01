package Duke.Command;

import Duke.Ui.Ui;
import Duke.Storage.Storage;
import Duke.Tasks.Deadline;
import Duke.Tasks.TaskList;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": add a deadline task to the current list.\n"
            + " Parameters: DESCRIPTION, BY_TIME\n"
            + " Example: " + COMMAND_WORD + " take the quiz /by Tuesday";

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Execute the add deadline command by adding a new deadline task to TaskList
     *
     * @param tasks TaskList the command to be executed on
     * @param ui Ui used for execution
     * @param storage Storage which the command may make change on
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printAddTaskMessage(new Deadline(this.description, this.by));
        tasks.addDeadline(this.description, this.by);
        ui.printNumOfTasks(tasks);
        storage.save(tasks.getTasks());
    }
}
