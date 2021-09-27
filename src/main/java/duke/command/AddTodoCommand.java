package duke.command;

import duke.exception.UnsavedFile;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;
import duke.task.ToDo;

import java.io.IOException;

/**
 * Represents a command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private final String todoName;

    /**
     * The AddTodoCommand constructor.
     * @param todoName the name/description of the todo task.
     */
    public AddTodoCommand(String todoName) {
        this.todoName = todoName;
    }

    /**
     * Add the todo task into the list.
     * @param tasks the TaskList object that takes in the new todo task
     * @param ui the Ui object responsible for printing messages
     * @param storage the Storage object responsible for saving data in a local txt file
     * @throws UnsavedFile if fail to save data in the local file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UnsavedFile {
        tasks.addTask(new ToDo(this.todoName));
        int currentTaskIndex = tasks.getTaskListSize() - 1;
        ui.printAdded(tasks.getTaskDetails(currentTaskIndex));
        try {
            storage.writeData(tasks.getTaskList());
        } catch (IOException e) {
            throw new UnsavedFile();
        }
    }
}
