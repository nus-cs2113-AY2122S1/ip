package commands;
import exception.DukeException;
import storage.Storage;
import task.type.TaskList;

public class ExecuteDoneCommand extends Command {
    private int index;
    public ExecuteDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasksList, Storage storage) throws DukeException {
        try {
            tasksList.markTaskAsDone(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
