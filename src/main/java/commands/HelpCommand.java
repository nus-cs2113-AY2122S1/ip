package commands;

import ui.TextUi;

public class HelpCommand extends Command{

    public static final String COMMAND_WORD = "help";

    //@@author nus-cs2113-AY2122S1-reused
    //Reused from https://github.com/nus-cs2113-AY2122S1/contacts/edit/master/src/main/java/Contacts1.java
    // with minor modifications
    private static final String MESSAGE_COMMAND_HELP = "%1$s: %2$s";
    private static final String MESSAGE_COMMAND_HELP_PARAMETERS = "\tParameters: %1$s";
    private static final String MESSAGE_COMMAND_HELP_EXAMPLE = "\tExample: %1$s";

    public static final String COMMAND_EXIT_WORD = ExitCommand.COMMAND_WORD;
    public static final String COMMAND_EXIT_DESC = ExitCommand.COMMAND_DESC;
    public static final String COMMAND_EXIT_EXAMPLE = COMMAND_EXIT_WORD;

    public static final String COMMAND_LIST_WORD = ListCommand.COMMAND_WORD;
    public static final String COMMAND_LIST_DESC = ListCommand.COMMAND_DESC;
    public static final String COMMAND_LIST_EXAMPLE = COMMAND_LIST_WORD;

    public static final String COMMAND_DONE_WORD = MarkTaskAsDoneCommand.COMMAND_WORD;
    public static final String COMMAND_DONE_DESC = MarkTaskAsDoneCommand.COMMAND_DESC;
    public static final String COMMAND_DONE_PARAMETER = "TASK_INDEX";
    public static final String COMMAND_DONE_EXAMPLE = COMMAND_DONE_WORD + " 1";

    public static final String COMMAND_TODO_WORD = AddToDoCommand.COMMAND_WORD;
    public static final String COMMAND_TODO_DESC = AddToDoCommand.COMMAND_DESC;
    public static final String COMMAND_TODO_PARAMETER = "TASK_DESCRIPTION";
    public static final String COMMAND_TODO_EXAMPLE = COMMAND_TODO_WORD + " do laundry";

    private static final String TASK_DEADLINE_PREFIX = "/by ";
    private static final String TASK_TIME_RANGE_PREFIX = "/at ";

    public static final String COMMAND_DEADLINE_WORD = AddDeadlineCommand.COMMAND_WORD;
    public static final String COMMAND_DEADLINE_DESC = AddDeadlineCommand.COMMAND_DESC;
    public static final String COMMAND_DEADLINE_PARAMETER = "TASK_DESCRIPTION "
            + TASK_DEADLINE_PREFIX
            + "DEADLINE";
    public static final String COMMAND_DEADLINE_EXAMPLE = COMMAND_DEADLINE_WORD + " do laundry /by 3pm";


    public static final String COMMAND_EVENT_WORD = AddEventCommand.COMMAND_WORD;
    public static final String COMMAND_EVENT_DESC = AddEventCommand.COMMAND_DESC;
    public static final String COMMAND_EVENT_PARAMETER = "TASK_DESCRIPTION "
            + TASK_TIME_RANGE_PREFIX + "TIME_RANGE";
    public static final String COMMAND_EVENT_EXAMPLE = COMMAND_EVENT_WORD + " do laundry /at 2-3pm";

    public static final String COMMAND_FIND_WORD = FindCommand.COMMAND_WORD;
    public static final String COMMAND_FIND_DESC = FindCommand.COMMAND_DESC;
    public static final String COMMAND_FIND_PARAMETER = "KEYWORD";
    public static final String COMMAND_FIND_EXAMPLE = COMMAND_FIND_WORD + " book";


    public static final String COMMAND_DELETE_WORD = DeleteCommand.COMMAND_WORD;
    public static final String COMMAND_DELETE_DESC = DeleteCommand.COMMAND_DESC;
    public static final String COMMAND_DELETE_PARAMETER = "TASK_INDEX";
    public static final String COMMAND_DELETE_EXAMPLE = COMMAND_DELETE_WORD + " 1";

    /*
     * ===============================================
     *         COMMAND HELP INFO FOR USERS
     * ===============================================
     */

    /** Prints usage info for all commands **/
    private static String getUsageInfoForAllCommands() {
        return  getUsageInfoForTodoCommand() + TextUi.LS
                + getUsageInfoForDeadlineCommand() + TextUi.LS
                + getUsageInfoForEventCommand() + TextUi.LS
                + getUsageInfoForDoneCommand() + TextUi.LS
                + getUsageInfoForDeleteCommand() + TextUi.LS
                + getUsageInfoForFindCommand() + TextUi.LS
                + getUsageInfoForListCommand() + TextUi.LS
                + getUsageInfoForExitCommand() + TextUi.LS;
    }

    /** Returns the string for 'todo' command usage instruction **/
    private static String getUsageInfoForTodoCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_TODO_WORD, COMMAND_TODO_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_TODO_PARAMETER) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_TODO_EXAMPLE) + System.lineSeparator();
    }

    /** Returns the string for 'deadline' command usage instruction **/
    private static String getUsageInfoForDeadlineCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_DEADLINE_WORD, COMMAND_DEADLINE_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_DEADLINE_PARAMETER) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_DEADLINE_EXAMPLE) + System.lineSeparator();
    }

    /** Returns the string for 'event' command usage instruction **/
    private static String getUsageInfoForEventCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_EVENT_WORD, COMMAND_EVENT_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_EVENT_PARAMETER) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_EVENT_EXAMPLE) + System.lineSeparator();
    }

    /** Returns the string for 'done' command usage instruction **/
    private static String getUsageInfoForDoneCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_DONE_WORD, COMMAND_DONE_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_DONE_PARAMETER) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_DONE_EXAMPLE) + System.lineSeparator();
    }

    /** Returns the string for 'delete' command usage instruction **/
    private static String getUsageInfoForDeleteCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_DELETE_WORD, COMMAND_DELETE_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_DELETE_PARAMETER) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_DELETE_EXAMPLE) + System.lineSeparator();
    }

    private static String getUsageInfoForFindCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_FIND_WORD, COMMAND_FIND_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_FIND_PARAMETER) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_FIND_EXAMPLE) + System.lineSeparator();
    }

    /** Returns the string for 'list' command usage instruction **/
    private static String getUsageInfoForListCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_LIST_WORD, COMMAND_LIST_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_LIST_EXAMPLE) + System.lineSeparator();
    }

    /** Returns the string for 'exit' command usage instruction **/
    private static String getUsageInfoForExitCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_EXIT_WORD, COMMAND_EXIT_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_EXIT_EXAMPLE) + System.lineSeparator();
    }

    @Override
    public void execute() {
        TextUi.showHelpMessage(getUsageInfoForAllCommands());
    }
}
