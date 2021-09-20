package duke.command;

import duke.exception.DateError;
import duke.exception.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;


public class DateCommand extends Command {
    private LocalDate key;

    public DateCommand(LocalDate dateKey) {
        key = dateKey;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        try {
            ArrayList<Task> tasksDateFound = TaskList.findByDate(key, tasks);
            returnString = TaskList.listDateTaskList(tasksDateFound);
            return returnString;
        } catch (Exception e) {
            throw new DateError();
        }
    }
}
