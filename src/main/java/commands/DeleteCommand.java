package commands;

import exceptions.TaskNotExistException;
import tasks.Task;
import tasks.TaskList;


/**
 * A class to handle commands of deleting certain task inside the task list.
 */
public class DeleteCommand extends UserCommand{
    private int index;

    public DeleteCommand (int index, TaskList tasks) {
        super(tasks);
        this.index = index;
    }

    @Override
    public String execute () throws TaskNotExistException {
        Task deletedTask;
        try {
            deletedTask = this.tasks.deleteTask(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotExistException();
        }

        String result = "     Noted. I've removed this task:\n";
        result += "       " + deletedTask;
        result += "     Now you have " + this.tasks.getTotalTaskNumber() + " tasks in the list.";
        return result;
    }
}
