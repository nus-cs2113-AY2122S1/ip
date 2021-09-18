package commands;

import console.InputParser;
import task.TaskManager;

/**
 * Adds a new 'event' type task to TaskManager.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    /**
     * Creates a new event command and set the TaskManager object to perform operations on.
     *
     * @param taskManager The TaskManager object to execute commands on.
     */
    public EventCommand(TaskManager taskManager) {
        super(taskManager);
    }

    /**
     * Adds a new 'event' task.
     *
     * @return The event command type.
     */
    @Override
    public String executeCommand() {
        taskManager.addEventTask(InputParser.getTaskDetails(commandComponents));
        return COMMAND_WORD;
    }
}
