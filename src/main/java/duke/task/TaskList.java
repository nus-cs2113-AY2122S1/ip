package duke.task;

import duke.command.Command;
import duke.command.CommandResult;
import duke.exception.DukeInvalidAddTaskException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor of TaskList object, initializing the task list to be that of 
     * the input tasks.
     * 
     * @param tasks input task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Return the tasks stored in this list.
     * 
     * @return list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Return success result for the task list to be shown to the user.
     * 
     * @param userCommand input command from user.
     * @return result of the command.
     */
    private CommandResult executeListCommand(Command userCommand) {
        return new CommandResult(userCommand, CommandResult.EXECUTION_SUCCESS, CommandResult.BLANK_DESCRIPTION);
    }

    /**
     * Mark a task as done. Return success result if a valid task number
     * is provided. Otherwise, return fail result along with the reason
     * causing the command to fail.
     * 
     * @param userCommand input command from user containing the number of 
     *                    the task to be marked as done.
     * @return result of the command.
     */
    private CommandResult executeDoneCommand(Command userCommand) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(userCommand.getCommandDescription());
            if (tasks.get(taskNumber - 1).isDone()) {
                return new CommandResult(userCommand, CommandResult.EXECUTION_FAIL, CommandResult.INVALID_TASK_ALREADY_DONE);
            }
            tasks.get(taskNumber - 1).markAsDone();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return new CommandResult(userCommand, CommandResult.EXECUTION_FAIL, CommandResult.INVALID_NUMBER);
        }
        return new CommandResult(userCommand, CommandResult.EXECUTION_SUCCESS, CommandResult.BLANK_DESCRIPTION);
    }

    /**
     * Add a task with a specific type to the task list. Return success result
     * if the task can be added to the list. Otherwise, return fail result along
     * with the reason causing the command to fail.
     * 
     * @param userCommand input command from user containing a specific task type
     *                    and the task's description.
     * @return result of the command.
     */
    private CommandResult executeAddCommand(Command userCommand) {
        switch (userCommand.getCommandType()) {
        case Command.COMMAND_ADD_TODO:
            if (userCommand.getCommandDescription().equals(CommandResult.BLANK_DESCRIPTION)) {
                return new CommandResult(userCommand, CommandResult.EXECUTION_FAIL, CommandResult.INVALID_TODO);
            }
            try {
                tasks.add(new Todo(userCommand.getCommandDescription()));
            } catch (DukeInvalidAddTaskException e) {
                return new CommandResult(userCommand, CommandResult.EXECUTION_FAIL, CommandResult.INVALID_TODO);
            }
            break;
        case Command.COMMAND_ADD_DEADLINE:
            try {
                tasks.add(new Deadline(userCommand.getCommandDescription()));
            } catch (DukeInvalidAddTaskException | StringIndexOutOfBoundsException e) {
                return new CommandResult(userCommand, CommandResult.EXECUTION_FAIL, CommandResult.INVALID_DEADLINE);
            } 
            break;
        case Command.COMMAND_ADD_EVENT:
            try {
                tasks.add(new Event(userCommand.getCommandDescription()));
            } catch (DukeInvalidAddTaskException | StringIndexOutOfBoundsException e) {
                return new CommandResult(userCommand, CommandResult.EXECUTION_FAIL, CommandResult.INVALID_EVENT);
            }
            break;
        default:
            return new CommandResult(userCommand, CommandResult.EXECUTION_FAIL, CommandResult.INVALID_ADD_TASK);
        }
        return new CommandResult(userCommand, CommandResult.EXECUTION_SUCCESS, CommandResult.BLANK_DESCRIPTION);
    }

    /**
     * Delete a task from the task list. If the deletion is successful, return
     * the command result along with the deleted task's description. Otherwise,
     * return the result with the reason causing the command to fail.
     * 
     * @param userCommand input command from user containing the number of task
     *                    to be deleted.
     * @return result of the command.
     */
    private CommandResult executeDeleteCommand(Command userCommand) {
        int taskNumber;
        String deletedTask;
        try {
            taskNumber = Integer.parseInt(userCommand.getCommandDescription());
            deletedTask = tasks.get(taskNumber - 1).toString();
            tasks.remove(taskNumber - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return new CommandResult(userCommand, CommandResult.EXECUTION_FAIL, CommandResult.INVALID_NUMBER);
        }
        return new CommandResult(userCommand, CommandResult.EXECUTION_SUCCESS, deletedTask);
    }

    /**
     * Return success result for the tasks containing the keyword to be shown
     * to the user.
     *
     * @param userCommand input command from user containing the keyword
     *                    for the tasks.
     * @return result of the command.
     */
    private CommandResult executeFindCommand(Command userCommand) {
        String keyword = userCommand.getCommandDescription();
        return new CommandResult(userCommand, CommandResult.EXECUTION_SUCCESS, keyword);
    }

    /**
     * Return success result for the help message to be shown to user.
     *
     * @param userCommand input command from user.
     * @return result of the command.
     */
    private CommandResult executeHelpCommand(Command userCommand) {
        return new CommandResult(userCommand, CommandResult.EXECUTION_SUCCESS, CommandResult.BLANK_DESCRIPTION);
    }

    /**
     * Return success result for the program to be terminated.
     *
     * @param userCommand input command from the user.
     * @return result of the command.
     */
    private CommandResult executeExitCommand(Command userCommand) {
        return new CommandResult(userCommand, CommandResult.EXECUTION_SUCCESS, CommandResult.BLANK_DESCRIPTION);
    }

    /**
     * Return fail result of the command to the user.
     * 
     * @param userCommand input command from the user.
     * @return result of the command.
     */
    private CommandResult executeInvalidCommand(Command userCommand) {
        return new CommandResult(userCommand, CommandResult.EXECUTION_FAIL, CommandResult.INVALID_COMMAND);
    }

    /**
     * Call different command execution method depending on the input user's command,
     * then return the result of the command execution.
     * 
     * @param userCommand input command from user.
     * @return result of the command
     */
    public CommandResult executeCommand(Command userCommand) {
        switch (userCommand.getCommandType()) {
        case Command.COMMAND_LIST:
            return executeListCommand(userCommand);
        case Command.COMMAND_DONE:
            return executeDoneCommand(userCommand);
        case Command.COMMAND_ADD_TODO:
        case Command.COMMAND_ADD_DEADLINE:
        case Command.COMMAND_ADD_EVENT:
            return executeAddCommand(userCommand);
        case Command.COMMAND_DELETE:
            return executeDeleteCommand(userCommand);
        case Command.COMMAND_FIND:
            return executeFindCommand(userCommand);
        case Command.COMMAND_HELP:
            return executeHelpCommand(userCommand);
        case Command.COMMAND_EXIT:
            return executeExitCommand(userCommand);
        default:
            return executeInvalidCommand(userCommand);
        }
    }
}
