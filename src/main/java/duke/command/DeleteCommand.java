package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.exception.InvalidTaskException;
import duke.ui.Ui;
import java.io.IOException;

public class DeleteCommand extends Command {

    private final int taskNumber;

    /**
     * Setup delete command with task number for task to be deleted
     *
     * @param taskNumber integer given by user input
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * A delete command execute method which will delete task in tasks as requested by user and display what was deleted
     * by user using ui
     *
     * @param tasks   TaskList Class which stores all tasks related information such as task array and handle all task
     *                related operations
     * @param ui      UI class which handles all user interactions
     * @param storage Storage class which allows all loading and saving of task information
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.deleteTask(taskNumber);
            storage.saveData(tasks.getTaskList());
            ui.printDeleteTaskMessage(task, tasks.getTaskList().size());
        } catch (InvalidTaskException | IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
