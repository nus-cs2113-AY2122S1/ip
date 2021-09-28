package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskManager;

/** Includes the operations needed to delete a task from the current task list. */
public class DeleteTaskCommand extends Command {

    private static final String DELETE_TASK_MESSAGE = "Alright, I have deleted the following task for you:";
    private static final String MISSING_NUMBER_ERROR =
            "OH NO! You need to specify the task number...\n"
                    + "Enter \"list\" to check the task number!";
    private static final String NUMBER_FORMAT_ERROR = "OH NO! That wasn't a number...";
    private static final String NUMBER_NOT_FOUND_ERROR =
            "OH NO! The task number is invalid, I can't find any tasks matching that number...\n"
                    + "Enter \"list\" to check the task number!";
    private int taskNumber;

    /**
     * Constructed when the command word of the user input is "delete".
     *
     * @param argument Argument provided by the user after separating the command word from the user input string
     */
    public DeleteTaskCommand(String argument) {
        super(argument);
    }

    /**
     * Gets the task number from the argument provided by the user.
     *
     * @param argument Argument provided by the user after separating the command word from the user input string
     * @return Task number corresponding to the {@code Task} that needs to be deleted
     * @throws DukeException If the argument provided is empty, not an integer, or an integer that does not
     * correspond to any task in the task list.
     */
    private int retrieveNumberParameter(String argument) throws DukeException {

        int taskNumber;

        if (isEmptyArgument(argument)) {
            throw new DukeException(MISSING_NUMBER_ERROR);
        }
        try {
            taskNumber = Integer.parseInt(argument);
        } catch (NumberFormatException exception) {
            throw new DukeException(NUMBER_FORMAT_ERROR);
        }
        if (!TaskManager.isValidTaskNumber(taskNumber)) {
            throw new DukeException(NUMBER_NOT_FOUND_ERROR);
        }

        return taskNumber;
    }

    @Override
    public CommandResult executeCommand() throws DukeException {
        taskNumber = retrieveNumberParameter(argument);
        Task deletedTask = TaskManager.deleteTask(taskNumber);
        CommandResult result = new CommandResult(
                DELETE_TASK_MESSAGE + "\n" + deletedTask.toString() + "\n"
                        + "You have " + TaskManager.getCurrentTasksCount() + " tasks in your list now!");
        return result;
    }
}
