package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.exception.InvalidTaskException;
import duke.ui.Ui;
import java.io.IOException;

public class DoneCommand extends Command {

    private final int taskNumber;

    /**
     * Setup done command with task number for task to be marked as done
     *
     * @param taskNumber integer given by user input
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * A list command execute method which will print all task in tasks using ui
     *
     * @param tasks   TaskList Class which stores all tasks related information such as task array and handle all task
     *                related operations
     * @param ui      UI class which handles all user interactions
     * @param storage Storage class which allows all loading and saving of task information
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.completeTask(taskNumber);
            storage.saveData(tasks.getTaskList());
            ui.printCompleteTaskMessage(task);
        } catch (InvalidTaskException | IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

}
