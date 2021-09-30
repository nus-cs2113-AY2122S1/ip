package duke.commands;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    int index;

    /**
     * Command when user wants to delete a Task (e.g. Todo, Deadline, Event).
     * When executed, it will find the task to the TaskList, delete it, and return a successful delete message.
     *
     * @param index Index of the task in the TaskList, to be deleted.
     */

    public DeleteCommand(int index){
        this.index = index;
    }

    public void execute(TaskList taskList, Ui ui) {
        ui.deleteTask(index, taskList);
        taskList.remove(index);
    }

    public boolean isExit(){
        return false;
    }
}