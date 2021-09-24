package duke.commands;

import duke.Storage;
import duke.tasks.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * This class is used to mark a task in the task list as completed
 */
public class DoneCommand extends Command{
    private int taskIndex;

    /**
     * Sets up the done command to be execute by the application
     *
     * @param command it is the name of the command
     * @param taskIndex it is the index of the task that needs to be marked as completed
     */
    public DoneCommand(String command, int taskIndex) {
        super(command);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command and mark the task at the taskIndex as done in the task list. Save the data in the task
     * list into the file
     *
     * @param tasks the task list that stores task for the application which is manipulated based on the command
     * @param ui the user interface that interacts with users which prints messages based on the different commands
     * @param storage the area which data can be read from and written to, to save task list data permanently
     * @throws IOException is thrown if storage fails to store the updated task list into the file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getTask(taskIndex);
        if (task.isDone()){
            ui.showAlreadyDoneMessage();
        } else {
            ui.showDoneMessage(tasks.doneTask(taskIndex));
        }
        storage.store(tasks);
    }
}
