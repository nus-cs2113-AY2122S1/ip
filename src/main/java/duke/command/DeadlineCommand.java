package duke.command;

import duke.TaskManager;
import duke.task.Task;

public class DeadlineCommand implements Command {
    public static final CommandType type = CommandType.DEADLINE;

    private final String deadlineTitle;
    private final String deadlineDue;

    public DeadlineCommand(String deadlineTitle, String deadlineDue) {
        this.deadlineTitle = deadlineTitle;
        this.deadlineDue = deadlineDue;
    }

    @Override
    public void run(boolean printMessage) {
        Task task = TaskManager.addDeadline(deadlineTitle, deadlineDue);
        if (printMessage) {
            System.out.print("Got it. I've added this task:\n");
            System.out.printf("   %s\n", task);
            System.out.printf("Now you have %d tasks in the list.\n", TaskManager.getTasklistSize());
        }
    }

    @Override
    public CommandType getType() {
        return type;
    }
}
