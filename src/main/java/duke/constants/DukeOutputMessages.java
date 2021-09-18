package duke.constants;

public class DukeOutputMessages extends DukeConstants {
    // Messages to greet user upon entry and exit of program
    public static final String HEY_MESSAGE = WHITESPACE + "Hey there! I'm Duke." + System.lineSeparator() + " How may I help you?";
    public static final String BYE_MESSAGE = WHITESPACE + "Goodbye! Hope to see you again soon.";
    public static final String DUKE_LOGO = " ____        _        \n"
                                         + "|  _ \\ _   _| | _____ \n"
                                         + "| | | | | | | |/ / _ \\\n"
                                         + "| |_| | |_| |   <  __/\n"
                                         + "|____/ \\__,_|_|\\_\\___|\n";

    // Messages informing user of erroneous commands
    public static final String UNRECOGNIZED_COMMAND_MESSAGE = WHITESPACE + "I don't know what that means!";
    public static final String COMMAND_WRONG_FORMAT_MESSAGE = WHITESPACE + "Your command is of the wrong format!";
    public static final String COMMAND_LACKS_ARG_MESSAGE = WHITESPACE + "Your command lacks an argument!";
    public static final String TASK_NOT_IN_LIST_MESSAGE = WHITESPACE + "Sorry, the task is not in the list! Try again.";

    // Messages informing user that a certain command has been executed
    public static final String HERE_IS_TASK_LIST_MESSAGE = WHITESPACE + "Here are the tasks in your list:";
    public static final String TASK_LIST_EMPTY_MESSAGE = WHITESPACE + "The list is empty! Add a task first.";
    public static final String TASK_ADDED_MESSAGE = WHITESPACE + "I have added a task:";
    public static final String TASK_DELETED_MESSAGE = WHITESPACE + "Got it! I have deleted this task:";
    public static final String TASK_MARKED_DONE_MESSAGE = WHITESPACE + "Great job! The following task is done:";

    // Messages for {@code help} command
    public static final String ENTER_HELP = WHITESPACE + "Enter \"help\" for a list of valid commands and their respective formats!";
    public static final String HELP_INTRO_MESSAGE      = WHITESPACE + "Here are the commands I recognize along with their formats:";
    public static final String LIST_COMMAND_FORMAT     = WHITESPACE + "list     | Format: list";
    public static final String DONE_COMMAND_FORMAT     = WHITESPACE + "done     | Format: done [task ID (int)]";
    public static final String TODO_COMMAND_FORMAT     = WHITESPACE + "todo     | Format: todo [description]";
    public static final String DEADLINE_COMMAND_FORMAT = WHITESPACE + "deadline | Format: deadline [description] /by [deadline]";
    public static final String EVENT_COMMAND_FORMAT    = WHITESPACE + "event    | Format: event [description] /at [time]";
    public static final String DELETE_COMMAND_FORMAT   = WHITESPACE + "delete   | Format: delete [task ID (int)]";
}
