package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

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
