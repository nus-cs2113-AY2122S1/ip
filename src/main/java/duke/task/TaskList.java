package duke.task;

import duke.command.Command;
import duke.command.CommandResult;
import duke.exception.DukeInvalidAddTaskException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private CommandResult executeListCommand(Command userCommand) {
        return new CommandResult(userCommand, CommandResult.EXECUTION_SUCCESS, CommandResult.BLANK_DESCRIPTION);
    }
    
    private CommandResult executeDoneCommand(Command userCommand) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(userCommand.getCommandDescription());
            if (tasks.get(taskNumber - 1).isDone()) {
                return new CommandResult(userCommand, CommandResult.EXECUTION_SUCCESS, CommandResult.INVALID_TASK_ALREADY_DONE);
            }
            tasks.get(taskNumber - 1).markAsDone();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return new CommandResult(userCommand, CommandResult.EXECUTION_FAIL, CommandResult.INVALID_NUMBER);
        }
        return new CommandResult(userCommand, CommandResult.EXECUTION_SUCCESS, CommandResult.BLANK_DESCRIPTION);
    }
    
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
    
    private CommandResult executeFindCommand(Command userCommand) {
        String keyword = userCommand.getCommandDescription();
        return new CommandResult(userCommand, CommandResult.EXECUTION_SUCCESS, keyword);
    }
    
    private CommandResult executeExitCommand(Command userCommand) {
        return new CommandResult(userCommand, CommandResult.EXECUTION_SUCCESS, CommandResult.BLANK_DESCRIPTION);
    }
    
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
        case Command.COMMAND_EXIT:
            return executeExitCommand(userCommand);
        default:
            return new CommandResult(userCommand, CommandResult.EXECUTION_FAIL, CommandResult.INVALID_COMMAND);
        }
    }
}
