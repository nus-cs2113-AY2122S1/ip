package commands;

import task.Todo;

/**
 * Adds a todo to the task list.
 */
public class AddTodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = "todo: Adds a todo to the task list. \n"
            + "\tParameters: DESCRIPTION\n"
            + "\tExample: todo wipe the table\n";
    public static final String MESSAGE_SUCCESS = "New todo added: %1$s";
    private final Todo toAdd;

    /**
     * Constructor using raw values.
     * @param description description of todo
     * @param isDone whether todo is completed
     */
    public AddTodoCommand(String description, boolean isDone) {
        this.toAdd = new Todo(description, isDone);
    }

    @Override
    public CommandResult execute() {
        taskManager.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
