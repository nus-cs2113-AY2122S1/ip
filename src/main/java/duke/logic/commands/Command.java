package duke.logic.commands;


import duke.data.task.TaskList;

/**
 * Abstract class used to represent executable Commands
 * All Commands can be executed to return a CommandResult
 */
public abstract class Command {
    protected TaskList tasks;

    /**
     * Returns the appropriate CommandResult after execution of the command.
     * Each Command class will have its own implementation of this method that is specific to the actions it is expected
     * to execute.
     *
     * @return CommandResult Result of execution of command
     */
    public abstract CommandResult execute();

    /**
     * Provides the task list that the command will operate on
     *
     * @param tasks TaskList class containing current tasks
     */
    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns true if the command requires the storage file to be rewritten after execution,
     * i.e. after making modifications to the task list.
     *
     * @param command Command that has just been executed
     * @return True if storage file is to be rewritten after execution of the command
     */
    public static boolean requiresStorageRewrite(Command command) {
        return command instanceof AddDeadlineCommand
                || command instanceof AddEventCommand
                || command instanceof AddTodoCommand
                || command instanceof DeleteTaskCommand
                || command instanceof MarkTaskAsDoneCommand
                || command instanceof ByeCommand;
    }

}
