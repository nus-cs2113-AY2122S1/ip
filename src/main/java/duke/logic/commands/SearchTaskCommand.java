package duke.logic.commands;

import static duke.ui.Ui.EMPTY;
import static duke.ui.Ui.LS;
import static duke.ui.Ui.MESSAGE_NO_TASKS_YET;
import static duke.ui.Ui.QUOTATION;

public class SearchTaskCommand extends Command {
    public static final String COMMAND_WORD = "search";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD + " X" + QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : Search for task containing X";
    public static final String MESSAGE_NO_TASKS_FOUND = "No matching tasks found! :( Try using another search term?";
    public static final String MESSAGE_INVALID_FORMAT = "Please input a search term!";
    private static final String MESSAGE_INTRODUCE_TASKS = "Here are the matching tasks in your list:" + LS + "%s";

    private final String searchTerm;

    public SearchTaskCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public CommandResult execute() {
        if (super.tasks.isEmpty()) {
            return new CommandResult(MESSAGE_NO_TASKS_YET);
        }

        String listOfTasksInString = super.tasks.getStringOfTasksWithMatchingTerm(this.searchTerm);
        if (listOfTasksInString.equals(EMPTY)) {
            return new CommandResult(MESSAGE_NO_TASKS_FOUND);
        }
        return new CommandResult(String.format(MESSAGE_INTRODUCE_TASKS, listOfTasksInString));
    }
}
