package duke.command;

import duke.TaskList;
import duke.task.Task;
import duke.Ui;
import java.time.LocalDate;

public class DeadlineCommand implements Command {
    private static final CommandType type = CommandType.DEADLINE;
    private final String deadlineTitle;
    private final LocalDate deadlineDue;

    /**
     * Deadline command constructor
     *
     * @param deadlineTitle Title of deadline
     * @param deadlineDue Due date of deadline in yyyy-mm-dd format
     */
    public DeadlineCommand(String deadlineTitle, String deadlineDue) {
        this.deadlineTitle = deadlineTitle;
        this.deadlineDue = LocalDate.parse(deadlineDue);
    }

    /**
     * Adds deadline to task list and saves changes to data file
     *
     * @param printMessage Print message to user on executing command
     */
    @Override
    public void run(boolean printMessage) {
        Task task = TaskList.addDeadline(deadlineTitle, deadlineDue);
        if (printMessage) {
            Ui.printTaskAddedMessage(task);
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
