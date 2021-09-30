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
     * Command is valid when argument is an integer
     */
    boolean isValid(){
        return Utility.isInteger(argument);
    }


    /**
     * parses argument to integer and then passes the job of deletion to TaskManager
     *
     * @return true
     */
    boolean execute() {
        int index = Integer.parseInt(argument) - 1;
        TaskManager.deleteTask(index);
        return CONTINUE_EXECUTING;
    }

}
