package duke.logic.commands;

import duke.ui.Ui;

import static duke.ui.Ui.EMPTY;
import static duke.ui.Ui.MESSAGE_NO_TASKS_YET;

/**
 *  Represents the command that when executed, lists all the tasks in the current TaskList
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + Ui.QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : See lists of tasks";
    private static final String MESSAGE_INTRODUCE_TASKS = "These are your current tasks:" + Ui.LS + "%s";

    @Override
    public CommandResult execute() {
        String listOfTasks = super.tasks.getStringOfAllTasks();
        if (listOfTasks.equals(EMPTY)) {
            return new CommandResult(MESSAGE_NO_TASKS_YET);
        }
        return new CommandResult(String.format(MESSAGE_INTRODUCE_TASKS, listOfTasks));
    }
}
