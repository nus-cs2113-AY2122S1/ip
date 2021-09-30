package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;

public class AddCommand extends Command {

    private final Task task;

    /**
     * Setup add command with task to be added
     *
     * @param task task created according to user input interpreted by Parser class
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * An add command execute method which will add task to tasks as given by user input and notify user of results via
     * ui
     *
     * @param tasks   TaskList Class which stores all tasks related information such as task array and handle all task
     *                related operations
     * @param ui      UI class which handles all user interactions
     * @param storage Storage class which allows all loading and saving of task information
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskNumber = tasks.getSize() + 1;
            task.setTaskNumber(taskNumber);
            tasks.addTask(task);
            storage.saveData(tasks.getTaskList());
            ui.printAddTaskMessage(task, tasks.getSize());
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }

    }

}
