package alfred.command;

import alfred.task.Task;
import alfred.task.TaskList;
import alfred.ui.TextUi;

public class FindCommand extends Command {
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    public void execute() {
        TaskList filteredList = filterList(query);
        TextUi.printFoundTasks(filteredList);
    }

    private TaskList filterList(String query) {
        TaskList filteredList = new TaskList();
        for (int i = 0; i < taskList.getSize(); i++) {
            Task checkedTask = taskList.getTask(i);
            if (checkedTask.isQueriedTask(query)) {
                filteredList.addTask(checkedTask);
            }
        }
        return filteredList;
    }
}
