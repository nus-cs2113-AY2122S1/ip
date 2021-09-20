package duke.command;

import duke.TaskManager;
import duke.task.Task;

public class EventCommand implements Command {
    public static final CommandType type = CommandType.EVENT;

    private final String eventTitle;
    private final String eventTime;

    public EventCommand(String eventTitle, String eventTime) {
        this.eventTitle = eventTitle;
        this.eventTime = eventTime;
    }

    @Override
    public void run(boolean printMessage) {
        Task task = TaskManager.addEvent(eventTitle, eventTime);
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
