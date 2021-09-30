package duke.command;

import duke.TaskManager;
import duke.task.Task;

public class DeleteCommand implements Command {
    private static final CommandType type = CommandType.DELETE;
    private final int taskNo;

    /**
     * Delete command constructor
     *
     * @param taskNo Task number of task stored in task list to be deleted
     */
    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Deletes task in task list and saves changes to data file
     *
     * @param printMessage Print message to user on executing command
     */
    @Override
    public void run(boolean printMessage) {
        Task task = TaskManager.deleteTask(taskNo);
        if (printMessage) {
            System.out.println("Noted. I've removed this task:");
            System.out.printf("   %s\n", task);
            System.out.printf("Now you have %d tasks in the list.\n", TaskManager.getTasklistSize());
        }
    }

    /**
     * @return Type of command
     */
    @Override
    public CommandType getType() {
        return type;
    }
}