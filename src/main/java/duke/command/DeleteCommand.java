package duke.command;

import duke.TaskManager;

public class DeleteCommand implements Command {
    public static final CommandType type = CommandType.DELETE;

    private final int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void run() {
        TaskManager.deleteTask(taskNo);
    }

    @Override
    public CommandType getType() {
        return type;
    }
}