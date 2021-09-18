package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskManager;

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

    public DeleteTaskCommand(String argument) {
        super(argument);
    }

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
        Task deletedTask = taskManager.deleteTask(taskNumber);
        CommandResult result = new CommandResult(
                DELETE_TASK_MESSAGE + "\n" + deletedTask.toString() + "\n"
                        + "You have " + TaskManager.getCurrentTasksCount() + " tasks in your list now!");
        return result;
    }
}
