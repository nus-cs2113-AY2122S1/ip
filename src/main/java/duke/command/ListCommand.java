package duke.command;

import duke.TaskList;

public class ListCommand implements Command {
    private static final CommandType type = CommandType.LIST;

    /**
     * Lists all Tasks in task list
     *
     * @param printMessage Print message to user on executing command. Value is irrelevant
     *                     as values will be printed out anyways
     */
    @Override
    public void run(boolean printMessage) {
        TaskList.listTasks();
    }

    /**
     * @return Type of command
     */
    @Override
    public CommandType getType() {
        return type;
    }
}
