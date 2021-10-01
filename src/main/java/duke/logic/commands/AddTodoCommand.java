package duke.logic.commands;

import duke.data.task.Todo;
import duke.ui.Ui;


/**
 * Represents the command that when executed, adds a new Todo to the TaskList
 */
public class AddTodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_COMMAND_FORMAT =  Ui.QUOTATION + COMMAND_WORD + " X" + Ui.QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : Add task X";
    public static final String MESSAGE_INVALID_FORMAT = "Please specify a name for the task!";

    private Todo toAdd;

    public AddTodoCommand(String description) {
        this.toAdd = new Todo(description);
    }

    @Override
    public CommandResult execute() {
        super.tasks.addTask(toAdd);
        return new CommandResult(String.format(Ui.MESSAGE_TASK_ADDED, toAdd, super.tasks.getNumTasks()));
    }
}
