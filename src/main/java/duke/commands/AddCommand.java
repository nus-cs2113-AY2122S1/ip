package duke.commands;
import duke.TaskList;
import duke.tasks.*;
import duke.Ui;

public class AddCommand extends Command {

    Task task;

    /**
     * Command when user wants to add a Task (e.g. Todo, Deadline, Event).
     * When executed, it will add the task to the TaskList, and return a successful add message.
     *
     * @param t Task to be added to the TaskList.
     */

    public AddCommand(Task t){
        this.task = t;
    }

    public void execute(TaskList taskList, Ui ui) {
        taskList.add(task);
        ui.addTask(task, taskList);
    }

    public boolean isExit(){
        return false;
    }
}
