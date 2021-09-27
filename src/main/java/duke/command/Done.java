package duke.command;

import duke.Utility;
import duke.task.TaskManager;

public class Done extends Command {
    static final String NAME = "done";
    private static final String USAGE = " <task number>";

    Done(String argument) {
        super(NAME, USAGE, argument);
    }

    boolean isValid(){
        return Utility.isInteger(argument);
    }

    boolean execute(){
        int index = Integer.parseInt(argument) - 1;
        TaskManager.taskDone(index);
        return true;
    }

}
