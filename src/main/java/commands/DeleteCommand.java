package commands;
import storage.Storage;
import task.type.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasksList, Storage storage) {
        tasksList.deleteTaskFromList(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
