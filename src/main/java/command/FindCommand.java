package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.UI;

import java.util.ArrayList;
import java.util.Locale;

public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        ui.printMatchingTasks(matchingTasks, matchingTasks.size());
    }
}
