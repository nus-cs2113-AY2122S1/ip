package duke.command;

import duke.Utility;
import duke.task.TaskManager;

public class Done extends Command {
    static final String NAME = "done";
    private static final String USAGE = " <task number>";
    private static final boolean CONTINUE_EXECUTING = true;

    Done(String argument) {
        super(NAME, USAGE, argument);
    }

    /**
     * Returns boolean on whether the argument is an integer.
     */
    boolean isValid() {
        return Utility.isInteger(argument);
    }

    /**
     * Returns <code>true</code>.
     * Parses argument to integer and then passes the job of marking the task as done to TaskManager.
     *
     * @return true
     */
    boolean execute() {
        int index = Integer.parseInt(argument) - 1;
        TaskManager.taskDone(index);
        return CONTINUE_EXECUTING;
    }

}
