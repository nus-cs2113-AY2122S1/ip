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
     * Returns boolean on whether the argument is not empty
     */
    boolean isValid() {
        return argument.length() != 0;
    }

    /**
     * Returns <code>true</code>
     * Passes the argument to TaskManager for finding the tasks with description.
     *
     * @return true
     */
    boolean execute() {
        TaskManager.findTasks(argument);
        return CONTINUE_EXECUTING;
    }

}
