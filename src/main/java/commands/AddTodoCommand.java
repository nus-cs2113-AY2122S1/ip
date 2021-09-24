package commands;

import task.Todo;

public class AddTodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo to the task list. \n" +
            "Example: " + COMMAND_WORD+" {DESCRIPTION}";
    public static final String MESSAGE_SUCCESS = "New todo added: %1$s";
    public final Todo toAdd;

    public AddTodoCommand(String description, boolean isDone) {
        this.toAdd = new Todo(description, isDone);
    }

    @Override
    public CommandResult execute() {
        taskManager.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
