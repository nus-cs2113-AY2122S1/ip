package duke.command;

import duke.task.TaskManager;

public class List extends Command {
    static final String NAME = "list";
    private static final String USAGE = "";

    List(String argument) {
        super(NAME, USAGE, argument);
    }

    boolean isValid(){
        return argument.length() == 0;
    }

    boolean execute() {
        TaskManager.printTasks();
        return true;
    }

}
