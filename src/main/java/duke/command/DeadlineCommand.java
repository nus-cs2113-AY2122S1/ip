package duke.command;

import duke.TaskManager;
import duke.task.Task;

public class DeadlineCommand implements Command {
    private static final CommandType type = CommandType.DEADLINE;
    private final String deadlineTitle;
    private final String deadlineDue;

    /**
     * Deadline command constructor
     *
     * @param deadlineTitle Title of deadline
     * @param deadlineDue Due date of deadline
     */
    public DeadlineCommand(String deadlineTitle, String deadlineDue) {
        this.deadlineTitle = deadlineTitle;
        this.deadlineDue = deadlineDue;
    }

    /**
     * Adds deadline to task list and saves changes to data file
     *
     * @param printMessage Print message to user on executing command
     */
    @Override
    public void run(boolean printMessage) {
        Task task = TaskManager.addDeadline(deadlineTitle, deadlineDue);
        if (printMessage) {
            System.out.print("Got it. I've added this task:\n");
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
