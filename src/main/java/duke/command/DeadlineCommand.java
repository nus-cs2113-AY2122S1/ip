package duke.command;

import duke.TaskManager;

public class DeadlineCommand implements Command {
    public static final CommandType type = CommandType.DEADLINE;

    private final String deadlineTitle;
    private final String deadlineDue;

    public DeadlineCommand(String deadlineTitle, String deadlineDue) {
        this.deadlineTitle = deadlineTitle;
        this.deadlineDue = deadlineDue;
    }

    @Override
    public void run() {
        TaskManager.addDeadline(deadlineTitle, deadlineDue);
    }

    @Override
    public CommandType getType() {
        return type;
    }
}
