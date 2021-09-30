package duke.command;

import duke.TaskManager;
import duke.task.Task;

public class DoneCommand implements Command {
    private static final CommandType type = CommandType.DONE;
    private final int taskNo;

    /**
     * Done command constructor
     *
     * @param taskNo Task number of task stored in task list to be marked as done
     */
    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Marks task at specified index of task list as done and saves changes to data file
     *
     * @param printMessage Print message to user on executing command
     */
    @Override
    public void run(boolean printMessage) {
        Task task = TaskManager.markTaskNoAsDone(taskNo);
        if (printMessage) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.printf("   %s\n", task);
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
