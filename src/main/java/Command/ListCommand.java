package Command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand(String description) {
        super(description, IS_NOT_EXIT);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        printTasks(ui, tasks);
    }

    public void printTasks(Ui ui, TaskList tasks) {
        ui.printHorizontalLine();
        for (int i = 0; i < getTasksSize(tasks.getTasks()); i++) {
            int taskNumber = i + 1;
            Task task = tasks.getTasks().get(i);
            ui.printTask(taskNumber, task);
        }
        ui.printHorizontalLine();
    }
}
