package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class TaskDoneCommand extends Command{
    
    private int taskIndex;

    public TaskDoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
     public void execute(TaskList list, Ui ui, Storage storage) {
        if (taskIndex < list.size() && taskIndex >= 0) {
            if (list.getList().get(taskIndex).isDone()) {
                ui.printTaskIsDoneMessage();
            } else {
                list.markTaskAsDone(taskIndex);
                storage.updateFile();
                ui.printMarkTaskAsDoneMessage(list.getList().get(taskIndex));
            }
        } else {
            ui.printTaskDoesNotExistMessage();
        }
     }

    @Override
    public boolean isExit() {
        return false;
    }
}