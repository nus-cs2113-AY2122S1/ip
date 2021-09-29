package duke.command;

import duke.exception.FindCommandError;
import duke.tasks.TaskList;
import duke.exception.DukeException;
import duke.exception.ToDoCommandError;
import duke.tasks.Todo;

import static duke.database.Database.autoSaveFile;
import static duke.logic.Logic.listIndex;

public class ToDoCommand extends Command{
    private String description;

    /**
     * Constructor
     *
     * @param arg description of task
     */
    public ToDoCommand(String arg) {
        description = arg;
    }

    /**
     * Executes ToDoCommand
     *
     * @param tasks TaskList
     * @return appropriate message to be sent to user
     * @throws ToDoCommandError
     */
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
