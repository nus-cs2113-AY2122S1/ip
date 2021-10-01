package Duke.commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Class constructor for DeleteCommand
     *
     * @param taskNumber index of Task in taskList
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes task from tasks
     * Saves current tasks to file
     *
     * @param tasks TaskList containing Task
     * @param ui User interface
     * @param storage File management system
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(taskNumber, ui);
        storage.writeToFile(tasks);
    }
}
