package commands;

import tasks.Task;
import tasks.TaskList;
import exceptions.TaskNotExistException;


/**
 * A class to handle commands of setting certain task done.
 */
public class DoneCommand extends UserCommand {
    private int index;

    public DoneCommand (int index, TaskList tasks) {
        super(tasks);
        this.index = index;
    }

    @Override
    public String execute () throws TaskNotExistException {
        Task completedTask;
        try {
            completedTask = this.tasks.markAsDone(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotExistException();
        }

        String result = "     Nice! I've marked this task as done: \n";
        result += "       " + completedTask;
        return result;
    }
}
