package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Adds a todo task into the list and the file
 */
public class AddTodoTaskCommand extends Command {
    public static final String COMMAND_KEYWORD = "todo";
    private String description;

    /**
     * Constructs AddTodoTaskCommand
     *
     * @param description Task description
     */
    public AddTodoTaskCommand(String description) {
        this.description = description;
    }

    /**
     * Executes AddTodoTaskCommand by adding the todo task into the list and into the file.
     * It also prints the confirmation message into the console.
     *
     * @param tasks The task list which stores the task
     * @param ui In charge of informing the user that the command has been executed
     * @param storage In charge of reading and writing to data file
     * @throws IOException If there is an error accessing the file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTodoTask(description);
        storage.updateFile(tasks);
        ui.acknowledgeAdd();
    }
}
