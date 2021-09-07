package InputHandler.command;

import InputHandler.exception.DukeException;
import InputHandler.exception.TaskNotExistException;

public class DeleteCommand extends UserCommand{
    private int index;

    public DeleteCommand (int index, TaskList tasks) {
        super(tasks);
        this.index = index;
    }

    @Override
    public void execute () throws TaskNotExistException {
        Task deletedTask;
        try {
            deletedTask = this.tasks.deleteTask(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotExistException();
        }
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + deletedTask);
        System.out.println("     Now you have " + this.tasks.getTotalTaskNumber() + " tasks in the list.");
    }
}
