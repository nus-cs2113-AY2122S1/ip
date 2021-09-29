package duke.command;

import duke.exception.DateError;
import duke.exception.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;


public class DateCommand extends Command {
    private LocalDate key;

    /**
     * Constructor
     *
     * @param dateKey search criteria
     */
    public DateCommand(LocalDate dateKey) {
        key = dateKey;
    }

    /**
     * Executes DateCommand
     *
     * @param tasks TaskList
     * @return appropriate message to be sent to user
     * @throws DateError
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        try {
            ArrayList<Task> tasksDateFound = tasks.findByDate(key);
            returnString = TaskList.listQueryTaskList(tasksDateFound);
            return returnString;
        } catch (Exception e) {
            throw new DateError();
        }
    }
}
