package duke.command;

import duke.exception.DoneListIndexError;
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

    /**
     * Constructor
     *
     * @param descriptionArg description of task
     * @param dateArg date of task
     */
    public EventCommand(String descriptionArg, LocalDate dateArg) {
        description = descriptionArg;
        date = dateArg;
    }

    /**
     * Executes EventCommand
     *
     * @param tasks TaskList
     * @return appropriate message to be sent to user
     * @throws EventCommandError
     */
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
