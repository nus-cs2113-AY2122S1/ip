package duke.command;

import duke.exception.DukeException;
import duke.exception.EventCommandError;
import duke.exception.FindCommandError;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String keyword;

    /**
     * Constructor
     *
     * @param searchTerm search criteria
     */
    public FindCommand(String searchTerm) {
        keyword = searchTerm;
    }

    /**
     * Executes FindCommand
     *
     * @param tasks TaskList
     * @return appropriate message to be sent to user
     * @throws FindCommandError
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        try {
            ArrayList<Task> filteredList = tasks.findTask(keyword);
            returnString = TaskList.listQueryTaskList(filteredList);
            return returnString;
        } catch (Exception e) {
            throw new FindCommandError();
        }
    }
}
