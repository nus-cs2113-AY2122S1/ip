package commands;

import console.InputParser;
import error.DukeTaskNameEmptyException;
import error.Error;
import task.TaskManager;

import java.time.format.DateTimeParseException;

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
        try {
            taskManager.addEventTask(InputParser.getTaskDetails(commandComponents));
        } catch (IndexOutOfBoundsException e) {
            Error.displayTaskFormatError();
        } catch (DukeTaskNameEmptyException e) {
            Error.displayTaskNameEmptyError();
        } catch (DateTimeParseException e) {
            Error.displayDateFormatError();
        }
        return COMMAND_WORD;
    }
}
