package duke.command;

import duke.exception.DukeException;
import duke.exception.EventCommandError;
import duke.exception.FindCommandError;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

import static duke.database.Database.autoSaveFile;
import static duke.logic.Logic.listIndex;



public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String searchTerm) {
        keyword = searchTerm;
    }

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
