package duke.logic.commands;

import static duke.ui.Ui.EMPTY;
import static duke.ui.Ui.LS;
import static duke.ui.Ui.MESSAGE_NO_TASKS_YET;
import static duke.ui.Ui.QUOTATION;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD + QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : See lists of tasks";
    private static final String MESSAGE_INTRODUCE_TASKS = "These are your current tasks:" + LS + "%s";

    @Override
    public CommandResult execute() {
        String listOfTasks = super.getStringOfAllTasks();
        if (listOfTasks.equals(EMPTY)) {
            return new CommandResult(MESSAGE_NO_TASKS_YET);
        }
        return new CommandResult(String.format(MESSAGE_INTRODUCE_TASKS, listOfTasks));
    }
}
