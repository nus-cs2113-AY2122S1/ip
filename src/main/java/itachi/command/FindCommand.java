package itachi.command;

import itachi.Storage;
import itachi.TaskList;
import itachi.exception.ItachiException;

/**
 * Finds an existing task in the task list from the given keyword
 */
public class FindCommand extends Command {
    protected String keyword;

    /**
     * Constructor initialises the command object with the given keyword to be searched for
     *
     * @param keyword is the word to be searched for in the existing task list
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes Find task command to find the task with the given keyword from the task list
     *
     * @param taskList is an object of TaskList that consists of task operations
     * @param storage  is an object of Storage that saves current list of tasks
     * @throws ItachiException if any error occurs within finding the tasks
     */
    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) throws ItachiException {
        taskList.findTask(keyword);
    }
}
