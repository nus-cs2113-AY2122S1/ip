package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class DeleteCommand extends Command{
    
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
     public void execute(TaskList list, Ui ui, Storage storage) {
        if (taskIndex < list.size() && taskIndex >= 0) {
            ui.printRemoveTaskMessge(list.getList().get(taskIndex), list.size()-1);     
            list.deleteTask(taskIndex);
            storage.updateFile();
        } else {
            ui.printTaskDoesNotExistMessage();
        }
     }

    @Override
    public boolean isExit() {
        return false;
    }
}