package duke.logic.commands;


import duke.data.task.TaskList;

/**
 * Abstract class used to represent executable Commands
 * All Commands can be executed to return a CommandResult
 */
public abstract class Command {
    protected TaskList tasks;
    public abstract CommandResult execute();

    /**
     * Provides the task list that the command will operate on
     * @param tasks TaskList class containing current tasks
     */
    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    public static boolean requiresStorageRewrite(Command command) {
        return command instanceof AddDeadlineCommand
                || command instanceof AddEventCommand
                || command instanceof AddTodoCommand
                || command instanceof DeleteTaskCommand
                || command instanceof MarkTaskAsDoneCommand
                || command instanceof ByeCommand;
    }

}
