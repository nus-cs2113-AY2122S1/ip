package duke.command;

import duke.exception.FindCommandError;
import duke.tasks.TaskList;

public class IncorrectCommand extends Command {

    /**
     * Executes IncorrectCommand
     *
     * @param tasks TaskList
     * @return appropriate message to be sent to user
     */
    @Override
    public String execute(TaskList tasks) {
        returnString = "OH NO! I do not know what command is that!";
        return  returnString;
    }
}
