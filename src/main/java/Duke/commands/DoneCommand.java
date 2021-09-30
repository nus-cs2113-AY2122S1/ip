package Duke.commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class DoneCommand extends Command {
    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.doneTask(taskNumber, ui);
        storage.writeToFile(tasks);
    }
}
