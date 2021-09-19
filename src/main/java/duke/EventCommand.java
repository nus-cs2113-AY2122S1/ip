package duke;

import duke.exception.DukeException;
import duke.exception.EventCommandError;
import duke.tasks.Event;
import duke.tasks.Task;
import java.util.ArrayList;

import static duke.Database.autoSaveFile;
import static duke.Logic.listIndex;

public class EventCommand extends Command{
    private String description;
    private String date;

    public EventCommand(String descriptionArg, String dateArg) {
        description = descriptionArg;
        date = dateArg;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        try {
            tasks.addTask(new Event(description, date));
            listIndex++;
            returnString = "Got it. I've added this task:\n" + tasks.getTask(listIndex) + "\n"
                    + "Now you have " + String.valueOf(listIndex) + " tasks in the list.";
            autoSaveFile(tasks);
            return returnString;
        } catch (Exception e) {
            throw new EventCommandError();
        }
    }
}
