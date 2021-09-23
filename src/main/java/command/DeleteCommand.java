package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.UI;

import java.io.IOException;

public class DeleteCommand extends Command {

    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Removes the specified task from both the taro.txt storage file and the internal list of tasks stored by the
     * task manager.
     *
     * @param taskList the TaskList object used for modifying the tasks stored by taro
     * @param ui the UI type object used to handle i/o for taro
     * @param storage the object used for handling read/write operations to the taro.txt data file
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
       try {
            Task deletedTask = taskList.deleteTask(taskIndex);
            storage.writeDeleteTask(taskIndex);
            ui.printTaskDeletedMessage(deletedTask, taskList.getTaskCount());
        } catch (IOException e) {
           ui.printString("there was an error while deleting that task in memory");
       }
    }
}
