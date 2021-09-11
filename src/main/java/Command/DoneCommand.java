package Command;

import Tasks.TaskList;
import Exception.DukeException;
import Exception.InvalidDoneCommandException;
import Exception.TaskNotExistException;

public class DoneCommand extends UserCommand {
    private int index;

    public DoneCommand (int index, TaskList tasks) {
        super(tasks);
        this.index = index;
    }

    @Override
    public void execute () throws TaskNotExistException {
        try {
            this.tasks.markAsDone(index - 1);
            System.out.println("     Nice! I've marked this task as done: ");
        } catch (NullPointerException e) {
            throw new TaskNotExistException();
        }
    }
}
