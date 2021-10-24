package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasks.TaskList;
import duke.tasks.Todo;

import java.io.IOException;

/**
 * Adds a todo to the task list
 */
public class AddTodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private final String description;

    /**
     * Constructs AddTodoCommand
     *
     * @param description description of the todo
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the AddTodoCommand by adding the todo to the TaskList and storage file.
     *
     * @param tasks   the TaskList that contains all current tasks
     * @param storage the storage object responsible for writing to the data file
     * @param ui      the ui object responsible for acknowledging to user that the command has been executed
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        Todo toAdd = new Todo(description, false);
        tasks.addTask(toAdd);
        storage.writeToData(toAdd, tasks.getNumberOfTasks());
        ui.acknowledgeAddCommand(toAdd, tasks.getNumberOfTasks());
    }
}
