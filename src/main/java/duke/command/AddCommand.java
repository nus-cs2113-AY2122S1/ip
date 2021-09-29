package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;

public class AddCommand extends Command{

    private Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addTask(task);
            storage.saveData(taskList.getTaskList());
            ui.printAddTaskMessage(task, taskList.getSize());
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }

    }

}
