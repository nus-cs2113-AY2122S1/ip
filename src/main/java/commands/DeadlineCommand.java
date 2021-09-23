package commands;

import console.InputParser;
import error.DukeTaskNameEmptyException;
import error.Error;
import task.TaskManager;

import java.time.format.DateTimeParseException;

/**
 * Add a new 'deadline' type task to TaskManager.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    /**
     * Creates a new deadline command and set the TaskManager object to perform operations on.
     *
     * @param taskManager The TaskManager object to execute commands on.
     */
    public DeadlineCommand(TaskManager taskManager) {
        super(taskManager);
    }

    /**
     * Add a new 'deadline' task.
     *
     * @return The deadline command type.
     */
    @Override
    public String executeCommand() {
        try {
            taskManager.addDeadlineTask(InputParser.getTaskDetails(commandComponents));
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
