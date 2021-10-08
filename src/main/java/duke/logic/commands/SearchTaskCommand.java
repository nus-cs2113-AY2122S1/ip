package duke.logic.commands;


import duke.ui.Ui;

/**
 *  Represents the command that when executed, returns a list of tasks with names that match the search term
 */
public class SearchTaskCommand extends Command {
    public static final String COMMAND_WORD = "search";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + " X" + Ui.QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : Search for task containing search term X";
    public static final String MESSAGE_NO_TASKS_FOUND = "No matching tasks found! :( Try using another search term?";
    public static final String MESSAGE_INVALID_FORMAT = "Please input a search term!";
    private static final String MESSAGE_INTRODUCE_TASKS = "Here are the matching tasks in your list:" + Ui.LS + "%s";

    private final String searchTerm;

    public SearchTaskCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public CommandResult execute() {
        if (super.tasks.isEmpty()) {
            return new CommandResult(Ui.MESSAGE_NO_TASKS_YET);
        }

        String listOfTasksInString = super.tasks.getStringOfTasksWithMatchingTerm(this.searchTerm);
        if (listOfTasksInString.equals(Ui.EMPTY)) {
            return new CommandResult(MESSAGE_NO_TASKS_FOUND);
        }
        return new CommandResult(String.format(MESSAGE_INTRODUCE_TASKS, listOfTasksInString));
    }
}
