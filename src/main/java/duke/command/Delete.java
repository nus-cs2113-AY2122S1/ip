package duke.command;

import duke.Utility;
import duke.task.TaskManager;

public class Delete extends Command {
    static final String NAME = "delete";
    private static final String USAGE = " <task number>";
    private static final boolean CONTINUE_EXECUTING = true;

    Delete(String argument) {
        super(NAME, USAGE, argument);
    }

    /**
     * Returns boolean on whether the command argument is an integer.
     */
    boolean isValid() {
        return Utility.isInteger(argument);
    }


    /**
     * Returns <code>true</code>.
     * Parses argument to integer and then passes the job of deletion to TaskManager.
     *
     * @return true
     */
    boolean execute() {
        int index = Integer.parseInt(argument) - 1;
        TaskManager.deleteTask(index);
        return CONTINUE_EXECUTING;
    }

}
