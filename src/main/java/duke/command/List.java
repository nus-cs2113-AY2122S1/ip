package duke.command;

import duke.task.TaskManager;

public class List extends Command {
    static final String NAME = "list";
    private static final String USAGE = "";
    private static final boolean CONTINUE_EXECUTING = true;

    List(String argument) {
        super(NAME, USAGE, argument);
    }

    /**
     * Command is valid when argument is empty
     */
    boolean isValid() {
        return argument.length() == 0;
    }

    /**
     * passes the argument to TaskManager for listing the tasks.
     *
     * @return true
     */
    boolean execute() {
        TaskManager.printTasks();
        return CONTINUE_EXECUTING;
    }

}
