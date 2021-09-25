package duke.commands;

import duke.storage.DataStorage;
import duke.exception.QueryNotFoundException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to search for a keyword in the task list.
 */
public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes find by creating a list with the tasks containing {@code keyword} and printing the list. {@code keyword}
     * is searched for in {@code taskList}.
     *
     * @param taskList task list to be searched
     */
    @Override
    public void execute(TaskList taskList, DataStorage dataStorage) {
        try {
            ArrayList<Task> tasksContainingQuery = search(taskList, keyword);
            Ui.printTaskListContainingQuery(tasksContainingQuery, keyword);
        } catch (QueryNotFoundException qnfe) {
            Ui.printQueryNotFoundMessage();
        }
    }

    /**
     * Helper function which searches for a keyword in {@code taskList} A keyword is said to be found in a {@code Task} if
     * it is a continuous substring contained in the task description.
     *
     * @param taskList task list to be searched
     * @param keyword keyword to be searched for in the task list
     * @return a list of tasks containing the keyword
     * @throws QueryNotFoundException if the keyword could not be found in any of the tasks in the task list
     */
    private static ArrayList<Task> search(TaskList taskList, String keyword) throws QueryNotFoundException {
        ArrayList<Task> taskListContainingQuery = new ArrayList<>();
        ArrayList<Task> taskListToSearch = taskList.getTaskList();
        for (Task t : taskListToSearch) {
            /* find is case-insensitive */
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                taskListContainingQuery.add(t);
            }
        }
        if (taskListContainingQuery.isEmpty()) {
            throw new QueryNotFoundException();
        }
        return taskListContainingQuery;
    }
}
