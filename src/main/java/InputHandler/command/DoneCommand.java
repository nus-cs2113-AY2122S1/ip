package InputHandler.command;


import InputHandler.exception.TaskNotExistException;

public class DoneCommand extends UserCommand {
    private int index;

    public DoneCommand (int index, TaskList tasks) {
        super(tasks);
        this.index = index;
    }

    @Override
    public void execute () throws TaskNotExistException {
        Task completedTask;
        try {
            completedTask = this.tasks.markAsDone(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotExistException();
        }
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + completedTask);
    }
}
