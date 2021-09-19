package duke.command;

import duke.tasks.TaskList;
import duke.exception.DeadLineCommandError;
import duke.exception.DukeException;
import duke.tasks.Deadline;

import static duke.database.Database.autoSaveFile;
import static duke.logic.Logic.listIndex;

public class DeadlineCommand extends Command{
    private String description;
    private String date;

    public DeadlineCommand(String descriptionArg, String dateArg) {
        description = descriptionArg;
        date = dateArg;
    }

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
            throw new DeadLineCommandError();
        }
    }
}
