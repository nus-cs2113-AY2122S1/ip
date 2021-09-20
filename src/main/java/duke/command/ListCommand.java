package duke.command;

import duke.TaskManager;

public class ListCommand implements Command {
    public static final CommandType type = CommandType.LIST;

    @Override
    public void run(boolean printMessage) {
        TaskManager.listTasks();
    }

    @Override
    public CommandType getType() {
        return type;
    }
}
