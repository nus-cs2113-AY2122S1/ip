package duke.command;

import duke.TaskManager;
import duke.task.Task;

public class DoneCommand implements Command {
    public static final CommandType type = CommandType.DONE;
    private final int taskNo;

    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void run(boolean printMessage) {
        Task task = TaskManager.markTaskNoAsDone(taskNo);
        if (printMessage) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.printf("   %s\n", task);
        }
    }

    @Override
    public CommandType getType() {
        return type;
    }
}
