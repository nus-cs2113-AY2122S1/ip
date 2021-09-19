package duke;

import duke.tasks.Task;
import java.util.ArrayList;

public class IncorrectCommand extends Command {

    @Override
    public String execute(TaskList tasks) {
        returnString = "OH NO! I do not know what command is that!";
        return  returnString;
    }
}
