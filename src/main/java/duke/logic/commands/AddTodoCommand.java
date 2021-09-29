package duke.logic.commands;

import duke.data.task.Todo;
import duke.ui.Ui;


/**
 * Represents the command that when executed, adds a new Todo to the TaskList
 */
public class AddTodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_COMMAND_FORMAT =  Ui.QUOTATION + COMMAND_WORD + " X" + Ui.QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : Add task X with deadline Y";
    private static final String MESSAGE_SUCCESS = "Added to list: %1$s " + Ui.LS + "Current number of tasks: %2$d";

    private Todo toAdd;

    public AddTodoCommand(String description) {
        this.toAdd = new Todo(description);
    }

    @Override
    public CommandResult execute() {
        super.tasks.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd, super.tasks.getNumTasks()));
    }
}
