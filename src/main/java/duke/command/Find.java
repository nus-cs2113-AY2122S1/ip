package duke.command;

import duke.Parser;
import duke.task.TaskManager;

public class Find extends Command {
    static final String NAME = "find";
    private static final String USAGE = " <Description>";

    Find(String argument) {
        super(NAME, USAGE, argument);
    }

    boolean isValid(){
        return argument.length() != 0;
    }

    boolean execute() {
        TaskManager.findTasks(argument);
        return true;
    }

}
