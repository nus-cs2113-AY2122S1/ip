package duke.command;

import duke.TaskManager;

public class DoneCommand implements Command {
    public static final CommandType type = CommandType.DONE;
    private final int taskNo;

    public DoneCommand(int taskNo) {
        this.taskNo = taskNo - 1;
    }

    @Override
    public void run() {
        TaskManager.markTaskNoAsDone(taskNo);
    }

    @Override
    public CommandType getType() {
        return type;
    }
}
