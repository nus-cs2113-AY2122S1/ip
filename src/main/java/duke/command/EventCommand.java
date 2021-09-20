package duke.command;

import duke.tasks.TaskList;
import duke.exception.DukeException;
import duke.exception.EventCommandError;
import duke.tasks.Event;

import java.time.LocalDate;

import static duke.database.Database.autoSaveFile;
import static duke.logic.Logic.listIndex;

public class EventCommand extends Command{
    private String description;
    private LocalDate date;

    public EventCommand(String descriptionArg, LocalDate dateArg) {
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
