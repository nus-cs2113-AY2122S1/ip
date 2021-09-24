package duke.command;

import duke.datasaver.DataManager;
import duke.exception.QueryNotFoundException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes find by creating a list with the tasks containing the keyword and printing the list.
     * {@code userInput} is parsed to extract the keyword (or query) and the query is searched for in {@code taskList}.
     *
     * @param taskList task list to be searched
     */
    @Override
    public void execute(TaskList taskList, DataManager dataManager) {
        try {
            ArrayList<Task> tasksContainingQuery = search(taskList, keyword);
            Ui.printTaskListContainingQuery(tasksContainingQuery, keyword);
        } catch (QueryNotFoundException qnfe) {
            Ui.printQueryNotFoundMessage();
        }
    }

    /**
     * Helper function which searches for a query in {@code taskList} A query is said to be found in a {@code Task} if
     * it is a continuous substring/contained in the task description.
     *
     * @param taskList task list to be searched
     * @param query keyword to be searched for in the task list
     * @return a list of tasks containing the query
     * @throws QueryNotFoundException if the query could not be found in any of the tasks in the task list
     */
    private static ArrayList<Task> search(TaskList taskList, String query) throws QueryNotFoundException {
        ArrayList<Task> taskListContainingQuery = new ArrayList<>();
        ArrayList<Task> taskListToSearch = taskList.getTaskList();
        for (Task t : taskListToSearch) {
            if (t.getDescription().toLowerCase().contains(query.toLowerCase())) {
                taskListContainingQuery.add(t);
            }
        }
        if (taskListContainingQuery.isEmpty()) {
            throw new QueryNotFoundException();
        }
        return taskListContainingQuery;
    }

}
