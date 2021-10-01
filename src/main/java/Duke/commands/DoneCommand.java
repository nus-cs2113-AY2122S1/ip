package Duke.commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class DoneCommand extends Command {
    private int taskNumber;

    /**
     * Class constructor for DoneCommand
     *
     * @param taskNumber index of Task in tasks
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks task as done
     * Saves tasks to file
     *
     * @param tasks TaskList containing Task
     * @param ui User interface
     * @param storage File management system
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.doneTask(taskNumber, ui);
        storage.writeToFile(tasks);
    }
}
