package duke.command;

import duke.Parser;
import duke.task.TaskManager;

public class Find extends Command {
    static final String NAME = "find";
    private static final String USAGE = " <Description>";
    private static final boolean CONTINUE_EXECUTING = true;

    Find(String argument) {
        super(NAME, USAGE, argument);
    }

    /**
     * Command is valid when argument is not empty
     */
    boolean isValid(){
        return argument.length() != 0;
    }

    /**
     * passes the argument to TaskManager for finding the tasks with description.
     *
     * @return true
     */
    boolean execute() {
        TaskManager.findTasks(argument);
        return CONTINUE_EXECUTING;
    }

}
