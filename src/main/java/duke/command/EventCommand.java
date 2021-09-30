package duke.command;

import duke.TaskManager;
import duke.task.Task;

public class EventCommand implements Command {
    private static final CommandType type = CommandType.EVENT;
    private final String eventTitle;
    private final String eventTime;

    /**
     * Event command constructor
     *
     * @param eventTitle Title of event
     * @param eventTime Time of event
     */
    public EventCommand(String eventTitle, String eventTime) {
        this.eventTitle = eventTitle;
        this.eventTime = eventTime;
    }

    /**
     * Adds event to task list and saves changes to data file
     *
     * @param printMessage Print message to user on executing command
     */
    @Override
    public void run(boolean printMessage) {
        Task task = TaskManager.addEvent(eventTitle, eventTime);
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
