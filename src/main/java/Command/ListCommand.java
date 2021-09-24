package Command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a <code>Command</code> to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Instantiates a <code>ListCommand</code> object.
     *
     * @param description Description of the task.
     */
    public ListCommand(String description) {
        super(description, IS_NOT_EXIT);
    }

    /**
     * Prints all tasks in <code>tasks</code> list.
     *
     * @param tasks Tasks to be executed on.
     * @param ui UI to interact with user.
     * @param storage Storage to save task changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        printTasks(ui, tasks);
    }

    private void printTasks(Ui ui, TaskList tasks) {
        ui.printHorizontalLine();
        for (int i = 0; i < getTasksSize(tasks.getTasks()); i++) {
            int taskNumber = i + 1;
            Task task = tasks.getTasks().get(i);
            ui.printTask(taskNumber, task);
        }
        ui.printHorizontalLine();
    }
}
