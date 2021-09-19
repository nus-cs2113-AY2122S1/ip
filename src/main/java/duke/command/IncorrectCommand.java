package duke.command;

import duke.tasks.TaskList;

public class IncorrectCommand extends Command {

    @Override
    public String execute(TaskList tasks) {
        returnString = "OH NO! I do not know what command is that!";
        return  returnString;
    }
}
