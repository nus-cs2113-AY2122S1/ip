package duke.logic.commands;

import duke.ui.Ui;

/**
 *  Represents the command that when executed, lists all the tasks in the current TaskList
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + Ui.QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : See lists of tasks";

    @Override
    public CommandResult execute() {
        if (super.tasks.isEmpty()) {
            return new CommandResult(Ui.MESSAGE_NO_TASKS_YET);
        }

        String listOfTasksInString = super.tasks.getStringOfAllTasks();
        return new CommandResult(String.format(Ui.MESSAGE_INTRODUCE_TASKS, listOfTasksInString));
    }
}
