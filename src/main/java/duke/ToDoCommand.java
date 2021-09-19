package duke;

import duke.exception.DukeException;
import duke.exception.ToDoCommandError;
import duke.tasks.Task;
import duke.tasks.Todo;
import java.util.ArrayList;

import static duke.Database.autoSaveFile;
import static duke.Logic.listIndex;

public class ToDoCommand extends Command{
    private String description;

    public ToDoCommand(String arg) {
        description = arg;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        try {
            tasks.addTask(new Todo(description));
            listIndex++;
            returnString = "Got it. I've added this task:\n" + tasks.getTask(listIndex) + "\n"
                    + "Now you have " + String.valueOf(listIndex) + " tasks in the list.";
            autoSaveFile(tasks);
            return returnString;
        } catch (Exception e) {
            throw new ToDoCommandError();
        }
    }
}
