package duke.command;

import duke.Utility;
import duke.task.TaskManager;

public class Delete extends Command {
    static final String NAME = "delete";
    private static final String USAGE = " <task number>";

    Delete(String argument) {
        super(NAME, USAGE, argument);
    }

    boolean isValid(){
        return Utility.isInteger(argument);
    }

    boolean execute() {
        int index = Integer.parseInt(argument) - 1;
        TaskManager.deleteTask(index);
        return true;
    }

}
