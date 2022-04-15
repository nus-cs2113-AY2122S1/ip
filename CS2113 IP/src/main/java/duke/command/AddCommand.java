package duke.command;

import duke.TaskList;
import duke.TaskType;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private final TaskType specificTask;
    private final String fullCommand;

    /**
     * Class constructor for AddCommand
     *
     * @param fullCommand  User input String
     * @param specificTask Enum that instructs which specific task to add (event, todo, deadline)
     */
    public AddCommand(String fullCommand, TaskType specificTask) {
        this.specificTask = specificTask;
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the addition of task to the list, depending on the specific task to add.
     *
     * @param tasks   Task list that stores all the tasks
     * @param ui      User interface of Duke
     * @param storage Database of Duke
     * @throws DateTimeParseException If there is an error with format of date/time input from String
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DateTimeParseException {
        tasks.addTask(fullCommand, specificTask, ui);
    }
}
