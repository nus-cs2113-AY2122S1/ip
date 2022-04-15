package duke.commands;

import duke.Storage;
import duke.tasks.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * This class is used to add task into the task list
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Sets up the add command to be execute by the application
     *
     * @param command it is the name of the command
     * @param task it is the task that was parsed by Parser and will be added to the task list
     */
    public AddCommand(String command, Task task) {
        super(command);
        this.task = task;
    }

    /**
     * Executes the command and adds the task into the task list. Save the data in the task
     * list into the file
     *
     * @param tasks the task list that stores task for the application which is manipulated based on the command
     * @param ui the user interface that interacts with users which prints messages based on the different commands
     * @param storage the area which data can be read from and written to, to save task list data permanently
     * @throws IOException is thrown if storage fails to store the updated task list into the file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(task);
        ui.showAddMessage(task,tasks);
        storage.store(tasks);
    }
}
