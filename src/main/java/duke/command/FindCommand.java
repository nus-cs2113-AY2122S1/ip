package duke.command;

import duke.DukeException;
import duke.storage.TaskList;
import duke.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The command to find a task contains a given keyword
 * This class implements the <code>Command</code> interface
 */
public class FindCommand implements Command {
    private String keyword;

    /**
     * Constructor method for <code>FindCommand</code>
     *
     * @param keyword the keyword to look for a task in the task list
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command
     *
     * @return the list of tasks as string that have the given keyword
     * @throws DukeException if tasks cannot be loaded from memory
     */
    public String run() throws DukeException {
        int i;
        String resultMsg;
        ArrayList<Task> taskList = TaskList.getInstance().getTaskList();
        ArrayList<Task> filteredTasks = (ArrayList<Task>) taskList.stream()
                .filter((t) -> t.getTaskDescription().contains(keyword))
                .collect(Collectors.toList());

        if (filteredTasks.size() == 0) {
            resultMsg = "No matching task and keyword found.";
            return resultMsg;
        }

        resultMsg = "Here are the matching tasks with you query:" + '\n' + '\t';
        for (i = 0; i < filteredTasks.size() - 1; i++) {
            resultMsg += (i + 1) + "."
                    + filteredTasks.get(i).toString() + '\n' + '\t';
        }
        resultMsg = resultMsg + filteredTasks.size() + "."
                + filteredTasks.get(i).toString();

        return resultMsg;
    }
}
