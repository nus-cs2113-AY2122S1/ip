package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.time.LocalDate;

public class EventCommand implements Command {
    private static final CommandType type = CommandType.EVENT;
    private final String eventTitle;
    private final LocalDate eventTime;

    /**
     * Event command constructor
     *
     * @param eventTitle Title of event
     * @param eventTime Time of event
     */
    public EventCommand(String eventTitle, String eventTime) {
        this.eventTitle = eventTitle;
        this.eventTime = LocalDate.parse(eventTime);
    }

    /**
     * Adds event to task list and saves changes to data file
     *
     * @param printMessage Print message to user on executing command
     */
    @Override
    public void run(boolean printMessage) {
        Task task = TaskList.addEvent(eventTitle, eventTime);
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
