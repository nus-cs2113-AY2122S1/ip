package commands;
import storage.Storage;
import task.type.Task;
import task.type.TaskList;
import ui.UI;
import java.util.ArrayList;

public class ExecuteFind extends Command {
    private String keyword;

    public ExecuteFind(String filter) {
        this.keyword = filter;
    }

    @Override
    public void execute(TaskList tasksList, Storage storage) {
        ArrayList<Task> filteredTasks = tasksList.findTasks(keyword);
        UI.printListFoundTasks(filteredTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
