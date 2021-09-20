package duke.command;

import duke.TaskManager;
import duke.task.Task;

public class DeleteCommand implements Command {
    public static final CommandType type = CommandType.DELETE;

    private final int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void run(boolean printMessage) {
        Task task = TaskManager.deleteTask(taskNo);
        if (printMessage) {
            System.out.println("Noted. I've removed this task:");
            System.out.printf("   %s\n", task);
            System.out.printf("Now you have %d tasks in the list.\n", TaskManager.getTasklistSize());
        }
    }

    @Override
    public CommandType getType() {
        return type;
    }
}