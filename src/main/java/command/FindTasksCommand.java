package command;

import exception.AustinEmptyListException;
import exception.AustinException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Extracts and prints tasks that matches with the keyboard input by the user
 */
public class FindTasksCommand extends Command {
    public static final String COMMAND_KEYWORD = "find";
    private String keyword;

    /**
     * Constructs FindTasksCommand
     *
     * @param keyword The keyword used to filter tasks
     */
    public FindTasksCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes FindTasksCommand by printing all the tasks which contain the
     * keyword.
     *
     * @param tasks The task list which stores the task
     * @param ui In charge of informing the user that the command has been executed
     * @param storage In charge of reading and writing to data file
     * @throws AustinException If the list is empty
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AustinEmptyListException {
        ArrayList<Task> filteredList = tasks.findTasks(keyword);
        if (filteredList.size() == 0) {
            System.out.println("There are no matching tasks based on the " +
                    "keyword you have given.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            Ui.printList(filteredList);
        }
    }
}
