package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Class that defines and represents the behaviour of the Find command
 */
public class FindCommand extends Command{
    protected String keyword;

    /**
     * Constructor initialises Find command with the keyword to look out for in every task
     * @param keyword Keyword given by user to filter tasks
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes Command and finds every task with the given keyword
     * @param taskList Task Manager that executes task based on the given command
     * @param storage Stores User data in a csv file
     */
    @Override
    public void executeCommand(TaskList taskList, Storage storage) {
        taskList.findTask(keyword);
    }
}
