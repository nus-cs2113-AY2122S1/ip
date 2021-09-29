package duke.command;

import duke.tasks.TaskList;
import duke.exception.DukeException;
import duke.exception.DeadlineCommandError;
import duke.tasks.Deadline;

import java.time.LocalDate;

import static duke.database.Database.autoSaveFile;
import static duke.logic.Logic.listIndex;

public class DeadlineCommand extends Command{
    private String description;
    private LocalDate date;

    /**
     * Constructor
     *
     * @param descriptionArg task description
     * @param dateArg date of deadline
     */
    public DeadlineCommand(String descriptionArg, LocalDate dateArg) {
        description = descriptionArg;
        date = dateArg;
    }

    /**
     * Executes DeadlineCommand
     *
     * @param tasks TaskList
     * @return appropriate message to be sent to user
     * @throws DeadlineCommandError
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        try {
            tasks.addTask(new Deadline(description, date));
            listIndex++;
            returnString = "Got it. I've added this task:\n" + tasks.getTask(listIndex) + "\n"
                    + "Now you have " + String.valueOf(listIndex) + " tasks in the list.";
            autoSaveFile(tasks);
            return returnString;
        } catch (Exception e) {
            throw new DeadlineCommandError();
        }
    }
}
