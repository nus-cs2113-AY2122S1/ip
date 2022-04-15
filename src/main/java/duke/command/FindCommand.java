package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.exception.InvalidTaskIndexException;
import duke.task.exception.TaskListEmptyException;
import java.util.ArrayList;

public class FindCommand extends Command {

    private String searchTerm;

    public FindCommand(String searchTerm) {
        super();
        this.searchTerm = searchTerm.toLowerCase();
    }

    /**
     * Print the tasks that contains the search term provided.
     *
     * @param taskList The current task list.
     */
    @Override
    public void execute(TaskList taskList) {
        ArrayList<Task> filteredTaskList = new ArrayList<>();

        int numTotalTasks = taskList.getTotalTasks();
        for (int i = 0; i < numTotalTasks; i++) {
            try {
                Task task = taskList.getTask(i);
                String taskDescription = task.getRawDescription().toLowerCase();
                if (taskDescription.contains(searchTerm)) {
                    filteredTaskList.add(task);
                }
            } catch (TaskListEmptyException e) {
                ui.printTaskListEmptyError();
                return;
            } catch (InvalidTaskIndexException e) {
                ui.printInvalidTaskIndexError();
                return;
            }
        }

        ui.printFoundTask(filteredTaskList);
    }
}
